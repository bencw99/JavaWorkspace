import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
public class Game extends Applet 
{
    Cell[][]cells = new Cell[150][150];
    static int xSize = 600;
    static int ySize = 600;
    JButton start = new JButton("Start");
    public void init()
    {
        setSize(xSize + 1, ySize + 1);
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
        start.setVerticalTextPosition(JButton.CENTER);
        start.setHorizontalTextPosition(JButton.LEADING);
        start.setBackground(Color.YELLOW);
        start.setForeground(Color.RED);
        add(start);
    }
    public void start()
    {
        for(int i = 0; i < cells.length; i++)
        {
            for(int j = 0; j < cells[0].length; j++)
            {
                cells[i][j] = new Cell(i,j);
            }
        }
        for(int i = 0; i < 4000; i++)
        {
            cells[(int)(Math.random()*cells.length)][(int)(Math.random()*cells[0].length)].setState(true);
        }
        while(true)
        {
            for(int i = 0; i < cells.length; i++)
            {
                for(int j = 0; j < cells[0].length; j++)
                {
                    cells[i][j].update(cells);
                }
            }
            repaint();
            try                              
            {
                Thread.sleep(100);                        
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            for(int i = 0; i < cells.length; i++)
            {
                for(int j = 0; j < cells[0].length; j++)
                {
                    cells[i][j].statusUpdate();
                }
            }
        }
    }
    public void paint(Graphics screen)
    {
    	
    	screen.setColor(Color.BLACK);
        for(int i = 0; i <= cells.length; i++)
        {
            screen.drawLine(0, i*ySize/cells.length, xSize, i*ySize/cells.length);
        }
        for(int i = 0; i <= cells.length; i++)
        {
            screen.drawLine(i*xSize/cells.length,0,i*xSize/cells.length,ySize);
        }
        for(int i = 0; i < cells.length; i++)
        {
            for(int j = 0; j < cells[0].length; j++)
            {
                cells[i][j].draw(screen);
            }
        }
    }
}
