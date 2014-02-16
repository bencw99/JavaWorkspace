package appl.graphics.polygons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.math.linear.RealMatrix;

import appl.graphics.Canvas3D;

public class Polygon {
    
    private int                 myNumPoints;          // Number of points in polygon
    private RealMatrix          myMasterPoints;       // Master list of points from which polygon points are drawn.  Each column of the matrix specifies a point.
    private int[]               myPointIndices;       // List of points in polygon (each point is specified as the index of the column corresponding to the point.
    private PolygonProperties   myProperties;

    public Polygon(RealMatrix points, int[] indices, PolygonProperties properties) {
        myNumPoints = indices.length;
        myMasterPoints = points;
        for (int i : indices) {
            assert(i >= 0 && i < myMasterPoints.getColumnDimension());
        }
        myPointIndices = indices;
        myProperties = properties;
    }

    public void draw(Graphics2D g2d, Canvas3D canvas3d) {
        int numPoints = getNumPoints();
        int[] x = new int[numPoints];
        int[] y = new int[numPoints];
        for (int j = 0; j < numPoints; j++) {
            // TODO: Round
            x[j] = (int) ( (getMasterPoints().getEntry(0, getPointIndex(j))) / (getMasterPoints().getEntry(3, getPointIndex(j))) +
                    canvas3d.getSize().getWidth() / 2.0 );
            // TODO: Get using allowed methods
            y[j] = (int) ( (getMasterPoints().getEntry(1, getPointIndex(j))) / (getMasterPoints().getEntry(3, getPointIndex(j))) +
                    canvas3d.getSize().getHeight() / 2.0 );
            if ((x[j] < 0) || (x[j] > canvas3d.getSize().getWidth()) ||
                (y[j] < 0) || y[j] > canvas3d.getSize().getHeight()) {
                // System.out.println(String.format("%d %d", x[j], y[j]));
                // System.out.println();
            }
        }
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(getColor());
        g2d.fillPolygon(x, y, numPoints);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(x, y, numPoints);
    }
    
    public double[] getCenter() {
        double[] center = new double[4];
        for (int i = 0; i < myNumPoints; i++) {
            for (int j = 0; j < 4; j++) {
                center[j] += myMasterPoints.getEntry(j,  myPointIndices[i]);
            }
            // TODO: Fix the treatment of the homogeneous portion (which right now should work because they are all 1).
        }
        for (int j = 0; j < 4; j++) {
            center[j] /= myNumPoints;
        }
        
        return center;
    }

    RealMatrix getMasterPoints() {
        return myMasterPoints;
    }

    public void setColor(Color color) {
        myProperties.setColor(color);
    }

    public Color getColor() {
        return myProperties.getColor();
    }

    public int getPointIndex(int i) {
        assert (i >= 0 && i < myNumPoints);
        return myPointIndices[i];
    }

    public Polygon shallowCopy() {
        return new Polygon(myMasterPoints, myPointIndices, myProperties);
    }

    void setMasterPoints(RealMatrix masterPoints) {
        myMasterPoints = masterPoints;
    }

    public int getNumPoints() {
        return myNumPoints;
    }
}
