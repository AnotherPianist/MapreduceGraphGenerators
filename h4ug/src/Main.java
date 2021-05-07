
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fernandalopezgallegos
 */
public class Main extends Configured implements Tool {

    //static private final String TMP_DIR_PREFIX = Main.class.getSimpleName();
    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(null, new Main(), args));

    }

    @Override
    public int run(String[] args) throws Exception {

        long n = 1000;
        int m = 1; //numero de maps
        int l = 0;
        int i = 0;
        while (i < args.length) {
            try {
                if (args[i].compareTo("-n") == 0) {
                    n = Long.parseLong(args[i + 1]);
                    if (n < 1) {
                        throw new IllegalArgumentException("Invalid value for -n");
                    }
                } else if (args[i].compareTo("-m") == 0) {
                    m = Integer.parseInt(args[i + 1]);
                    if (m < 1) {
                        throw new IllegalArgumentException("Invalid value for -t");
                    }
                } else if (args[i].compareTo("-l") == 0) {
                    l = 1;
                    if (l < 0 || l > 1) {
                        throw new IllegalArgumentException("Invalid value for -l");
                    }
                } else {
                    System.out.println("Error: Invalid parameters");
                    return 0;
                }
                i = i + 2;
            } catch (Exception ex) {
                System.out.println("Error: Invalid parameters");
                return 0;

            }
        }
        System.out.println("=== Parameters ===");
        System.out.println("-n = " + n);
        System.out.println("-m = " + m);
        System.out.println("-l = " + l);
        
        System.out.println("=== Begin ==="); 
        

        final long N = n;
        final long E = (long) ((2.0 / 3.0) * N * Math.log(N) + (0.38481 * N));
        final int nMaps = m;
        
        final Path tmpDir;

        if(l == 0){
            tmpDir = new Path(getConf().get("fs.defaultFS") + "/hrmat");        
        }
        else{
            tmpDir = new Path("d1_"+N); //local
            
        }

        System.out.println("Number of nodes  = " + N);
        System.out.println("Number of edges = " + E);
        System.out.println("Number of maps = " + nMaps);
        System.out.println("TMP_DIR: " + tmpDir);

        generateGraph(N, E, nMaps, tmpDir, getConf());

        return 0;
    }

    private void generateGraph(long N, long E, int nMaps, Path tmpDir, Configuration conf) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        /*
        Job 1
         */
        conf.set("n.nodes", Long.toString(N));
        conf.set("n.edges", Long.toBinaryString(E));
        conf.set("mapreduce.map.memory.mb", "3072");
        conf.set("mapreduce.reduce.memory.mb", "3072");
        conf.set("mapreduce.map.Java.opts", "-Xmx2458m");
        conf.set("mapreduce.reduce.Java.opts", "-Xmx2458m");


        Job job = Job.getInstance(conf);

        job.setJobName(Main.class.getSimpleName());
        job.setJarByClass(Main.class);

        job.setMapperClass(UndirectedGraphMapper.class);
        
        //job.setCombinerClass(UndirectedGraphCombiner.class);

        job.setReducerClass(UndirectedGraphReducer.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        //setup input/output directories
        final Path inDir = new Path(tmpDir, "in");
        final Path outDir = new Path(tmpDir, "out");

        System.out.println("in Dir: " + inDir);
        System.out.println("out Dir: " + outDir);
        FileInputFormat.setInputPaths(job, inDir);
        FileOutputFormat.setOutputPath(job, outDir);

        URI uri = new URI(conf.get("fs.defaultFS"));  
        final FileSystem fs = FileSystem.get(uri, conf);

        if (fs.exists(tmpDir)) {
            throw new IOException("Tmp directory " + fs.makeQualified(tmpDir)
                    + " already exists.  Please remove it first.");
        }
        if (!fs.mkdirs(inDir)) {
            throw new IOException("Cannot create input directory " + inDir);
        }

        try {
            //generate an input file for each map task
            for (int i = 0; i < nMaps; ++i) {

                long nEdges;
                if (i == 0) {
                    nEdges = E / nMaps + E % nMaps;
                } else {
                    nEdges = E / nMaps;
                }
                final LongWritable size = new LongWritable(nEdges);

                try {
                    String data = size + "\n";

                    final Path file = new Path(inDir, "part" + i);
                    System.out.println("File: " + file);

                    if (!fs.exists(file)) {
                        fs.delete(file, true);
                    }
                    
                    OutputStream os = fs.create(file, new Progressable() {
                        @Override
                        public void progress() {
//                            out.println(".");
                        }
                    });
                    ;
                    BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    br.write(data);
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ///
                System.out.println("Wrote input for Map #" + i);
            }
            

            //start a map/reduce job
            System.out.println("Starting Job ...");
            final long startTime = System.currentTimeMillis();
            job.waitForCompletion(true);
            final double duration = (System.currentTimeMillis() - startTime) / 1000.0;
            System.out.println("Job Finished in " + duration + " seconds");

        } finally {
            //fs.delete(tmpDir, true);
            fs.close();
        }

    }
}