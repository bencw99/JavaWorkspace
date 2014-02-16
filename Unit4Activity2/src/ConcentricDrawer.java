
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class ConcentricDrawer extends JPanel 
{
	public static void main(String[]args) throws InterruptedException
	{
		 JFrame frame = new JFrame();
		 frame.setSize(300,300);
		 frame.setTitle("Concentric Circles");
		 frame.setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ConcentricDrawer drawer = new ConcentricDrawer();
		 frame.add(drawer);
		 frame.setVisible(true);
		 frame.repaint();
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		for(int i = 1; i <= 12; i++)
		{
			g.drawOval(150 - 10*i, 140 - 10*i, 20*i, 20*i);
		}
	}
}
