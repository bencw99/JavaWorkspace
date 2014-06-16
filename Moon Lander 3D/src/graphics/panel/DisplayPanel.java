package graphics.panel;

import game.terrain.*;
import graphics.polygon.*;
import graphics.projection.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class DisplayPanel extends JPanel implements KeyListener
{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private static Terrain terrain;
	private static View view;
    private static JFrame frame = new JFrame();
	
	public static void main(String[]args)
	{
        view = new View(new Point3D(5000, 5000, 7500), new Point3D(5000, 5000, 100), Math.PI, WIDTH, HEIGHT);
//        view = new View(new Point3D(-1000, -12000, 9000), new Point3D(0, 5000, 4500), Math.PI);
        
        terrain = new Terrain(100, 100, new Point2D(0, 0));
        terrain.create();
        
        frame.setSize(WIDTH,HEIGHT);
        frame.setTitle("Terrain");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.addKeyListener(panel);
        frame.setVisible(true);

//        for(double i = 0; i < 5000; i += 1)
//        {	
//        	try
//			{
//				Thread.sleep(5);
//			} 
//        	catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//        	
//        	view.translateView(0, -1, 0);
//        	frame.repaint();
//        }
	}
	
	public void paintComponent(Graphics graphics)
	{
		terrain.draw(graphics);
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
        	view.translateView(50, 0, 0);
//        	view.translateViewed(50, 0, 0);
		}
		
		if(code == '2')
		{
        	view.translateView(0, 50, 0);
//        	view.translateViewed(0, 50, 0);
		}
		
		if(code == '3')
		{
        	view.translateView(0, 0, 50);
//        	view.translateViewed(0, 0, 50);
		}
		
		if(code == '4')
		{
        	view.translateView(-50, 0, 0);
//        	view.translateViewed(-50, 0, 0);
		}
		
		if(code == '5')
		{
        	view.translateView(0, -50, 0);
//        	view.translateViewed(0, -50, 0);
		}
		
		if(code == '6')
		{
        	view.translateView(0, 0, -50);
//        	view.translateViewed(0, 0, -50);
		}
		
		if(code == '7')
		{
			view.zoom(0.95);
		}
		
		if(code == '8')
		{
			view.zoom(1.05);
		}
		frame.repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		
	}

	public void keyTyped(KeyEvent e)
	{

	}
}
