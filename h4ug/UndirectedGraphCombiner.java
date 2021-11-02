import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author fernandalopezgallegos
 */
public class UndirectedGraphCombiner extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
    @Override
    public void reduce(LongWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long degree = 0;
        for (LongWritable val : values) {
            degree += 1;
        }
        context.write(key, new LongWritable(degree));
    }
}
