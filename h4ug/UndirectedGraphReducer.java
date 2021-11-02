import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author fernandalopezgallegos
 */
public class UndirectedGraphReducer extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
    @Override
    public void reduce(LongWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String strProp = conf.get("n.nodes");
        long N = Long.parseLong(strProp);

        Long source = Long.valueOf(key.toString());
        long degree = 0;

        for (LongWritable val : values) {
            degree += val.get();
        }

        writeEdgeAlwaysGoingForward(degree, source, N, context);
    }

    private void writeEdgeAlwaysGoingForward(long degree, Long source, long N, Context context) throws IOException, InterruptedException {
        long i = 0;

        long target = source + 1;
        while (i < degree && target < N) {
            LongWritable nkey = new LongWritable(source);
            LongWritable nvalue = new LongWritable(target);
            context.write(nkey, nvalue);
            i++;
            target++;
        }

        target = 0;
        while (i < degree) {
            LongWritable nkey = new LongWritable(source);
            LongWritable nvalue = new LongWritable(target);
            context.write(nkey, nvalue);
            i++;
            target++;
        }
    }
}
