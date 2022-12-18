package GUII;

import javax.swing.*;
import javax.swing.text.*;
public class WordCount
{
	public WordCount(JTextComponent area)
	{
		String words[] = area.getText().split("\\s+"); // splits every space and next line   
		
		if(words[0].length() == 0 && words.length == 1)// if text area is empty, then the output is 0
		{
			JOptionPane.showMessageDialog(null ,"Total number of words are: [" + (words.length-1) + "]"); 
		}
		else
		{
			JOptionPane.showMessageDialog(null ,"Total number of words are: [" + words.length + "]"); 
		}	
	}
}
