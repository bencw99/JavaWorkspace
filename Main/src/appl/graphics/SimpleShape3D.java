package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math.linear.RealMatrix;

import appl.graphics.polygons.PolygonSet;

public class SimpleShape3D extends Shape3D {

    protected RealMatrix myPoints;
    protected PolygonSet myPolygons;
    
    public SimpleShape3D() {
        super();
    }

    public void setColor(int face, Color color) {
        assert(face >= 0 && face < 6);
        myPolygons.getPolygon(face).setColor(color);
    }
    
    public double[] getCenter() {
        int numPolygons = myPolygons.getNumPolygons();
        double[] center = new double[4];
        for (int i = 0; i < numPolygons; i++) {
            double[] polygonCenter = myPolygons.getPolygon(i).getCenter();
            for (int j = 0; j < 4; j++) {
                center[j] += polygonCenter[j];
            }
            // TODO: Fix the treatment of the homogeneous portion (which right now should work because they are all 1).
        }
        for (int j = 0; j < 4; j++) {
            center[j] /= numPolygons;
        }
//        System.out.println(myTransform);
//        for (double d : center) {
//            System.out.println(d);
//        }
//        System.out.println();
//
        center = myTransform.applyTo(center);

//        for (double d : center) {
//            System.out.println(d);
//        }
//        System.out.println();
        
        return center;
    }


    
    private class Shape3DIterator implements Iterator<Shape3D> {
        int myCurIndex = 0;
        
        @Override
        public boolean hasNext() {
            return (myCurIndex == 0);
        }

        @Override
        public SimpleShape3D next() {
            if (hasNext()) {
                myCurIndex++;
                return SimpleShape3D.this;
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
    
    @Override
    public Iterator<Shape3D> iterator() {
        return new Shape3DIterator();
    }

    @Override
    public Iterator<Shape3D> sort(Canvas3D canvas3d, TransformationMatrix transMat) {
        // No sorting needed, since this is just a single shape, so return the iterator
        return iterator();        
    }

    @Override
    public void draw(Graphics g, Canvas3D canvas3d, TransformationMatrix transMat) {
        // Project polygons onto 2-D plane 
        PolygonSet polygonSet = myPolygons.makeTransformedPolygonSet(transMat.applyTo(myTransform));
        // PolygonSet newPolygonSet = canvas3d.transform3Dto2D(polygonSet);
        PolygonSet newPolygonSet = polygonSet;
  
        // Sort new polygon set by distance from viewer
        Integer[] sortedPtrs = canvas3d.reverseSortByDistance(newPolygonSet);
        
        // Project onto plane and draw
        canvas3d.render((Graphics2D) g, newPolygonSet, sortedPtrs);
    }



    public PolygonSet getTransformedPolygonSet() {
        return myPolygons.makeTransformedPolygonSet(myTransform);
    }
}
