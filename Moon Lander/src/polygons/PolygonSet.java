package appl.graphics.polygons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math.linear.RealMatrix;

import appl.graphics.TransformationMatrix;

public class PolygonSet implements Iterable<Polygon> {
    private int                   myNumPolygons;
    private ArrayList<RealMatrix> myListOfMasterPoints;   // TODO: Make these a points class
    private Polygon[]             myPolygons;             // Array of array of column indices giving the points of a polygon
    private int[]                 myPtrsToMasterPoints;   // Indices of master points used by polygons
    
    class PolygonIterator implements Iterator<Polygon> {
        int myCurIndex = 0;
        
        @Override
        public boolean hasNext() {
            return (myCurIndex < myNumPolygons);
        }

        @Override
        public Polygon next() {
            if (hasNext()) {
                myCurIndex++;
                return getPolygon(myCurIndex - 1);
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            Polygon[] newPolygons = new Polygon[myNumPolygons - 1];
            for (int i = 0; i < myNumPolygons - 1; i++) {
                if (i < myCurIndex) {
                    newPolygons[i] = myPolygons[i]; 
                }
                else {
                    newPolygons[i] = myPolygons[i+1]; 
                }
            }
            
            myNumPolygons--;
            assert(myNumPolygons == myPolygons.length);
        }
    }
    
    public PolygonSet(RealMatrix points, Polygon[] polygons) {
        myNumPolygons = polygons.length;
        myListOfMasterPoints = new ArrayList<RealMatrix>();
        myListOfMasterPoints.add(points);
        myPtrsToMasterPoints = new int[polygons.length];
        myPolygons = polygons;
        
        for (int i = 0; i < polygons.length; i++) {
            Polygon polygon = polygons[i];
            if (polygon.getMasterPoints() != points) {
                assert(polygon.getMasterPoints() == points);
            }
            myPtrsToMasterPoints[i] = 0;
        }
    }

    public PolygonSet(Polygon[] polygons) {
        myNumPolygons = polygons.length;
        myListOfMasterPoints = new ArrayList<RealMatrix>();
        myPtrsToMasterPoints = new int[polygons.length];
        myPolygons = polygons;
        
        for (int i = 0; i < polygons.length; i++) {
            Polygon polygon = polygons[i];
            int idx = myListOfMasterPoints.indexOf(polygon.getMasterPoints());
            if (idx >= 0) {
                myPtrsToMasterPoints[i] = idx;
            }
            else {
                myPtrsToMasterPoints[i] = myListOfMasterPoints.size();
                myListOfMasterPoints.add(polygon.getMasterPoints());
            }
        }
    }

    private PolygonSet(ArrayList<RealMatrix> listOfMasterPoints, Polygon[] polygons, int[] ptrsToMasterPoints) {
        assert(polygons.length == ptrsToMasterPoints.length);
        myNumPolygons = polygons.length;
        myListOfMasterPoints = listOfMasterPoints;
        myPolygons = polygons;
        myPtrsToMasterPoints = ptrsToMasterPoints;
        for (int i = 0; i < polygons.length; i++) {
            assert(polygons[i].getMasterPoints() == myListOfMasterPoints.get(myPtrsToMasterPoints[i]));
        }
        for (int ptr : ptrsToMasterPoints) {
            assert(ptr >= 0 && ptr < myListOfMasterPoints.size());
        }
    }
    
    public PolygonSet(RealMatrix myPoints, ArrayList<Polygon> polygons) {
        this(myPoints, polygons.toArray(new Polygon[0]));
    }

    public Polygon getPolygon(int i) {
        assert (i >= 0 && i < myNumPolygons);
        return myPolygons[i];
    }
    
    double[] getPolygonCenter(int i) {
        assert (i >= 0 && i < myNumPolygons);
        return getPolygon(i).getCenter();
    }

    /**
     * Returns the master points matrix for polygon i
     * 
     * @param i index of the polygon
     * @return master points matrix for the polygon
     */
    public RealMatrix getPoints(int i) {
        return myListOfMasterPoints.get(myPtrsToMasterPoints[i]);
    }

    public PolygonSet makeTransformedPolygonSet(TransformationMatrix transMat) {
        ArrayList<RealMatrix> newListOfMasterPoints;   // TODO: Make these a points class
        Polygon[]             newPolygons;             // Array of array of column indices giving the points of a polygon

        newListOfMasterPoints = new ArrayList<RealMatrix>();
        for (RealMatrix mat : myListOfMasterPoints) {
            newListOfMasterPoints.add(transMat.applyTo(mat));
        }
        newPolygons = new Polygon[myNumPolygons];
        for (int i = 0; i < myNumPolygons; i++) {
            newPolygons[i] = myPolygons[i].shallowCopy();
            newPolygons[i].setMasterPoints(newListOfMasterPoints.get(myPtrsToMasterPoints[i]));
        }
        
        return new PolygonSet(newListOfMasterPoints, newPolygons, myPtrsToMasterPoints);   
        // TODO: Fix to make points in polygons consistent
    }

    public int getNumPolygons() {
        return myNumPolygons;
    }

    @Override
    public Iterator<Polygon> iterator() {
        return new PolygonIterator();
    }
}
