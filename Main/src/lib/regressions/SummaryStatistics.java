/*
 * $Id: SummaryStatistics.java 22620 2009-07-25 18:57:20Z stonej $
 * $HeadURL: file:///proj/alu/svn/repository/alusys/trunk/src/java/deshaw/alu/common/math/SummaryStatistics.java $
 */

/*
 * Copyright 2005 D.E. Shaw & Co.  All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of D.E. Shaw & Co. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with D.E. Shaw & Co.
 */

package lib.regressions;

// import deshaw.alu.common.log.AluLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Compute joint statistics of a collection of vectors.
 *
 * @author stonej
 * @version   $Id: SummaryStatistics.java 22620 2009-07-25 18:57:20Z stonej $
 */
public class SummaryStatistics
{
    static
    {
        // AluLog.logEarlyIo("JavaVersions", "$HeadURL: file:///proj/alu/svn/repository/alusys/trunk/src/java/deshaw/alu/common/math/SummaryStatistics.java $ - $Id: SummaryStatistics.java 22620 2009-07-25 18:57:20Z stonej $");
    }

    /* ************************* Static Members ************************** */

    /**
     * Indicate beginning of object output string.
     */
    private static final String OBJECT_OUTPUT_BEGIN =
        " ------- SummaryStatistics object output (BEGIN) ------- ";

    /**
     * Indicate end of object output string.
     */
    private static final String OBJECT_OUTPUT_END =
        " ------- SummaryStatistics object output (END) ------- ";


    /* ********************* Members ************************** */

    /**
     * Dimension of data points.
     */
    private int myDim;

    /**
     * Number of data points.
     */
    private int myCount;

    /**
     * Number of data points with x==0.
     */
    private int[] myZeroCount;

    /**
     * Weighted count of data points.
     */
    private double myWeightedCount;

    /**
     * Decay constant (in seconds).
     */
    private double myTimeDecayConstantSeconds;

    /**
     * Time of last update.
     */
    private long myLastTimestampMs;

    /**
     * Matrix containing sum across data points of x[i]*x[j]
     * (for computing VCV matrix).
     */
    private double[][] mySumXX;

    /**
     * Array containing sum across data points of x[i]
     * (for computing mean vector).
     */
    private double[] mySumX;

    /**
     * Min value.
     */
    private double[] myMinX;

    /**
     * Max value.
     */
    private double[] myMaxX;

    /**
     * VCV matrix.
     */
    private double[][] myVCV;

    /**
     * Correlation matrix.
     */
    private double[][] myCorrelation;

    /**
     * Mean vector.
     */
    private double[] myMean;

    /**
     * Standard deviation vector.
     */
    private double[] myStdDev;

    /**
     * Standard error vector.
     */
    private double[] myStdErr;

    /**
     * T-stat vector.
     */
    private double[] myTStat;

    /**
     * Have we computed since the most recent data addition?
     */
    private boolean myIsComputed;

    // -------------------------------------------------------------------------

    /**
     * Get 2d array as a String.
     */
    static private String getArray2dToString(double[][] array,
                                             int dim1, int dim2)
    {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2-1; j++) {
                b.append(array[i][j]).append(" ");
            }
            b.append(array[i][dim2-1]).append("\n");
        }
        return b.toString();
    }

    /**
     * Allocate memory and init
     */
    private void init()
    {
        myCount             = 0;
        myWeightedCount     = 0.0;
        myLastTimestampMs   = 0;
        myZeroCount         = new int[myDim];
        mySumXX             = new double[myDim][myDim];
        mySumX              = new double[myDim];
        myMinX              = new double[myDim];
        myMaxX              = new double[myDim];
        for (int i = 0; i < myDim; i++) {
            myMinX[i] =  Double.MAX_VALUE;
            myMaxX[i] = -Double.MAX_VALUE;
        }
        myVCV               = new double[myDim][myDim];
        myCorrelation       = new double[myDim][myDim];
        myMean              = new double[myDim];
        myStdDev            = new double[myDim];
        myStdErr            = new double[myDim];
        myTStat             = new double[myDim];
        myIsComputed        = false;
    }

    /**
     * Compute mean, VCV, standard errors, etc.
     */
    private void compute()
    {
        if (myCount < 2) {
            // We do not have enough data points to compute.
            // Set everything to 0.
            for (int i = 0; i < myDim; i++) {
                myMean[i]   = 0.0;
                myStdDev[i] = 0.0;
                myStdErr[i] = 0.0;
                myTStat[i]  = 0.0;
                for (int j = 0; j < myDim; j++) {
                    myVCV[i][j]         = 0.0;
                    myCorrelation[i][j] = 0.0;
                }
            }

            // One data point is enough to compute the means, but
            // not enough to compute anything else.
            if (myCount == 1) {
                for (int i = 0; i < myDim; i++) {
                    myMean[i] = mySumX[i] / myWeightedCount;
                }
            }
        }
        else {  // We have enough data to compute

            // Compute means
            for (int i = 0; i < myDim; i++) {
                myMean[i] = mySumX[i] / myWeightedCount;
            }

            // Compute VCV matrix
            for (int i = 0; i < myDim; i++) {
                for (int j = 0; j < myDim; j++) {
                    myVCV[i][j] = mySumXX[i][j]/myWeightedCount - myMean[i]*myMean[j];
                }
            }

            // Compute correlation matrix
            for (int i = 0; i < myDim; i++) {
                for (int j = 0; j < myDim; j++) {
                    if (myVCV[i][i]*myVCV[j][j] > 0.0) {
                        myCorrelation[i][j] = myVCV[i][j]/
                            Math.sqrt(myVCV[i][i]*myVCV[j][j]);
                    }
                    else {
                        myCorrelation[i][j] = 0.0;
                    }
                }
            }

            // Compute standard deviations, standard errors, and t-stats.
            for (int i = 0; i < myDim; i++) {
                if (myVCV[i][i] > 0.0) {
                    myStdDev[i] = Math.sqrt(myVCV[i][i]*myCount/(myCount-1));
                }
                else {
                    myStdDev[i] = 0.0;
                }
                myStdErr[i] = myStdDev[i]/Math.sqrt(myWeightedCount);
                myTStat[i] = (myStdErr[i] > 0.0) ? myMean[i]/myStdErr[i] : 0.0;
            }
        }
        myIsComputed = true;
    }

    /**
     * Constructor.
     *
     * @param  numVars             Dimension of data points.
     * @param  timeDecayConstantSeconds   Time decay constant (in seconds).
     */
    public SummaryStatistics(int numVars, double timeDecayConstantSeconds)
    {
        myDim = numVars;
        myTimeDecayConstantSeconds = timeDecayConstantSeconds;
        init();
    }

    /**
     * Constructor for univariate data.
     *
     * @param  timeDecayConstantSeconds   Time decay constant (in seconds).
     */
    public SummaryStatistics(double timeDecayConstantSeconds)
    {
        this(1, timeDecayConstantSeconds);
    }

    /**
     * Constructor with no time decay.
     *
     * @param  numVars   Dimension of data points.
     */
    public SummaryStatistics(int numVars)
    {
        this(numVars, -1.0);
    }

    /**
     * Constructor for univariate data with no time decay.
     */
    public SummaryStatistics()
    {
        this(1, -1.0);
    }

    /**
     * Copy constructor
     *
     * @param  ss    The SummaryStatistics to copy
     */
    public SummaryStatistics(SummaryStatistics ss)
    {
        myDim               = ss.getDim();

        init();

        myCount             = ss.getCount();
        myWeightedCount     = ss.getWeightedCount();
        myTimeDecayConstantSeconds = ss.getTimeDecayConstantSeconds();
        myLastTimestampMs   = ss.getLastTimestampMs();

        for (int i = 0; i < myDim; i++) {
            myZeroCount[i] = ss.getZeroCount()[i];
            mySumX[i] = ss.getSumX()[i];
            for (int j = 0; j < myDim; j++) {
                mySumXX[i][j] = ss.getSumXX()[i][j];
            }
            myMinX[i] = ss.getMin()[i];
            myMaxX[i] = ss.getMax()[i];
        }
    }

    /**
     * Construct from previously saved object.
     *
     * @param  filename   Name of file containing object
     */
    public SummaryStatistics(String filename)
    {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null &&
                   !line.contains(OBJECT_OUTPUT_BEGIN)) {}

            myDim = Integer.parseInt(br.readLine().split(" ")[1]);

            init();

            myCount = Integer.parseInt(br.readLine().split(" ")[1]);
            myWeightedCount = Double.parseDouble(br.readLine().split(" ")[1]);
            myTimeDecayConstantSeconds = Double.parseDouble(br.readLine().split(" ")[1]);
            myLastTimestampMs = Long.parseLong(br.readLine().split(" ")[1]);

            br.readLine();
            for (int i = 0; i < myDim; i++) {
                String[] fields = br.readLine().split(" ");
                myZeroCount[i] = Integer.parseInt(fields[0]);
                mySumX[i] = Double.parseDouble(fields[1]);
                myMinX[i] = Double.parseDouble(fields[2]);
                myMaxX[i] = Double.parseDouble(fields[3]);
            }

            br.readLine();
            for (int i = 0; i < myDim; i++) {
                String[] fields = br.readLine().split(" ");
                for (int j = 0; j < myDim; j++) {
                    mySumXX[i][j] = Double.parseDouble(fields[j]);
                }
            }

            br.close();
        }
        catch (IOException e) {
            throw new Error("Error reading object file " + filename + ": " + e);
        }
    }

    /**
     * Add a data point.
     *
     * @param  x       Vector of values.
     * @param  timeMs  Timestamp of values.
     */
    public void add(double[] x, long timeMs)
    {
        double decay = 1.0;
        if (myTimeDecayConstantSeconds > 0.0 && timeMs > 0 && myLastTimestampMs > 0) {
            decay = Math.exp(-(timeMs - myLastTimestampMs)/1000.0 /
                              myTimeDecayConstantSeconds);
        }
        myLastTimestampMs = timeMs;

        //Accumulate sums.
        myCount++;
        myWeightedCount = decay*myWeightedCount + 1.0;
        for (int i = 0; i < myDim; i++) {
            if (x[i] == 0) {
                myZeroCount[i]++;
            }
            mySumX[i] = decay*mySumX[i] + x[i];
            for (int j = 0; j < myDim; j++) {
                mySumXX[i][j] = decay*mySumXX[i][j] + x[i]*x[j];
            }
            if (x[i] < myMinX[i]) myMinX[i] = x[i];
            if (x[i] > myMaxX[i]) myMaxX[i] = x[i];
        }

        // New data has been added, so previously computed
        // results are no longer valid.
        myIsComputed = false;
    }

    /**
     * Add a univariate data point.
     *
     * @param  x       Value.
     * @param  timeMs  Timestamp of value.
     */
    public void add(double x, long timeMs)
    {
        double[] x1 = {x};
        add(x1, timeMs);
    }

    /**
     * Add a data point with no time decay.
     *
     * @param  x  Vector of values.
     */
    public void add(double[] x)
    {
        add(x, 0);
    }

    /**
     * Add a univariate data point with no time decay.
     *
     * @param  x  Value.
     */
    public void add(double x)
    {
        double[] x1 = {x};
        add(x1, 0);
    }

    /**
     * Decay past data.
     *
     * @param  decay   Decays the existing data (relative to future 
     *                 updates) by multiplying with decayFactor.
     */
    public void decay(double decayFactor)
    {
        myWeightedCount *= decayFactor;
        for (int i = 0; i < myDim; i++) {
            mySumX[i] *= decayFactor;
            for (int j = 0; j < myDim; j++) {
                mySumXX[i][j] *= decayFactor;
            }
        }
    }

    /**
     * Combine two SummaryStatistics objects.
     *
     * @param  ss  SummaryStatistics object to combine with the current one.
     */
    public void accumulate(SummaryStatistics ss)
    {
        myCount += ss.getCount();
        myWeightedCount += ss.getWeightedCount();
        for (int i = 0; i < myDim; i++) {
            myZeroCount[i] += ss.getZeroCount()[i];
            mySumX[i] += ss.getSumX()[i];
            for (int j = 0; j < myDim; j++) {
                mySumXX[i][j] += ss.getSumXX()[i][j];
            }
            if (ss.getMin()[i] < myMinX[i]) myMinX[i] = ss.getMin()[i];
            if (ss.getMax()[i] > myMaxX[i]) myMaxX[i] = ss.getMax()[i];
        }
        myIsComputed = false;
    }

    /**
     * Write summary stats to a String.
     */
    public String toString()
    {
        if (!myIsComputed) {
            compute();
        }
        StringBuilder b = new StringBuilder("Count ZeroCount Mean StdDev StdErr " +
                                            "tStat min max\n");
        for (int i = 0; i < myDim; i++) {
            b.append(myCount).append(" ");
            b.append(myZeroCount[i]).append(" ");
            b.append(myMean[i]).append(" ");
            b.append(myStdDev[i]).append(" ");
            b.append(myStdErr[i]).append(" ");
            b.append(myTStat[i]).append(" ");
            b.append(myMinX[i]== Double.MAX_VALUE ? 0 : myMinX[i]).append(" ");
            b.append(myMaxX[i]==-Double.MAX_VALUE ? 0 : myMaxX[i]).append("\n");
        }
        if (myDim > 1) {
            b.append("\nVCV\n").append(getArray2dToString(myVCV, myDim, myDim));
            b.append("\nCorrelation\n").append(getArray2dToString(myCorrelation,
                                               myDim, myDim));
        }
        return b.toString();
    }

    /**
     * Write to a String all info necessary to re-create the object.
     */
    public String objectToString(String linePrefix)
    {
        StringBuilder b = new StringBuilder();

        b.append(linePrefix).append(OBJECT_OUTPUT_BEGIN).append("\n");
        b.append(linePrefix).append("dim ").append(myDim).append("\n");
        b.append(linePrefix).append("count ").append(myCount).append("\n");
        b.append(linePrefix).append("weightedCount ").append(myWeightedCount).append("\n");
        b.append(linePrefix).append("timeDecayConstantSeconds ").append(myTimeDecayConstantSeconds).append("\n");
        b.append(linePrefix).append("lastTimestampMs ").append(myLastTimestampMs).append("\n");

        b.append(linePrefix).append("zeroCount sumX minX maxX\n");
        for (int i = 0; i < myDim; i++) {
            b.append(linePrefix);
            b.append(myZeroCount[i]).append(" ");
            b.append(mySumX[i]).append(" ");
            b.append(myMinX[i]).append(" ");
            b.append(myMaxX[i]).append("\n");
        }

        b.append(linePrefix).append("sumXX matrix\n");
        for (int i = 0; i < myDim; i++) {
            for (int j = 0; j < myDim; j++) {
                b.append((j==0) ? linePrefix : "").append(mySumXX[i][j]).append((j==myDim-1) ? "\n" : " ");
            }
        }
        b.append(linePrefix).append(OBJECT_OUTPUT_END).append("\n");

        return b.toString();
    }

    /**
     * Get dimension of data points.
     */
    public int getDim()
    {
        return myDim;
    }

    /**
     * Get number of data points.
     */
    public int getCount()
    {
        return myCount;
    }

    /**
     * Get weighted number of data points.
     */
    public double getWeightedCount()
    {
        return myWeightedCount;
    }

    /**
     * Get time decay constant (in seconds).
     */
    public double getTimeDecayConstantSeconds()
    {
        return myTimeDecayConstantSeconds;
    }

    /**
     * Get timestamp of last data point.
     */
    public long getLastTimestampMs()
    {
        return myLastTimestampMs;
    }

    /**
     * Get array containing number of data points with x==0.
     */
    public int[] getZeroCount()
    {
        return myZeroCount;
    }

    /**
     * Get array containing sum across data points of x[i]
     * (for computing mean vector).
     */
    public double[] getSumX()
    {
        return mySumX;
    }

    /**
     * Get matrix containing sum across data points of x[i]*x[j]
     * (for computing VCV matrix).
     */
    public double[][] getSumXX()
    {
        return mySumXX;
    }

    /**
     * Get min value vector.
     */
    public double[] getMin()
    {
        return myMinX;
    }

    /**
     * Get max value vector.
     */
    public double[] getMax()
    {
        return myMaxX;
    }

    /**
     * Get mean vector.
     */
    public double[] getMean()
    {
        if (!myIsComputed) {
            compute();
        }
        return myMean;
    }

    /**
     * Get VCV matrix.
     */
    public double[][] getVCV()
    {
        if (!myIsComputed) {
            compute();
        }
        return myVCV;
    }

    /**
     * Get correlation matrix.
     */
    public double[][] getCorrelation()
    {
        if (!myIsComputed) {
            compute();
        }
        return myCorrelation;
    }

    /**
     * Get standard deviation vector.
     */
    public double[] getStdDev()
    {
        if (!myIsComputed) {
            compute();
        }
        return myStdDev;
    }

    /**
     * Get standard error vector.
     */
    public double[] getStdErr()
    {
        if (!myIsComputed) {
            compute();
        }
        return myStdErr;
    }

    /**
     * Get t-stat vector.
     */
    public double[] getTStat()
    {
        if (!myIsComputed) {
            compute();
        }
        return myTStat;
    }
}
