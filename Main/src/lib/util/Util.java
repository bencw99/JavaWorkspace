package lib.util;

public class Util {

    static final double TOL = 1e-10; 
    
    public static boolean approximatelyEqual(double d0, double d1) {
        return(Math.abs(d0 - d1) < TOL);
    }
}
