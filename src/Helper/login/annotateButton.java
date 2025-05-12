package Helper.login;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;

public class annotateButton {

    /*//////////////////////////////////////////////////////////////
                                  Bold
    //////////////////////////////////////////////////////////////*/    

    public static JButton boldButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.BOLD, X, Y); 

        button.addActionListener( _ -> {
            StyledDocument doc = textPane.getStyledDocument();

            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            if (start == end) return;

            Element ele = doc.getCharacterElement(start);
            boolean isBold = StyleConstants.isBold(ele.getAttributes());
            
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setBold(attribute, !isBold);
            doc.setCharacterAttributes(start, end - start, attribute, false);

        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                              Italic/Relic
    //////////////////////////////////////////////////////////////*/    

    public static JButton italicButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.RALIC, X, Y); 

        button.addActionListener( _ -> {
            StyledDocument doc = textPane.getStyledDocument();

            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            if (start == end) return;

            Element ele = doc.getCharacterElement(start);
            boolean isItalic = StyleConstants.isItalic(ele.getAttributes());
            
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setItalic(attribute, !isItalic);
            doc.setCharacterAttributes(start, end - start, attribute, false);

        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                               Underline
    //////////////////////////////////////////////////////////////*/    

    public static JButton underlineButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.UNDERLINE, X, Y); 

        button.addActionListener( _ -> {
            StyledDocument doc = textPane.getStyledDocument();

            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            if (start == end) return;

            Element ele = doc.getCharacterElement(start);
            boolean isUnderline = StyleConstants.isUnderline(ele.getAttributes());
            
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setUnderline(attribute, !isUnderline);
            doc.setCharacterAttributes(start, end - start, attribute, false);

        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                             Left Alignment
    //////////////////////////////////////////////////////////////*/    

    public static JButton LeftButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.LEFT_TEXT, X, Y); 

        button.addActionListener( _ -> {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribute, StyleConstants.ALIGN_LEFT);
            textPane.getStyledDocument().setParagraphAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                attribute, false
            );
        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                            Center Alignment
    //////////////////////////////////////////////////////////////*/    

    public static JButton CenterButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.CENTER_TEXT, X, Y); 

        button.addActionListener( _ -> {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribute, StyleConstants.ALIGN_CENTER);
            textPane.getStyledDocument().setParagraphAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                attribute, false
            );
        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                            Right Alignment
    //////////////////////////////////////////////////////////////*/    

    public static JButton RightButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.RIGHT_TEXT, X, Y); 

        button.addActionListener( _ -> {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribute, StyleConstants.ALIGN_RIGHT);
            textPane.getStyledDocument().setParagraphAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                attribute, false
            );
        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                           Justify Alignment
    //////////////////////////////////////////////////////////////*/    

    public static JButton JustifyButton(JTextPane textPane, int X, int Y) {
        JButton button = _createButton(imageSystem.JUSTIFY_TEXT, X, Y); 

        button.addActionListener( _ -> {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribute, StyleConstants.ALIGN_JUSTIFIED);
            textPane.getStyledDocument().setParagraphAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                attribute, false
            );
        });
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                                 Helper
    //////////////////////////////////////////////////////////////*/    

    private static JButton _createButton(ImageIcon img, int X, int Y) {
        JButton button = createComp.createJButton(
            imageSystem._scaleImage(img, 30, 30), 
            X, Y, 
            30, 30
        );
        button.setBorder(
            new roundedBorder(
                10, 
                imageSystem._reduceColorTransparency(Color.BLACK, 0.5f),
                imageSystem._reduceColorTransparency(Color.GRAY, 0.2f)
            )
        );
        return button;
    }

}
