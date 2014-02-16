package appl.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Viewer3D extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -383797094081007403L;

    public Viewer3D() {
        super();
    }

    public Viewer3D(GraphicsConfiguration gc) {
        super(gc);
     }

    public Viewer3D(String title, GraphicsConfiguration gc) {
        super(title, gc);
     }

    public Viewer3D(String title) throws HeadlessException {
        super(title);
     }

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        Viewer3D viewer = new Viewer3D("Test Viewer");
        viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.setSize(1900, 1200);
        viewer.setLocationRelativeTo(null);
        final Canvas3D canvas = new Canvas3D();
        canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        viewer.setLayout(new BorderLayout());
        viewer.add(canvas, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new GridLayout());
        controlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Control Panel", TitledBorder.CENTER, TitledBorder.TOP));
        viewer.add(controlPanel, BorderLayout.SOUTH);
  
        JPanel viewerInfoPanel = new JPanel();
        viewerInfoPanel.setLayout(new BoxLayout(viewerInfoPanel, BoxLayout.LINE_AXIS));
        viewerInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Viewer Panel", TitledBorder.CENTER, TitledBorder.TOP));

        JPanel viewerCoordinatesPanel = new JPanel();
        viewerCoordinatesPanel.setLayout(new BoxLayout(viewerCoordinatesPanel, BoxLayout.PAGE_AXIS));
        viewerCoordinatesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Viewer Coordinates", TitledBorder.CENTER, TitledBorder.TOP));
        JSliderTextFieldCombo xPositionInput = new JSliderTextFieldCombo("X", -3000, 3000,    0); 
        JSliderTextFieldCombo yPositionInput = new JSliderTextFieldCombo("Y", -3000, 3000,    0); 
        JSliderTextFieldCombo zPositionInput = new JSliderTextFieldCombo("Z", -3000, 3000, 3000); 
        xPositionInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setViewerX(((JSliderTextFieldCombo) e.getSource()).getValue());
            }
        });
        yPositionInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setViewerY(((JSliderTextFieldCombo) e.getSource()).getValue());
            }
        });
        zPositionInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setViewerZ(((JSliderTextFieldCombo) e.getSource()).getValue());
            }
        });
        viewerCoordinatesPanel.add(xPositionInput);
        viewerCoordinatesPanel.add(yPositionInput);
        viewerCoordinatesPanel.add(zPositionInput);
        viewerInfoPanel.add(viewerCoordinatesPanel);

        JPanel viewerTargetPanel = new JPanel();
        viewerTargetPanel.setLayout(new BoxLayout(viewerTargetPanel, BoxLayout.PAGE_AXIS));
        viewerTargetPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Target Coordinates", TitledBorder.CENTER, TitledBorder.TOP));
        JSliderTextFieldCombo xTargetInput = new JSliderTextFieldCombo("X", -1000, 1000); 
        JSliderTextFieldCombo yTargetInput = new JSliderTextFieldCombo("Y", -1000, 1000); 
        JSliderTextFieldCombo zTargetInput = new JSliderTextFieldCombo("Z", -1000, 1000); 
        viewerTargetPanel.add(xTargetInput);
        viewerTargetPanel.add(yTargetInput);
        viewerTargetPanel.add(zTargetInput);
        viewerInfoPanel.add(viewerTargetPanel);

        JPanel viewerViewingPlanePanel = new JPanel();
        viewerViewingPlanePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Viewing Plane", TitledBorder.CENTER, TitledBorder.TOP));
        JSliderTextFieldCombo viewingPlaneDistanceInput = new JSliderTextFieldCombo("Viewing Plane Distance", 10, 3000, 500); 
        viewingPlaneDistanceInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setViewingPlaneDistanceFromViewer(((JSliderTextFieldCombo) e.getSource()).getValue());
            }
        });
        viewerViewingPlanePanel.add(viewingPlaneDistanceInput);
        viewerInfoPanel.add(viewerViewingPlanePanel);
        
        controlPanel.add(viewerInfoPanel);
        
        viewer.setVisible(true);
        
        RubiksCube3D rubiks = new RubiksCube3D();
        canvas.register(rubiks);
        Axes3D axes = new Axes3D(1000.0, 4.0, 700.0, 
                                 new Color[] { Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE });
        canvas.register(axes);
        
        canvas.setVisible(true);

        int delay = 10;
        double t = 0.0;
        Thread.sleep(delay);
        rubiks.rotate(Math.PI / 6, Math.PI/6, 0.0);
        canvas.repaint();
        // rubiks.moveFace(5, 1);
        Thread.sleep(1000);
        
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
                    canvas.repaint();
                }
                rubiks.permuteCubeSlice(face, slice, direction);
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(2.0 * Math.PI / 1000, 0.0, 0.0);
                t += 0.01;
                Thread.sleep(delay);
                canvas.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(0.0, 2.0 * Math.PI / 1000, 0.0);
                t += 0.01;
                Thread.sleep(delay);
                canvas.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(0.0, 0.0, 2.0 * Math.PI / 1000);
                t += 0.01;
                Thread.sleep(delay);
                canvas.repaint();
            }

            for (int i = 0; i < 1000; i++) {
                rubiks.rotate(2.0 * Math.PI / 1000, 2.0 * Math.PI / 1000, 2.0 * Math.PI / 1000);
                t += 0.01;
                Thread.sleep(delay);
                canvas.repaint();
            }

            for (int j = numMoves - 1; j >= 0; j--) {
                int face = moveFaces[j];
                int slice = moveSlices[j];
                int direction = -moveDirs[j];
                for (int i = 0; i < 100; i++) {
                    rubiks.rotateSlice(face, slice, (Math.PI / (200 * direction)));
                    Thread.sleep(delay / 10);
                    canvas.repaint();
                }
                rubiks.permuteCubeSlice(face, slice, direction);
            }
            Thread.sleep(2000);
        }
    }

}
