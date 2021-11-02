import java.util.Random;

/**
 * @author renzo
 */
public class RMat {

    double alpha = 0.75; //0.6
    double beta = 0.05; //0.15
    double gamma = 0.18; //0.15
    double delta = 0.02; //0.10
    long seed;
    Random rand;
    private double offset1 = 0;
    private double offset2 = 0;
    private double offset3 = 0;
    private double offset4 = 0;

    public RMat() {
        rand = new Random();
    }

    public RMat(long _seed) {
        this.seed = _seed;
        rand = new Random(seed);
    }

    public long[] GenerateEdge(long N) {
        double a = alpha;
        double b = beta;
        double c = gamma;
        double d = delta;
        double m = 0.25;
        double depth = Math.ceil(Math.log(N) / Math.log(2));
        offset1 = (m - a) / depth;
        offset2 = (m - b) / depth;
        offset3 = (m - c) / depth;
        offset4 = (m - d) / depth;
        return this.chooseEdge(1, 1, N, N, a, b, c, d);
    }

    //method that simulates the RMat partition method
    public long[] chooseEdge(long x1, long y1, long xn, long yn, double a, double b, double c, double d) {
        double r = rand.nextDouble();
        long pair[] = new long[2];

        if ((xn - x1) == 0 && (yn - y1) == 0) {
            pair[0] = x1;
            pair[1] = y1;
            return pair;
        }
        if ((xn - x1) == 0 && (yn - y1) == 1) {
            if (r < 0.5) {
                pair[0] = x1;
                pair[1] = y1;
            } else {
                pair[0] = x1;
                pair[1] = yn;
            }
            return pair;
        }
        if ((xn - x1) == 1 && (yn - y1) == 0) {
            if (r < 0.5) {
                pair[0] = x1;
                pair[1] = y1;
            } else {
                pair[0] = xn;
                pair[1] = yn;
            }
            return pair;
        }

        double ab = a + b;
        double abc = a + b + c;
        double new_a = Math.abs(a + offset1);
        double new_b = Math.abs(b + offset2);
        double new_c = Math.abs(c + offset3);
        double new_d = Math.abs(d + offset4);
        long halfx;
        long halfy;
        if (r < a) {
            halfx = (long) Math.floor((x1 + xn) / 2);
            halfy = (long) Math.floor((y1 + yn) / 2);
            return chooseEdge(x1, y1, halfx, halfy, new_a, new_b, new_c, new_d);
        } else if (r >= a && r < ab) {
            halfx = (long) Math.ceil((x1 + xn) / 2);
            halfy = (long) Math.floor((y1 + yn) / 2);
            return chooseEdge(halfx, y1, xn, halfy, new_a, new_b, new_c, new_d);
        } else if (r >= ab && r < abc) {
            halfx = (long) Math.floor((x1 + xn) / 2);
            halfy = (long) Math.ceil((y1 + yn) / 2);
            return chooseEdge(x1, halfy, halfx, yn, new_a, new_b, new_c, new_d);
        } else {
            halfx = (long) Math.ceil((x1 + xn) / 2);
            halfy = (long) Math.ceil((y1 + yn) / 2);
            return chooseEdge(halfx, halfy, xn, yn, new_a, new_b, new_c, new_d);
        }
    }
}
