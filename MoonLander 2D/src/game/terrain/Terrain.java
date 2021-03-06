package game.terrain;


import gui.Main;

import java.awt.Color;
import java.awt.Graphics;
public class Terrain
{
    double heights[] = new double[100];
    private final int landingPos;
    public Terrain()
    {
        landingPos = 5 + (int)(75*Math.random());
    }
    public void setTerrain()
    {
        for(int x = 0; x < heights.length; x++)
        {
            heights[x] = 50 + 100*Math.random();
        }
        for(int x = getLandingPos(); x < getLandingPos() + 10; x ++)
        {
        	heights[x] = heights[getLandingPos()];
        }
    }
    public void spire()
    {
    	int b = (int)(2 + (heights.length - 4)*Math.random());
    	while(b > getLandingPos() - 10 && b < getLandingPos() + 20)
    	{
    		b = (int)(heights.length*Math.random());
    	}
    	heights[b] = 200 + Main.getLevel()*100*Math.random();
    }
    public void change()
    {
        for(int x = 0; x < heights.length; x++)
        {
            heights[x] += -75 + 150*Math.random();
        }
        for(int x = getLandingPos(); x < getLandingPos() + 10; x ++)
        {
        	heights[x] = heights[getLandingPos()];
        }
    }
    public void smooth()
    {
        for(int x = 0; x < heights.length; x++)
        {
            if( x == 0)
            {
                heights[x] = (heights[x] + 0.2*heights[x+1])/1.2;
            }
            if( x == heights.length - 1)
            {
                heights[x] = (heights[x] + 0.2*heights[x-1])/1.2;
            }
            if((x > 1) && (x < heights.length - 1))
            {
                heights[x] = (heights[x] + 0.1*heights[x-1] + 0.1*heights[x+1])/1.2;
            }
        }
    }
    public double getHeight(int x)
    {
        double height = 0;
        if(x % (1000/heights.length) == 0)
        {
            height = heights[x/10];
        }
        else
        {
            height = ((x % (1000/heights.length))*heights[(x - (x % (1000/heights.length)))/10] + ((1000/heights.length) - (x % (1000/heights.length)))*heights[(x + 1000/heights.length - 2*(x % (1000/heights.length)))/10])/(1000/heights.length);
        }
        return height;
    }
    public void draw(Graphics screen)
    {
        int X[] = new int[heights.length + 2];
        int Y[] = new int[heights.length + 2];
        for(int i = 0; i < heights.length; i++)
        {
            X[i] = i*10;
            Y[i] = 600 - (int) heights[i];
        }
        X[heights.length] = 990;
        X[heights.length + 1] = 0;
        Y[heights.length] = 600;
        Y[heights.length + 1] = 600;
        Color brown = new Color(170,85,50);
        screen.setColor(brown);
        screen.fillPolygon( X , Y , heights.length + 2);
    }
    public void getSpireConfiguration()
    {
        setTerrain();
        for(int i = 0; i < Main.getLevel(); i++)
        {
        	spire();
        }
        for(int i = 0; i < 50; i++ )
        {
            smooth();
        }
        change();
        for(int i = 0; i < 8; i++ )
        {
            smooth();
        }
    }
    public void getSmoothConfiguration()
    {
    	setTerrain();
        for(int i = 0; i < 10; i++ )
        {
            smooth();
        }
    }
	public int getLandingPos()
	{
		return landingPos;
	}
}
