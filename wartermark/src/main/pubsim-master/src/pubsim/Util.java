package pubsim;

import bignums.BigInteger;
import bignums.BigRational;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for common math operations etc.
 *
 * @author Robby McKilliam
 */
public final class Util {

    /**
     * Round a double to 6 decimal places. Usefull for printing
     */
    public static Double dround6(double x) {
        return Math.round(1e6 * x) / 1e6;
    }

    /**
     * Takes x mod y and works for negative numbers. Ie is not just a remainder
     * like java's % operator.
     */
    public static int mod(int x, int y) {
        int t = x % y;
        if (t < 0) {
            t += y;
        }
        return t;
    }

    /**
     * Takes x mod y and works for negative numbers. Ie is not just a remainder
     * like java's % operator.
     */
    public static long mod(long x, long y) {
        long t = x % y;
        if (t < 0) {
            t += y;
        }
        return t;
    }

    /**
     * Return greatest common divisor of p and q
     */
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }

    /**
     * Return array [d, a, b] such that d = gcd(p, q), ap + bq = d
     */
    public static int[] extended_gcd(int p, int q) {
        if (q == 0) {
            return new int[]{p, 1, 0};
        }
        int[] vals = extended_gcd(q, p % q);
        int d = vals[0];
        int a = vals[2];
        int b = vals[1] - (p / q) * vals[2];
        return new int[]{d, a, b};
    }
    
    /**
     * Return array [d, a, b] such that d = gcd(p, q), ap + bq = d
     */
    public static long[] extended_gcd(long p, long q) {
        if (q == 0) {
            return new long[]{p, 1, 0};
        }
        long[] vals = extended_gcd(q, p % q);
        long d = vals[0];
        long a = vals[2];
        long b = vals[1] - (p / q) * vals[2];
        return new long[]{d, a, b};
    }
    
    
    /**
     * Return array [d, a, b] such that d = gcd(p, q), ap + bq = d
     */
    public static BigInteger[] extended_gcd(BigInteger p, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO)==0) {
            return new BigInteger[]{p, BigInteger.ONE, BigInteger.ZERO};
        }
        BigInteger[] vals = extended_gcd(q, p.mod(q));
        BigInteger d = vals[0];
        BigInteger a = vals[2];
        BigInteger b = vals[1].subtract( p.divide(q).multiply(vals[2]) );
        return new BigInteger[]{d, a, b};
    }

    /**
     * Returns the centered fractional part of x. i.e. x - round(x);
     */
    public static double centeredFracPart(double x) {
        return x - Math.round(x);
    }
    /**
     * The desired accuracy of the erf function
     */
    public static double ERF_TOLERANCE = 1e-10;

    /**
     * The error function. Calculates erf with accuracy tolerance specified by
     * ERF_TOLERANCE. Or when 100 elements of the Talyor series have been
     * summed.
     */
    public static double erf(double x) {

        if (x < 0) {
            return -erf(-x);
        }

        //if x is sufficiently large use asymptotic approximation
        if (x > 3.5) {
            return 1.0 - 1.0 / (x * Math.sqrt(Math.PI)) * Math.exp(-x * x);
        }

        double prod = 1.0;
        double sum = 0.0;
        double tooAdd = Double.POSITIVE_INFINITY;

        int n = 0;
        while (Math.abs(tooAdd) > ERF_TOLERANCE && n < 100) {
            tooAdd = x / (2 * n + 1) * prod;
            sum += tooAdd;
            prod *= -x * x / (n + 1);
            n++;
        }

        return 2.0 / Math.sqrt(Math.PI) * sum;

    }

    /**
     * The error function. Calculates erf with accuracy at least as small as the
     * parameter tol. Uses BigRational's so there is no numerical problems
     * unless you get to double precision!
     */
    public static double erf(BigRational x, BigRational tol) {

        BigRational prod = new BigRational(1, 1);
        BigRational sum = new BigRational(0, 1);
        BigRational tooAdd = new BigRational(1, 1);

        int n = 0;
        while (tooAdd.abs().compareTo(tol) > 0) {
            tooAdd = x.divide(new BigRational(2 * n + 1, 1)).multiply(prod);
            sum = sum.add(tooAdd);
            prod = prod.multiply(x.pow(2).negate()).divide(new BigRational(n + 1, 1));
            n++;
        }

        return 2.0 / Math.sqrt(Math.PI) * sum.doubleValue();

    }

    /**
     * 1 - erf(x)
     */
    public static double erfc(double x) {
        return 1.0 - erf(x);
    }

    /**
     * Q function.
     */
    public static double Q(double x) {
        return 0.5 * (1.0 - erf(x / Math.sqrt(2.0)));
    }

    /**
     * Factorial
     */
    public static long factorial(int i) {
        long ret = 1;
        for (int j = 1; j <= i; j++) {
            ret *= j;
        }
        return ret;
    }

    /**
     * This is just a wrapper for Math.IEEEremainder
     */
    public static double modPart(double x, double m) {
        return Math.IEEEremainder(x, m);
    }

    /**
     * Compute the intersection points of two circles. Returns an array of
     * Point2 objects. Return null if the circles don't intersect.
     */
    public static Point2[] circleIntersections(Point2 c1, double r1, Point2 c2, double r2) {
        double D = c1.minus(c2).normF();

        //circles don't intersect
        if (r1 + r2 < D) {
            return null;
        }
        //one circle is inside the other
        if (D < Math.abs(r1 - r2)) {
            return null;
        }

        Point2[] ret = new Point2[2];

        double x1 = c1.getX(), y1 = c1.getY();
        double x2 = c2.getX(), y2 = c2.getY();

        double d = c1.minus(c2).normF();
        double a = (r1 * r1 - r2 * r2 + d * d) / (2 * d);
        double h = Math.sqrt(r1 * r1 - a * a);

        double x3 = x1 + a * (x2 - x1) / d;
        double y3 = y1 + a * (y2 - y1) / d;

        ret[0] = new Point2(x3 + h * (y2 - y1) / d, y3 - h * (x2 - x1) / d);
        ret[1] = new Point2(x3 - h * (y2 - y1) / d, y3 + h * (x2 - x1) / d);

        return ret;
    }

    /**
     * Return the angles with respect to c1 where the intersections occur
     * between circles c1 and c2. Return null if the circles don't intersect.
     */
    public static double[] circleIntersectionAngles(Point2 c1, double r1, Point2 c2, double r2) {
        double D = c1.minus(c2).normF();

        //circles don't intersect
        if (r1 + r2 < D) {
            return null;
        }
        //one circle is inside the other
        if (D < Math.abs(r1 - r2)) {
            return null;
        }

        double[] ret = new double[2];

        double d = c1.minus(c2).normF();
        double a = (r1 * r1 - r2 * r2 + d * d) / (2 * d);
        double h = Math.sqrt(r1 * r1 - a * a);

        double x1 = c1.getX(), y1 = c1.getY();
        double x2 = c2.getX(), y2 = c2.getY();
        double x3 = a * (x2 - x1) / d;
        double y3 = a * (y2 - y1) / d;

        double xi = x3 + h * (y2 - y1) / d;
        double yi = y3 - h * (x2 - x1) / d;
        ret[0] = Math.atan2(yi, xi);

        xi = x3 - h * (y2 - y1) / d;
        yi = y3 + h * (x2 - x1) / d;
        ret[1] = Math.atan2(yi, xi);

        return ret;
    }

    /**
     * Return atan2 in the range [0, 2pi] rather than [-pi,pi]
     *
     * @param y
     * @param x
     */
    public static double atan2_0_to_2PI(double y, double x) {
        double a = Math.atan2(y, x);
        return convertAtan2Angle(a);
    }

    /**
     * Convert an angle in [-pi,pi] to [0,2pi] interval
     *
     * @param a
     */
    public static double convertAtan2Angle(double a) {
        if (a < 0) {
            a = 2 * Math.PI + a;
        }
        return a;
    }

    /**
     * Compute the nearest half integer i.e k + 0.5 to x
     */
    public static double roundToHalfInt(double x) {
        return Math.floor(x) + 0.5;
    }

    /**
     * Round x down to a half integer i.e k + 0.5
     */
    public static double floorToHalfInt(double x) {
        return Math.floor(x + 0.5) - 0.5;
    }

    /**
     * Round x up to a half integer i.e k + 0.5
     */
    public static double ceilToHalfInt(double x) {
        return Math.ceil(x + 0.5) - 0.5;
    }

    /**
     * Solves the quadratic equation ax^2 + bx + c = 0 Returns null if solution
     * is complex. Returns the solution in vector of length two. Least result is
     * the first element.
     */
    public static double[] solveQuadratic(double a, double b, double c) {
        double[] result = new double[2];
        double b24ac = b * b - 4 * a * c;
        //solution is complex and the line does not pass through the sphere
        if (b24ac < 0.0) {
            return null;
        }

        double sol1 = (-b - Math.sqrt(b24ac)) / (2 * a);
        double sol2 = (-b + Math.sqrt(b24ac)) / (2 * a);

        result[0] = Math.min(sol1, sol2);
        result[1] = Math.max(sol1, sol2);

        return result;
    }

    public static double log2(double a) {
        return Math.log(a) / Math.log(2);
    }

    /**
     * Returns the natural logarithm of the gamma function Gamma(x) = integral(
     * t^(x-1) e^(-t), t = 0 .. infinity) Uses Lanczos approximation formula.
     * See Numerical Recipes 6.1.
     */
    public static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    /**
     * Returns the gamma function
     */
    public static double gamma(double x) {
        return Math.exp(logGamma(x));
    }

    /**
     * Volume of hypersphere of radiis 1
     */
    public static double hyperSphereVolume(int n) {
        return Math.exp(logHyperSphereVolume(n));
    }

    /**
     * Natural logarithm of the volume of hypersphere of radiis 1
     */
    public static double logHyperSphereVolume(int n) {
        if (n % 2 == 0) {
            double lnumer = n / 2.0 * Math.log(Math.PI);
            double ldenom = 0.0;
            for (int i = 1; i <= n / 2; i++) {
                ldenom += Math.log(i);
            }
            return lnumer - ldenom;
        } else {
            double lnumer = n * Math.log(2) + (n - 1) / 2 * Math.log(Math.PI);
            for (int i = 1; i <= (n - 1) / 2; i++) {
                lnumer += Math.log(i);
            }
            double ldenom = 0.0;
            for (int i = 1; i <= n; i++) {
                ldenom += Math.log(i);
            }
            return lnumer - ldenom;
        }
    }

    /**
     * Natural logarithm of the surface area of the hypersphere of dimension n
     * and radius 1
     */
    public static double logHyperSphereSurfaceArea(int n) {
        return Math.log(2) + n * Math.log(Math.PI) / 2.0 - logGamma(n / 2.0);
    }

    /**
     * Log base 2 of the volume of hypersphere of radiis 1
     */
    public static double log2HyperSphereVolume(int n) {
        double e = Math.exp(1);
        return logHyperSphereVolume(n) / log2(e);
    }

    /**
     * Log base 2 of the volume of hypersphere of radiis 1
     */
    public static double pow2(double d) {
        return Math.pow(d, 2);
    }

    /**
     * Calculate the binomial coefficient using a recursive procedure.
     */
    public static long binom(int n, int m) {
        if (m > n) {
            return 0;
        }
        if (n == m || m == 0) {
            return 1;
        }
        if (n - m < m) {
            return binom(n, n - m);
        }
        return binom(n - 1, m - 1) * n / m;
    }

    /**
     * Return log2 of the binomial coefficient.
     */
    public static double log2Binom(int n, int m) {
        double num = 0, den = 0;
        for (int t = n - m + 1; t <= n; t++) {
            num += log2(t);
        }
        for (int t = 1; t <= m; t++) {
            den += log2(t);
        }
        return num - den;
    }

    /**
     * Returns the value of the mth discrete Legendre polynomial of length N
     * evalated at x.
     */
    public static double discreteLegendrePolynomial(int m, int N, int x) {
        double p = 0.0;
        double scale = factorial(m) / ((double) binom(2 * m, m));
        //System.out.println("factorial(m) = " + factorial(m));
        //System.out.println("binom(2*m, m) = " + binom(2*m, m));
        for (int s = 0; s <= m; s++) {
            p += scale * Math.pow(-1, s + m) * binom(s + m, s) * binom(N - s - 1, N - m - 1) * binom(x, s);
        }
        return p;
    }

    /**
     * Return the centered fractional part of x. i.e. x - round(x)
     */
    public static double fracpart(double x) {
        return x - Math.round(x);
    }

    /**
     * Return the fractional part 'mod 2pi'
     */
    public static double mod2pi(double x) {
        return Math.IEEEremainder(x, 2 * Math.PI);
    }

    /**
     * Returns the power sum i.e. 1 + 2^m + ... + N^m. This is just a brute
     * force approach. Could be a lot smarter.
     */
    public static double powerSum(int N, int m) {
        double sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.pow(i, m);
        }
        return sum;
    }

    /**
     * Returns the value of the nth modified Bessel function at x. For some
     * reason, the colt library does not have this.
     */
    public static double besselI(int n, double x) {
        double sum = 0, toadd = 1, tol = 1e-10;
        int m = 0;
        while (Math.abs(toadd) > tol) {
            long mf = factorial(m);
            long nf = factorial(m + n);
            toadd = Math.pow(x / 2, 2 * m + n) / mf / nf;
            //System.out.println(toadd);
            sum += toadd;
            m++;
        }
        return sum;
    }
    
    /** Make a copy of set S with element v removed */    
    public static <T> Set<T> set_remove(Set<T> S, T v) {
       HashSet<T> Sm1 = new HashSet();
       for( T x : S ) if( ! v.equals(x) ) Sm1.add(x);
       return Sm1;
    }

    
}
