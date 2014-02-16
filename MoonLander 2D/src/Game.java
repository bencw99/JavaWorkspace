import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class Game extends Applet implements KeyListener
{
	int score = 0;
    static int level = 0;
    int highscore;
    boolean start = false;
    boolean settings = false;
    boolean pause = false;
    Terrain terrain = new Terrain();
    SuperLander lander = new Beginner();
    PortalPair portals[];
    FileOutputStream file;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    public void init()
    {
        setSize(1000, 600);
        setBackground(Color.BLACK);
        setVisible(true);
        addKeyListener( this );
    }
    public void start()
    {
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
				e.printStackTrace();
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
	    	repaint();
	    	while(start == false)
	    	{
	            try                               
	            {
	                Thread.sleep(10);                        
	            } 
	            catch (InterruptedException e) 
	            {
	                e.printStackTrace();
	            }
	    	}
    		lander.setState(true);
	        repaint();
	        while(lander.state)
	        {
	            level ++;
	            terrain = new Terrain();
	            lander.setAngle(0);
	            terrain.terrain1();
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
	            while(lander.land(terrain))
	            {
	            	repaint();
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
			            repaint();
		            }
	            }
	            if(lander.state)
	            {
	            	score += lander.fuel + 100/Math.pow(lander.xVel*lander.xVel + lander.yVel*lander.yVel, 0.5) + (level - 1)*50;
	            }
	            repaint();
	            try                               
	            {
	                Thread.sleep(3000);                        
	            } 
	            catch (InterruptedException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        if(score > highscore)
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
	        repaint();
		}
	}
    public void paint(Graphics screen)
    {
    	if(start)
    	{
            setBackground(Color.BLACK);
    		terrain.drawTerrain(screen);
    		lander.drawLander(screen);
    		for(int a = 0; a < portals.length; a++)
    		{
    			portals[a].draw(screen);
    		}
    		screen.setColor(Color.WHITE);
    		Font font = new Font("Cambria",Font.BOLD,30);
    		screen.setFont(font);
	    	lander.land(terrain);
	    	if(lander.land)
	    	{
	    		screen.drawString("Landed Successfully", 300, 100);
	    	}
	    	else if(lander.land(terrain) == false)
	    	{
	    		screen.drawString("Landing Failed", 300, 100);
	    	}
	    	if(lander.state == false)
	    	{
	    		Font f2 = new Font("Cambria",Font.BOLD, 80);
	    		screen.setFont(f2);
	           	screen.drawString("GAME OVER", 250, 200);
	    	}
    		Font f = new Font("Cambria",Font.BOLD,20);
    		screen.setFont(f);
    		screen.drawString("Level " + level, 50, 50);
    		screen.drawString("Score: " + score, 50, 100);
	    	screen.drawString("Fuel: ", 820, 50);
        	Color c = new Color(250 - lander.fuel/4,5 + lander.fuel/4,0);
        	screen.setColor(c);
        	screen.drawRect(870, 33, 100, 20);
        	screen.fillRect(873, 36, lander.fuel/10 - 5, 15);
        	screen.setColor(Color.WHITE);
	    	if(Math.pow(lander.xVel*lander.xVel + lander.yVel*lander.yVel, 0.5) > 1)
	    	{
	    		screen.setColor(Color.RED);
	    	}
	    	screen.drawString("Speed " + (int)(100*Math.pow(lander.xVel*lander.xVel + lander.yVel*lander.yVel, 0.5)), 820, 100);
	    	screen.setColor(Color.WHITE);
    	}
    	else if(settings == false)
    	{
    		setBackground(Color.WHITE);
			Font font = new Font("Cambria",Font.BOLD, 80);
			screen.setFont(font);
        	screen.drawString("Moon Lander", 270, 200);
			Font f = new Font("Cambria",Font.BOLD, 20);
			screen.setFont(f);
        	screen.drawString("PRESS SPACE TO START", 413, 300);
        	screen.drawString("PRESS S TO OPEN SETTINGS", 395, 350);
        	screen.drawString("High Score: " + highscore, 440, 400);	
    	}
    	if(settings && start == false)
    	{
    		setBackground(Color.WHITE);
			Font font = new Font("Cambria",Font.BOLD, 40);
			screen.setFont(font);
        	screen.drawString("Settings: ", 100, 100);
			Font f = new Font("Cambria",Font.BOLD, 20);
			screen.setFont(f);
			screen.drawString("Press 1 to change lander color to blue", 100, 200);
			screen.drawString("Press 2 to change lander color to green", 100, 250);
			screen.drawString("Press 3 to change lander color to red", 100, 300);
			screen.drawString("Press 4 to change lander color to random", 100, 350);
			screen.drawString("Press 5 to change to beginner controls", 100, 400);
			screen.drawString("Press 6 to change to advanced controls", 100, 450);
			screen.drawString("Press s to return to home screen", 100, 500);
    	}
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
        	repaint();
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
    		lander.changeColor(lander.color);
    	}
    	if(code == KeyEvent.VK_2)
    	{
    		lander.setColor(1);
    		lander.changeColor(lander.color);
    	}
    	if(code == KeyEvent.VK_3)
    	{
    		lander.setColor(2);
    		lander.changeColor(lander.color);
    	}
    	if(code == KeyEvent.VK_4)
    	{
    		lander.setColor(3);
    		lander.changeColor(lander.color);
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
}
