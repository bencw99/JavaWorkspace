import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Player extends Character
{
    Color color;
    double angle = 0;
    public Player(double a, double b, int c, int d, int t, Color col, int[]sim) throws IOException
    {
        super(a, b, c, d, t,sim);
        color = col;
    }
    public void draw(Graphics screen)
    {
    	drawStats(screen);
        super.draw(screen);
    }
    public void drawStats(Graphics screen)
    {
        screen.setColor(color);
        for(double i = 0; i <= Math.PI/2; i += 0.01)
        {
            if(i > ((double)magicPoints/(double)mpBase)*Math.PI/2)
            {
                screen.setColor(Color.red);
            }
            screen.fillRect((int)x + (int)((width/2 + 10)*Math.cos(angle + i)) - 1,(int)y + height/2 + (int)((10)*Math.sin(angle + i) - 5), 2, 2);
            screen.fillRect((int)x + (int)((width/2 + 10)*Math.cos(angle + i + Math.PI)) - 1,(int)y + height/2 + (int)((10)*Math.sin(angle + i + Math.PI) - 5), 2, 2);
            screen.setColor(color);
            if(i > ((double)healthPoints/(double)hpBase)*Math.PI/2)
            {
                screen.setColor(Color.red);
            }
            screen.fillRect((int)x + (int)((width/2 + 14)*Math.cos(angle + i)) - 1,(int)y + height/2 + (int)((14)*Math.sin(angle + i) - 5), 2, 2);
            screen.fillRect((int)x + (int)((width/2 + 14)*Math.cos(angle + i + Math.PI)) - 1,(int)y + height/2 + (int)((14)*Math.sin(angle + i + Math.PI) - 5), 2, 2);
            screen.setColor(color);
        }
    }
    public void update(ArrayList<Spell>spell,ArrayList<Character> characters)
    {
        super.update(spell, characters);
        angle += 0.1;
    }
    public void walk(List<Spell> spells, ArrayList<Character> characters)
    {
 
    }
}