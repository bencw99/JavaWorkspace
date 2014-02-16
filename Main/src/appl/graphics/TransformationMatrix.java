package appl.graphics;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class TransformationMatrix extends Array2DRowRealMatrix {

    /**
     * 
     */
    private static final long serialVersionUID = -5676014293741043961L;
    
    public static enum Type {
        ROTATE, SCALE, SHEAR, TRANSLATE
    }
    
    public TransformationMatrix() {
        this(Type.SCALE, 1.0, 1.0, 1.0);
    }
    
    public TransformationMatrix(double[][] d) {
        super(d);
    }

    public TransformationMatrix(Array2DRowRealMatrix m) {
        super(m.getDataRef());
    }

    /**
     * Creates a transformation matrix that effects a transformation operation given three parameters, one for
     * each of the x, y, and z directions.  The following types of transformations can be chosen:
     * 
     * Type.ROTATE: Rotate  
     * Type.SCALE: Scale 
     * Type.TRANSLATE: 
     * 
     * @param type
     * @param paramX
     * @param paramY
     * @param paramZ
     */
    public TransformationMatrix(Type type, double paramX, double paramY, double paramZ) {
        super(4, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assert(getEntry(i,  j) == 0.0);
            }
        }
        if (type == Type.ROTATE) {
            double angle = Math.sqrt(paramX * paramX + paramY * paramY + paramZ * paramZ);
            TransformationMatrix t = new TransformationMatrix(Type.ROTATE, paramX, paramY, paramZ, 0.0, 0.0, 0.0, angle);
            setSubMatrix(t.getDataRef(), 0, 0);
        }
        if (type == Type.SCALE) {
            setEntry(0, 0, paramX);
            setEntry(1, 1, paramY);
            setEntry(2, 2, paramZ);
            setEntry(3, 3, 1.0);
        }
        if (type == Type.SHEAR) {
            assert(false);
            // TODO: Implement
        }
        if (type == Type.TRANSLATE) {
            setSubMatrix(new double[][] { { 1.0, 0.0, 0.0, paramX },
                                          { 0.0, 1.0, 0.0, paramY },
                                          { 0.0, 0.0, 1.0, paramZ },
                                          { 0.0, 0.0, 0.0, 1.0    } },
                         0, 0);
        }
    }
    
    /**
     * Create a transformation matrix to rotate by <angle> around a vector (vecX, vecY, vecZ) going through (pointX, pointY, pointZ).
     * 
     * @param vecX
     * @param vecY
     * @param vecZ
     * @param pointX
     * @param pointY
     * @param pointZ
     * @param angle
     */
    public TransformationMatrix(Type type, double vecX, double vecY, double vecZ, double pointX, double pointY, double pointZ, double angle) {
        super(4, 4);
        assert (type == Type.ROTATE);

        TransformationMatrix t = new TransformationMatrix(Type.TRANSLATE, pointX, pointY, pointZ);
        double normDenom = Math.sqrt(vecX * vecX + vecY * vecY + vecZ * vecZ);
        double unitX = vecX / normDenom;
        double unitY = vecY / normDenom;
        double unitZ = vecZ / normDenom;
        double oneMinusCosAngle = 1.0 - Math.cos(angle);
        t = t.applyTo(new TransformationMatrix(new double[][] { { unitX * unitX * oneMinusCosAngle + Math.cos(angle),         unitX * unitY * oneMinusCosAngle - unitZ * Math.sin(angle), unitX * unitZ * oneMinusCosAngle + unitY * Math.sin(angle), 0.0 },
                                                                { unitX * unitY * oneMinusCosAngle + unitZ * Math.sin(angle), unitY * unitY * oneMinusCosAngle + Math.cos(angle),         unitY * unitZ * oneMinusCosAngle - unitX * Math.sin(angle), 0.0 },
                                                                { unitX * unitZ * oneMinusCosAngle - unitY * Math.sin(angle), unitY * unitZ * oneMinusCosAngle + unitX * Math.sin(angle), unitZ * unitZ * oneMinusCosAngle + Math.cos(angle),         0.0 },
                                                                { 0.0,                                                        0.0,                                                        0.0,                                                        1.0 } } )); 
        
        t = t.applyTo(new TransformationMatrix(Type.TRANSLATE, -pointX, -pointY, -pointZ));
        setSubMatrix(t.getDataRef(), 0, 0);
    }

    /**
     * Create a transformation matrix to rotate by <angle> around a vector (vecX, vecY, vecZ) going through the origin.
     * @param type TODO
     * @param vecX
     * @param vecY
     * @param vecZ
     * @param angle
     */
    public TransformationMatrix(Type type, double vecX, double vecY, double vecZ, double angle) {
        this(type, vecX, vecY, vecZ, 0.0, 0.0, 0.0, angle);
        assert (type == Type.ROTATE);
    }

    
    

    public TransformationMatrix applyTo(TransformationMatrix matrix) { 
        return new TransformationMatrix(multiply(matrix).getDataRef());
    }

    public RealMatrix applyTo(RealMatrix matrix) {
        return multiply(matrix);
    }
    
    public double[] applyTo(double[] vector) {
        return transpose().preMultiply(vector);
    }
    
    public String toString() {
        String str = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                str += String.format(" %10.6f", getEntry(i,  j));
            }
            str += "\n";
        }
        return str;
    }
    
    public static void main(String[] args) {
    }
}
