package graphics.panel;

import graphics.polygon.*;
import graphics.projection.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import component.terrain.*;

public class DisplayPanel extends JPanel implements KeyListener
{
	public static final int panelWidth = 500;
	public static final int panelHeight = 500;
	private static Terrain terrain;
	private static Polygon3D poly;
	private static Polygon3D poly2;
	private static Polygon3D poly3;
	private static View view;
	
	public static void main(String[]args)
	{

        terrain = new Terrain(3, 2);
        terrain.init();
		
        Point3D[] points = new Point3D[4];
        Point3D[] points2 = new Point3D[4]; 
        Point3D[] points3 = new Point3D[4];
        
        points[0] = new Point3D(200, 100, 300);
        points[1] = new Point3D(200, 100, 200);
        points[2] = new Point3D(250, 170, 200);
        points[3] = new Point3D(250, 170, 300);
        
        points2[0] = new Point3D(300, 100, 400);
        points2[1] = new Point3D(200, 100, 200);
        points2[2] = new Point3D(250, 170, 200);
        points2[3] = new Point3D(350, 170, 400);
        
        points3[0] = new Point3D(250, 170, 300);
        points3[1] = new Point3D(250, 170, 200);
        points3[2] = new Point3D(300, 100, 400);
        points3[3] = new Point3D(300, 270, 400);
        
		poly = new Polygon3D(points, Color.GRAY);
		poly2 = new Polygon3D(points2, Color.LIGHT_GRAY);
		poly3 = new Polygon3D(points3, Color.DARK_GRAY);
		
        view = new View(new Point3D(7, 0, 5), new Point3D(10, 10, 10), -Math.PI/3);
        
        JFrame frame = new JFrame();
        frame.setSize(panelWidth,panelHeight);
        frame.setTitle("Terrain");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.addKeyListener(panel);
        frame.setVisible(true);

        for(double i = 0; i < 100; i += 0.0005)
        {	
        	try
			{
				Thread.sleep(1);
			} 
        	catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	Point3D viewPoint = view.getViewPoint();
        	frame.repaint();
        }
	}
	
	public void paintComponent(Graphics graphics)
	{
		terrain.paint(graphics);
//		poly.getProjection(view).paint(graphics);
//		poly2.getProjection(view).paint(graphics);
//		poly3.getProjection(view).paint(graphics);
	}
	
	public static View getView()
	{
		return view;
	}
	
	public void keyPressed(KeyEvent e)
	{
		char code = (char)e.getKeyCode();
		
		if(code == '1')
		{
        	view.translateView(0.5, 0, 0);
		}
		
		if(code == '2')
		{
        	view.translateView(0, 0.5, 0);
		}
		
		if(code == '3')
		{
        	view.translateView(0, 0, 2.5);
		}
		
		if(code == '4')
		{
        	view.translateView(-0.5, 0, 0);
		}
		
		if(code == '5')
		{
        	view.translateView(0, -0.5, 0);
		}
		
		if(code == '6')
		{
        	view.translateView(0, 0, -2.5);
		}
		
		if(code == '7')
		{
			view.zoom(1.2);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

}
