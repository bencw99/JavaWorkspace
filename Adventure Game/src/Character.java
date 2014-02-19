import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
public abstract class Character extends Applet implements Locatable
{
    final BufferedImage spritePage;
    int direction = 0;
    boolean walking = false;
    boolean sprint = false;
    boolean alive = true;
    double baseSpeed = 1;
    double speed = baseSpeed;
    int a = 0;
    int z = 0;
    int strength = 10;
    double mpBase = 50;
    double magicPoints = mpBase;
    double mpUpdate = 0.1;
    double hpBase = 100;
    double healthPoints = hpBase;
    double hpUpdate = 0.05;
    double x = 0;
    double y = 0;
    int walkPhase = 1;
    int xID;
    int yID;
    final int width = 32;
    final int height = 48;
    BufferedImage[][] sprites;
    int[] similarity = new int[3];
    Item item = new Item(-1, true);
    ArrayList<Spell> spells = new ArrayList<Spell>();
    public double getXPos()
    {
        return x;
    }
    public double getYPos()
    {
        return y;
    }
    public void executeSpell(ArrayList<Character> characters) throws IOException
    {
        if(item.type == 1 || item.type == 2)
        {
            if(magicPoints >= 5)
            {
                Spell spell = new Spell(x, y, direction, item.type);
                spells.add(spell);
                magicPoints -= 5;
            }
        }
        if(item.type == 3)
        {
            if(magicPoints >= 10)
            {
                summon(characters, strength);
                magicPoints -= 10;
            }
        }
    }
    public Character(double a, double b, int c, int d, int t, int[]sim) throws IOException
    {
        x = a;
        y = b;
        xID = c;
        yID = d;
        spritePage = ImageIO.read(new File("characters.png"));
        sprites = new BufferedImage[3][4];
        hold(new Item(t,true));
        for (int i = 0; i < sprites.length; i++)
        {
            for (int j = 0; j < sprites[0].length; j++)
            {
                sprites[i][j] = spritePage.getSubimage(sprites.length * width * xID + i * width, sprites[0].length * height * yID + j * height, width, height);
            }
        }
        similarity = sim;
    }
    public void draw(Graphics screen)
    {
        if(walking)
        {
            a += 1;
            if(a % ((int)(4/speed) + 1) == 0)
            {
                z++;
                if(z % 4 == 0)
                {
                    walkPhase = 2;
                }
                if(z % 4 == 1)
                {
                    walkPhase = 1;
                }
                if(z % 4 == 2)
                {
                    walkPhase = 0;
                }
                if(z % 4 == 3)
                {
                    walkPhase = 1;
                }
            }
        }
        else
        {
            walkPhase = 1;
        }
        screen.drawImage(sprites[walkPhase][direction], (int)x - width/2, (int)y - height/2, width, height, this);
        for(int i = 0; i < spells.size(); i++)
        {
            spells.get(i).draw(screen);
        }
    }
    public void setDirection(int d)
    {
        direction = d;
    }
    public void setWalking(boolean b)
    {
        walking = b;
    }
    public void update(ArrayList<Spell> spell, ArrayList<Character> characters)
    {
        if(walking)
        {
            switch(direction)
            {
            case 0:
            {
                y += speed;
                break;
            }
            case 1:
            {
                x -= speed;
                break;
            }
            case 2:
            {
                x += speed;
                break;
            }
            case 3:
            {
                y -= speed;
                break;
            }
            }
        }
        if(x > Game.screenWidth)
        {
            x = 0;
        }
        if(x < 0)
        {
            x = Game.screenWidth;
        }
        if(y > Game.screenHeight)
        {
            y = 0;
        }
        if(y < 0)
        {
            y = Game.screenHeight;
        }
        for(int i = 0; i < spells.size(); i++)
        {
            spells.get(i).update();
            if(spells.get(i).x < -30 || spells.get(i).x > Game.screenWidth + 10 ||spells.get(i).y < -30 || spells.get(i).y > Game.screenHeight + 10)
            {
                spells.remove(i);
            }
        }
        hit(spell);
        magicPoints += mpUpdate;
        healthPoints += hpUpdate;
        if(magicPoints > mpBase)
        {
            magicPoints = mpBase;
        }
        if(healthPoints > hpBase)
        {
            healthPoints = hpBase;
        }
        if(healthPoints <= 0)
        {
        	death(characters);
        }
        if(sprint && walking)
        {
            healthPoints -= 0.1;
        }
    }
    public void sprintOn()
    {
        speed = 2*baseSpeed;
        sprint = true;
    }
    public void sprintOff()
    {
        speed = baseSpeed;
        sprint = false;
    }
    public void hold(Item i)
    {
        int j = item.type;
        i.setHeld(true);
        item = i;
        if(j == 0)
        {
            baseSpeed /= 2;
        }
        if(item.type == 0)
        {
            baseSpeed *= 2;
        }
        speed = baseSpeed;
    }
    public void pickUp(Item[]items)
    {
        for(int i = 0; i < items.length; i++)
        {
            if(distance(items[i]) < 15)
            {
                hold(items[i]);
                break;
            }
        }
    }
    public void summon(ArrayList<Character> characters , int n) throws IOException
    {
        for(int i = 0; i < Math.min(n, characters.size()); i++)
        {
            int j = (int)(characters.size()*Math.random());
            if(j != 0) //FIX
                characters.set(j,new Summoned(characters.get(j).x, characters.get(j).y, characters.get(j).xID, characters.get(j).yID, characters.get(j).item.type, this, characters.get(i).similarity ));
        }
    }
    public void release(ArrayList<Character> characters) throws IOException
    {
        for(int i = 1; i < characters.size(); i++)
        {
            characters.set(i, new Other(characters.get(i).x, characters.get(i).y, characters.get(i).xID, characters.get(i).yID, characters.get(i).item.type, characters.get(i).similarity));
        }
    }
    public double distance(Spell spell)
    {
        return Math.sqrt(Math.pow(x - spell.x,2) + Math.pow(y - spell.y,2));
    }
    public double distance(Item item)
    {
    	return Math.sqrt(Math.pow(x - item.x,2) + Math.pow(y + height/2 - item.y,2));
    }
    public double distance(Character character)
    {
        return Math.sqrt(Math.pow(x - character.x,2) + Math.pow(y - character.y,2));
    }
    public void setAlive(boolean b)
    {
        alive = b;
    }
    public void hit(ArrayList<Spell> spells)
    {
    	for(int i = 0; i < spells.size(); i++)
    	{
    		if(distance(spells.get(i)) < 20)
    		{
    			if(this.spells.indexOf(spells.get(i)) < 0)
    			{
    		    	speed = 3*baseSpeed/2;
	    			spells.remove(i);
	    			healthPoints -= 55;
    			}
    		}
    	}
    }
    public void death(ArrayList<Character> characters)
    {
    	characters.remove(this);
    }
    public int similar(Character character)
    {
    	int different = 0;
    	for(int i = 0; i < similarity.length; i++)
    	{
    		different += similarity[i] - character.similarity[i];
    	}
    	return different;
    }
    public void change(Character character)
    {
    	if(Math.random() < 0.05)
    	{
        	double d = distance(character);
    		int j = (int)(Math.random()*similarity.length);
    		int i = character.similarity[j] - similarity[j];
    		if(d > 10)
    		{
	    		similarity[j] += (int)(i/d);
    		}
    		else
    		{
    			similarity[j] += (int)(i/10);
    		}
    		if(Math.random() < 0.1)
    		{
    			similarity[j] += (int)(100*Math.random()) - 50;
    		}
    	}
    }
    public abstract void walk(List<Spell> spells, ArrayList<Character> characters);
    public abstract void drawStats(Graphics screen);
}