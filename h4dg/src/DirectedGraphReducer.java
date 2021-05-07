import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernanda
 */
public class DirectedGraphReducer extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {

    @Override
    public void reduce(LongWritable key, Iterable<LongWritable> values,
            Context context
    ) throws IOException, InterruptedException {
       
        long source = Long.valueOf(key.toString());
        
        long degree = 0;
        for (LongWritable val : values) {
            degree += val.get();
        }
      
        long i = 0;
        i = writeEdgeGoingBack(i, degree, source, context);
        i = writeEdgeGoingForward(i, degree, source, context);
        
    }

    private long writeEdgeGoingBack(long i, long degree, long source, Context context) throws IOException, InterruptedException {
        //Se escriben las aristas desde source - 1 a 0
        long target = source - 1;
        while (i < degree && target >= 0) {
            LongWritable nkey = new LongWritable(source);
            LongWritable nvalue = new LongWritable(target);
            context.write(nkey, nvalue);
            i++;
            target--;
        }
        return i;    
    }

    private long writeEdgeGoingForward(long i, long degree, long source, Context context) throws IOException, InterruptedException {
        //Se escriben las aristas desde source + 1 hasta N-1
        long target = source + 1;
        while (i < degree) {
            LongWritable nkey = new LongWritable(source);
            LongWritable nvalue = new LongWritable(target);
            context.write(nkey, nvalue);
            i++;
            target++;
        }  
        return i;
    }
}
