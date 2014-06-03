import org.lwjgl.input.*;
import org.lwjgl.opengl.*;


public class Test
{
	public static void main(String[]args)
	{
		for(int i = 0; i < 10000; i ++)
		{
			try
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(Mouse.getX());
		}
	}
}
