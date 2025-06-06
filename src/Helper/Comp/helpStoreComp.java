package Helper.Comp;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Components.initializer;
import Helper.Animation.componentAnim;

public class helpStoreComp {
    
    /*//////////////////////////////////////////////////////////////
                             add JTextArea
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * adding texts into JTextArea data type, usually long text
     * 
     * @param _text texts to be added into JTextArea
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JTextArea
     * @param height the length height of the JTextArea
     * @param font the type font to be used for the text
     * @param border JTextArea box border
     * @param textColor text color
     * 
     */
    public static void addJTextArea(
        initializer i, JPanel windowPanel,
        String _text, 
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextArea text = createComp.createJTextArea(
            _text, 
            startX, startY, 
            width, height, 
            font, border, 
            textColor
        );
        
        i.switchThemeComp.texts.add(text);
        windowPanel.add(text);
        
        i.compAnimStorage.addAnim(
            new componentAnim(
                text, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );
    }

    /**
     * adding texts into JTextArea data type, usually long text
     * 
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JTextArea
     * @param height the length height of the JTextArea
     * @param font the type font to be used for the text
     * @param border JTextArea box border
     * @param textColor text color
     * 
     */
    public static JTextField addJTextField(
        initializer i, JPanel windowPanel,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextField text = createComp.createJTextField(
            startX, startY, 
            width, height, 
            font, border, 
            textColor
        );
        
        i.switchThemeComp.searchBar.add(text);
        windowPanel.add(text);
        
        i.compAnimStorage.addAnim(
            new componentAnim(
                text, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );

        return text;
    }    

    /*//////////////////////////////////////////////////////////////
                              add JButton
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 
     * adding texts into JButton data type, usually clickable
     * 
     * @param _text texts to be added into JButton
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JButton
     * @param height the length height of the JButton
     * @param border the border of the JButton
     * @param textColor text color
     * @return a JButton, usually when there'a an additional action required
     * `
     */
    public static JButton addJButton(
        initializer i, JPanel panelWindow,
        String _text, 
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Border border, 
        Color textColor, Font font
    ) {
        JButton button = createComp.createJButton(
            _text, 
            startX, startY, 
            width, height, 
            border, textColor, font
        );

        i.switchThemeComp.TButtons.add(button);
        panelWindow.add(button);

        i.compAnimStorage.addAnim(
            new componentAnim(
                button, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );

        return button; 
    }   
    
    /**
     * 
     * adding image/icon into JButton, usually clickable
     * 
     * @param icon image/icon to be added into JButton
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JButton
     * @param height the length height of the JButton
     * @return a JButton, usually when there's an additional action required
     * 
     */
    public static JButton addJButton(
        initializer i, JPanel panelWindow,
        ImageIcon icon,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height
    ) {
        JButton button = createComp.createJButton(
            icon, 
            startX, startY, 
            width, height
        );
        
        i.switchThemeComp.IButtons.add(button);
        panelWindow.add(button);
        
        i.compAnimStorage.addAnim(
            new componentAnim(
                button, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );
        
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                               add JLabel
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 
     * adding texts into JLabel, usually short text.
     * 
     * @param _text texts to be added into JLabel
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JLabel
     * @param height the length height of the JLabel
     * @param font the type font to be used for the text
     * @param darkColor text color when light theme
     * @param lightColor text color when dark theme
     * 
     */
    public static void addJLabel(
        initializer i, JPanel windowPanel,
        String _text,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Font font, Color textColor
    ) {        
        JLabel label = createComp.createJLabel(
            _text, 
            startX, startY, 
            width, height, 
            font, textColor
        );
        
        i.switchThemeComp.TLabels.add(label);
        windowPanel.add(label);

        i.compAnimStorage.addAnim(
            new componentAnim(
                label, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );
    }
    
    /**
     * 
     * adding image/icon into JLabel, normal image/icon
     * 
     * @param icon image/icon to be added into JLabel
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JLabel
     * @param height the length height of the JLabel
     * 
     */
    public static void addJLabel(
        initializer i, JPanel windowPanel,
        ImageIcon icon,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height
    ) {
        JLabel label = createComp.createJLabel(
            icon, 
            startX, startY, 
            width, height
        );
        
        i.switchThemeComp.ILabels.add(label);
        windowPanel.add(label);

        i.compAnimStorage.addAnim(
            new componentAnim(
                label, 
                startX, startY, 
                targetX, targetY, 
                i.scrollPane
            )
        );
    }   
 
    /*//////////////////////////////////////////////////////////////
                               Drop Down
    //////////////////////////////////////////////////////////////*/    
    
    
    public static void _startDropDown(
        initializer i,
        Runnable initializer, 
        Supplier<JComponent> componentGetter,
        int width, int height
    ) {        
        initializer.run();

        JComponent target = componentGetter.get();

        int X = (1280 - width) / 2; 
        int Y = (720 - height) / 2;

        target.setBounds(X, Y, width, height);
        target.setVisible(true);

        i.frame.getContentPane().add(target);

        i.compAnimStorage.addAnim(
            new componentAnim(
                target, 
                X, Y - 100, 
                X, Y, 
                i.scrollPane
            ).start()
        );
    }    

}
