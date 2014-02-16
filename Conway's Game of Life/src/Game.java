import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class Game extends Applet 
{
    Cell[][]cells = new Cell[150][150];
    static int xSize = 600;
    static int ySize = 600;
    public void init()
    {
        setSize(xSize + 1, ySize + 1);
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
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
//	private class PauseButton extends JButton implements ActionListener
//	{
//    	PauseButton()
//    	{
//        	super("Pause");
//        	addActionListener(this);
//    	}
//    	public void actionPerformed(ActionEvent arg0)
//    	{
//        	// nextGeneration(); // test the start button
//        	if (this.getText().equals("Start"))
//        	{
//            	togglePaintLoop();
//            	setText("Stop");
//        	}
//        	else
//        	{
//            	togglePaintLoop();
//            	setText("Pause");
//        	}
//        	repaint();
//    	}
//	}
//	private class ClearButton extends JButton implements ActionListener
//	{
//    	ClearButton()
//    	{
//        	super("Clear");
//        	addActionListener(this);
//    	}
//    	public void actionPerformed(ActionEvent arg0)
//    	{
//        	setText("Clear");
//        	for (int y = 0; y < cells[0].length; y++)
//        	{
//            	for (int x = 0; x < cells.length; x++)
//            	{
//                	cells[x][y] = new Cell(x,y);
//                	System.out.println(x);
//            	}
//        	}
//        	repaint();
//    	}
//	}
//	private class StartButton extends JButton implements ActionListener
//	{
//    	StartButton()
//    	{
//        	super("Start");
//        	addActionListener(this);
//    	}
//    	public void actionPerformed(ActionEvent arg0)
//    	{
//        	if (this.getText().equals("Start"))
//        	{
//            	setText("Stop");
//        	}
//        	else
//        	{
//            	setText("Start");
//        	}
//        	repaint();
//    	}
//	}
//	private class QuitButton extends JButton implements ActionListener
//	{
//    	QuitButton()
//    	{
//        	super("Quit");
//        	addActionListener(this);
//    	}
//    	public void actionPerformed(ActionEvent arg0)
//    	{
//        	setText("Quit");
//        	System.exit(0);
//    	}
//	}
}
