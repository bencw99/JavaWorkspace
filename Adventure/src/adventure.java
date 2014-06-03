import javax.swing.JOptionPane;
public class adventure 
{
	public static void main(String[] args)
	{
		boolean cont = true;
		while(cont)
		{
	    	int i = 0;
	    	i =JOptionPane.showConfirmDialog(null, "Do you want to start your adventure?", "Adventure", JOptionPane.YES_NO_OPTION, 1);
	    	if(i == JOptionPane.YES_OPTION)
	    	{
	    		death();
	    	}
	    	else
	    	{
	    		i = JOptionPane.showConfirmDialog(null, "Too bad, now do you want to jump off a cliff?", "Adventure", JOptionPane.YES_NO_CANCEL_OPTION);
	    		if(i == JOptionPane.YES_OPTION)
	    		{
	    			i = JOptionPane.showConfirmDialog(null, "A bird catches you and forces you to live.", "Adventure", JOptionPane.YES_NO_OPTION);
	    			if(i == JOptionPane.YES_OPTION)
	    			{
	    				String j =JOptionPane.showInputDialog("yes, that is the answer to life, and so is the number...");
	    				try
	    				{
	    					if(j.equals("42"))
	    					{
	    						JOptionPane.showMessageDialog(null, "CORRECT");
	    						death();
	    					}
	            	else
	            	{
	                	JOptionPane.showMessageDialog(null, "INCORRECT, YOU FREAKING NUB");
	                	i = JOptionPane.showConfirmDialog(null, "You see an old man begging for food, do you feed him?", "Adventure", JOptionPane.YES_NO_OPTION);
	                	if(i == JOptionPane.YES_OPTION)
	                	{
	                    	JOptionPane.showMessageDialog(null, "He is evil and blows up the whole world.");
	                    	death();
	                	}
	                	else
	                	{
	                    	JOptionPane.showMessageDialog(null, "GJ GREED FTW");
	                    	JOptionPane.showMessageDialog(null, "He tries to stab you and take your money");
	                    	i = JOptionPane.showConfirmDialog(null, "Kill him?","Adventure", JOptionPane.YES_NO_OPTION );
	                    	if(i == JOptionPane.YES_OPTION)
	                    	{
	                        	JOptionPane.showMessageDialog(null, "You kill him.");
	                        	JOptionPane.showMessageDialog(null, "Stuff happens.");
	                        	death();
	                    	}
	                    	else
	                    	{
	                        	JOptionPane.showMessageDialog(null, "After contemplating it, you kill him anyways.");
	                        	JOptionPane.showMessageDialog(null, "You just saved the world from the evil lord of doom.");
	                        	JOptionPane.showMessageDialog(null, "After some contemplation, you decide to become the new dark lord.");
	                        	JOptionPane.showMessageDialog(null, "The world hates you.");
	                        	JOptionPane.showMessageDialog(null, "But you know you are a boss.");
	                        	JOptionPane.showMessageDialog(null, "Or not.");
	                        	JOptionPane.showMessageDialog(null, "TROLOLOLOLOL");
	                        	death();
	                    	}
	                	}
	            	}
	   		 }
	   		 catch(NullPointerException e)
	   		 {
	            	JOptionPane.showMessageDialog(null, "too bad no canceling");
	            	for(int x = 0;x<5; x++)
	            	{
	                	JOptionPane.showMessageDialog(null, "too bad no canceling");
	            	}
	            	death();
	   		 }
	    }
	        	else
	        	{
	            	JOptionPane.showMessageDialog(null, "Your answer makes no sense, anyways lets continue.");
	            	i = JOptionPane.showConfirmDialog(null, "You find a sword lying by a sleeping hobo, take it?", "Adventure", JOptionPane.YES_NO_OPTION);
	            	if(i == JOptionPane.YES_OPTION)
	            	{
	                	JOptionPane.showMessageDialog(null, "The sleeping hobo is the prince of the country, and he orders you to be locked up.");
	                	i = JOptionPane.showConfirmDialog(null, "Break out of prison?", "Adventure", JOptionPane.YES_NO_OPTION);
	                	if(i == JOptionPane.YES_OPTION)
	                	{
	                    	JOptionPane.showMessageDialog(null, "They catch you and execute you.");
	                    	death();
	                	}
	                	else
	                	{
	                    	JOptionPane.showMessageDialog(null, "They decide to release you.");
	                    	i = JOptionPane.showConfirmDialog(null, "Take over the world?", "Adventure", JOptionPane.YES_NO_OPTION);
	                    	if(i == JOptionPane.YES_OPTION)
	                    	{
	                        	JOptionPane.showMessageDialog(null, "You get killed in the process.");
	                        	death();
	                    	}
	                    	else
	                    	{	
	                        	JOptionPane.showMessageDialog(null, "You end up ruling it somehow anyways GG.");
	                        	JOptionPane.showMessageDialog(null, "You have nearly fulfilled your destiny, and you are about to be released!");
	                        	JOptionPane.showMessageDialog(null, "Or not.");
	                        	death();
	                    	}
	                	}
	            	}
	            	else
	            	{
	                	JOptionPane.showMessageDialog(null, "The sleeping hobo decides to kill you.");
	                	i = JOptionPane.showConfirmDialog(null, "Fight back?", "Adventure", JOptionPane.YES_NO_OPTION);
	                	if(i == JOptionPane.YES_OPTION)
	                	{
	                    	JOptionPane.showMessageDialog(null, "You lost the fight.");
	                    	death();
	                	}
	                	else
	                	{
	                    	JOptionPane.showMessageDialog(null, "What an idiot.");
	                    	JOptionPane.showMessageDialog(null, "You Lived.");
	                    	JOptionPane.showMessageDialog(null, "Wait what?");
	                    	i = JOptionPane.showConfirmDialog(null, "You Died.", "Adventure", JOptionPane.YES_NO_OPTION);                  	 
	                    	if(i == JOptionPane.YES_OPTION)
	                    	{
		                    	JOptionPane.showMessageDialog(null, "Muahahahah.");
		                    	JOptionPane.showMessageDialog(null, "You become an evil zomebie of doomnesss.");
		                    	JOptionPane.showMessageDialog(null, "...");
		                    	i = JOptionPane.showConfirmDialog(null, "Take over the world?", "Adventure", JOptionPane.YES_NO_OPTION);
		                    	if(i == JOptionPane.YES_OPTION)
		                    	{
		                        	JOptionPane.showMessageDialog(null, "You failed you nub.");
		                        	death();
		                    	}
		                    	else
		                    	{
		                        	JOptionPane.showMessageDialog(null, "Well too bad you take over the world blah blah blah.");
		                        	JOptionPane.showMessageDialog(null, "GJ YOUR DONE, PEACE.");
		                        	cont= false;
		                        	break;
		                    	}
	                    	}
	                    	else
	                    	{
	                        	JOptionPane.showConfirmDialog(null, "What do you mean by no?", "Adventure", JOptionPane.YES_NO_OPTION);
	                        	death();
	                    	}
	                	}
	            	}
	        	}
	    	}
	    	else if(i == JOptionPane.NO_OPTION)
	    	{
	        	JOptionPane.showMessageDialog(null, "A deer pushes you down anyways.");
	        	JOptionPane.showConfirmDialog(null, "Grab the branch?", "Adventure", JOptionPane.YES_NO_OPTION);
	        	death();
	    	}
	    	else
	    	{
	        	JOptionPane.showMessageDialog(null, "too bad no canceling");
	        	for(int x = 0;x<5; x++)
	        	{
	            	JOptionPane.showMessageDialog(null, "too bad no canceling");
	        	}
	        	death();
	    	}
		}
	}
	}
	public static void death()
	{
	
		JOptionPane.showMessageDialog(null, "You Died.");
	
		JOptionPane.showMessageDialog(null, "Some asshole rewinds time and forces you to restart your Adventure until you complete your destiny.");
	
	}

}