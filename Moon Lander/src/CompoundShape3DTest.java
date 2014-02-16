package appl.graphics;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompoundShape3DTest {

    @Test
    public void testSimpleShape3D() {
        Cube3D cube = new Cube3D();
        double[] center;
        center = cube.getCenter();
        assertArrayEquals(new double[] {0.0, 0.0, 0.0, 1.0}, center, 0.000000001);
        cube.scale(200.0);
        
        cube.translate(150, 200, 300);
        center = cube.getCenter();
        assertArrayEquals(new double[] {150.0, 200.0, 300.0, 1.0}, center, 0.000000001);
    }

    @Test
    public void testCompoundShape3D() {
        RubiksCube3D cube = new RubiksCube3D();
        double[] center;
        center = cube.getCenter();
        assertArrayEquals(new double[] {0.0, 0.0, 0.0, 1.0}, center, 0.000000001);
        cube.translate(150, 200, 300);
        center = cube.getCenter();
        assertArrayEquals(new double[] {150.0, 200.0, 300.0, 1.0}, center, 0.000000001);
    }
    
    @Test
    public void testMultiLevelCompoundShape3D() {
        CompoundShape3DArray shapeRoot = new CompoundShape3DArray();

        shapeRoot.add(new RubiksCube3D(200.0, 0.0).translate( 500.0,  500.0, 0.0));
        shapeRoot.add(new RubiksCube3D(200.0, 0.0).translate(-500.0,  500.0, 0.0));
        shapeRoot.add(new RubiksCube3D(200.0, 0.0).translate( 500.0, -500.0, 0.0));
        shapeRoot.add(new RubiksCube3D(200.0, 0.0).translate(-500.0, -500.0, 0.0));
        
        double[] center;
        center = shapeRoot.getCenter();
        assertArrayEquals(new double[] {0.0, 0.0, 0.0, 1.0}, center, 0.000000001);
        shapeRoot.translate(150, 200, 300);
        center = shapeRoot.getCenter();
        assertArrayEquals(new double[] {150.0, 200.0, 300.0, 1.0}, center, 0.000000001);
        
        int i = 0;
        for (Shape3D shape : shapeRoot) {
            center = shape.getCenter();
            // getCenter returns the center of the sub-shape within the frame of reference of its parent shape, so the translation here
            // has no effect on the center
            assertArrayEquals(new double[] {500.0 * ( (i % 2) - 0.5) * (-2.0), 500.0 * ( (i / 2) - 0.5) * (-2.0), 0.0, 1.0}, 
                    center,
                    0.000000001);

            double[] center0;
            int j = 0;
            for (Shape3D subShape : shape) {
                int x = j / 9;
                int y = (j % 9) / 3;
                int z = j % 3;
                center0 = subShape.getCenter();
                assertArrayEquals(new double[] {(x - 1) * 200.0, (y - 1) * 200.0, (z - 1) * 200.0, center[3]},
                                  center0,
                                  0.000000001);
                j++;
            }
            i++;
        }
    }
    
    
}
