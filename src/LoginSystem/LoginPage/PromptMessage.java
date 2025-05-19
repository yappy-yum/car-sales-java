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
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;

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

    }

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

    void _addMessage() { S.dummy.add(text); add(text); text.setVisible(true); }

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
                remove(this);
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
