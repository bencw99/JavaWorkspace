import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Summoned extends Character
{
	Character character;
	public Summoned(double a, double b, int c, int d, int t, Character cha, int[]sim) throws IOException
	{
		super(a, b, c, d, t, sim);
		character = cha;
	}
	public void walk(List<Spell> spells, ArrayList<Character> characters) 
	{
		int i = (int)(100*Math.random());
		int j = (int)(2*Math.random());
		if(i > 95)
		{
			if(j % 3 != 0)
			{
				walking = true;
			}
			else
			{
				walking = false;
			}
			if(Math.abs(y - character.y) > Math.abs(x - character.x))
			{
				if(y - character.y > 0)
				{
					direction = 3;
				}
				else
				{
					direction = 0;
				}
			}
			if(Math.abs(x - character.x) > Math.abs(y - character.y))
			{
				if(x - character.x > 0)
				{
					direction = 1;
				}
				else
				{
					direction = 2;
				}
			}
		}
		speed = Math.max(Math.min(baseSpeed + distance(character)/50 - 2, 2), 1);
		for(int f = 0; f < spells.size(); f++)
		{
			if(distance(spells.get(f)) < 50)
			{
				walking = true;
				speed = 60*(baseSpeed)/(distance(spells.get(f)) + 10);
				if(spells.get(f).direction == 0 || spells.get(f).direction == 3)
				{
					if(x - spells.get(f).x < 0)
					{
						direction = 1;
					}
					else
					{
						direction = 2;
					}
				}
				if(spells.get(f).direction == 1 || spells.get(f).direction == 2)
				{
					if(y - spells.get(f).y < 0)
					{
						direction = 3;
					}
					else
					{
						direction = 0;
					}
				}
			}
			else
			{
				speed = baseSpeed;
			}
		}
	}
	public void drawStats(Graphics screen)
	{
		
	}
}
