package appl.graphics.lunarlander;

import java.awt.Color;

import javax.swing.JFrame;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

import appl.graphics.Canvas3D;
import appl.graphics.SimpleShape3D;
import appl.graphics.polygons.Polygon;
import appl.graphics.polygons.PolygonProperties;
import appl.graphics.polygons.PolygonSet;

public class Terrain3D extends SimpleShape3D {
        
    int myNumPointsX;
    int myNumPointsZ;
    int myNumPolygonsX;
    int myNumPolygonsZ;
    int myStartX;
    int myEndX;
    int myStartZ;
    int myEndZ;
    
    public Terrain3D(Terrain terrain) {
        this(terrain, 1.0);
    }

    public Terrain3D(Terrain terrain, double myScaleFactorXZ) {
        super();
        myNumPointsX = terrain.getXLength();
        myNumPointsZ = terrain.getZLength();
        myNumPolygonsX = myNumPointsX - 1;
        myNumPolygonsZ = myNumPointsZ - 1;
        myStartX = terrain.getXStart();
        myEndX = terrain.getXEnd();
        myStartZ = terrain.getZStart();
        myEndZ = terrain.getZEnd();
        System.out.println(myStartX);
        System.out.println(myEndX);
        System.out.println(myNumPointsX);
        assert((myEndX - myStartX + 1) == myNumPointsX);
        assert((myEndZ - myStartZ + 1) == myNumPointsZ);
        
        // Create points array with points for every point in the terrain
        myPoints = new Array2DRowRealMatrix(4, myNumPointsX * myNumPointsZ);
        for (int x = myStartX; x <= myEndX; x++) {
            for (int z = myStartZ; z <= myEndZ; z++) {
                int column = getPointsIndex(x, z);
                myPoints.setColumn(column, new double[] 
                    {   myScaleFactorXZ * x,
                        terrain.get(x, z),
                        myScaleFactorXZ * z,
                        1.0
                    });
            }
        }

        // Create polygons
        Polygon[] polys = new Polygon[myNumPolygonsX * myNumPolygonsZ];
        // Note that <myStart> and <myEnd> give the starting and ending point references, so the references for the
        // polygons go from <myStart> to (<myEnd> - 1).
        for (int x = myStartX; x < myEndX; x++) {
            System.out.println(x);
            for (int z = myStartZ; z < myEndZ; z++) {
                int[] cornerPointIndices = new int[] {
                        getPointsIndex(    x,     z),
                        getPointsIndex(x + 1,     z),
                        getPointsIndex(x + 1, z + 1),
                        getPointsIndex(    x, z + 1),
                };
                
                // TODO: Add constructors to allow this to be specified better
                // TODO: Add other properties to PolygonProperties
                Polygon polygon = new Polygon(myPoints, cornerPointIndices, new PolygonProperties(Color.BLACK));
                // TODO: Scale color depending on max height
                double colorFactor = polygon.getCenter()[1] / terrain.getMaxHeight();
                Color color = new Color((float) colorFactor, (float) colorFactor, (float) colorFactor); 
                polygon.setColor(color);
                polys[getPolygonIndex(x,  z)] = polygon;
            }
        }
    
        myPolygons = new PolygonSet(myPoints, polys);
    }
    
    private final int getPointsIndex(int x, int z) {
        return (z - myStartZ) * myNumPointsX + (x - myStartX); 
    }

    private final int getPolygonIndex(int x, int z) {
        return (z - myStartZ) * myNumPolygonsX + (x - myStartX);    
    }
    
    public static void main(String[] args) throws InterruptedException {
        Terrain terrain = new Terrain(101, 101, 500, 50.0, 50, 50);
        for (int i = 0; i < 10; i++) {
            terrain.smoothOnePass();
        }
        Terrain3D terrain3d = new Terrain3D(terrain, 10.0);

        JFrame frame = new JFrame("Testing Rubik's Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        Canvas3D canvas = new Canvas3D();
        frame.add(canvas);
        canvas.register(terrain3d);
        frame.setVisible(true);
        
        int delay = 10;
        for (int i = 0; i < 1000; i++) {
            terrain3d.rotate((Math.PI / 3.0) / 1000, 0.0, 0.0);
            Thread.sleep(delay);
            frame.repaint();
        }


        
    }

}
