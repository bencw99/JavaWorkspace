import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
public class Testing
{
	public static void main(String[]args)
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(500,500));
			Display.create();
		} 
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		for(int i = 0; i < 10; i ++)
		{
			try
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			Display.update();
			System.out.println("( " + Mouse.getX() + ", " + Mouse.getY() + ")");
		}
		System.exit(0);
	}
}
