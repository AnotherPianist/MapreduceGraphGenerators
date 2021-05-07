
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fernandalopezgallegos
 */
public class UndirectedGraphMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context
    ) throws IOException, InterruptedException {
        
        Configuration conf = context.getConfiguration();
        String strProp = conf.get("n.nodes");
        long N = Long.parseLong(strProp);

        long E = Long.valueOf(value.toString());
        
        RMat rmat = new RMat();
        for (int i = 0; i < E; i++) {
            long[] edge = rmat.GenerateEdge(N);
            LongWritable nkey = new LongWritable(edge[0] - 1);
            context.write(nkey, new LongWritable(1));
        }
    }

}
