package appl.graphics;

import java.awt.Color;

import javax.swing.JFrame;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

import appl.graphics.polygons.Polygon;
import appl.graphics.polygons.PolygonProperties;
import appl.graphics.polygons.PolygonSet;

public class RectangularPrism extends SimpleShape3D {

    

    /*
     *              length
     *       2 ---------------------- 3
     *        /|                   /|
     *       / |                  / |
     *      /  |                 /  | height
     *     /   |                /   |
     *  6 -----|---------------- 7  |
     *    |  0 ----------------+----- 1
     *    |   /                |   /
     *    |  /                 |  / width
     *    | /                  | /
     *    |/                   |/
     *  4 ---------------------- 5
     *  
     */
    
    
    public RectangularPrism(double length, double height, double width) {
        this(length, height, width, Color.BLACK);
    }

    public RectangularPrism(double length, double height, double width, Color color) {
        this(length, height, width, new Color[] { color, color, color, color, color, color });
    }
    
    public RectangularPrism(double length, double height, double width, Color[] colors) {
        super();
        double halfLength = length / 2.0;
        double halfHeight = height / 2.0;
        double halfWidth = width / 2.0;
        myPoints = new Array2DRowRealMatrix(4, 8);
        myPoints.setColumn(0, new double[] { -halfLength, -halfHeight, -halfWidth, 1.0 });
        myPoints.setColumn(1, new double[] {  halfLength, -halfHeight, -halfWidth, 1.0 });
        myPoints.setColumn(2, new double[] { -halfLength,  halfHeight, -halfWidth, 1.0 });
        myPoints.setColumn(3, new double[] {  halfLength,  halfHeight, -halfWidth, 1.0 });
        myPoints.setColumn(4, new double[] { -halfLength, -halfHeight,  halfWidth, 1.0 });
        myPoints.setColumn(5, new double[] {  halfLength, -halfHeight,  halfWidth, 1.0 });
        myPoints.setColumn(6, new double[] { -halfLength,  halfHeight,  halfWidth, 1.0 });
        myPoints.setColumn(7, new double[] {  halfLength,  halfHeight,  halfWidth, 1.0 });
        
        Color[] faceColors = colors;
        int [][] faces = new int[][] {
                { 0, 1, 3, 2 },
                { 0, 1, 5, 4 },
                { 0, 2, 6, 4 },
                { 1, 3, 7, 5 },
                { 2, 3, 7, 6 },
                { 4, 5, 7, 6 }
        };

        Polygon[] polys = new Polygon[6];
        for (int i = 0; i < 6; i++) {
            polys[i] = new Polygon(myPoints, faces[i], new PolygonProperties(faceColors[i]));
        }
        myPolygons = new PolygonSet(myPoints, polys);
    }
}
