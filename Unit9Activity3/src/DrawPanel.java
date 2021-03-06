import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
public class DrawPanel extends JPanel
{
	private Random randomNumbers = new Random();
	private MyLine[] lines;
	private int lineNum = 1 + randomNumbers.nextInt(5);
	private MyRectangle[] rects;
	private int rectNum = 1 + randomNumbers.nextInt(5);
	private MyOval[] ovals;
	private int ovalNum = 1 + randomNumbers.nextInt(5);
	public DrawPanel()
	{
		//constructor that initializes array elements
		setBackground(Color.white);
		lines = new MyLine[lineNum];
		rects = new MyRectangle[rectNum];
		ovals = new MyOval[ovalNum];
		for(int count = 0; count < lines.length; count ++)
		{
			int x1 = randomNumbers.nextInt(300);
			int y1 = randomNumbers.nextInt(300);
			int x2 = randomNumbers.nextInt(300);
			int y2 = randomNumbers.nextInt(300);
			Color color = new Color( randomNumbers.nextInt(256),randomNumbers.nextInt(256),randomNumbers.nextInt(256));
			lines[count] = new MyLine(x1, y1, x2, y2, color);
		}
		for(int count = 0; count < rects.length; count ++)
		{
			int x1 = randomNumbers.nextInt(300);
			int y1 = randomNumbers.nextInt(300);
			int x2 = randomNumbers.nextInt(300);
			int y2 = randomNumbers.nextInt(300);
			boolean flag;
			if(randomNumbers.nextInt(2) == 1)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
			Color color = new Color( randomNumbers.nextInt(256),randomNumbers.nextInt(256),randomNumbers.nextInt(256));
			rects[count] = new MyRectangle(x1, y1, x2, y2, color, flag);
		}
		for(int count = 0; count < ovals.length; count ++)
		{
			int x1 = randomNumbers.nextInt(300);
			int y1 = randomNumbers.nextInt(300);
			int x2 = randomNumbers.nextInt(300);
			int y2 = randomNumbers.nextInt(300);
			boolean flag;
			if(randomNumbers.nextInt(2) == 1)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
			Color color = new Color( randomNumbers.nextInt(256),randomNumbers.nextInt(256),randomNumbers.nextInt(256));
			ovals[count] = new MyOval(x1, y1, x2, y2, color, flag);
		}
	}
	public void paintComponent( Graphics screen)
	{
		//Draws various shapes on applet window given they were initialized
		super.paintComponent(screen);
		for(MyOval oval : ovals)
		{
			oval.draw(screen);
		}
		for(MyRectangle rect : rects)
		{
			rect.draw(screen);
		}
		for(MyLine line : lines)
		{
			line.draw(screen);
		}
	}
	public String getStatus()
	{
		//returns status string given initialization of shape arrays
		return "Lines: " + lineNum + ", Rectangles: " + rectNum + ", Ovals: " + ovalNum;
	}
}
