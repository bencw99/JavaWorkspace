import java.awt.Color;
import java.awt.Graphics;
public class Cell 
{
    boolean state = false;
    Color color = Color.black;
    int x = 0;
    int y = 0;
    int n = 0;
    public Cell(int X, int Y)
    {
        x = X;
        y = Y;
    }
    public void update(Cell[][]cells)
    {
        n = 0;
        for(int i = Math.max(x - 1,0); i <= Math.min( x + 1, cells.length - 1); i++)
        {
            for(int j = Math.max(y - 1,0); j <= Math.min( y + 1, cells.length - 1); j++)
            {
//                if(i == x && j == y)
//                {
// 
//                }
//                else
//                {
                    if(cells[i][j].state )
                    {
                        n++;
                    }
//                }
            }
        }
        Color(n);
    }
    public void statusUpdate()
    {
        if(state == true)
        {
            if(n == 2 || n == 3)
            {
                state = true;
            }
            else
            {
                state = false;
            }
        }
        if(state == false)
        {
            if(n == 3)
            {
                state = true;
            }
            else
            {
                state = false;
            }
        }
    }
    public void draw(Graphics screen)
    {
        if(state == true)
        {
            screen.setColor(color);
            screen.fillRect(4*x, 4*y, 4, 4);
        }
    }
    public void setState(boolean s)
    {
        state = s;
    }
    public void Color(int a)
    {
    	if(a == 0)
    	{
    		color = Color.BLUE;
    	}
    	if(a == 1)
    	{
    		color = new Color(66,200,66);
    	}
    	if(a == 2)
    	{
    		color = Color.GREEN;
    	}
    	if(a == 3)
    	{
    		color = Color.YELLOW;
    	}
    	if(a == 4)
    	{
    		color = new Color(255,132,0);
    	}
    	if(a > 4)
    	{
    		color = Color.RED;
    	}
    }
}
