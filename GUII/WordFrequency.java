package GUII;

import java.awt.*;
import javax.swing.*;
import java.util.Queue;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.text.*;
import java.util.LinkedList;

public class WordFrequency
{
	JPanel panel[];
	JLabel label[];
	
	public WordFrequency(JTextComponent area)
	{
		String[] words = area.getText().toLowerCase()
		.replaceAll("\\p{Punct}", "").split("\\s+"); // replaces punctuations and splits the words by spaces
		Arrays.sort(words); // sorts the array
		
		//Queue is used so the list could be removed from the front
		Queue<String> wordList = new LinkedList<String>(Arrays.asList(words));
		//hashmap will serves as a storage for the word and its frequency
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		
		int counter = 0; // counter for how many words
		String temp = ""; // temporary storage for the word
		
		if(words[0].length() == 0 && words.length == 1) //checks if textarea is empty
		{
			JOptionPane.showMessageDialog(null, "There are no words on the area!");
		}
		else
		{	
			while(!wordList.isEmpty())// loops until queue is empty
			{
				temp = wordList.remove();//removes the first word in the queue
				if(temp.equals(wordList.peek()))//checks if the the removed word is equals to the front of the queue
				{
					counter++;// counter will add by 1 and temp will reset
					temp = "";
				}
				else // will only run if the word is not equals thus ending the cycle
				{
					counter++;
					list.put(temp, counter); // it gets put in the hashmap
					counter = 0;// resets the counter for the next words
				}
			}
			
			panel = new JPanel[list.size()+1]; // for the GUI
			label = new JLabel[list.size()]; 
			panel[0] = new JPanel();
			int labelNum = 0;//counter and serves as an index
			
			for (String i : list.keySet())// gets the keyset as a string  and prints it on the left side, and list.get will be printed on the right side
			{
				label[labelNum] = new JLabel((labelNum+1) + ". )   " + i + "    =    [" + list.get(i) + "]");
				labelNum++;
			}
			
			//GUI
			panel[0].setLayout(new BorderLayout());
			panel[0].add(new JLabel("Frequency of words:"), BorderLayout.WEST);
			for(int i = 1; i < list.size()+1; i++)
			{
				panel[i] = new JPanel();
				panel[i].setLayout(new BorderLayout());
				panel[i].add(label[i-1], BorderLayout.CENTER);// sets 
			}	
			JOptionPane.showMessageDialog(null, panel);
		}	
	}
}