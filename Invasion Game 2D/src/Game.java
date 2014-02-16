import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends Applet implements KeyListener
{
	AlienShip[]ships = new AlienShip[3];
	SpaceShip ship = new SpaceShip();
	AlienShip alienShip = new AlienShip();
//	SpaceShip ship2 = new SpaceShip();
    public void init()
    {
        setSize(1000, 600);
        setBackground(Color.WHITE);
        setVisible(true);
        addKeyListener(this);
    }
    public void start()
    {
    	ship.setColor(Color.BLUE);
//    	ship2.setColor(Color.GREEN);
//    	ship2.setYPos(500);
    	while(true)
    	{
    		ship.update();
    		alienShip.update();
//    		ship2.update();
    		repaint();
            try                               
            {
                Thread.sleep(10);                        
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
    	}
    }
    public void paint(Graphics screen)
    {
    	ship.draw(screen);
    	alienShip.draw(screen);
//    	ship2.draw(screen);
    }
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
	    if(code == KeyEvent.VK_UP)
	    {
	    	ship.ThrustOn();
	    }
	    if(code == KeyEvent.VK_LEFT)
	    {
	    	ship.setLeftOn();
	    }
	    if(code == KeyEvent.VK_RIGHT)
	    {
	    	ship.setRightOn();
	    }
//	    if(code == KeyEvent.VK_W)
//	    {
//	    	ship2.ThrustOn();
//	    }
//	    if(code == KeyEvent.VK_A)
//	    {
//	    	ship2.setLeftOn();
//	    }
//	    if(code == KeyEvent.VK_D)
//	    {
//	    	ship2.setRightOn();
//	    }
	    if(code == KeyEvent.VK_SPACE)
	    {
	    	ship.setWeapon();
	    }
	    if(code == KeyEvent.VK_N)
	    {
	    	ship.upgrade();
	    }
//	    if(code == KeyEvent.VK_1)
//	    {
//	    	ship2.setWeapon();
//	    }
	    if(code == KeyEvent.VK_0)
	    {
	    	alienShip.setWeapon(ship);
	    }
    }
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
	    if(code == KeyEvent.VK_UP)
	    {
	    	ship.ThrustOff();
	    }
	    if(code == KeyEvent.VK_LEFT)
	    {
	    	ship.setLeftOff();
	    }
	    if(code == KeyEvent.VK_RIGHT)
	    {
	        ship.setRightOff();
	    }
//	    if(code == KeyEvent.VK_W)
//	    {
//	    	ship2.ThrustOff();
//	    }
//	    if(code == KeyEvent.VK_A)
//	    {
//	    	ship2.setLeftOff();
//	    }
//	    if(code == KeyEvent.VK_D)
//	    {
//	    	ship2.setRightOff();
//	    }
    }
	public void keyTyped(KeyEvent e) 
	{
        int code = e.getKeyCode();
	}
	
}
