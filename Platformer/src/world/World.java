package world;

import java.awt.*;
import java.util.*;
import platform.*;
import character.Character;

public class World
{
	private ArrayList<Platform> platforms;
	private ArrayList<Character> characters;
	
	public World()
	{
		platforms = new ArrayList<Platform>();
		characters = new ArrayList<Character>();
	}
	
	public void update()
	{
		for(Character character : characters)
		{
			character.update();
		}
	}
	
	public void draw(Graphics graphics)
	{
		for(Platform platform : platforms)
		{
			platform.draw(graphics);
		}
		
		for(Character character : characters)
		{
			character.draw(graphics);
		}
	}
	
	public void add(Platform platform)
	{
		platform.setWorld(this);
		platforms.add(platform);
	}
	
	public void add(Character character)
	{
		character.setWorld(this);
		characters.add(character);
	}
	
	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}
	public ArrayList<Character> getCharacters()
	{
		return characters;
	}
}
