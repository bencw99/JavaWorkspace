package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas2D extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 6701576899423226442L;

    private ArrayList<Shape2D> myShapes = new ArrayList<Shape2D>();

    public class Listener implements KeyListener {

        Shape2D myShape;
        
        public Listener(Shape2D shape) {
            myShape = shape;    
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP) {
                myShape.move(0,  -1);
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                myShape.move(0, 1);
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                myShape.move(1, 0);
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                myShape.move(-1, 0);
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }

    public Canvas2D() {
    }
    
    
    public void registerShape(Shape2D shape) {
        myShapes.add(shape);
        
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape2D shape : myShapes) {
            shape.draw(g);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Moving Rectangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        Canvas2D canvas = new Canvas2D();
        frame.add(canvas);
        frame.setVisible(true);
        Rectangle rectangle0 = new Rectangle();
        canvas.registerShape(rectangle0);
        Rectangle rectangle1 = new Rectangle();
        rectangle1.move(1000, 0);
        rectangle1.setColor(Color.RED);
        canvas.registerShape(rectangle1);

        Listener listener = canvas.new Listener(rectangle1);
        frame.addKeyListener(listener);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            rectangle0.move(1, 1);
            int factor = (i / 20) % 2 * 2 - 1;
            rectangle0.resize(factor, factor);
            if (i % 10 == 0) {
                rectangle0.resize(1, 1);
            }
            frame.repaint();
        }
    }
}
