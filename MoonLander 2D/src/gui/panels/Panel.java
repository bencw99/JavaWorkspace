package gui.panels;


import gui.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
public class Panel extends JPanel
{
	JTabbedPane pane;
	HomePanel homePanel;
	SettingsPanel settingsPanel;
	GamePanel gamePanel;
	OptionPanel optionPanel;
	ScorePanel scorePanel;
	public Panel()
	{
		pane = new JTabbedPane();
		homePanel = new HomePanel();
		settingsPanel = new SettingsPanel();
		gamePanel = new GamePanel();
		optionPanel = new OptionPanel();
		scorePanel = new ScorePanel();
		
		pane.addTab("Home", homePanel);
		pane.addTab("Game", gamePanel);
		pane.addTab("Settings", settingsPanel);
		pane.addTab("Options", optionPanel);
		pane.addTab("High Scores", scorePanel);
		
		setLayout(new BorderLayout());
		add(pane, BorderLayout.CENTER);
	}
    public void paint(Graphics screen)
    {
    	pane.paint(screen);
//    	if(Main.isStart())
//    	{
//            setBackground(Color.BLACK);
//    		Main.getTerrain().drawTerrain(screen);
//    		Main.getLander().drawLander(screen);
//    		for(int a = 0; a < Main.getPortals().length; a++)
//    		{
//    			Main.getPortals()[a].draw(screen);
//    		}
//    		screen.setColor(Color.WHITE);
//    		Font font = new Font("Cambria",Font.BOLD,30);
//    		screen.setFont(font);
//	    	Main.getLander().land(Main.getTerrain());
//	    	if(Main.getLander().isLand())
//	    	{
//	    		screen.drawString("Landed Successfully", 300, 100);
//	    	}
//	    	else if(Main.getLander().land(Main.getTerrain()) == false)
//	    	{
//	    		screen.drawString("Landing Failed", 300, 100);
//	    	}
//	    	if(Main.getLander().isState() == false)
//	    	{
//	    		Font f2 = new Font("Cambria",Font.BOLD, 80);
//	    		screen.setFont(f2);
//	           	screen.drawString("GAME OVER", 250, 200);
//	    	}
//    		Font f = new Font("Cambria",Font.BOLD,20);
//    		screen.setFont(f);
//    		screen.drawString("Level " + Main.getLevel(), 50, 50);
//    		screen.drawString("Score: " + Main.getScore(), 50, 100);
//	    	screen.drawString("Fuel: ", 820, 50);
//        	Color c = new Color(250 - Main.getLander().getFuel()/4,5 + Main.getLander().getFuel()/4,0);
//        	screen.setColor(c);
//        	screen.drawRect(870, 33, 100, 20);
//        	screen.fillRect(873, 36, Main.getLander().getFuel()/10 - 5, 15);
//        	screen.setColor(Color.WHITE);
//	    	if(Math.pow(Main.getLander().getxVel()*Main.getLander().getxVel() + Main.getLander().getyVel()*Main.getLander().getyVel(), 0.5) > 1)
//	    	{
//	    		screen.setColor(Color.RED);
//	    	}
//	    	screen.drawString("Speed " + (int)(100*Math.pow(Main.getLander().getxVel()*Main.getLander().getxVel() + Main.getLander().getyVel()*Main.getLander().getyVel(), 0.5)), 820, 100);
//	    	screen.setColor(Color.WHITE);
//    	}
//    	else if(Main.isSettings() == false)
//    	{
//    		setBackground(Color.WHITE);
//			Font font = new Font("Cambria",Font.BOLD, 80);
//			screen.setFont(font);
//        	screen.drawString("Moon Lander", 270, 200);
//			Font f = new Font("Cambria",Font.BOLD, 20);
//			screen.setFont(f);
//        	screen.drawString("PRESS SPACE TO START", 413, 300);
//        	screen.drawString("PRESS S TO OPEN SETTINGS", 395, 350);
//        	screen.drawString("High Score: " + Main.getHighscore(), 440, 400);	
//    	}
//    	if(Main.isSettings() && Main.isStart() == false)
//    	{
//			Font font = new Font("Cambria",Font.BOLD, 40);
//			screen.setFont(font);
//        	screen.drawString("Settings: ", 100, 100);
//			Font f = new Font("Cambria",Font.BOLD, 20);
//			screen.setFont(f);
//			screen.drawString("Press 1 to change lander color to blue", 100, 200);
//			screen.drawString("Press 2 to change lander color to green", 100, 250);
//			screen.drawString("Press 3 to change lander color to red", 100, 300);
//			screen.drawString("Press 4 to change lander color to random", 100, 350);
//			screen.drawString("Press 5 to change to beginner controls", 100, 400);
//			screen.drawString("Press 6 to change to advanced controls", 100, 450);
//			screen.drawString("Press s to return to home screen", 100, 500);
//    	}
    }
}
