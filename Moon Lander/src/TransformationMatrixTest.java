package appl.graphics;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lib.util.Util;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.junit.Test;

import appl.graphics.TransformationMatrix.Type;

public class TransformationMatrixTest {
    
    public class TestPoints extends Array2DRowRealMatrix {
        
        /**
         * 
         */
        private static final long serialVersionUID = 3484179560926252243L;

        public TestPoints() {
            super(4, 3);
            this.setEntry(0,  0, 1.0);
            this.setEntry(3,  0, 1.0);
            this.setEntry(1,  1, 1.0);
            this.setEntry(3,  1, 1.0);
            this.setEntry(2,  2, 1.0);
            this.setEntry(3,  2, 1.0);
        }
        
    }

    static private String toString(RealMatrix matrix) {
        String str = "";
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                str += String.format(" %10.6f", matrix.getEntry(i,  j));
            }
            str += "\n";
        }
        return str;
    }
    
    static private RealMatrix makeMatrix(double[][] cols) {
        return (new Array2DRowRealMatrix(cols)).transpose();
    }
    
    static private boolean matrixEquals(RealMatrix m0, RealMatrix m1) {
        if (m0.getColumnDimension() != m1.getColumnDimension()) {
            return false;
        }
        if (m0.getRowDimension() != m1.getRowDimension()) {
            return false;
        }
        for (int i = 0; i <= m0.getRowDimension(); i++) {
            for (int j = 0; i <= m0.getColumnDimension(); i++) {
                if (! Util.approximatelyEqual(m0.getEntry(i,  j), m1.getEntry(i,  j))) {
                    return false;
                }
            }
        }
        
        return true;
    }

    @Test
    public void test() {
        ArrayList<TransformationMatrix> transMats = new ArrayList<TransformationMatrix>();
        ArrayList<RealMatrix> expectedResults = new ArrayList<RealMatrix>();
        RealMatrix expectedResult;

        transMats.add(new TransformationMatrix(Type.SCALE, 2.0, 3.0, 4.0));
        expectedResult = makeMatrix(new double[][] {
                { 2.0, 0.0, 0.0, 1.0 },
                { 0.0, 3.0, 0.0, 1.0 },
                { 0.0, 0.0, 4.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        transMats.add(new TransformationMatrix(Type.TRANSLATE, 1.0, 2.0, 3.0));
        expectedResult = makeMatrix(new double[][] {
                { 2.0, 2.0, 3.0, 1.0 },
                { 1.0, 3.0, 3.0, 1.0 },
                { 1.0, 2.0, 4.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        transMats.add(new TransformationMatrix(Type.ROTATE, Math.PI / 2.0, 0,  0));
        expectedResult = makeMatrix(new double[][] {
                { 1.0, 0.0,  0.0, 1.0 },
                { 0.0, 0.0, -1.0, 1.0 },
                { 0.0, 1.0,  0.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        transMats.add(new TransformationMatrix(Type.ROTATE, 1, 0, 0, Math.PI / 2.0));
        expectedResult = makeMatrix(new double[][] {
                { 1.0, 0.0,  0.0, 1.0 },
                { 0.0, 0.0, -1.0, 1.0 },
                { 0.0, 1.0,  0.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        transMats.add(new TransformationMatrix(Type.ROTATE, 0, 1, 0, Math.PI / 2.0));
        expectedResult = makeMatrix(new double[][] {
                { 0.0, 0.0, -1.0, 1.0 },
                { 0.0, 1.0,  0.0, 1.0 },
                { 1.0, 0.0,  0.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        transMats.add(new TransformationMatrix(Type.ROTATE, 0, 0, 1, Math.PI / 2.0));
        expectedResult = makeMatrix(new double[][] {
                {  0.0, 1.0, 0.0, 1.0 },
                { -1.0, 0.0, 0.0, 1.0 },
                {  0.0, 0.0, 1.0, 1.0 }
        });
        expectedResults.add(expectedResult);

        for (int i = 0; i < transMats.size(); i++) {
            TransformationMatrix t = transMats.get(i);
            TestPoints tp = new TestPoints();
            RealMatrix result = t.applyTo(tp);
            System.out.println(t);
            System.out.println(toString(result));
            System.out.println();
            assertTrue(matrixEquals(expectedResults.get(i), result));
        }
    }

}
