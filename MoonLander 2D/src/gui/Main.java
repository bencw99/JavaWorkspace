package gui;
import game.lander.Advanced;
import game.lander.Beginner;
import game.lander.SuperLander;
import game.portal.PortalPair;
import game.terrain.Terrain;
import gui.panels.Panel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
public class Main
{
    private static int level = 0;
	private static int highscore;
    private static boolean start = false;
    private static boolean settings = false;
    private static boolean pause = false;
    private static Terrain terrain = new Terrain();
    private static SuperLander lander = new Beginner();
    private static PortalPair portals[];
    private static FileOutputStream file;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    private static int screenWidth = 990;
    private static int screenHeight = 622;
    private static int score = 0;
	private static JFrame frame = new JFrame();
    public static void main(String[]args)
    {
        Panel panel = new Panel();
        frame.setSize(screenWidth,screenHeight);
        frame.setTitle("Mars Lander");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.addKeyListener(new ControlListener());
    	while(true)
    	{
	        String string = null;
	        try 
	        {
	            bufferedReader = new BufferedReader(new FileReader("highscore"));
				string = bufferedReader.readLine();
				bufferedReader.close();
			} 
	        catch (IOException e) 
			{
	        	highscore = 0;
			}
	        if(string == null)
	        {
	        	highscore = 0;
	        }
	        else
	        {
		        highscore = Integer.parseInt(string);
	        }
    		score = 0;
    		level = 0;
	    	frame.repaint();
    		frame.setBackground(Color.WHITE);
	    	while(start == false)
	    	{
	            try                               
	            {
	                Thread.sleep(30);                        
	            } 
	            catch (InterruptedException e) 
	            {
	                e.printStackTrace();
	            }
	            frame.repaint();
	    	}
    		lander.setState(true);
    		frame.setBackground(Color.BLACK);
	        frame.repaint();
	        while(lander.isState())
	        {
	            level ++;
	            terrain = new Terrain();
	            lander.setAngle(0);
	            terrain.getSpireConfiguration();
	            portals = new PortalPair[level/3 + 2];
	            for(int b = 0; b < portals.length; b++)
	            {
	            	portals[b] = new PortalPair(50 + (int)(Math.random()*900), 50 + (int)(Math.random()*(350)), 50 + (int)(Math.random()*900), 50 + (int)(Math.random()*(350)));
	            	while(portals[b].getY1() >= 550 - terrain.getHeight(portals[b].getX1()) || portals[b].getY2() >= 550 - terrain.getHeight(portals[b].getX2()) || portals[b].distance() < 400)
	            	{
	            		portals[b] = new PortalPair(50 + (int)(Math.random()*900), 50 + (int)(Math.random()*(350)), 50 + (int)(Math.random()*900), 50 + (int)(Math.random()*(350)));
	            	}
	            }
	            lander.setXPos(500);
	            lander.setYPos(100);
	            lander.setXVel(0);
	            lander.setYVel(0);
	            lander.setLand(false);
	            lander.setFuel(1000);
	            int phase = 0;
	            while(lander.land(terrain))
	            {
	            	phase ++;
	            	if(phase % 2 == 0)
	            	{
	            		terrain.smooth();
	            	}
	            	if(phase % 20 == 0)
	            	{
	            		terrain.spire();
	            	}
	            	frame.repaint();
	                lander.gravity(portals);
	                lander.teleport(portals);
			        for(int b = 0; b < portals.length; b++)
			        {
			        	if(portals[b].distance(lander) < 467)
			            portals[b].setRotateSpeed(7/portals[b].distance(lander));
			        	else
			        	portals[b].setRotateSpeed(0.015);
			        }
	                lander.update();
	                try                               
	                {
	                    Thread.sleep(9);                        
	                } 
	                catch (InterruptedException e) 
	                {
	                    e.printStackTrace();
	                }
		            while(pause)
		            {
			            try                               
			            {
			                Thread.sleep(100);                        
			            } 
			            catch (InterruptedException e) 
			            {
			                e.printStackTrace();
			            }
			            frame.repaint();
		            }
	            }
	            if(lander.isState())
	            {
	            	score += lander.getFuel() + 100/Math.pow(lander.getxVel()*lander.getxVel() + lander.getyVel()*lander.getyVel(), 0.5) + (level - 1)*50;
	            }
	            frame.repaint();
	            try                               
	            {
	                Thread.sleep(3000);                        
	            } 
	            catch (InterruptedException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        if(score >= highscore)
	        {
	            try 
	            {
					file = new FileOutputStream("highscore");
				} 
	            catch (FileNotFoundException e1) 
	            {
					e1.printStackTrace();
				}
	            printWriter = new PrintWriter(file);
	        	printWriter.println(score);
	        	printWriter.close();
	        	try 
	        	{
					file.close();
				} 
	        	catch (IOException e) 
				{
					e.printStackTrace();
				}
	        }
	        settings = false;
	        start = false;
	        frame.repaint();
		}
	}
    public static class ControlListener implements KeyListener
    {
    	public ControlListener()
    	{
    		super();
    	}
        public void keyPressed(KeyEvent e) 
        {
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_Q)
            {
            	System.exit(0);
            }
            if(code == KeyEvent.VK_SPACE)
            {
                start = true;
            }
            if(code == KeyEvent.VK_P)
            {
            	if(pause == true)
            	{
            		pause = false;
            	}
            	else
            	{
            		pause = true;
            	}
            }
            if(code == KeyEvent.VK_S)
            {
            	if(settings)
            	{
            		settings = false;
            	}
            	else
            	{
            		settings = true;
            	}
            	frame.repaint();
            } 
    	    if(code == KeyEvent.VK_UP)
    	    {
    	    	lander.ThrustOn();
    	    	lander.upThrustOn();
    	    }
    	    if(code == KeyEvent.VK_LEFT)
    	    {
    	    	lander.setLeftOn();
    	    	lander.leftThrustOn();
    	    }
    	    if(code == KeyEvent.VK_RIGHT)
    	    {
    	    	lander.setRightOn();
    	    	lander.rightThrustOn();
    	    }
        	if(code == KeyEvent.VK_1)
        	{
        		lander.setColor(0);
        		lander.changeColor(lander.getColor());
        	}
        	if(code == KeyEvent.VK_2)
        	{
        		lander.setColor(1);
        		lander.changeColor(lander.getColor());
        	}
        	if(code == KeyEvent.VK_3)
        	{
        		lander.setColor(2);
        		lander.changeColor(lander.getColor());
        	}
        	if(code == KeyEvent.VK_4)
        	{
        		lander.setColor(3);
        		lander.changeColor(lander.getColor());
        	}
        	if(code == KeyEvent.VK_5)
        	{
        		if(start == false)
        		lander = new Beginner();
        	}
        	if(code == KeyEvent.VK_6)
        	{
        		if(start == false)
        		lander = new Advanced();
        	}
        }
	    public void keyReleased(KeyEvent e) 
	    {
	        int code = e.getKeyCode();
		    if(code == KeyEvent.VK_UP)
		    {
		    	lander.ThrustOff();
		    	lander.upThrustOff();
		    }
		    if(code == KeyEvent.VK_LEFT)
		    {
		    	lander.setLeftOff();
		       	lander.leftThrustOff();
		    }
		    if(code == KeyEvent.VK_RIGHT)
		    {
		        lander.setRightOff();
		        lander.rightThrustOff();
		    }
	    }
		public void keyTyped(KeyEvent e)
		{
			
		}
    }
    public Color nextColor(int a)
    {
    	if(a == 0)
    	{
    		return Color.blue;
    	}
    	if(a == 1)
    	{
    		return Color.green;
    	}
    	if(a == 2)
    	{
    		return Color.red;
    	}
    	if(a == 3)
    	{
    		Color c = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
    		return c;
    	}
    	else
    	{
    		return Color.black;
    	}
    }
    public static int getHighscore()
	{
		return highscore;
	}
	public static boolean isSettings()
	{
		return settings;
	}
	public static boolean isPause()
	{
		return pause;
	}
	public static Terrain getTerrain()
	{
		return terrain;
	}
	public static SuperLander getLander()
	{
		return lander;
	}
	public static PortalPair[] getPortals()
	{
		return portals;
	}
	public static int getScreenHeight()
	{
		return screenHeight;
	}
	public static int getScore()
	{
		return score;
	}
    public static boolean isStart()
	{
		return start;
	}
	public static void setStart(boolean start)
	{
		Main.start = start;
	}
    public static int getLevel()
    {
    	return level;
    }
    public static int getScreenWidth()
    {
    	return screenWidth;
    }
}
