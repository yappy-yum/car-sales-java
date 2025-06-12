package framework;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class StringToStyledDocument extends DefaultStyledDocument {
        
    public StringToStyledDocument(String _text) 
    {
        try 
        {
            this.insertString(0, _text, null);
        } 
        catch (BadLocationException e) 
        {
            e.printStackTrace();
        }
    }

}
