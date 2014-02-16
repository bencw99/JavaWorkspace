package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.math.genetics.PermutationChromosome;

public class RubiksCube3D extends CompoundShape3DArray {

    /**
     * 
     */
    private static final long serialVersionUID = 6511468714173051641L;
    private SimpleShape3D[][][] myCubes;
    private Color[] myColors = new Color[] { Color.WHITE, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE };

/*
      x  y      x  y
     [0][0] -> [2][0]
     [1][0] -> [2][1]
     [2][0] -> [2][2]
     [0][1] -> [1][0]
     [1][1] -> [1][1]
     [2][1] -> [1][2]
     [0][2] -> [0][0]
     [1][2] -> [0][1]
     [2][2] -> [0][2]
     
      z  x      z  x
     [0][0] -> [2][0]
     [1][0] -> [2][1]
     [2][0] -> [2][2]
     [0][1] -> [1][0]
     [1][1] -> [1][1]
     [2][1] -> [1][2]
     [0][2] -> [0][0]
     [1][2] -> [0][1]
     [2][2] -> [0][2]
     
      y  z      y  z
     [0][0] -> [2][0]
     [1][0] -> [2][1]
     [2][0] -> [2][2]
     [0][1] -> [1][0]
     [1][1] -> [1][1]
     [2][1] -> [1][2]
     [0][2] -> [0][0]
     [1][2] -> [0][1]
     [2][2] -> [0][2]
*/            
        
            
    
    
    public RubiksCube3D() {
        this(200.0, 0.0);
    }

    public RubiksCube3D(double cubeLength, double explodeLength) {
        super();
        myCubes = new SimpleShape3D[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    Cube3D cube = new Cube3D();
                    for (int face = 0; face < 6; face++) {
                        cube.setColor(face,  myColors[face]);
                    }
                    // Set colors of internal faces to black.
                    if (i <= 1) {
                        cube.setColor(3, Color.BLACK);
                    }
                    if (i >= 1) {
                        cube.setColor(2, Color.BLACK);
                    }
                    if (j <= 1) {
                        cube.setColor(4, Color.BLACK);
                    }
                    if (j >= 1) {
                        cube.setColor(1, Color.BLACK);
                    }
                    if (k <= 1) {
                        cube.setColor(5, Color.BLACK);
                    }
                    if (k >= 1) {
                        cube.setColor(0, Color.BLACK);
                    }
                    
                    cube.scale(cubeLength);
                    cube.translate((cubeLength + explodeLength) * (i - 1), 
                                   (cubeLength + explodeLength) * (j - 1), 
                                   (cubeLength + explodeLength) * (k - 1));
                    myCubes[i][j][k] = cube;
                    this.add(cube);                   // Add to Shape3DCollectionArray
                }   
            }
        }
    }

/**
     * Rotates face <face> clockwise <n> times (or counterclockwise <n> times if n is negative).
     *
     *   Face 0: k = 0
     *   Face 1: j = 0
     *   Face 2: i = 0
     *   Face 3: i = 2
     *   Face 4: j = 2
     *   Face 5: k = 2
     *
     * @param face
     * @param n
     */
    public void moveFace(int face, int n) {
        int iLower, iUpper, jLower, jUpper, kLower, kUpper;
        double vecX, vecY, vecZ;
        iLower = jLower = kLower = 0;
        iUpper = jUpper = kUpper = 3;
        vecX = vecY = vecZ = 0.0;
        switch (face) {
            case 0:
                kUpper = 1;
                vecZ = 1.0;
                break;
            case 1:
                jUpper = 1;
                vecY = 1.0;
                break;
            case 2:
                iUpper = 1;
                vecX = 1.0;
                break;
            case 3:
                iLower = 2;
                vecX = -1.0;
                break;
            case 4:
                jLower = 2;
                vecY = -1.0;
                break;
            case 5:
                kLower = 2;
                vecZ = -1.0;
                break;
        }
            
        int numIncrements = 250;
        int delay = 1;
        for (int cnt = 0; cnt < n; cnt++) {
            for (int cnt1 = 0; cnt1 < numIncrements; cnt1++) {
                for (int i = iLower; i < iUpper; i++) {
                    for (int j = jLower; j < jUpper; j++) {
                        for (int k = kLower; k < kUpper; k++) {
                            myCubes[i][j][k].rotate(vecX, vecY, vecZ, 0.0, 0.0, 0.0, (Math.PI / 2.0) / numIncrements);
                        }
                    }
                }
            }
        }
        
        // Reposition cubes in array
        
        
    }


    
    
    /**
     * Rotates face with center <color> clockwise <n> times (or counterclockwise <n> times if n is negative)
     * 
     * @param color
     * @param n
     * @throws Exception 
     */
    public void moveFace(Color color, int n) throws Exception {
        for (int i = 0; i < myColors.length; i++) {
            if (myColors[i] == color) {
                moveFace(i, n);
            }
        }
        throw new Exception();
    }
    

    /**
     * Rotates slice of cube clockwise by the given <angle>, where angle follows the right hand rule with the thumb
     * pointing out for the face (i.e., a counterclockwise turn for a positive angle if looking towards the face).  The
     * slice that is rotated is perpendicular to the vector coming out of the face and is the face itself if <slice> is 0,
     * the middle layer if <slice> is 1, and the far layer (opposite the face) if <slice> is 2;
     * 
     *   Face 0: k = 0
     *   Face 1: j = 0
     *   Face 2: i = 0
     *   Face 3: i = 2
     *   Face 4: j = 2
     *   Face 5: k = 2
     * 
     * @param face
     * @param angle
     */
    public void rotateSlice(int face, int slice, double angle) {
        assert(face >=0 && face <= 5);
        assert(slice >= 0 && slice <= 2);
        int iLower, iUpper, jLower, jUpper, kLower, kUpper;
        double vecX, vecY, vecZ;
        iLower = jLower = kLower = 0;
        iUpper = jUpper = kUpper = 3;
        vecX = vecY = vecZ = 0.0;
        switch (face) {
            case 0:
                kLower = slice;
                kUpper = slice + 1;
                vecZ = 1.0;
                break;
            case 1:
                jLower = slice;
                jUpper = slice + 1;
                vecY = 1.0;
                break;
            case 2:
                iLower = slice;
                iUpper = slice + 1;
                vecX = 1.0;
                break;
            case 3:
                iLower = 2 - slice;
                iUpper = iLower + 1;
                vecX = -1.0;
                break;
            case 4:
                jLower = 2 - slice;
                jUpper = jLower + 1;
                vecY = -1.0;
                break;
            case 5:
                kLower = 2 - slice;
                kUpper = kLower + 1;
                vecZ = -1.0;
                break;
        }
            
        for (int i = iLower; i < iUpper; i++) {
            for (int j = jLower; j < jUpper; j++) {
                for (int k = kLower; k < kUpper; k++) {
                    myCubes[i][j][k].rotate(vecX, vecY, vecZ, 0.0, 0.0, 0.0, angle);
                }
            }
        }
    }

    /**
     * Rotates face <face> clockwise by the given <angle>, where angle follows the right hand rule with the thumb
     * pointing out for the face (i.e., a counterclockwise turn for a positive angle if looking towards the face).
     * 
     *   Face 0: k = 0
     *   Face 1: j = 0
     *   Face 2: i = 0
     *   Face 3: i = 2
     *   Face 4: j = 2
     *   Face 5: k = 2
     * 
     * @param face
     * @param angle
     */
    public void rotateFace(int face, double angle) {
        rotateSlice(face, 0, angle);
    }

    /**
     * Rotates middle slice of cube clockwise by the given <angle>, where angle follows the right hand rule with the thumb
     * pointing out for the face (i.e., a counterclockwise turn for a positive angle if looking towards the face).  The
     * slice that is rotated is the middle slice perpendicular to the vector coming out of the face.
     * 
     *   Face 0: k = 0
     *   Face 1: j = 0
     *   Face 2: i = 0
     *   Face 3: i = 2
     *   Face 4: j = 2
     *   Face 5: k = 2
     * 
     * @param face
     * @param angle
     */
    public void rotateMiddle(int face, double angle) {
        rotateSlice(face, 1, angle);        
    }

    /**
     * Reassigns pointers to cube objects after a rotation of a slice of the cube clockwise by the given <angle>, where angle follows the right hand rule with the thumb
     * pointing out for the face (i.e., a counterclockwise turn for a positive angle if looking towards the face).  The
     * slice that is rotated is perpendicular to the vector coming out of the face and is the face itself if <slice> is 0,
     * the middle layer if <slice> is 1, and the far layer (opposite the face) if <slice> is 2;
     * 
     * @param face
     * @param slice
     */
    public void permuteCubeSlice(int face, int slice) {
        SimpleShape3D[][][] newCubes = new SimpleShape3D[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (face) {
                        case 0:
                            if (k == slice) {
                                newCubes[i][j][k] = myCubes[j][2-i][k];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                        case 1:
                            if (j == slice) {
                                newCubes[i][j][k] = myCubes[2-k][j][i];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                        case 2:
                            if (i == slice) {
                                newCubes[i][j][k] = myCubes[i][k][2-j];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                        case 3:
                            if (i == (2 - slice)) {
                                newCubes[i][j][k] = myCubes[i][2-k][j];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                        case 4:
                            if (j == (2 - slice)) {
                                newCubes[i][j][k] = myCubes[k][j][2-i];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                        case 5:
                            if (k == (2 - slice)) {
                                newCubes[i][j][k] = myCubes[2-j][i][k];
                            }
                            else {
                                newCubes[i][j][k] = myCubes[i][j][k];
                            }
                            break;
                    }
                }
            }
        }
        myCubes = newCubes;        
        
    }

    public void permuteCubeSlice(int face, int slice, int n) {
        assert(face >= 0 && face <= 5);
        assert (n >= -3 && n <= 3);
        if (n < 0) {
            n = n + 4;
        }
        for (int i = 0; i < n; i++) {
            permuteCubeSlice(face, slice);
        }
    }

    public void permuteCubeFace(int face) {
        assert(face >= 0 && face <= 5);
        permuteCubeSlice(face, 0);
    }
    
    public void permuteCubeFace(int face, int n) {
        assert(face >= 0 && face <= 5);
        assert (n >= -3 && n <= 3);
        permuteCubeSlice(face, 0, n);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Testing Rubik's Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        Canvas3D canvas = new Canvas3D();
        frame.add(canvas);
        RubiksCube3D rubiks = new RubiksCube3D();
        canvas.register(rubiks);
        frame.setVisible(true);
        
        int delay = 10;
        double deltaX, deltaY;
        double a = 0.0;
        double b = 0.001;
        double t = 0.0;
        Thread.sleep(delay);
        rubiks.rotate(Math.PI / 6, Math.PI/6, 0.0);
        frame.repaint();
        // rubiks.moveFace(5, 1);
        Thread.sleep(1000);
//        for (int face = 0; face < 6; face++) {
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateFace(face, (Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 1, 1);
//            Thread.sleep(500);
//            
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateFace(face, (-Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 1, -1);
//            Thread.sleep(500);
//
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateMiddle(face, (Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 1, 1);
//            Thread.sleep(500);
//
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateMiddle(face, (-Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 1, -1);
//            Thread.sleep(500);
//
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateSlice(face, 2, (Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 2, 1);
//            Thread.sleep(500);
//
//            for (int i = 0; i < 100; i++) {
//                rubiks.rotateSlice(face, 2, (-Math.PI / 200));
//                Thread.sleep(delay / 10);
//                frame.repaint();
//            }
//            rubiks.permuteCubeSlice(face, 2, -1);
//            Thread.sleep(500);
//        }
//        Thread.sleep(1000);
        
        Random random = new Random();
        while (true) {
            int numMoves = 100;
            int moveFaces[]  = new int[numMoves];
            int moveSlices[] = new int[numMoves];
            int moveDirs[]   = new int[numMoves];
            for (int j = 0; j < numMoves; j++) {
                int face = random.nextInt(6);
                int direction = random.nextInt(2) * 2 - 1;
                int slice = random.nextInt(3);
                moveFaces[j] = face;
                moveSlices[j] = slice;
                moveDirs[j] = direction;
                for (int i = 0; i < 100; i++) {
                    rubiks.rotateSlice(face, slice, (Math.PI / (200 * direction)));
                    Thread.sleep(delay / 10);
                    frame.repaint();
                }
                rubiks.permuteCubeSlice(face, slice, direction);
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(2.0 * Math.PI / 1000, 0.0, 0.0);
                deltaX = a * Math.exp(b * t) * Math.cos(t);
                deltaY = a * Math.exp(b * t) * Math.sin(t);
                rubiks.translate(deltaX, deltaY, 0);
                t += 0.01;
                Thread.sleep(delay);
                frame.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(0.0, 2.0 * Math.PI / 1000, 0.0);
                deltaX = a * Math.exp(b * t) * Math.cos(t);
                deltaY = a * Math.exp(b * t) * Math.sin(t);
                rubiks.translate(deltaX, deltaY, 0);
                t += 0.01;
                Thread.sleep(delay);
                frame.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(0.0, 0.0, 2.0 * Math.PI / 1000);
                deltaX = a * Math.exp(b * t) * Math.cos(t);
                deltaY = a * Math.exp(b * t) * Math.sin(t);
                rubiks.translate(deltaX, deltaY, 0);
                t += 0.01;
                Thread.sleep(delay);
                frame.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(2.0 * Math.PI / 1000, 2.0 * Math.PI / 1000, 2.0 * Math.PI / 1000);
                deltaX = a * Math.exp(b * t) * Math.cos(t);
                deltaY = a * Math.exp(b * t) * Math.sin(t);
                rubiks.translate(deltaX, deltaY, 0);
                t += 0.01;
                Thread.sleep(delay);
                frame.repaint();
            }

            for (int j = numMoves - 1; j >= 0; j--) {
                int face = moveFaces[j];
                int slice = moveSlices[j];
                int direction = -moveDirs[j];
                for (int i = 0; i < 100; i++) {
                    rubiks.rotateSlice(face, slice, (Math.PI / (200 * direction)));
                    Thread.sleep(delay / 10);
                    frame.repaint();
                }
                rubiks.permuteCubeSlice(face, slice, direction);
            }

            Thread.sleep(2000);
        }
    }

}
