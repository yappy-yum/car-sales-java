package LoginSystem.LoginPage;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;

/**
 * drop down small JPanel that shows error/success messages
 * 
 * <p>
 *  
 * Ensures that below code exist in the class that calls this:
 * 
 * <pre>java
 * 
 *  PromptMessage message;
 *  initializer i;
 * 
 *  public void _removeMessage() { remove(message); message = null; }
 *
 *  void _promptMessage(JLabel text) {
 *      message = new PromptMessage(i, text, this);
 *      message.setBounds(300, 150, 550, 180);
 *      message.setVisible(true);
 *
 *      i.frame.getContentPane().add(message);
 *
 *      i.compAnimStorage.addAnim(
 *          new componentAnim(
 *              message, 
 *              350, 150, 
 *              350, 250, 
 *              i.scrollPane
 *          ).start()
 *      );
 *   }
 * </pre>
 * 
 * 
 * @author yappy-yum
 * 
 * 
 */
public class PromptMessage extends JPanel {
 
    blur blur;
    JLabel text;
    Object classToInteractWith;
    SwitchThemeComp S;

    public PromptMessage(initializer i, JLabel text, Object classToInteractWith) {
        this.blur = new blur(i.frame);
        this.text = text;
        this.classToInteractWith = classToInteractWith;
        this.S = i.switchThemeComp;

        _background();
        _addHeader();
        _addMessage();
        _addX();

        S.dummy.add(this);

    }

    /*//////////////////////////////////////////////////////////////
                               Background
    //////////////////////////////////////////////////////////////*/    

    void _background() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );
    }

    /*//////////////////////////////////////////////////////////////
                              Add Message
    //////////////////////////////////////////////////////////////*/    

    void _addMessage() { S.dummy.add(text); add(text); text.setVisible(true); }

    /*//////////////////////////////////////////////////////////////
                              Close Button
    //////////////////////////////////////////////////////////////*/    

    void _addX() {

        JButton closeButton = createComp.createJButton(
            "Noted!", 
            200,110, 
            120, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                blur = null;
                PanelHelper.clear(this);
                SwingUtilities.invokeLater(() -> { 
                    try {
                        classToInteractWith
                                .getClass()
                                .getMethod("_removeMessage")
                                .invoke(classToInteractWith);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace(); 
                    }
                });
            }
        );
        add(closeButton);
        S.dummy.add(closeButton);

    }

    /*//////////////////////////////////////////////////////////////
                                 Header
    //////////////////////////////////////////////////////////////*/    

    void _addHeader() {
        JLabel header = createComp.createJLabel(
            "Message:", 
            20, 20, 
            100, 40, 
            new Font("Arial", Font.BOLD, 20), 
            Color.BLACK
        );
        header.setVisible(true);
        add(header);
        S.dummy.add(header);
    }

}
