
//Pizza Selection display Panel
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
public class SelectionPanel extends JPanel
{
	//Instance variables for size selection
	private final double SMALL = 10.95;
	private final double MEDIUM = 13.95;
	private final double LARGE = 16.95;
	private enum Type {SMALL, MEDIUM, LARGE};
	private Type type;
	private JRadioButton smallButton;
	private JRadioButton mediumButton;
	private JRadioButton largeButton;
	private ButtonGroup sizeButtons;
	private Border sizeButtonsBorder;
	
	//Instance variables for topping selection
	private final double TOP_COST = 1.00;
	private int topNum;
	private JCheckBox plain;
	private JCheckBox sausage;
	private JCheckBox mushroom;
	private JCheckBox pepperoni;
	private Border toppingButtonsBorder;
	
	//Instance variables for price display
	private double priceVal;
	private JLabel priceLabel;
	private JTextField price;
	private Border priceBorder;
	
	//Cnstructor to initialize instance variables and organize Panel Components
	public SelectionPanel()
	{
		//Component Panels declared and created
		JPanel sizePanel = new JPanel();
		JPanel toppingPanel = new JPanel();
		JPanel pricePanel = new JPanel();
		
		//Size variables initialized, added to their Panel, and organized
		sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		smallButton = new JRadioButton("Small");
		mediumButton = new JRadioButton("Medium");
		largeButton = new JRadioButton("Large");
		
		smallButton.addActionListener(new SizeListener());
		mediumButton.addActionListener(new SizeListener());
		largeButton.addActionListener(new SizeListener());
		
		sizeButtons = new ButtonGroup();
		sizeButtons.add(smallButton);
		sizeButtons.add(mediumButton);
		sizeButtons.add(largeButton);
		
		sizeButtonsBorder = BorderFactory.createEtchedBorder();
		sizeButtonsBorder = BorderFactory.createTitledBorder(sizeButtonsBorder, "Pizza Size");
		
		sizePanel.add(smallButton);
		sizePanel.add(mediumButton);
		sizePanel.add(largeButton);
		sizePanel.setBorder(sizeButtonsBorder);
		
		//Topping variables initialized, added to their Panel, and organized
		toppingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		plain = new JCheckBox("Plain");
		sausage = new JCheckBox("Sausage");
		mushroom = new JCheckBox("Mushroom");
		pepperoni = new JCheckBox("Pepperoni");
		
		plain.addActionListener(new ToppingListener());
		sausage.addActionListener(new ToppingListener());
		mushroom.addActionListener(new ToppingListener());
		pepperoni.addActionListener(new ToppingListener());
		
		toppingButtonsBorder = BorderFactory.createEtchedBorder();
		toppingButtonsBorder = BorderFactory.createTitledBorder(toppingButtonsBorder, "Toppings");
		
		toppingPanel.add(plain);
		toppingPanel.add(sausage);
		toppingPanel.add(mushroom);
		toppingPanel.add(pepperoni);
		toppingPanel.setBorder(toppingButtonsBorder);
		
		//Price variables initialized, added to their Panel, and organized
		priceVal = 0;
		priceLabel = new JLabel("Price: ");
		price = new JTextField(priceVal + " $");
		price.setEditable(false);
		price.setColumns(4);
		
		priceBorder = BorderFactory.createEtchedBorder();
		
		pricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		pricePanel.add(priceLabel);
		pricePanel.setBorder(priceBorder);
		pricePanel.add(price);
		
		//Component Panels added to main Panel and layout set
		setLayout(new BorderLayout());
		add(sizePanel, BorderLayout.NORTH);
		add(toppingPanel, BorderLayout.CENTER);
		add(pricePanel, BorderLayout.SOUTH);
	}
	//Listener for size Radio Buttons
	public class SizeListener implements ActionListener
	{
		public SizeListener()
		{
			super();
		}
		//sets price and sets type
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			if(source == smallButton)
			{
				type = Type.SMALL;
			}
			if(source == mediumButton)
			{
				type = Type.MEDIUM;
			}
			if(source == largeButton)
			{
				type = Type.LARGE;
			}
			setPrice();
		}
	}
	public class ToppingListener implements ActionListener
	{
		public ToppingListener()
		{
			super();
		}
		//Sets price and number of currwnt toppings
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			if(source == plain)
			{
				sausage.setSelected(false);
				mushroom.setSelected(false);
				pepperoni.setSelected(false);
			}
			if(source != plain)
			{
				plain.setSelected(false);
			}
			topNum = 0;
			if(sausage.isSelected())
			{
				topNum ++;
			}
			if(mushroom.isSelected())
			{
				topNum ++;
			}
			if(pepperoni.isSelected())
			{
				topNum ++;
			}
			setPrice();
		}
	}
	//Sets price given type and number of toppings
	public void setPrice()
	{
		if(type == Type.SMALL)
		{
			priceVal = SMALL;
		}
		if(type == Type.MEDIUM)
		{
			priceVal = MEDIUM;
		}
		if(type == Type.LARGE)
		{
			priceVal = LARGE;
		}
		priceVal += topNum*TOP_COST;
		price.setText(priceVal + " $");
	}
}
