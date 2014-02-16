import java.awt.Color;
import java.awt.Graphics;
public class Cell 
{
    boolean state = false;
    Color color = Color.black;
    int x = 0;
    int y = 0;
    int n = 0;
    int nSumA = 0;
    int nSumB = 0;
    int nA;
    int nB;
    int a = 2;
    int b = 3;
    public Cell(int X, int Y)
    {
        x = X;
        y = Y;
    }
    public void update(Cell[][]cells)
    {
        n = 0;
        nSumA = 0;
        nSumB = 0;
        for(int i = Math.max(x - 1,0); i <= Math.min( x + 1, cells.length - 1); i++)
        {
            for(int j = Math.max(y - 1,0); j <= Math.min( y + 1, cells.length - 1); j++)
            {
                if(i == x && j == y)
                {
 
                }
                else
                {
                    if(cells[i][j].state )
                    {
                    	nSumA += cells[i][j].a;
                    	nSumB += cells[i][j].b;
                        n++;
                    }
                }
            }
        }
        if(n > 0)
        {
        	nA = nSumA/n;
        	nB = nSumB/n;
        }
        else
        {
        	nA = 2;
        	nB = 3;
        }
        Color(n);
        color = new Color(35*b,255 - a*35,255);
    }
    public void statusUpdate()
    {
        if(state == true)
        {
            if(n >= a && n <= b)
            {
                survive();
            }
            else
            {
                die();
            }
        }
        if(state == false)
        {
            if(n == 3)
            {
                revive();
            }
            else
            {
                die();
            }
        }
    }
    public void revive()
    {
    	state = true;
    	a = nA;
    	b = nB;
    	int i = (int)(30*Math.random());
    	int j = (int)(30*Math.random());
    	if(i == 1)
    	{
    		a = a + 2 - (int)(4*Math.random());
    		if(a <= 2)
    		{
    			a = 2;
    		}
    		if(a >= b)
    		{
    			a = b;
    		}
    	}
    	if(j == 1)
    	{
    		b = b + 2 - (int)(4*Math.random());
    		if(b >= 7)
    		{
    			b = 7;
    		}
    		if(b <= a)
    		{
    			b = a;
    		}
    	}
    }
    public void survive()
    {
    	state = true;
    }
    public void die()
    {
    	state = false;
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
