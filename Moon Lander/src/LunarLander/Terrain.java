package LunarLander;

import java.util.Random;

public class Terrain {
    int myXLength;
    int myZLength;
    double myMaxHeight;
    double myBorderHeight;
    int myXOffset;
    int myZOffset;
    double myHeight[][];
    
    Random myRandom;

    double[][] mySmoothingMatrix = new double[][] 
        {   { 0.05, 0.05, 0.05 },
            { 0.05, 0.60, 0.05 },
            { 0.05, 0.05, 0.05 }   };

    // TODO: Fix scaling issue / size of square between points
    // TODO: Allow setting of "features" where heights of certain points are given
    // TODO: Allow specification of smoothing matrix weights 
    // TODO: Create different constructors to allow specifications in different ways
    // TODO: Rationalize x/z treatment
    // TODO: Implement option to have the border weights be the averages of the rows/columns
    // TODO: Change variable and method names from XFoo to FooX
            
    Terrain(int xLength, int zLength, double maxHeight, double borderHeight, int xOffset, int zOffset) {
        myXLength = xLength;
        myZLength = zLength;
        myMaxHeight = maxHeight;
        myBorderHeight = borderHeight;
        myXOffset = xOffset;
        myZOffset = zOffset;
        myHeight = new double[myXLength + 2][myZLength + 2];
        myRandom = new Random();
        
        for (int i = 0; i < myXLength + 2; i++) {
            myHeight[i][0] = myBorderHeight;
            myHeight[i][myZLength + 1] = myBorderHeight;
        }
        for (int j = 0; j < myZLength + 2; j++) {
            myHeight[0][j] = myBorderHeight;
            myHeight[myXLength + 1][j] = myBorderHeight;
        }
        for (int i = 1; i < myXLength + 1; i++) {
            for (int j = 1; j < myZLength + 1; j++) {
               myHeight[i][j] = myRandom.nextDouble() * myMaxHeight;
            }
        }
    }
    
    public final void smoothOnePass() {
        
        double newHeight[][] = new double[myXLength + 2][myZLength + 2];

        for (int i = 0; i < myXLength + 2; i++) {
            newHeight[i][0] = myBorderHeight;
            newHeight[i][myZLength + 1] = myBorderHeight;
        }
        for (int j = 0; j < myZLength + 2; j++) {
            newHeight[0][j] = myBorderHeight;
            newHeight[myXLength + 1][j] = myBorderHeight;
        }
        for (int i = 1; i < myXLength + 1; i++) {
            for (int j = 1; j < myZLength + 1; j++) {
               double sum = 0.0;
               for (int i0 = 0; i0 < 3; i0++) {
                   for (int j0 = 0; j0 < 3; j0++) {
                       sum += myHeight[i - 1 + i0][j - 1 + j0] * mySmoothingMatrix[i0][j0];
                   }
               }
               newHeight[i][j] = sum;
            }
        }
        
        myHeight = newHeight;
    }

    public double get(int x, int y) {
        return myHeight[x + myXOffset + 1][y + myZOffset + 1];
    }

    public void set(int x, int y, double value) {
        myHeight[x + myXOffset + 1][y + myZOffset + 1] = value;
    }

    public String toString() {
        String str = "";
        for (int j = 1; j < myZLength + 1; j++) {
            if (j != 1) {
                str += "\n";
            }
            for (int i = 1; i < myXLength + 1; i++) {
                if (i != 1) {
                    str += " ";
                }
                str += String.format("%4.1f", myHeight[i][j]);
            }
        }
        return str;
    }
    
    public int getXLength() {
        return myXLength;
    }

    public int getZLength() {
        return myZLength;
    }
        
    public int getXStart() {
        return -myXOffset;
    }
    public int getXEnd() {
        return -myXOffset + myXLength - 1;
    }

    public int getZStart() {
        return -myZOffset;
    }
    public int getZEnd() {
        return -myZOffset + myZLength - 1;
    }

    public double getMaxHeight() {
        return myMaxHeight;
    }

    public static void main(String[] args) {
        Terrain terrain = new Terrain(21, 7, 100.0, 20.0, 10, 3);
        System.out.println(terrain);
        System.out.println(terrain.get(-10, -3));
        System.out.println();
        for (int i = 0; i < 10; i++) {
            terrain.smoothOnePass();
            System.out.println(terrain);
            System.out.println();
        }
    }

}
