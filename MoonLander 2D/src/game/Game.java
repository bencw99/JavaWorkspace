package game;
import game.terrain.Terrain;
import game.updateable.Updatable;

import java.awt.Graphics;
import java.util.*;
public class Game
{
	Graphics screen;
	Terrain terrain;
	ArrayList<Updatable> components;
	enum State {IDLE, STARTED, PAUSED};
	State state;
	int level;
	int score;
	final int screenWidth = 700;
	final int screeHeight = 500;
	
	public Game(Graphics screen)
	{
		this.screen = screen;
		terrain = new Terrain();
		components = new ArrayList<Updatable>();
		state = State.IDLE;
		level = 1;
		score = 0;
	}
	
	public void start()
	{
		state = State.STARTED;
	}
	
	public void pause()
	{
		state = State.PAUSED;
	}
	
	public void stop()
	{
		state = State.IDLE;
	}
	
	public void update()
	{
		switch(state)
		{
		case IDLE:
		{
			break;
		}
		case STARTED:
		{
			for(Updatable component : components)
			{
				component.update();
			}
			break;
		}
		case PAUSED:
		{
			break;
		}
		}

	}
	
	public void draw()
	{
		switch(state)
		{
		case IDLE:
		{
			//Draw IDLE screen
			break;
		}
		case STARTED:
		{
			terrain.draw(screen);
			for(Updatable component : components)
			{
				component.draw(screen);
			}
			break;
		}
		case PAUSED:
		{
			//Draw PAUSED screen
			break;
		}
		}
	}
	
	public void add(Updatable component)
	{
		components.add(component);
	}
	
}
