package pubsim;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robby McKilliam
 */
public class ChirpZTest {
    
    public ChirpZTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    final double tol = 1e-6;
    
    // Directly computes the chirp-z transform for testing
    public Complex[] slowChirpZ(Complex A, Complex W, int M, int N, Complex[] x){
        if( x.length != N ) throw new ArrayIndexOutOfBoundsException("length of x should be " + N);
        Complex[] X = new Complex[M];
        for(int k = 0; k < M; k++){
            X[k] = Complex.zero;
            for(int n = 0; n < N; n++) {
                Complex term = x[n].multiply(A.pow(-n)).multiply(W.pow(n*k));
                X[k] = X[k].add( term );
            }
        }
        return X;
    }
    
    /**
     * Test of compute method, of class ChirpZ.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        Complex[] x = {Complex.zero, new Complex(0.1,4), new Complex(3,1.1), new Complex(1,-1) };
        int N = x.length;
        int M = 10;
        Complex A = new Complex(0.5, 0.8);
        Complex W = new Complex(1.0,0.1);
        Complex[] Xres = ChirpZ.compute(A,W,M,x);
        Complex[] Xexp = slowChirpZ(A,W,M,N,x);
        assertTrue(Xres.length == M);
        assertTrue(Xexp.length == M);
        //System.out.println(VectorFunctions.print(Xexp));
        //System.out.println(VectorFunctions.print(Xres));
        for(int m = 0; m < M; m++) assertTrue( (Xres[m].subtract(Xexp[m])).abs() < tol );
    }
    
    /**
     * If the chirp z-transform is the be computing on the unit circle thin much can be gained
     * by using Complex.UnitCircle for the input A and W.  The transform is both faster, and more
     * stable if this is done.
     */
    @Test
    public void testUnitCircle() {
        System.out.println("test on unit circle");
        Complex A = Complex.one;
        Complex W = new Complex.UnitCircle(0.4);
        int N = 10;
        int M = 20;
        Random rand = new Random();
        Complex[] x = new Complex[N];
        for(int n = 0; n < N; n++) x[n] = new Complex(rand.nextGaussian(), rand.nextGaussian());
        Complex[] Xres = ChirpZ.compute(A,W,M,x);
        Complex[] Xexp = slowChirpZ(A,W,M,N,x);
        System.out.println(VectorFunctions.print(Xexp));
        System.out.println(VectorFunctions.print(Xres));
        for(int m = 0; m < M; m++) assertTrue( (Xres[m].subtract(Xexp[m])).abs() < tol );
    }
    
    /// Test computation of the FFT using the Chirp z-transform
    @Test
    public void testFFT() {
        System.out.println("test on unit circle");
        int N = 7;
        int M = N;
        Complex A = Complex.one;
        Complex W = new Complex.UnitCircle(-2.0*Math.PI/N);
        Random rand = new Random();
        Complex[] x = new Complex[N];
        for(int n = 0; n < N; n++) x[n] = new Complex(rand.nextGaussian(), rand.nextGaussian());
        Complex[] Xres = ChirpZ.compute(A,W,M,x);
        Complex[] Xexp = FFT.fft(x);
        System.out.println(VectorFunctions.print(Xexp));
        System.out.println(VectorFunctions.print(Xres));
        for(int m = 0; m < M; m++) assertTrue( (Xres[m].subtract(Xexp[m])).abs() < tol );
    }
}
