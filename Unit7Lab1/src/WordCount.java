import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class WordCount 
{
	public static void main(String[]args) throws FileNotFoundException
	{
		Scanner input;
		if(args.length > 1)
		{
			if(args[0].equals("WordCount"))
			{
				if(args.length == 2)
				{
					input = new Scanner(new File(args[1]));
					int c = charCount(input);
					input = new Scanner(new File(args[1]));
					int w = wordCount(input);
					input = new Scanner(new File(args[1]));
					int s = sentenceCount(input);
					input = new Scanner(new File(args[1]));
					int p = paragraphCount(input);
					System.out.println("Number of characters: " + c);
					System.out.println("Number of words: " +( w + p - 1));
					System.out.println("Number of sentences: " + s);
					System.out.println("Number of paragraphs: " + p);
				}
				if(args.length == 3 && args[1].charAt(0) == '-')
				{
					input = new Scanner(new File(args[2]));
					int c = charCount(input);
					input = new Scanner(new File(args[2]));
					int w = wordCount(input);
					input = new Scanner(new File(args[2]));
					int s = sentenceCount(input);
					input = new Scanner(new File(args[2]));
					int p = paragraphCount(input);
					if(args[1].indexOf('c') > 0)
					{
						System.out.println("Number of characters: " + c);
					}
					if(args[1].indexOf('w') > 0)
					{
						System.out.println("Number of words: " + (w + p - 1));
					}
					if(args[1].indexOf('s') > 0)
					{
						System.out.println("Number of sentences: " + s);
					}
					if(args[1].indexOf('p') > 0)
					{
						System.out.println("Number of paragraphs: " + p);
					}
				}
			}
		}
	}
	public static int wordCount(Scanner input)
	{
		int wordNum = 0;
		input = input.useDelimiter(" ");
		while(input.hasNext())
		{
			if(input.next() != null)
			{
				wordNum ++;
			}
		}
		return wordNum;
	}
	public static int charCount(Scanner input)
	{
		int charNum = 0;
		input = input.useDelimiter("");
		while(input.hasNext())
		{
			if(input.next() != null)
			{
				charNum ++;
			}
		}
		return charNum;
	}
	public static int sentenceCount(Scanner input)
	{
		int num = 0;
		input = input.useDelimiter("\\.");
		while(input.hasNext())
		{
			num ++;
			input.next();
		}
		return num;
	}
	public static int paragraphCount(Scanner input)
	{
		int num = 0;
		input = input.useDelimiter("\n");
		while(input.hasNext())
		{
			num ++;
			input.next();
		}
		return num;
	}
}
