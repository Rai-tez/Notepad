package GUII;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.border.*;
import java.io.BufferedReader;

public class BenitezNotepad implements ActionListener
{
	static JPanel p1, p2, p3, p4, p5;
	
	static JFrame frame;
	
	static JTextArea typingArea;
	
	static JButton load, save, clear, close, ascend,
	descend, search, replace, wordCount,
	sentenceCount, wordFrequency;	
	
	String fileName;
	String fileAddress;
	String name;
	
	BenitezNotepad(String name)
	{
		this.name = name;
		notepadLayout(); // contains the GUI for the layout of the notepad that is run in the main method
	}
	
	public void notepadLayout()//contains the GUI of the notepad
	{	
		frame = new JFrame("Benitez' Simple Notepad");
		frame.setSize(550, 550);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		
		Font myFont = new Font("Helvetica", Font.PLAIN, 14); // font for the textArea
		
		//TextArea
		typingArea = new JTextArea("Type text here!", 20, 20);
		typingArea.setLineWrap(true);
		typingArea.setWrapStyleWord(true);
		typingArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 15));
		typingArea.setFont(myFont);
		buttons();
		
		frame.setLayout(new BorderLayout()); // frame layout
		//
			p2.setLayout(new GridLayout(3,1));// upper part
			//	
				p3.add(save);
				p3.add(load);
				p3.add(clear);
				p3.add(close);
				p2.add(p3);//grid(1,1)
				
				p4.add(ascend);
				p4.add(descend);
				p4.add(search);
				p4.add(replace);
				p2.add(p4);//grid(2,1)
				
				p5.add(wordCount);
				p5.add(sentenceCount);
				p5.add(wordFrequency);
				p2.add(p5);//grid(3,1)
			//
			//Border for the buttons
			p2.setBorder(new LineBorder(Color.LIGHT_GRAY, 10));
		//	
		
		frame.add(p2, BorderLayout.NORTH);
		frame.add(typingArea, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main (String args[])
	{
		BenitezNotepad np;
		
		if (args.length == 0) 
		{
			np = new BenitezNotepad("noname.txt");
		} 
		else 
		{
			np = new BenitezNotepad(args[0]);
		}
	}
	
	// method to hold all the buttons
	public void buttons()
	{
		final int LENGTH1 = 100; //1st and 2nd row length 
		final int WIDTH = 20;	// width of all the buttons
		final int LENGTH2 = 135; // 3rd row button length
		
		save = new JButton("Save");
		// setPreferredSize is used for adjusting the button size
		save.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		save.setActionCommand("Save"); //sets the button name as a string so it can be get by getActionCommand(); 
		save.addActionListener(this); // gices the button an action whenever pressed
		
		load = new JButton("Load");
		load.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		load.setActionCommand("Load");
		load.addActionListener(this);
		
		clear = new JButton("Clear");
		clear.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		clear.setActionCommand("Clear");
		clear.addActionListener(this);
		
		close = new JButton("Close");
		close.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		close.setActionCommand("Close");
		close.addActionListener(this);
		
		ascend = new JButton("Ascend");
		ascend.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		ascend.setActionCommand("Ascend");
		ascend.addActionListener(this);
		
		descend = new JButton("Descend");
		descend.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		descend.setActionCommand("Descend");
		descend.addActionListener(this);
		
		search = new JButton("Search");
		search.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		search.setActionCommand("Search");
		search.addActionListener(this);
		
		replace = new JButton("Replace");
		replace.setPreferredSize(new Dimension(LENGTH1, WIDTH));
		replace.setActionCommand("Replace");
		replace.addActionListener(this);
		
		wordCount = new JButton("Word Count");
		wordCount.setPreferredSize(new Dimension(LENGTH2, WIDTH));
		wordCount.setActionCommand("Word_Count");
		wordCount.addActionListener(this);
		
		sentenceCount = new JButton("Sentence Count");
		sentenceCount.setPreferredSize(new Dimension(LENGTH2, WIDTH));
		sentenceCount.setActionCommand("Sentence_Count");
		sentenceCount.addActionListener(this);
		
		wordFrequency = new JButton("Word Frequency");
		wordFrequency.setPreferredSize(new Dimension(LENGTH2, WIDTH));
		wordFrequency.setActionCommand("Word_Frequency");
		wordFrequency.addActionListener(this);
	}
	
	// method for the function of the button
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand()) // gets the string from the set action command
		{
			case "Save": saveB();break;
			case "Load": loadB();break;
			case "Clear": typingArea.setText(""); break;
			case "Close": System.exit(1); break;
			case "Ascend": ascendB();break;
			case "Descend": descendB();break;
			case "Search": searchB() ;break;
			case "Replace": replaceB();break;
			case "Word_Count": wordCountB();break;
			case "Sentence_Count": sentenceCountB();break;
			case "Word_Frequency": wordFrequencyB();break;
		}
	}
	
	// below here are the functions for the buttons
	public void saveB()// saves the file
	{
		try 
		{
			FileWriter fw = new FileWriter(name);
			fw.write(BenitezNotepad.typingArea.getText());
			frame.setTitle(name);
			fw.close();
		}
		catch (Exception e) 
		{
			System.out.println("Error!!");
		}
	}
	public void loadB()// loads the file by opening a file directory
	{
		FileDialog fd = new FileDialog(BenitezNotepad.frame, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		
		if(fd.getFile() != null) {    
			
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			BenitezNotepad.frame.setTitle(name);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(name));
			BenitezNotepad.typingArea.setText("");
			
			String line = null;
			
			while((line = br.readLine()) != null){
				
				BenitezNotepad.typingArea.append(line + "\n");
			}
			
			br.close();
			
		} catch(Exception e) {
			
			System.out.println("File Not Opened	");
			
		}
	}
	public void ascendB()// arranges the characters or digits in ascending order
	{
		String words[] = typingArea.getText().split("\\n"); // splits the string from texArea into an array of String
		Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);// sorts the array in ascending order
		String ascended = "";
		
		for(int i = words.length-1; i >= 0; i--)// concatenates the array into a string
		{
			ascended += words[i] + "\n";
		}
		typingArea.setText(ascended);//sets the text of the textArea into the concatenated sorted string
	}
	public void descendB()// arranges the characters or digits in descending order
	{
		String words[] = typingArea.getText().split("\\n");// splits the string from texArea into an array of String
		Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);// sorts the array in ascending order
		String descended = "";
		
		for(int i = 0; i < words.length; i++)// concatenates the array into a string
		{
			descended += words[i] + "\n";
		}
		typingArea.setText(descended);//sets the text of the textArea into the concatenated sorted string
	}
	public void searchB()//searches and highlights all the word that is being searched
	{
		String word = JOptionPane.showInputDialog(null, "Search for a word(The word will be highlighted)");
		if(word.length() == 0)
		{
			return;
		}	
		
		Search a = new Search(typingArea, word);
	}
	public void replaceB()// replaces all the words that is inputted by the user by another word
	{
		//GUI
		JPanel p1 = new JPanel(); 
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		JLabel text = new JLabel("Note:  Case Sensitive");
		text.setPreferredSize(new Dimension(55,25));
		final int DX = 55;
		final int DY = 15;
		JLabel search = new JLabel("Search: ");
		search.setPreferredSize(new Dimension(DX, DY));
		JLabel replace = new JLabel("Replace: ");
		replace.setPreferredSize(new Dimension(DX, DY));
		
		JTextField searchBox = new JTextField("", 7);
		JTextField replaceBox = new JTextField("", 7);
		
		p1.setLayout(new GridLayout(3, 1));
		//
			p2.setLayout(new BorderLayout());
			//
				p2.add(search, BorderLayout.WEST);
				p2.add(searchBox, BorderLayout.CENTER);
				p1.add(p2);
			//	
			
			p3.setLayout(new BorderLayout());
			//
				p3.add(replace, BorderLayout.WEST);
				p3.add(replaceBox, BorderLayout.CENTER);
				p1.add(p3);
			//
			p4.setLayout(new BorderLayout());
			//
				p4.add(text, BorderLayout.CENTER);
				p1.add(p4);
			//
		//end of GUI
				
				
		String words[] = typingArea.getText().split("\\s+"); // turns the string from the textArea into an array of Strings
		if(words[0].length() == 0 && words.length == 1)//checks if words[] is empty
		{
			JOptionPane.showMessageDialog(null, "There are no words on the area!");
		}
		else
		{	
			int a = JOptionPane.showConfirmDialog(null, p1, "", JOptionPane.YES_NO_OPTION);
			String area = typingArea.getText();
			String searched = searchBox.getText();
			String replaced = replaceBox.getText();
			
			if(a == JOptionPane.YES_OPTION)//if user presses yes, then code will execute
			{
				if(area.toLowerCase().contains(searched.toLowerCase()))//is the textArea contains the searched word, then code runs below
				{
					typingArea.setText(area.replaceAll(searched, replaced));// replaces every instance of the searched word with the replaced				
				}	
			}
			else
			{
				return;		
			}
		}	
	}
	public void wordCountB()// counts all the words in the textArea
	{	
		WordCount a = new WordCount(typingArea);
	}
	public void sentenceCountB()// counts the sentence 
	{
		SentenceCount a = new SentenceCount(typingArea);
	}
	public void wordFrequencyB()// counts how many times all the words have been used in the text area
	{
		WordFrequency a = new WordFrequency(typingArea);
	}
}
