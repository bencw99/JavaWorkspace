import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RectangleDrawer extends JPanel
{
	private static int X = 2;
	private static int Y = 2;
	private int Xsize = 10, Ysize = 10;
	public class Listener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			 int key = e.getKeyCode();
			 	if (key == KeyEvent.VK_LEFT) 
			 	{
			        X -= 2;
			    }

			    if (key == KeyEvent.VK_RIGHT) 
			    {
			        X += 2;
			    }

			    if (key == KeyEvent.VK_UP) 
			    {
			        Y -= 2;
			    }

			    if (key == KeyEvent.VK_DOWN) 
			    {
			        Y += 2;
			    }
			    }

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public static void main(String[]args) throws InterruptedException
	{
		 JFrame frame = new JFrame();
		 frame.setSize(100,100);
		 frame.setTitle("Moving Rectangle");
		 frame.setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 RectangleDrawer drawer = new RectangleDrawer();
		 frame.add(drawer);
		 frame.setVisible(true);
		 Listener listener = drawer.new Listener();
		 frame.addKeyListener(listener);
		 while (true) {
			 frame.repaint();
			 Thread.sleep(1);
		 }
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(X, Y, Xsize, Ysize);
	}
}