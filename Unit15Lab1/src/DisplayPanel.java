import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.border.Border;
//This is the main panel to be displayed in the frame
public class DisplayPanel extends JPanel
{
	//Frame for panel to be displayed on
	private JFrame frame;
	
	//Instance variables for payment and personal information panels
	private JPanel paymentInfoPanel;
	
	private JPanel infoPanel;
	private JTextField emailField;
	private JTextField nameField;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private Border infoBorder;
	
	private JPanel paymentPanel;
	private JRadioButton visaButton;
	private JRadioButton masterCardButton;
	private JRadioButton amexButton;
	private ButtonGroup paymentButtons;
	private Border paymentBorder;
	
	//Instance variables for song selection Panel
	private JPanel songPanel;
	private JComboBox songList;
	private JButton selectSong;
	private Border songBorder;
	private final Song[] songs = new Song[15];
	
	//Instance variables for cart Panel
	private JPanel cartPanel;
	private JPanel cartTitle;
	private JPanel cartContents;
	private JScrollPane cartScroll;
	private HashSet<Song> cart;
	private JLabel cartName;
	private Border cartBorder;
	
	//Instance variables for price and button panels
	private JPanel buttonPanel;
	private JButton exitButton;
	private JButton calculateButton;
	private JLabel priceLabel;
	private JTextField priceField;
	private double price;
	private Border buttonBorder;
	
	//Parameterized constructor initializing frame, Panels, and othe components
	public DisplayPanel(JFrame frame)
	{
		this.frame  = frame;
		
		paymentInfoPanel = new JPanel();
		
		infoPanel = new JPanel();
		
		emailField = new JTextField();
		nameField = new JTextField();
		emailField.setEditable(true);
		nameField.setEditable(true);
		emailField.setColumns(10);
		nameField.setColumns(10);
		
		nameLabel = new JLabel("Name: ");
		emailLabel = new JLabel("Email: ");
		
		infoBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Information");
		
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		infoPanel.add(nameLabel);
		infoPanel.add(nameField);
		infoPanel.add(emailLabel);
		infoPanel.add(emailField);
		infoPanel.setBorder(infoBorder);
		
		paymentPanel = new JPanel();
		
		visaButton = new JRadioButton("Visa");
		masterCardButton = new JRadioButton("MasterCard");
		amexButton = new JRadioButton("Amex");
		
		paymentButtons = new ButtonGroup();
		paymentButtons.add(visaButton);
		paymentButtons.add(masterCardButton);
		paymentButtons.add(amexButton);
		paymentBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Payment Information");
		
		paymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		paymentPanel.add(visaButton);
		paymentPanel.add(masterCardButton);
		paymentPanel.add(amexButton);
		paymentPanel.setBorder(paymentBorder);
		
		paymentInfoPanel.setLayout(new BorderLayout());
		paymentInfoPanel.add(infoPanel, BorderLayout.NORTH);
		paymentInfoPanel.add(paymentPanel, BorderLayout.CENTER);
		
		songPanel = new JPanel();
		
		for(int i = 0; i < 15; i ++)
		{
			songs[i] = new Song("Song " + (i + 1), "Artist " + (i + 1), 9.99 + i);
		}
		songList = new JComboBox(songs);
		songList.setSize(200, 300);
		selectSong = new JButton("Add to Cart");
		selectSong.setFocusable(false);
		selectSong.addActionListener(new SelectionListener());
		songBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Song List");
		
		songPanel.setLayout(new BorderLayout());
		songPanel.add(songList, BorderLayout.NORTH);
		songPanel.add(selectSong, BorderLayout.SOUTH);
		songPanel.setBorder(songBorder);
		
		cartPanel = new JPanel();
		
		cartTitle = new JPanel();
		cartName = new JLabel("Shopping Cart");
		cartTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		cartTitle.add(cartName);
		
		cartContents = new JPanel();
		cart = new HashSet<Song>();
		cartContents.setLayout(new FlowLayout(FlowLayout.CENTER));

		cartBorder = BorderFactory.createEtchedBorder();
		cartPanel.setLayout(new BorderLayout());
		cartPanel.add(cartTitle, BorderLayout.NORTH);
		cartPanel.add(cartContents, BorderLayout.CENTER);
		cartPanel.setBorder(cartBorder);
		
		buttonPanel = new JPanel();
		exitButton = new JButton("Exit");
		calculateButton = new JButton("Calaculate Price");
		priceLabel = new JLabel("Total Price: ");
		price = 0;
		priceField = new JTextField("$ " + price);
		priceField.setEditable(false);
		priceField.setColumns(5);
		exitButton.addActionListener(new ExitListener());
		calculateButton.addActionListener(new CalculateListener());
		
		buttonBorder = BorderFactory.createEtchedBorder();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(exitButton);
		buttonPanel.add(calculateButton);
		buttonPanel.add(priceLabel);
		buttonPanel.add(priceField);
		buttonPanel.setBorder(buttonBorder);
		
		//Various panels being added to main panel
		setLayout(new BorderLayout());
		add(paymentInfoPanel, BorderLayout.NORTH);
		add(songPanel, BorderLayout.WEST);
		add(cartPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	//Listener for exit button
	public class ExitListener implements ActionListener
	{
		//called when exit button pressed
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	//Listener for Price calculator button
	public class CalculateListener implements ActionListener
	{
		//called when calculate button pressed
		public void actionPerformed(ActionEvent e)
		{
			price = 0;
			for(Song song : cart)
			{
				price += song.getPrice();
			}
			priceField.setText("$ " + Math.floor(100*price)/100);
		}
	}
	
	//Listener for add to cart button
	public class SelectionListener implements ActionListener
	{
		public SelectionListener()
		{
			super();
		}
		//called when select button pressed
		public void actionPerformed(ActionEvent e) 
		{
			Song song = (Song) songList.getSelectedItem();
			if(cart.add(song))
			{
				JLabel songLabel = new JLabel(song.getInfo());
				cartContents.add(songLabel, BorderLayout.PAGE_END);
				frame.repaint(1);
				cartPanel.repaint();
				frame.validate();
			}
		}
	}
}
