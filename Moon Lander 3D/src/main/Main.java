package main;

import game.terrain.*;
import graphics.polygon.*;
import graphics.projection.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Main extends JPanel implements KeyListener, MouseMotionListener
{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private static boolean[] navigator = new boolean[10];
	private static Polygon3D polygon;
	private static View view;
    private static JFrame frame = new JFrame();
	
	public static void main(String[]args)
	{
        view = new View(new Point3D(5000, 5000, 7500), new Point3D(5000, 5000, 100), 0, WIDTH, HEIGHT);
//        view = new View(new Point3D(-1000, -12000, 9000), new Point3D(0, 5000, 4500), Math.PI);
        
        double[] xCoords = {0, 10000, 10000, 0};
        double[] yCoords = {0, 0, 10000, 10000};
        double[] zCoords = {0, 0, 0, 0};
        polygon = new Polygon3D(xCoords, yCoords, zCoords, 4);
        
        frame.setSize(WIDTH,HEIGHT);
        frame.setTitle("Terrain");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Main panel = new Main();
        frame.add(panel);
        frame.addKeyListener(panel);
        frame.addMouseMotionListener(panel);
        frame.setVisible(true);

        for(int i = 0; i < 100000000; i ++)
        {
        	try
			{
				Thread.sleep(10);
			} 
        	catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	
    		if(navigator[0])
    		{
            	view.translateView(20, 0, 0);
//            	view.translateViewed(50, 0, 0);
    		}
    		
    		if(navigator[1])
    		{
            	view.translateView(0, 20, 0);
//            	view.translateViewed(0, 20, 0);
    		}
    		
    		if(navigator[2])
    		{
            	view.translateView(0, 0, 20);
//            	view.translateViewed(0, 0, 20);
    		}
    		
    		if(navigator[3])
    		{
            	view.translateView(-20, 0, 0);
//            	view.translateViewed(-20, 0, 0);
    		}
    		
    		if(navigator[4])
    		{
            	view.translateView(0, -20, 0);
//            	view.translateViewed(0, -20, 0);
    		}
    		
    		if(navigator[5])
    		{
            	view.translateView(0, 0, -20);
//            	view.translateViewed(0, 0, -20);
    		}
    		
    		if(navigator[6])
    		{
    			view.zoom(0.982);
    		}
    		
    		if(navigator[7])
    		{
    			view.zoom(1.02);
    		}
    		if(navigator[8])
    		{
    			view.setTurnAngle(view.getTurnAngle() + 0.01);
    		}
    		
    		if(navigator[9])
    		{
    			view.setTurnAngle(view.getTurnAngle() - 0.01);
    		}
    		frame.repaint();
        }
//        for(double i = 0; i < 5000; i += 1)
//        {	
//        	try
//			{
//				Thread.sleep(1);
//			} 
//        	catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//        	
//        	view.translateView(0, 1, 0);
//        	frame.repaint();
//        }
//        
//        for(double i = 0; i < 5000; i += 0.0005)
//        {	
//        	try
//			{
//				Thread.sleep(50);
//			} 
//        	catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//        	
//        	view.setView(new Point3D(5000 + 5000*Math.sin(i), 5000 + 5000*Math.cos(i), 7500));
//        	frame.repaint();
//        }
	}
	
	public void paintComponent(Graphics graphics)
	{
		polygon.getProjection(view).draw(graphics);
	}
	
	public static View getView()
	{
		return view;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		navigator[code - 49] = true;
	}

	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		navigator[code - 49] = false;
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void mouseDragged(MouseEvent e)
	{
		double x = e.getX();
		double y = e.getY();
		
		polygon.getProjection(view).highlight(new Point2D(x, y));
		
		frame.repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
		double x = e.getX();
		double y = e.getY();
		
		polygon.getProjection(view).highlight(new Point2D(x, y));
		
		frame.repaint();
	}
}