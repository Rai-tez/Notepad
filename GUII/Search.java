package GUII;

import javax.swing.text.*;
import java.awt.*;

public class Search 
{
	public Search(JTextComponent area, String word)
	{
		highligh(area, word);
	}

	class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter//a simple highlight painter 
	{
		public MyHighlighterPainter(Color color)// accepts a color for the highlighter
		{
			super(color);
		}
	}
	Highlighter.HighlightPainter myHighlightPainter = new MyHighlighterPainter(Color.YELLOW);
	
	public void removeHighlights(JTextComponent textComp)
	{
		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		
		for(int i = 0; i < hilites.length; i++)
		{
			if(hilites[i].getPainter() instanceof MyHighlighterPainter)
			{
				hilite.removeHighlight(hilites[i]);//removes every highlighted word
			}
		}	
	}
	
	public void highligh(JTextComponent textComp, String pattern)
	{
		removeHighlights(textComp);//removes every highlighted word
		try
		{
			Highlighter hilite = textComp.getHighlighter();
			Document doc = textComp.getDocument();// gets everything in the textArea
			String text = doc.getText(0, doc.getLength());//gets everything from index 0 up to the very end of the document
			int pos = 0;
				
			while((pos = text.toUpperCase().indexOf(pattern.toUpperCase(), pos)) >= 0)//highlights the specified word
			{
				hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
				pos += pattern.length();
			}	
		}
		catch(Exception e)
		{}
	}
}
