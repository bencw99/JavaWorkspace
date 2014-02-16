package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

import appl.graphics.polygons.Polygon;
import appl.graphics.polygons.PolygonProperties;
import appl.graphics.polygons.PolygonSet;

public class Cube3D extends SimpleShape3D {

    

    /*
     *       2 ----------------- 3
     *        /|              /|
     *       / |             / |
     *      /  |            /  |
     *     /   |           /   |
     *  6 -----|----------- 7  |
     *    |  0 -----------|----- 1
     *    |   /           |   /
     *    |  /            |  /
     *    | /             | /
     *    |/              |/
     *  4 ----------------- 5
     *  
     */
    
    
    public Cube3D() {
        super();
        myPoints = new Array2DRowRealMatrix(4, 8);
        myPoints.setColumn(0, new double[] { -0.5, -0.5, -0.5, 1.0 });
        myPoints.setColumn(1, new double[] {  0.5, -0.5, -0.5, 1.0 });
        myPoints.setColumn(2, new double[] { -0.5,  0.5, -0.5, 1.0 });
        myPoints.setColumn(3, new double[] {  0.5,  0.5, -0.5, 1.0 });
        myPoints.setColumn(4, new double[] { -0.5, -0.5,  0.5, 1.0 });
        myPoints.setColumn(5, new double[] {  0.5, -0.5,  0.5, 1.0 });
        myPoints.setColumn(6, new double[] { -0.5,  0.5,  0.5, 1.0 });
        myPoints.setColumn(7, new double[] {  0.5,  0.5,  0.5, 1.0 });
        
        Color[] faceColors = new Color[] { Color.WHITE, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE };
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
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Testing Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        Canvas3D canvas = new Canvas3D();
        frame.add(canvas);
        frame.setVisible(true);
        Cube3D cube = new Cube3D();
        cube.scale(200);
        frame.repaint();
        canvas.register(cube);
        frame.repaint();
        int delay = 3;
        double deltaX, deltaY;
        double a = 0.0;
        double b = 0.001;
        double t = 0.0;
        cube.rotate(Math.PI / 6, Math.PI/6, 0.0);
        for (int i = 0; i < 1000; i++) {
            cube.rotate(Math.PI / 1000, 0.0, 0.0);
            deltaX = a * Math.exp(b * t) * Math.cos(t);
            deltaY = a * Math.exp(b * t) * Math.sin(t);
            cube.translate(deltaX, deltaY, 0);
            t += 0.01;
            Thread.sleep(delay);
            frame.repaint();
        }
        for (int i = 0; i < 1000; i++) {
            cube.rotate(0.0, Math.PI / 1000, 0.0);
            deltaX = a * Math.exp(b * t) * Math.cos(t);
            deltaY = a * Math.exp(b * t) * Math.sin(t);
            cube.translate(deltaX, deltaY, 0);
            t += 0.01;
            Thread.sleep(delay);
            frame.repaint();
        }

        for (int i = 0; i < 1000; i++) {
            cube.rotate(0.0, 0.0, Math.PI / 1000);
            deltaX = a * Math.exp(b * t) * Math.cos(t);
            deltaY = a * Math.exp(b * t) * Math.sin(t);
            cube.translate(deltaX, deltaY, 0);
            t += 0.01;
            Thread.sleep(delay);
            frame.repaint();
        }

        for (int i = 0; i < 1000; i++) {
            cube.rotate(Math.PI / 1000, Math.PI / 1000, Math.PI / 1000);
            deltaX = a * Math.exp(b * t) * Math.cos(t);
            deltaY = a * Math.exp(b * t) * Math.sin(t);
            cube.translate(deltaX, deltaY, 0);
            t += 0.01;
            Thread.sleep(delay);
            frame.repaint();
        }

//        for (int i = 0; i < 1000; i++) {
//            Thread.sleep(50);
//            frame.repaint();
//        }
 
    
    }
}
