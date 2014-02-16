import java.applet.Applet;
import java.awt.Graphics;
public class Terrain extends Applet
{
	double points[][] = new double[20][20];
	public void start()
	{
		points[0][0] = 10*Math.random();
		for(int x = 0; x < points.length; x++)
		{
			for(int z = 0; z < points[0].length; z++)
			{
				if(x == 0 && z == 0)
				{
					break;
				}
				if(x == 0)
				{
					points[x][z] = points[x][z-1] + change();
					break;
				}
				if(z == 0)
				{
					points[x][z] = points[x-1][z] + change();
					break;
				}
				points[x][z] = ((points[x-1][z] + points[x][z-1])/2) + change();
			}
		}
		for(int a = 0; a <= 10; a++)
		{
			try 								
			{
                Thread.sleep(500);						
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			points = smooth(points);
			repaint();
		}
	}
	public void init() 
	{
	    setSize(1000, 600);
	    setVisible(true);
	}
	public void paint(Graphics screen)
	{
		
		for(int x = 0; x < points.length; x++)
		{
			for(int z = 0; z < points[0].length; z++)
			{
				if((x == points.length - 1)||(z == points[0].length - 1))
				{
					break;
				}
				
			}
		}
	}
	public static double[][] smooth(double a[][])
	{
		double sum = 0;
		double average;
		for(int x = 0; x < a.length; x++)
		{
			for(int z = 0; z < a[0].length; z++)
			{
				sum += a[x][z];
			}
		}
		average = sum/(a[0].length)*(a.length);
		for(int x = 0; x < a.length; x++)
		{
			for(int z = 0; z < a[0].length; z++)
			{
				a[x][z] = (a[x][z] + average*0.1)/1.1;
			}
		}
		return a;
	}
	public double change()
	{
		double x = 2*Math.random() - 1;
		return x;
	}
}
