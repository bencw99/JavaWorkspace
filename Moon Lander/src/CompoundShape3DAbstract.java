package appl.graphics;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import appl.graphics.TransformationMatrix.Type;

public abstract class CompoundShape3DAbstract extends Shape3D {

    protected Collection<Shape3D>  myShapes;
    
    private class SortedIterator implements Iterator<Shape3D> {
        private int                 myCurIndex;
        private Object[]            myShapesArray;
        private Integer[]           mySortedPtrs;
        private int                 myNumShapes;

        private SortedIterator(Canvas3D canvas3d, TransformationMatrix transMat) {
            myNumShapes = myShapes.size();
            myShapesArray = myShapes.toArray();
            
            final double[] distances = new double[myNumShapes];
            
            String str;
            str = "";
            for (int i = 0; i < myShapes.size(); i++) {
                distances[i] = canvas3d.getDistance((Shape3D) myShapesArray[i], transMat);
                str = String.format("%s %8.5f", str, distances[i]);
            }
            System.out.println(str);
            
            mySortedPtrs = new Integer[myNumShapes];
            for (int i = 0; i < myNumShapes; i++) {
                mySortedPtrs[i] = i;
            }

            Arrays.sort(mySortedPtrs, new Comparator<Integer>() {
                    public int compare(Integer i0, Integer i1) {
                        double d0 = distances[i0];
                        double d1 = distances[i1];
                        return ((d0 > d1) ? -1 : (d1 < d0) ? 1 : 0);
                    }
            });

            str = "";
            for (int i = 0; i < myNumShapes; i++) {
                str = String.format("%s %3d", str, mySortedPtrs[i]);
            }
            System.out.println(str);

            str = "";
            for (int i = 0; i < myNumShapes; i++) {
                str = String.format("%s %8.5f", str, distances[mySortedPtrs[i]]);
            }
            System.out.println(str);

            System.out.println();
            
            assert(myShapesArray.length == mySortedPtrs.length);
            myCurIndex = 0;
        }
        
        @Override
        public boolean hasNext() {
            return (myCurIndex < myNumShapes);
        }

        @Override
        public Shape3D next() {
            if (hasNext()) {
                myCurIndex++;
                return (Shape3D) myShapesArray[mySortedPtrs[myCurIndex-1]];
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public CompoundShape3DAbstract() {
        myTransform = new TransformationMatrix();
    }
    
    public void add(Shape3D shape) {
        myShapes.add(shape);
    }
    
    @Override
    public Iterator<Shape3D> iterator() {
        return myShapes.iterator();
    }

    public Shape3D scaleSubShapes(double scaleFactor) {
        for (Shape3D subShape :  myShapes) {
            subShape.scale(scaleFactor);
        }
        return this;
    }

    public Shape3D rotateSubShapes(double angleX, double angleY, double angleZ) {
        for (Shape3D subShape :  myShapes) {
            subShape.rotate(angleX, angleY, angleZ);
        }
        return this;
    }
    
    public Shape3D rotateSubShapes(double vecX, double vecY, double vecZ, double pointX, double pointY, double pointZ, double angle) {
        for (Shape3D subShape :  myShapes) {
            subShape.rotate(vecX, vecY, vecZ, pointX, pointY, pointZ, angle);  // TODO: Fix frame of reference issues
        }
        return this;
    }

    public Shape3D translateSubShapes(double transX, double transY, double transZ) {
        for (Shape3D subShape :  myShapes) {
            subShape.translate(transX, transY, transZ);
        }
        return this;
    }

    
    @Override
    public double[] getCenter() {
        int numShapes = myShapes.size();
        double[] center = new double[4];
        for (Shape3D subShape : myShapes) {
            double[] subShapeCenter = subShape.getCenter();
            for (int j = 0; j < 4; j++) {
                center[j] += subShapeCenter[j];
            }
            // TODO: Fix the treatment of the homogeneous portion (which right now should work because they are all 1).
        }
        for (int j = 0; j < 4; j++) {
            center[j] /= numShapes;
        }
        return myTransform.applyTo(center);
    }


    @Override
    public Iterator<Shape3D> sort(Canvas3D canvas3d, TransformationMatrix transMat) {
        return new SortedIterator(canvas3d, transMat);
    }

    @Override
    public void draw(Graphics g, Canvas3D canvas3d, TransformationMatrix transMat) {
        for (Iterator<Shape3D> iter = this.sort(canvas3d, transMat.applyTo(myTransform)); iter.hasNext(); ) { 
            iter.next().draw(g, canvas3d, transMat.applyTo(myTransform));
        }
    }
}
