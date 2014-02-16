/*
 * $Id: MultipleRegression.java 22620 2009-07-25 18:57:20Z stonej $
 * $HeadURL: file:///proj/alu/svn/repository/alusys/trunk/src/java/deshaw/alu/common/math/MultipleRegression.java $
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

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

/**
 * Linear regression with multiple independent variables.
 *
 * @author stonej
 * @version   $Id: MultipleRegression.java 22620 2009-07-25 18:57:20Z stonej $
 */
public class MultipleRegression
{
    /* ************************* Static Members ************************** */

    /**
     * Indicate beginning of object output string.
     */
    private static final String OBJECT_OUTPUT_BEGIN =
        " ------- MultipleRegression object output (BEGIN) ------- ";

    /**
     * Indicate end of object output string.
     */
    private static final String OBJECT_OUTPUT_END =
        " ------- MultipleRegression object output (END) ------- ";

    /* ********************* Members ************************** */

    /**
     * Number of good data points.
     */
    private int myCount;

    /**
     * Number of bad data points (outliers).
     */
    private int myNumOutliers;

    /**
     * Decay constant (in seconds).
     */
    private double myTimeDecayConstantSeconds;

    /**
     * Number of independent variables.
     */
    private int myNumVar;

    /**
     * Outliers can be either cut (removed from data set) or capped
     * (set to the lower or upper limit).  Setting myCutOutliers =
     * false results in them being capped.
     */
    private boolean myCutOutliers;

    /**
     * Summary statistics (means and co-variances) of the input vectors
     * (y, 1, x1, x2, x3, ...).
     */
    private SummaryStatistics mySums;

    /**
     * Lower limit for x values.
     * Data points with x < myLowerLimitX will
     * be removed from the regression analysis.
     */
    private double myLowerLimitX;

    /**
     * Upper limit for x values.
     * Data points with x > myUpperLimitX will
     * be removed from the regression analysis.
     */
    private double myUpperLimitX;

    /**
     * Lower limit for y values.
     * Data points with y < myLowerLimitY will
     * be removed from the regression analysis.
     */
    private double myLowerLimitY;

    /**
     * Upper limit for y values.
     * Data points with y > myUpperLimitY will
     * be removed from the regression analysis.
     */
    private double myUpperLimitY;

    /**
     * This array specifies the X components that we would like to
     * use in the regression analysis.  Not all of these may
     * be usable, however (see myOkX).
     */
    private boolean[] myUseX;

    /**
     * Any component of X that has zero variance is not ok to
     * use (since it will cause the matrix not to be invertible).
     * This array is true only for components of X that are both
     * ok to use and also are desired (myUseX set to true).
     */
    private boolean[] myOkX;

    /**
     * Regression coefficients.
     */
    private double[] myCoef;

    /**
     * Regression coefficient standard errors.
     */
    private double[] myStdErr;

    /**
     * Regression coefficient t-stats.
     */
    private double[] myTStat;

    /**
     * Regression chi squared.
     */
    private double myChiSq;

    /**
     * Regression R squared.
     */
    private double myRSq;

    /**
     * Regression adjusted R squared.
     */
    private double myAdjustedRSq;

    /**
     * Has the regression analysis been computed since adding last datum?
     */
    private boolean myIsComputed;

    // -------------------------------------------------------------------------


    /**
     * Allocate memory and init
     */
    private void init()
    {
        myCount       = 0;
        myNumOutliers = 0;

        myUseX = new boolean[myNumVar+1];
        myOkX  = new boolean[myNumVar+1];
        for (int i = 0; i <= myNumVar; i++) {
            myUseX[i] = true;
            myOkX[i]  = true;
        }

        // We will accumulate the summary statistics (means and
        // co-variances) of the input vectors (y, 1, x1, x2, x3, ...).
        mySums = new SummaryStatistics(myNumVar+2, myTimeDecayConstantSeconds);

        myCoef   = new double[myNumVar+1];  // "+1" is for the intercept.
        myStdErr = new double[myNumVar+1];
        myTStat  = new double[myNumVar+1];

        myIsComputed = false;
    }

    /**
     * Perform the regression computations
     */
    private void compute()
    {
        // Set everything to 0.
        for (int i = 0; i < (myNumVar+1); i++) {
            myCoef[i]   = 0.0;
            myStdErr[i] = 0.0;
            myTStat[i]  = 0.0;
        }
        myChiSq        = 0.0;
        myRSq          = 0.0;
        myAdjustedRSq  = 0.0;

        // Set coefficients, t-stat, etc. if there has been enough data added.
        if (myCount >= (myNumVar+1)) {
            RealMatrix dataMatrix = new RealMatrixImpl(mySums.getSumXX());
            RealMatrix xxMatrix = dataMatrix.getSubMatrix(1, myNumVar+1,
                                                          1, myNumVar+1);
            RealMatrix xyMatrix = dataMatrix.getSubMatrix(1, myNumVar+1,
                                                          0, 0);

            computeOkX();  // Determine which X components to use.
            int[] listX = getListX();
            int[] listY = {0};
            int numX = listX.length;
            RealMatrix xxSubMatrix = xxMatrix.getSubMatrix(listX, listX);
            RealMatrix xySubMatrix = xyMatrix.getSubMatrix(listX, listY);

            double sumY  = mySums.getSumXX()[0][1];
            double sumYY = mySums.getSumXX()[0][0];

            if (!xxSubMatrix.isSingular()) {
                RealMatrix xxInverse = xxSubMatrix.inverse();
                RealMatrix coefMatrix = xxInverse.multiply(xySubMatrix);
                double[] coef = coefMatrix.getColumn(0);

                // Compute chi-squared
                myChiSq = sumYY
                        - 2*coefMatrix.transpose().multiply(xySubMatrix).
                          getEntry(0, 0) +
                        + coefMatrix.transpose().multiply(xxSubMatrix).
                          multiply(coefMatrix).getEntry(0, 0);

                // Compute R^2 and adjusted R^2
                int offset = getUseIntercept() ? 1 : 0;
                myRSq = 1.0 - myChiSq / (sumYY - sumY*sumY/myCount);
                myAdjustedRSq = 1 - ((1-myRSq)*(myCount-1)+1-offset)/
                                    (myCount-numX);

                // Compute standard errors and t-stats
                int j = 0;
                for (int i = 0; i < (myNumVar+1); i++) {
                    if (myOkX[i]) {
                        j++;
                        myCoef[i]   = coef[j-1];
                        myStdErr[i] = Math.sqrt(myChiSq *
                                      xxInverse.getEntry(j-1, j-1) /
                                      (myCount-numX));
                        myTStat[i] = myCoef[i] / myStdErr[i];
                    }
                }
            }

        }
        myIsComputed = true;
    }

    /**
     * Constructor with limits on X and Y.
     * <p>
     * Data points falling outside the limits ("outliers") will
     * be removed from the regression analysis.
     *
     * @param  numVars            Number of independent variables.
     * @param  timeDecayConstantSeconds  Decay constant (in seconds).
     * @param  cutOutliers        Cut outliers (if true) or cap them (if false).
     * @param  lowerX             Lower limit for X.
     * @param  upperX             Upper limit for X.
     * @param  lowerY             Lower limit for Y.
     * @param  upperY             Upper limit for Y.
     */
    public MultipleRegression(int numVars,
                              double timeDecayConstantSeconds,
                              boolean cutOutliers,
                              double lowerX, double upperX,
                              double lowerY, double upperY)
    {
        myNumVar      = numVars;
        myTimeDecayConstantSeconds = timeDecayConstantSeconds;
        myCutOutliers = cutOutliers;
        myLowerLimitX = lowerX;
        myUpperLimitX = upperX;
        myLowerLimitY = lowerY;
        myUpperLimitY = upperY;

        init();
    }

    /**
     * Constructor with no limits on X or Y.
     *
     * @param  numVars            Number of independent variables.
     * @param  timeDecayConstantSeconds  Decay constant (in seconds).
     */
    public MultipleRegression(int numVars, double timeDecayConstantSeconds)
    {
        this(numVars, timeDecayConstantSeconds, true,
             -Double.MAX_VALUE, Double.MAX_VALUE,
             -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Constructor with limits on X and Y and no time decay.
     * <p>
     * Data points falling outside the limits ("outliers") will
     * be removed from the regression analysis.
     *
     * @param  numVars            Number of independent variables.
     * @param  cutOutliers        Cut outliers (if true) or cap them (if false).
     * @param  lowerX             Lower limit for X.
     * @param  upperX             Upper limit for X.
     * @param  lowerY             Lower limit for Y.
     * @param  upperY             Upper limit for Y.
     */
    public MultipleRegression(int numVars,
                              boolean cutOutliers,
                              double lowerX, double upperX,
                              double lowerY, double upperY)
    {
        this(numVars, -1.0, cutOutliers,
             lowerX, upperX,
             lowerY, upperY);
    }

    /**
     * Constructor with no limits on X or Y and no time decay.
     *
     * @param  numVars            Number of independent variables.
     */
    public MultipleRegression(int numVars)
    {
        this(numVars, -1.0, true,
             -Double.MAX_VALUE, Double.MAX_VALUE,
             -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Copy constructor
     *
     * @param  mr  The MultipleRegression to copy
     */
    public MultipleRegression(MultipleRegression mr) {
        myNumVar            = mr.getNumVar();

        init();

        myTimeDecayConstantSeconds = mr.getTimeDecayConstantSeconds();
        myCutOutliers       = mr.getCutOutliers();
        myCount             = mr.getCount();
        myNumOutliers       = mr.getNumOutliers();
        myLowerLimitX       = mr.getLowerLimitX();
        myUpperLimitX       = mr.getUpperLimitX();
        myLowerLimitY       = mr.getLowerLimitY();
        myUpperLimitY       = mr.getUpperLimitY();
        mySums              = new SummaryStatistics(mr.getSums());
    }

    /**
     * Construct from previously saved object.
     *
     * @param  filename   Name of file containing object
     */
    public MultipleRegression(String filename)
    {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null &&
                   !line.contains(OBJECT_OUTPUT_BEGIN)) {}

            myNumVar = Integer.parseInt(br.readLine().split(" ")[1]);

            init();

            myTimeDecayConstantSeconds = Double.parseDouble(br.readLine().split(" ")[1]);
            myCutOutliers = Boolean.parseBoolean(br.readLine().split(" ")[1]);
            myCount       = Integer.parseInt(br.readLine().split(" ")[1]);
            myNumOutliers = Integer.parseInt(br.readLine().split(" ")[1]);
            String[] fields = br.readLine().split(" ");
            myLowerLimitX = Double.parseDouble(fields[1]);
            myUpperLimitX = Double.parseDouble(fields[2]);
            fields = br.readLine().split(" ");
            myLowerLimitY = Double.parseDouble(fields[1]);
            myUpperLimitY = Double.parseDouble(fields[2]);

            mySums = new SummaryStatistics(filename);

            br.close();
        }
        catch (Exception e) {
            throw new Error("Error reading object file " + filename + ": " + e);
        }
    }

    /**
     * Combine a number of previously saved objects.
     *
     * @param  fileList   Name of file containing object list
     * @return            Combined object
     */
    public static MultipleRegression combineFileList(String fileList)
    {
        MultipleRegression combinedMr = null;

        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(fileList));
            while ((line = br.readLine()) != null) {
                MultipleRegression mr = new MultipleRegression(line);
                if (combinedMr == null) {
                    combinedMr = mr;
                }
                else {
                    combinedMr.accumulate(mr);
                }
            }
            br.close();
        }
        catch (Exception e) {
            throw new Error("Error reading file list " + fileList + ": " + e);
        }

        return combinedMr;
    }

    /**
     * Add a data point to the regression.
     *
     * @param  x       Vector of independent variables.
     * @param  y       Dependant variable.
     * @param  timeMs  Timestamp of the data.
     */
    public void add(double[] x, double y, long timeMs)
    {
        // Check to see if x, y fall within limits.
        boolean outlier = false;
        for (int i = 0; i < myNumVar; i++) {
            if (x[i] < myLowerLimitX || x[i] > myUpperLimitX) {
                outlier = true;
            }
        }
        if (y < myLowerLimitY || y > myUpperLimitY) {
            outlier = true;
        }
        if (outlier) {
            myNumOutliers++;
            if (myCutOutliers) {
                return;
            }
        }

        // Accumulate sums
        myCount++;
        double[] xInput = new double[myNumVar+2];
        xInput[0] = myCutOutliers ? y :
                    Math.max(Math.min(y, myUpperLimitY), myLowerLimitY);
        xInput[1] = 1;
        for (int i = 0; i < myNumVar; i++) {
            xInput[i+2] = myCutOutliers ? x[i] :
                          Math.max(Math.min(x[i], myUpperLimitX),
                                   myLowerLimitX);
        }
        mySums.add(xInput, timeMs);

        // New data has been added, so previously computed regression
        // results are no longer valid.
        myIsComputed = false;
    }

    /**
     * Add a data point to the regression with no time decay.
     *
     * @param  x       Vector of independent variables.
     * @param  y       Dependant variable.
     */
    public void add(double[] x, double y)
    {
        add(x, y, 0);
    }

    /**
     * Decay past data.
     *
     * @param  decay   Decays the existing data (relative to future 
     *                 updates) by multiplying with decayFactor.
     */
    public void decay(double decayFactor)
    {
        mySums.decay(decayFactor);
    }

    /**
     * Combine two MultipleRegression objects.
     * We assume that both are created with the same constructor parameters.
     *
     * @param  mr  MultipleRegression object to combine with the current one.
     */
    public void accumulate(MultipleRegression mr)
    {
        myCount += mr.getCount();
        myNumOutliers += mr.getNumOutliers();
        mySums.accumulate(mr.getSums());
        myIsComputed = false;
    }

    /**
     * Are we using the intercept?
     */
    public boolean getUseIntercept()
    {
        return myUseX[0];
    }

    /**
     * What components of X are desired when computing?
     */
    public boolean[] getUseX()
    {
        return myUseX;
    }

    /**
     * What components of X are ok to use when computing?
     */
    public boolean[] getOkX()
    {
        return myOkX;
    }

    /**
     * Get list of X components to use.
     */
    private int[] getListX()
    {
        int listSize = 0;
        for (int i = 0; i <= myNumVar; i++) {
            if (myOkX[i]) {
                listSize++;
            }
        }

        int[] list = new int[listSize];
        int index = 0;
        for (int i = 0; i <= myNumVar; i++) {
            if (myOkX[i]) {
                index++;
                list[index-1] = i;
            }
        }

        return list;
    }

    /**
     * Shall we use the intercept?
     */
    public void setUseIntercept(boolean useInt)
    {
        myUseX[0] = useInt;

        myIsComputed = false;
    }

    /**
     * Set components of X to use when computing.
     */
    public void setUseX(int[] useX)
    {
        for (int i = 1; i <= myNumVar; i++) {
            myUseX[i] = false;
            for (int j = 0; j < useX.length; j++) {
                if (useX[j] == i) {
                    myUseX[i] = true;
                    break;
                }
            }
        }

        myIsComputed = false;
    }

    /**
     * Set components of X not to use when computing.
     */
    public void setNotUseX(int[] notUseX)
    {
        for (int i = 1; i <= myNumVar; i++) {
            myUseX[i] = true;
            for (int j = 0; j < notUseX.length; j++) {
                if (notUseX[j] == i) {
                    myUseX[i] = false;
                    break;
                }
            }
        }

        myIsComputed = false;
    }

    /**
     * Find components of X ok to use when computing.
     * A component is not ok if it has zero variance.
     */
    private void computeOkX()
    {
        double[] minX = mySums.getMin();
        double[] maxX = mySums.getMax();

        myOkX[0] = myUseX[0];
        for (int i = 1; i <= myNumVar; i++) {
            if (myUseX[i] && minX[i+1] != maxX[i+1]) {
                myOkX[i] = true;
            }
            else {
                myOkX[i] = false;
            }
        }
    }

    /**
     * Write regression results to a String
     */
    public String toString()
    {
        if (!myIsComputed) {
            compute();
        }

        StringBuilder b = new StringBuilder("");
        b.append("Observations "    + myCount       + "\n");
        b.append("Outliers "        + myNumOutliers + "\n");
        b.append("RSquare "         + myRSq         + "\n");
        b.append("AdjustedRSquare " + myAdjustedRSq + "\n\n");
        b.append("CoefNum Coefficient StandardError tStat\n");
        for (int i = 0; i < (myNumVar+1); i++) {
            b.append(i==0 ? "int" : i).append(" ");
            b.append(myCoef[i]).append(" ");
            b.append(myStdErr[i]).append(" ");
            b.append(myTStat[i]).append(" ");
            b.append(myOkX[i] ? "" : " *").append("\n");
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
        b.append(linePrefix).append("numVar ").append(myNumVar).append("\n");
        b.append(linePrefix).append("timeDecayConstantSeconds ").append(myTimeDecayConstantSeconds).append("\n");
        b.append(linePrefix).append("cutOutliers ").append(myCutOutliers).append("\n");
        b.append(linePrefix).append("count ").append(myCount).append("\n");
        b.append(linePrefix).append("numOutliers ").append(myNumOutliers).append("\n");
        b.append(linePrefix).append("xLimits ").append(myLowerLimitX).append(" ").
                             append(myUpperLimitX).append("\n");
        b.append(linePrefix).append("yLimits ").append(myLowerLimitY).append(" ").
                             append(myUpperLimitY).append("\n");
        b.append(linePrefix).append("sums object\n").append(mySums.objectToString(linePrefix));
        b.append(linePrefix).append(OBJECT_OUTPUT_END).append("\n");

        return b.toString();
    }

    /**
     * Get number of independent variables.
     */
    public int getNumVar()
    {
        return myNumVar;
    }

    /**
     * Get time decay constant (in seconds).
     */
    public double getTimeDecayConstantSeconds()
    {
        return myTimeDecayConstantSeconds;
    }

    /**
     * Cut or cap outliers?
     */
    public boolean getCutOutliers()
    {
        return myCutOutliers;
    }

    /**
     * Get number of good data points (non-outliers).
     */
    public int getCount()
    {
        return myCount;
    }

    /**
     * Get number of bad data points (outliers).
     */
    public int getNumOutliers()
    {
        return myNumOutliers;
    }

    /**
     * Get lower X limit.
     */
    public double getLowerLimitX()
    {
        return myLowerLimitX;
    }

    /**
     * Get upper X limit.
     */
    public double getUpperLimitX()
    {
        return myUpperLimitX;
    }

    /**
     * Get lower Y limit.
     */
    public double getLowerLimitY()
    {
        return myLowerLimitY;
    }

    /**
     * Get upper Y limit.
     */
    public double getUpperLimitY()
    {
        return myUpperLimitY;
    }

    /**
     * Get summary statistics of input data.
     */
    public SummaryStatistics getSums()
    {
        return mySums;
    }

    /**
     * Get regression coefficients.
     */
    public double[] getCoefficients()
    {
        if (!myIsComputed) {
            compute();
        }
        return myCoef;
    }

    /**
     * Get standard error of regression coefficients.
     */
    public double[] getStdErr()
    {
        if (!myIsComputed) {
            compute();
        }
        return myStdErr;
    }

    /**
     * Get t-stat of regression coefficients.
     */
    public double[] getTStat()
    {
        if (!myIsComputed) {
            compute();
        }
        return myTStat;
    }

    /**
     * Get R-squared of regression.
     */
    public double getRSq()
    {
        if (!myIsComputed) {
            compute();
        }
        return myRSq;
    }

    /**
     * Get adjusted R-squared of regression.
     */
    public double getAdjustedRSq()
    {
        if (!myIsComputed) {
            compute();
        }
        return myAdjustedRSq;
    }
}
