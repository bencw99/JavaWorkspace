
import Contestants.*; 		//Allows access to the contestants package
import java.awt.Color; 		//Allows access to Color class
import java.awt.Font; 		//Allows access to Font class
import java.awt.Graphics;	//Allows access to Graphics class
import java.awt.Image; 		//Allows access to Image class
import java.applet.Applet; 	//Allows access to Applet class

public class Race extends Applet //Makes class continue applet class
{
	private Tortoise tortoise = new Tortoise(); //Creates tortoise object
	private Hare hare = new Hare(); 			//Creates hare object
	public void paint(Graphics screen) 
	{
		screen.drawRect(0, 0, 1200, 200); 		//Draws rectangle with h=200 , w=1200
		setBackground(Color.white);				//sets background color to white
		Color c = new Color(100,180,100); 		//Creates new color
		screen.setColor(c); 					//Sets all colored graphics to color c
		screen.drawLine(100, 100, 1200, 100); 	//Draws line to divide race track
		screen.fillRect(90, 0, 10, 200);		//Draws rectangle for start line
		Font f = new Font("Arial",Font.BOLD,20);//Creates font for Strings
		screen.setFont(f);						//Sets font to font c
		screen.drawString("START", 10, 107);	//Draws "START" at beginning
		drawHare(screen);						//Calls drawHare method
		drawTortoise(screen);					//Calls drawTortoise method
		extras(screen);							//Calls extras method
	}
	public void init() 
	{
	    setSize(1200, 200);	//Sets window size
	    setVisible(true);	//Sets graphics visibility to visible
	}
	
	public void start()
	{	
		while(Math.max((double)(tortoise.getPosition()) , (double)(hare.getPosition())) < 50) //makes loop active when neither the tortoise or hare ave won
		{
			repaint();							//Erases window screen and recalls paint method												
			try 								//try and catch to allow sleep method in start method
			{
                pause();						//calls pause method
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			int n = (int)(Math.random()*10 + 1);//generates random integer from 1-10
			moveHare(n);						//calls moveHare method
			moveTortoise(n);					//calls moveTortoise method
			System.out.println(String.format("%d %d", tortoise.getPosition(), hare.getPosition())); //Prints tortoise and hare positions
		}
		repaint(); 																//Final repaint used to draw extras that won't be calle in while loop
		if((tortoise.getPosition() == 50) && (hare.getPosition() == 50)==false)	//Tests if tortoise won
		{
			System.out.println("tortoise wins");								//Prints that tortoise won
		}
		if((hare.getPosition() == 50) && (tortoise.getPosition() == 50)==false)	//Tests if hare won
		{
			System.out.println("hare wins");									//Prints that hare won
		}
		if((hare.getPosition() == 50) && (tortoise.getPosition() == 50))		//Tests if contestants tied
		{
			System.out.println("tie");											//Prints that there was a tie
		}
	}
	public static void pause() throws InterruptedException	//Statement allows sleep method
	{
		Thread.sleep(500);									//Half a second pause
	}
	public void moveTortoise(int n)
	{
		//This method tests if the random integer is within certain ranges and calls methods accordingly
		if(1<=n && n<=5)
		{
			tortoise.FastPlod();
		}
		if(6<=n && n<=8)
		{
			tortoise.SlowPlod();
		}
		if(9<=n && n<=10)
		{
			tortoise.Slip();
		}
	}
	public void moveHare(int n)
	{
		//This method tests if the random integer is within certain ranges and calls methods accordingly
		if(1<=n && n<=2)
		{
			hare.BigHop();
		}
		if(3<=n && n<=5)
		{
			hare.SmallHop();
		}
		if(6==n)
		{
			hare.BigSlip();
		}
		if(7<=n && n<=8)
		{
			hare.SmallSlip();
		}
		if(9<=n && n<=10)
		{
			hare.FallAsleep();
		}
	}
	public void extras(Graphics screen)
	{
		Color c = new Color(100,180,190);								//Creates new color
		screen.setColor(c);												//Sets graphics color to c
		Font f = new Font("Courier",Font.BOLD,13);						//Creates new font object 
		screen.setFont(f);												//Sets font to f
		String H = ("Square " + hare.getPosition());					//Sets H to Square # of hare
		String T = ("Square " + tortoise.getPosition());				//Sets T to Square # of tortoise
		screen.drawString(T, 5, 60);									//Draws T
		screen.drawString(H, 5, 160);									//Draws H
		if((hare.getPosition() == 50) && (tortoise.getPosition() < 50))	//Tests if hare won
		{
			//Draws winner in the lane of the hare
			screen.setColor(Color.BLUE);
			Font d = new Font("Times",Font.ITALIC,20);
			screen.setFont(d);
			screen.drawString("WINNER!", 150, 160);
		}
		if((tortoise.getPosition() == 50) && (hare.getPosition() < 50)) //Tests if tortoise won
		{
			//Draws winner in the lane of the tortoise
			screen.setColor(Color.BLUE);
			Font d = new Font("Times",Font.ITALIC,20);
			screen.setFont(d);
			screen.drawString("WINNER!", 150, 60);
		}
		if((tortoise.getPosition() == 50) && (hare.getPosition() == 50)) //Tests if contestants tied
		{
			//Draws tie in both lanes
			screen.setColor(Color.BLUE);
			Font d = new Font("Times",Font.ITALIC,20);
			screen.setFont(d);
			screen.drawString("TIE!", 150, 60);
			screen.drawString("TIE!", 150, 160);
		}
	}
	public void drawTortoise(Graphics screen)
	{
		Image t = getImage(getDocumentBase(), "tortoise.gif");					//Creates t as tortoise image
		screen.drawImage(t,tortoise.getPosition()*20 + 100, 20, 80, 60,this);	//draws tortoise image 
	}
	public void drawHare(Graphics screen) 
	{
		Image h = getImage(getDocumentBase(), "hare.gif");						//Creates h as hare image
		screen.drawImage(h,hare.getPosition()*20 + 100, 120, 80, 60, this);		//draws hare image
	}
}

