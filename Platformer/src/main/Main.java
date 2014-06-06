package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import character.Player;
import kinematics.Position;
import kinematics.Velocity;
import platform.Platform;
import world.World;

public class Main extends JPanel
{
	public final static int screenWidth = 500;
	public final static int screenHeight = 500;
	public static JFrame frame = new JFrame();
	
	public static World world;
	
	public static void main(String[] args)
	{
        frame.setSize(screenWidth,screenHeight);
        frame.setTitle("Platformer");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main drawer = new Main();
        frame.add(drawer);
        frame.setVisible(true);
        
        Player player = new Player(new Position(250, 200), new Velocity(0, 0), 20, 40);
        Player player2 = new Player(new Position(200, 200), new Velocity(0, 0), 20, 40);
        
        world = new World();
        world.add(new Platform(new Position(250, 400), 200, 20));
        world.add(player);
        world.add(player2);
        
        for(int i = 0; i < 10000; i ++)
        {
        	try
			{
				Thread.sleep(10);
			} 
        	catch (InterruptedException e)
			{
				e.printStackTrace();
			}
        	System.out.println(player.getPos().getX() + " " + player.getPos().getY());
        	
        	player.jump();
        	player2.jump();
        	frame.repaint();
        	world.update();
        }
        
        frame.repaint();
	}
	
	public void paintComponent(Graphics graphics)
	{
		world.draw(graphics);
	}
}
