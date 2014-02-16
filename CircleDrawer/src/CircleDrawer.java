import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
public class CircleDrawer   extends JPanel
{
    private static double j = 0;
    private static double x = 0;
    static boolean pause = false;
    public void paintComponent(Graphics screen)
    {
        super.paintComponent(screen);
        setBackground(Color.white);
        for(double i = 0; i<=j; i+=0.001)
        {
            if(((int)((i*1000)%2)) == 0)
            {
                screen.setColor(Color.BLACK);
            }
            if((((int)(i*1000))%2) == 1)
            {
                screen.setColor(Color.RED);
            }
            screen.fillRect(100 + (int)(((int)(50*Math.sin(x)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)), 2, 2);
            screen.fillRect(250 + (int)(((int)(50*Math.sin(x)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)*Math.cos(x)), 2, 2);
            screen.fillRect(400 + (int)(((int)(50*Math.sin(x)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)*Math.sin(x)), 2, 2);        
            screen.fillRect(550 + (int)(((int)(50*Math.sin(x*100000)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)), 2, 2);        
            screen.fillRect(700 + (int)(((int)(50*Math.sin(x*100000)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)*Math.cos(x*100000)), 2, 2);
            screen.fillRect(850 + (int)(((int)(50*Math.sin(x*100000)))*Math.cos(i)), 90 + (int)(50*Math.sin(i)*Math.sin(x*100000)), 2, 2);
        }
    }
    public static void main(String[]args) throws InterruptedException
    {
        JFrame frame = new JFrame();
        frame.setSize(1000,200);
        frame.setTitle("Circle Drawer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircleDrawer drawer = new CircleDrawer();
        frame.add(drawer);
        frame.setVisible(true);
        while( j<= 20*Math.PI)
        {
        	if(pause == false)
        	{
        		j += 0.001;
        		x += 0.005;
        	}
            frame.repaint();
            Thread.sleep(1);
        }
    }
}