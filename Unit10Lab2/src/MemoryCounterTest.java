import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class MemoryCounterTest extends Applet
{
	MemoryCounterConsoleMenu counter = new MemoryCounterConsoleMenu(0); //declares and creates MemoryCounterConsoleMenu
	public void init()
	{
        setSize(1000, 120); //sets size
        setVisible(true); //sets visible
        setBackground(Color.WHITE); //sets background
		add(counter); //adds counter object
	}
	public void start()
	{
		while(true)
		{
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			repaint(); //calls paint
		}
	}
	public void paint(Graphics screen)
	{
		counter.draw(screen); // Draws counter
	}
}