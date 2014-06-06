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
	public static final int panelWidth = 500;
	public static final int panelHeight = 500;
	private static Terrain terrain;
	private static View view;
    private static JFrame frame = new JFrame();
	
	public static void main(String[]args)
	{
        terrain = new Terrain(100, 150, new Point2D(-3000, 0));
        terrain.init();
		
        view = new View(new Point3D(4000, 2000, 6000), new Point3D(4000, 12000, 2000), Math.PI*0.97);
        
        frame.setSize(panelWidth,panelHeight);
        frame.setTitle("Terrain");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.addKeyListener(panel);
        frame.setVisible(true);

        System.out.println(terrain);
//        for(double i = 0; i < 1000; i += 1)
//        {	
//        	try
//			{
//				Thread.sleep(100);
//			} 
//        	catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//        	terrain.smooth();
//        	terrain.loadPolys();
//        	frame.repaint();
//        }
	}
	
	public void paintComponent(Graphics graphics)
	{
		terrain.draw(graphics);
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
			view.zoom(1.2);
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
