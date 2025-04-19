package Helper.Comp;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class createComp {

    /*//////////////////////////////////////////////////////////////
                               JTextArea
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create long texts with JTextArea
     * 
     * @param _text long texts to be created
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the JTextArea
     * @param height height length of the JTextArea
     * @param font text font
     * @param border JTextArea box border
     * @param textColor text color
     * @return created JTextArea
     * 
     */
    public static JTextArea createJTextArea(
        String _text, 
        int X, int Y,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextArea text = new JTextArea(_text);

        text.setBounds(X, Y, width, height);
        text.setOpaque(false);
        text.setEditable(false);
        text.setForeground(textColor);
        text.setFont(font);
        text.setBorder(border);
        text.setFocusable(false);

        return text;
    }

    /*//////////////////////////////////////////////////////////////
                                JButton
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a clickable button containing short text(s)
     * 
     * @param _text short text(s) inside the button
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the button
     * @param height height length of the button
     * @param border button box border
     * @param textColor text color
     * @return created JButton
     * 
     */
    public static JButton createJButton(
        String _text, 
        int X, int Y,
        int width, int height,
        Border border, 
        Color textColor
    ) {
        JButton button = new JButton(_text);

        button.setBounds(X, Y, width, height);
        button.setBorder(border);
        button.setForeground(textColor);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        return button;
    }

    /**
     * create a clickable button containing ImageIcon
     * 
     * @param icon ImageIcon inside the button
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the button
     * @param height height length of the button
     * @return created JButton
     * 
     */
    public static JButton createJButton(
        ImageIcon icon, 
        int X, int Y,
        int width, int height
    ) {
        JButton button = new JButton(icon);

        button.setBounds(X, Y, width, height);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(null);

        return button;
    }

    /*//////////////////////////////////////////////////////////////
                                 JLabel
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a short label text
     * 
     * @param _text short text inside the label
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the label
     * @param height height length of the label
     * @param font text font
     * @param border label box border
     * @param textColor text color
     * @return created JLabel
     * 
     */
    public static JLabel createJLabel(
        String _text, 
        int X, int Y,
        int width, int height,
        Font font, Color textColor
    ) {
        JLabel label = new JLabel(_text);

        label.setBounds(X, Y, width, height);
        label.setOpaque(false);
        label.setForeground(textColor);
        label.setFont(font);
        label.setFocusable(false);

        return label;
    }

    /**
     * create a label image
     * 
     * @param icon ImageIcon inside the label
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the label
     * @param height height length of the label
     * @return created JLabel
     * 
     */
    public static JLabel createJLabel(
        ImageIcon icon, 
        int X, int Y,
        int width, int height
    ) {
        JLabel label = new JLabel(icon);

        label.setBounds(X, Y, width, height);
        label.setOpaque(false);
        label.setFocusable(false);

        return label;
    }

}
