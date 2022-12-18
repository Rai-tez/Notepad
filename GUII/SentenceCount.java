package GUII;

import javax.swing.*;
import javax.swing.text.*;
public class SentenceCount
{
	public SentenceCount(JTextComponent area)
	{
		String[] words = area.getText().split("\\s+");//turns textArea into an array of strings
		int frequency = 0;
		try// checks if the code will catch an exception
		{
			for(String word: words)// runs through evere single word in the texArea
			{
				if((word.contains(".") || word.contains("?") || word.contains("!")) 
				&& Character.isLetter(word.charAt(word.length()-2)))// checks if a certain word contains any marks  and if there is an existing character 
				{
					frequency++;// a counter to add to the sentence count
				}
			}
		}	
		catch(StringIndexOutOfBoundsException e)// will run if the index is -1
		{
			return;
		}
		JOptionPane.showMessageDialog(null, "The number of sentences are: [" + frequency + "]");
	}
}