package Helper.Comp;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
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

    public static JTextField createJTextField(
        int X, int Y,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextField text = new JTextField();

        text.setBounds(X, Y, width, height);
        text.setOpaque(false);
        text.setEditable(true);
        text.setForeground(textColor);
        text.setFont(font);
        text.setBorder(border);
        text.setFocusable(true);
        text.setVisible(false);

        return text;
    }

    public static JPasswordField createJPasswordField(
        int X, int Y,
        int width, int height,
        char echo, Color textColor,
        Font textFont, Border border
    ) {
        JPasswordField text = new JPasswordField();

        text.setBounds(X, Y, width, height);
        text.setEchoChar(echo);
        text.setForeground(textColor);
        text.setOpaque(false);
        text.setFocusable(true);
        text.setFont(textFont);
        text.setBorder(border);
        text.setVisible(false);

        return text;
    }

    /**
     * create a clickable multiple choice button: hardcoded ⚪ and ⚫
     * 
     * @apiNote dont forget to create ButtonGroup
     * 
     * @param font type of font
     * @param _command recorded action command from clicked toggle button
     * @return created JToggleButton
     * 
     */
    public static JToggleButton createJToggleButton(Font font, String _command) {
        JToggleButton dot = new JToggleButton();

        dot.setText("⚪");
        dot.setFont(font);
        dot.setFocusPainted(false);
        dot.setOpaque(false);
        dot.setContentAreaFilled(false);
        dot.setBorder(null);
        dot.setActionCommand(_command);
        dot.setVisible(true);

        return dot;
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
        label.setVisible(false);

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

    /*//////////////////////////////////////////////////////////////
                                 JPanel
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a JPanel (mostly to be used as a box)
     * 
     * @param x starting from X-coordinate
     * @param y starting from Y-coordinate
     * @param panelWidth width length of the JPanel
     * @param panelHeight height length of the JPanel
     * @param border JPanel box border
     * @return created JPanel
     * 
     */
    public static JPanel createJPanel(
        int x, int y, 
        int panelWidth, int panelHeight,
        Border border
    ) {
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBorder(border);
        panel.setLocation(x, y);
        panel.setOpaque(false);
        panel.setSize(panelWidth, panelHeight);

        return panel;
    }

}
