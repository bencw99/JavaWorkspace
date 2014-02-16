package appl.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.math.linear.RealMatrix;

import appl.graphics.polygons.Polygon;
import appl.graphics.polygons.PolygonSet;

public class Canvas3D extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 6701576899423226442L;

    private CompoundShape3DArray myShapes = new CompoundShape3DArray();
    private double myViewerX =    0.0;
    private double myViewerY =    0.0;
    private double myViewerZ = 3000.0;
    private double myViewingPlaneDistanceFromViewer = 3000.0;
    
    public Canvas3D() {
    }
    
    
    public void register(Shape3D shape) {
        myShapes.add(shape);
    }

    public void register(Shape3DList shapeList) {
        for (Shape3D shape : shapeList) {
            register(shape);
        }
    }
  
    protected Integer[] reverseSortByDistance(PolygonSet polygonSet) {
        int numPolygons = polygonSet.getNumPolygons();
        final double[] distances = new double[numPolygons];
        for (int i = 0; i < numPolygons; i++) {
            double[] center = polygonSet.getPolygon(i).getCenter();
            distances[i] = Math.sqrt(center[0] * center[0] + center[1] * center[1] + center[2] * center[2]);
        }
        
        Integer[] sortedPtrs = new Integer[numPolygons];
        for (int i = 0; i < numPolygons; i++) {
            sortedPtrs[i] = i;
        }

        Arrays.sort(sortedPtrs, new Comparator<Integer>() {
                public int compare(Integer i0, Integer i1) {
                    double d0 = distances[i0];
                    double d1 = distances[i1];
                    return ((d0 > d1) ? -1 : (d1 < d0) ? 1 : 0);
                }
        });

        return sortedPtrs;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        TransformationMatrix transMat = new TransformationMatrix(new double[][]
                { { 1.0,  0.0, 0.0,  -myViewerX },
                  { 0.0,  1.0, 0.0,  -myViewerY } ,
                  { 0.0,  0.0, 1.0,  -myViewerZ },
                  { 0.0,  0.0, 0.0,    1.0 }
                } );
        
        myShapes.draw(g,  this, transMat);
    }
    
    public double getDistance(Shape3D shape, TransformationMatrix transMat) {
        double center[];
        // TODO: Change to disallow null transformation matrix
        if (transMat == null) {
            center = shape.getCenter();
        }
        else {
            center = transMat.applyTo(shape.getCenter());
        }
        
        return Math.sqrt(center[0] * center[0] + center[1] * center[1] + center[2] * center[2]);
    }


    public void render(Graphics2D g, PolygonSet polygonSet, Integer[] sortedPtrs) {
        // TODO Auto-generated method stub
//        TransformationMatrix transMat = new TransformationMatrix(new double[][]
//                { { 1.0,  0.0,  0.0,                                       0.0 },
//                  { 0.0, -1.0,  0.0,                                       0.0 },
//                  { 0.0,  0.0,  1.0,                                       0.0 },
//      { 0.0,  0.0,  1.0 / myViewingPlaneDistanceFromViewer,    0.0 }
//                } );

        TransformationMatrix transMat = new TransformationMatrix(new double[][]
                { { 1.0,  0.0,  0.0,                                       0.0 },
                  { 0.0, -1.0,  0.0,                                       0.0 },
                  { 0.0,  0.0,  1.0,                                       0.0 },
                  { 0.0,  0.0, -1.0 / myViewingPlaneDistanceFromViewer,    0.0 },
                } );
        PolygonSet polygonSetToRender = polygonSet.makeTransformedPolygonSet(transMat);
        // PolygonSet polygonSetToRender = polygonSet;
                
        // TODO: Make everything pass Graphics2D

        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < polygonSetToRender.getNumPolygons(); i++) {
            Polygon p = polygonSetToRender.getPolygon(sortedPtrs[i]);
            // TODO: Handle negative z values
            // TODO: Make more efficient by normalizing for z only once per point
            p.draw(g2d, this);
        }
    }

    public void setViewerX(double value) {
        myViewerX = value;
    }

    public void setViewerY(double value) {
        myViewerY = value;
    }

    public void setViewerZ(double value) {
        myViewerZ = value;
    }
    
    public void setViewingPlaneDistanceFromViewer(double value) {
        myViewingPlaneDistanceFromViewer = value;
    }
}
