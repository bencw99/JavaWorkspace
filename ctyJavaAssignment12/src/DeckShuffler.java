import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class DeckShuffler	extends Applet
{
	int cards[] = new int[10];
	public void paint(Graphics screen)
	{
		setBackground(Color.white);
		Image deck[] = new Image[52];
		deck[0] = getImage(getDocumentBase() , "c1.gif");
		deck[1] = getImage(getDocumentBase() , "c2.gif");
		deck[2] = getImage(getDocumentBase() , "c3.gif");
		deck[3] = getImage(getDocumentBase() , "c4.gif");
		deck[4] = getImage(getDocumentBase() , "c5.gif");
		deck[5] = getImage(getDocumentBase() , "c6.gif");
		deck[6] = getImage(getDocumentBase() , "c7.gif");
		deck[7] = getImage(getDocumentBase() , "c8.gif");
		deck[8] = getImage(getDocumentBase() , "c9.gif");
		deck[9] = getImage(getDocumentBase() , "c10.gif");
		deck[10] = getImage(getDocumentBase() , "cj.gif");
		deck[11] = getImage(getDocumentBase() , "ck.gif");
		deck[12] = getImage(getDocumentBase() , "cq.gif");
		deck[13] = getImage(getDocumentBase() , "d1.gif");
		deck[14] = getImage(getDocumentBase() , "d2.gif");
		deck[15] = getImage(getDocumentBase() , "d3.gif");
		deck[16] = getImage(getDocumentBase() , "d4.gif");
		deck[17] = getImage(getDocumentBase() , "d5.gif");
		deck[18] = getImage(getDocumentBase() , "d6.gif");
		deck[19] = getImage(getDocumentBase() , "d7.gif");
		deck[20] = getImage(getDocumentBase() , "d8.gif");
		deck[21] = getImage(getDocumentBase() , "d9.gif");
		deck[22] = getImage(getDocumentBase() , "d10.gif");
		deck[23] = getImage(getDocumentBase() , "dj.gif");
		deck[24] = getImage(getDocumentBase() , "dk.gif");
		deck[25] = getImage(getDocumentBase() , "dq.gif");
		deck[26] = getImage(getDocumentBase() , "h1.gif");
		deck[27] = getImage(getDocumentBase() , "h2.gif");
		deck[28] = getImage(getDocumentBase() , "h3.gif");
		deck[29] = getImage(getDocumentBase() , "h4.gif");
		deck[30] = getImage(getDocumentBase() , "h5.gif");
		deck[31] = getImage(getDocumentBase() , "h6.gif");
		deck[32] = getImage(getDocumentBase() , "h7.gif");
		deck[33] = getImage(getDocumentBase() , "h8.gif");
		deck[34] = getImage(getDocumentBase() , "h9.gif");
		deck[35] = getImage(getDocumentBase() , "h10.gif");
		deck[36] = getImage(getDocumentBase() , "hj.gif");
		deck[37] = getImage(getDocumentBase() , "hk.gif");
		deck[38] = getImage(getDocumentBase() , "hq.gif");
		deck[39] = getImage(getDocumentBase() , "s1.gif");
		deck[40] = getImage(getDocumentBase() , "s2.gif");
		deck[41] = getImage(getDocumentBase() , "s3.gif");
		deck[42] = getImage(getDocumentBase() , "s4.gif");
		deck[43] = getImage(getDocumentBase() , "s5.gif");
		deck[44] = getImage(getDocumentBase() , "s6.gif");
		deck[45] = getImage(getDocumentBase() , "s7.gif");
		deck[46] = getImage(getDocumentBase() , "s8.gif");
		deck[47] = getImage(getDocumentBase() , "s9.gif");
		deck[48] = getImage(getDocumentBase() , "s10.gif");
		deck[49] = getImage(getDocumentBase() , "sj.gif");
		deck[50] = getImage(getDocumentBase() , "sk.gif");
		deck[51] = getImage(getDocumentBase() , "sq.gif");
		for(int j = 0;j <= 9; j++)
		{
			if( j<= 4)
			{
				screen.drawImage(deck[cards[j]], 20 + 100*(j), 20 ,this);
			}
			else
			{
				screen.drawImage(deck[cards[j]], 20 + 100*(j-5), 150 ,this);
			}
		}
	}
	public void init()
	{
		setSize(510,270);
		setVisible(true);
	}
	public void start()
	{
		for(int j = 0; j <= 9; j++)
		{
			cards[j] = (int)(Math.random()*52);
			for(int i = 1; i <= j; i++)
			{
				while(cards[j] == cards[j-i])
				{
					cards[j] = (int)(Math.random()*52);
				}
			}
		}
		repaint();
	}
}
