import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
    private static ArrayList<Character> characters = new ArrayList<Character>();
    private static Item[] items = new Item[12];
    private static boolean[] buttonHighlight = new boolean[8];
    private static int costumeX = 2;
    private static int costumeY = 1;
    private static int charNum = 30;
    private static enum page {HOME, SETTINGS, CHOICE, GAME, PAUSE};
    private static page current = page.CHOICE;
    static int screenWidth = 1000;
    static int screenHeight = 600;
    public static void main(String[]args)
    {
        for(int i = 0; i < buttonHighlight.length; i++)
        {
            buttonHighlight[i] = false;
        }
        try
        {
            for(int i = 0; i < items.length; i++)
            {
                items[i] = new Item(i%4,false);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setSize(screenWidth,screenHeight);
        frame.setTitle("Adventure Game");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game drawer = new Game();
        frame.add(drawer);
        frame.setVisible(true);
        frame.addKeyListener(drawer);
        frame.addMouseListener(drawer);
        frame.addMouseMotionListener(drawer);
        while(current == page.CHOICE)
        {
            frame.repaint();
            try                              
            {
                Thread.sleep(30);                       
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
        	int[]playerSim = {0 , 0, 0};
            characters.add(new Player(250,250,costumeX,costumeY,-1, Color.GREEN, playerSim));
            for(int i = 1; i < charNum; i++)
            {
            	int[]otherSim = {(int)(1000*Math.random()), (int)(1000*Math.random()), (int)(1000*Math.random())};
                characters.add(new Other ((int)(screenWidth*Math.random()), (int)(screenHeight*Math.random()), (int)(4*Math.random()), (int)(2*Math.random()),1, otherSim));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        while(true)
        {
            while(current == page.PAUSE)
            {
                try                              
                {
                    Thread.sleep(30);                       
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println(characters.get(1).similarity[0] + " , " + characters.get(1).similarity[1] + " , " + characters.get(1).similarity[2]);
            for(int i = 0; i < characters.size(); i++)
            {
                characters.get(i).walk(characters.get(0).spells, characters);
                characters.get(i).update(characters.get(0).spells, characters);
            }
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
    }
    public void paintComponent(Graphics screen)
    {
        if(current == page.GAME)
        {
            for(int i = 0; i < items.length; i++)
            {
                items[i].draw(screen);
            }
            characters.get(0).drawStats(screen );
            Locatable[] locatables = new Locatable[characters.size() + characters.get(0).spells.size()];
            for(int i = 0; i < characters.size(); i++)
            {
                locatables[i] = characters.get(i);
            }
            for(int i = characters.size(); i < characters.size() + characters.get(0).spells.size(); i++)
            {
                locatables[i] = characters.get(0).spells.get(i - characters.size());
            }
            display(order(locatables), screen);
        }
        if(current == page.CHOICE)
        {
            Font font = new Font("Arial",Font.BOLD, 40);
            screen.setFont(font);
            screen.drawString("Choose an Avatar", 300, 150);
            screen.setColor(Color.BLACK);
            for(int i = 0; i < buttonHighlight.length; i++)
            {
                if(buttonHighlight[i])
                {
                    screen.fillRect(115 + i*100,270,90,110);
                }
            }
            screen.setColor(Color.DARK_GRAY);
            for(int i = 0; i < 8; i++)
            {
                screen.fillRect(120 + i*100, 275, 80, 100);
            }
            screen.setColor(Color.LIGHT_GRAY);
            for(int i = 0; i < 8; i++)
            {
                screen.fillRect(125 + i*100, 280, 70, 90);
            }
            try
            {
            	int k = 0;
            	for(int i = 0; i < 2; i ++)
            	{
            		for(int j = 0; j < 4; j++)
            		{
            			(new Other(160 + 100*k, 325, j, i, 0, new int[3])).draw(screen);
            			k++;
            		}
            	}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(current == page.HOME)
        {
            Font font = new Font("Arial",Font.BOLD, 64);
            screen.setFont(font);
            screen.drawString("Adventure Game", 270, 200);
        }
        if(current == page.PAUSE)
        {
            Font font = new Font("Arial",Font.BOLD, 64);
            screen.setFont(font);
            screen.drawString("Paused", 270, 200);
        }
    }
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_Q)
        {
            System.exit(0);
        }
        if(current == page.GAME)
        {
            if(code == KeyEvent.VK_UP)
            {
                characters.get(0).setDirection(3);
                characters.get(0).setWalking(true);
            }
            if(code == KeyEvent.VK_LEFT)
            {
                characters.get(0).setDirection(1);
                characters.get(0).setWalking(true);
            }
            if(code == KeyEvent.VK_RIGHT)
            {
                characters.get(0).setDirection(2);
                characters.get(0).setWalking(true);
            }
            if(code == KeyEvent.VK_DOWN)
            {          
                characters.get(0).setDirection(0);
                characters.get(0).setWalking(true);
            }
            if(code == KeyEvent.VK_S)
            {
                characters.get(0).sprintOn();
            }
            if(code == KeyEvent.VK_SPACE)
            {
                try
                {
                    characters.get(0).executeSpell(characters);
                }
                catch (IOException i)
                {
                    i.printStackTrace();
                }
            }
            if(code == KeyEvent.VK_A)
            {
                try
                {
                    characters.get(0).release(characters);
                }
                catch (IOException i)
                {
                    i.printStackTrace();
                }
            }
        }
        if(code == KeyEvent.VK_P)
        {
            if(current == page.GAME)
            {
                current = page.PAUSE;
            }
            else if(current == page.PAUSE)
            {
                current = page.GAME;
            }
        }
        if(code == KeyEvent.VK_D)
        {
            characters.get(0).pickUp(items);
        }
    }
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(current == page.GAME)
        {
            if(code == KeyEvent.VK_UP)
            {
                characters.get(0).setWalking(false);
            }
            if(code == KeyEvent.VK_LEFT)
            {
                characters.get(0).setWalking(false);
            }
            if(code == KeyEvent.VK_RIGHT)
            {
                characters.get(0).setWalking(false);
            }
            if(code == KeyEvent.VK_DOWN)
            {
                characters.get(0).setWalking(false);
            }
            if(code == KeyEvent.VK_S)
            {
                characters.get(0).sprintOff();
            }
             
        }
    }
    public void keyTyped(KeyEvent e)
    {
         
    }
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(y <= 400 && y >= 300)
        {
        	int k = 0;
        	outerloop:
	        for(int i = 0; i < 2; i ++)
	        {
	        	for(int j = 0; j < 4; j++)
	        	{
	        		if(x <= 200 + 100*k && x >= 120 + 100*k)
	        		{
	        			costumeX = j;
	        			costumeY = i;
	        			current = page.GAME;
	        			break outerloop;
	        		}
	        		k++;
	        	}
	        }
        }
    }
    public void mouseEntered(MouseEvent e)
    {
         
    }
    public void mouseExited(MouseEvent e)
    {
 
    }
    public void mousePressed(MouseEvent e)
    {
         
    }
    public void mouseReleased(MouseEvent e)
    {
         
    }
    public void mouseDragged(MouseEvent e)
    {
 
    }
    public void mouseMoved(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(current == page.CHOICE)
        {
            for(int i = 0; i < buttonHighlight.length; i++)
            {
                buttonHighlight[i] = false;
            }
            if(y <= 400 && y >= 300)
            {
	        	for(int j = 0; j < 8; j++)
	        	{
	        		if(x <= 200 + 100*j && x >= 120 + 100*j)
	        		{
	        			buttonHighlight[j] = true;
	        			break;
	        		}
	        	}
            }
        }
    }
    public static Locatable[] order(Locatable[]locs)
    {
        Locatable[]locatables = new Locatable[locs.length];
        for(int i = 0; i < locs.length; i++)
        {
            locatables[i] = locs[i];
        }
        for(int i = locatables.length; i > 0; i--)
        {
            for(int j = 1; j < i; j++)
            {
                if(locatables[j - 1].getYPos() > locatables[j].getYPos())
                {
                    Locatable c = locatables[j];
                    locatables[j] = locatables[j - 1];
                    locatables[j - 1] = c;
                }
            }
        }
        return locatables;
    }
    public static void display(Locatable[]locs, Graphics screen)
    {
        for(int i = 0; i < locs.length; i++)
        {
            locs[i].draw(screen);
        }
    }
}
