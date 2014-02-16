/**
* This program demonstrates the use of dialogue boxes in prompting the user to enter a date and displaying it
* @BenjaminCohenWang
*/
import javax.swing.JOptionPane;
public class DateExercise 
{
	public static void main(String[]args)
	{
		Date date = new Date(); //Date object declared and initialized
		date.setYear(Input.getInt("Please enter the year:")); //Gets input for year and sets date object
		date.setMonth(Input.getInt("Please enter the month:")); //Gets input for month and sets date object
		date.setDay(Input.getInt("Please enter the day:")); //Gets input for day and sets date object
		JOptionPane.showMessageDialog(null, "The date is " + date.getDate()); //displays dialogue box with date from date object
	}
}
