import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Other extends Character
{
	public Other(double a, double b, int c, int d, int t, int[]sim) throws IOException
	{
		super(a, b, c, d, t, sim);
	}
	public void walk(List<Spell> spells, ArrayList<Character> characters)
	{
		int i = (int)(100*Math.random());
		int j = (int)(100*Math.random());
		int l = (int)(2*Math.random());
		int current = 0;
		for(int k = 0; k < characters.size(); k ++)
		{
			change(characters.get(k));
			if(similar(characters.get(k)) <= similar(characters.get(current)));
			{
				current = k;
			}
		}
		if(i > 95)
		{
			if(l == 0)
			{
				if(j % 3 != 0 && distance(characters.get(current)) > 70)
				{
					walking = true;
				}
				else
				{
					walking = false;
				}
				if(Math.abs(y - characters.get(current).y) > Math.abs(x - characters.get(current).x))
				{
					if(y - characters.get(current).y > 0)
					{
						direction = 3;
					}
					else
					{
						direction = 0;
					}
				}
				if(Math.abs(x - characters.get(current).x) > Math.abs(y - characters.get(current).y))
				{
					if(x - characters.get(current).x > 0)
					{
						direction = 1;
					}
					else
					{
						direction = 2;
					}
				}
			}
			else
			{
				direction = (i % 4);
				if(j % 2 == 0)
				{
					walking = true;
				}
				else
				{
					walking = false;
				}
			}
		}
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
