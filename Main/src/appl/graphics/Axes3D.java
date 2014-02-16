package appl.graphics;

import java.awt.Color;

import javax.swing.JFrame;

public class Axes3D extends Shape3DList {

    /**
     * 
     */
    private static final long serialVersionUID = 2123621975806166902L;

    public Axes3D(double length, double diameter, double explodeAmount, Color color) {
        this(length, diameter, explodeAmount,
             new Color[] { color, color, color, color, color, color });
    }
    
    public Axes3D(double length, double diameter, double explodeAmount, Color[] colors) {
        super();

        double amountToTranslate = (length / 2.0) + explodeAmount;
        add((new RectangularPrism(length,   diameter, diameter, colors[0])).translate( amountToTranslate,                0.0,                0.0));
        add((new RectangularPrism(length,   diameter, diameter, colors[1])).translate(-amountToTranslate,                0.0,                0.0));
        add((new RectangularPrism(diameter,   length, diameter, colors[2])).translate(               0.0,  amountToTranslate,                0.0));
        add((new RectangularPrism(diameter,   length, diameter, colors[3])).translate(               0.0, -amountToTranslate,                0.0));
        add((new RectangularPrism(diameter, diameter,   length, colors[4])).translate(               0.0,                0.0,  amountToTranslate));
        add((new RectangularPrism(diameter, diameter,   length, colors[5])).translate(               0.0,                0.0, -amountToTranslate));
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Testing Axes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        Canvas3D canvas = new Canvas3D();
        frame.add(canvas);
        Axes3D axes = new Axes3D(1000.0, 10.0, 100.0, Color.RED);
        canvas.register(axes);
        frame.setVisible(true);
        frame.repaint();
    }
}
