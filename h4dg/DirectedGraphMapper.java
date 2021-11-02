import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author fernanda
 */
public class DirectedGraphMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String strProp = conf.get("n.nodes");
        long N = Long.parseLong(strProp);
        long E = Long.valueOf(value.toString());

        RMat rmat = new RMat();
        for (long i = 0; i < E; i++) {
            long[] edge = rmat.GenerateEdge(N);
            LongWritable nkey = new LongWritable(edge[0] - 1);
            context.write(nkey, new LongWritable(1));
        }
    }
}
