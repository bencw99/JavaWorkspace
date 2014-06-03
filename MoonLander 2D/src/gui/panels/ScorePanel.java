package gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;
public class ScorePanel extends JPanel
{

	private JLabel highScoreLabel;
	private JLabel highScoreNameLabel;
	private JTextField [] highScoreNames;
	private JLabel scoreLabel;
	private JTextField [] highScores;
	public ScorePanel()
	{
		setBackground(Color.WHITE);
		
		Font titleFont = new Font("Default", Font.BOLD, 60);
		Font font = new Font("", Font.PLAIN, 36);
		highScoreLabel = new JLabel("High Scores");
		highScoreLabel.setFont(titleFont);
		highScoreNameLabel = new JLabel("Name");
		highScoreNameLabel.setFont(font);
		highScoreNames = new JTextField[5];
		for(int i = 0; i < highScoreNames.length; i ++)
		{
			highScoreNames[i] = new JTextField(" ");
			highScoreNames[i].setColumns(12);
			highScoreNames[i].setFont(font);
			highScoreNames[i].setEditable(false);
		}
		scoreLabel = new JLabel("Score");
		scoreLabel.setFont(font);
		highScores = new JTextField[5];
		for(int i = 0; i < highScores.length; i ++)
		{
			highScores[i] = new JTextField(" ");
			highScores[i].setColumns(13);
			highScores[i].setFont(font);
			highScores[i].setEditable(false);
		}
		
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.add(highScoreLabel);
		add(top, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1,2));
		JPanel nameFlow = new JPanel();
		nameFlow.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel scoreFlow = new JPanel();
		scoreFlow.setLayout(new FlowLayout(FlowLayout.CENTER));
		nameFlow.add(highScoreNameLabel);
		scoreFlow.add(scoreLabel);
		int i = 1; 
		for(JTextField highScoreName : highScoreNames)
		{
			JPanel currentName = new JPanel();
			currentName.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel label = new JLabel(i + ". ");
			label.setFont(font);
			label.setLabelFor(highScoreName);
			currentName.add(label);
			currentName.add(highScoreName);
			i ++;
			nameFlow.add(currentName);
		}
		for(JTextField highScore : highScores)
		{
			JPanel currentScore = new JPanel();
			currentScore.add(highScore);
			scoreFlow.add(currentScore);
		}
		center.add(nameFlow);
		center.add(scoreFlow);
		add(center, BorderLayout.CENTER);
		
	}
	public void paint(Graphics screen)
	{
		super.paint(screen);
	}
}
