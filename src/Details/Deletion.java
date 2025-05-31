package Details;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;

public class Deletion extends JPanel {
    
    blur blur;
    initializer i;
    Window W;
    Object classToInteractWith;

    public Deletion(initializer i, Window W, Object classToInteractWith) {
        this.classToInteractWith = classToInteractWith;
        this.blur = new blur(i.frame);
        this.i = i;
        this.W = W;

        _background();
        _addPermissionText();
        _createButtons();

        i.switchThemeComp.dummy.add(this);
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

    void _addPermissionText() {
        JLabel label = createComp.createJLabel(
            "Are you sure you want to delete your account?",
            20, 30,
            600, 50,
            new Font("Arial", Font.BOLD, 20),
            Color.BLACK
        );
        label.setVisible(true);
        add(label);
        i.switchThemeComp.dummy.add(label);
    }

    void _createButtons() {
        JButton yesButton = createComp.createJButton(
            "Yes, Delete It!", 
            100, 100, 
            200, 50, 
            new roundedBorder(15, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        yesButton.addActionListener(_ -> { _removeEverything(); });
        yesButton.setVisible(true);
        add(yesButton);
        i.switchThemeComp.dummy.add(yesButton);

        JButton noButton = createComp.createJButton(
            "No, Keep It", 
            400, 100, 
            200, 50, 
            new roundedBorder(15, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        noButton.addActionListener(_ -> { _removeThisOnly(); });
        noButton.setVisible(true);
        add(noButton);
        i.switchThemeComp.dummy.add(noButton);
    }

    void _removeEverything() {
        i.storage.removeUser(i.isLogin.currentProfile.username);
        i.isLogin.currentProfile = null;
        i.isLogin.isLogin = false;

        blur.removeBlur();
        blur = null;
        PanelHelper.clear(this);
        SwingUtilities.invokeLater(() -> { 
            try {
                classToInteractWith
                        .getClass()
                        .getMethod("_close")
                        .invoke(classToInteractWith);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }     
        });
    }

    void _removeThisOnly() {
        blur.removeBlur();
        blur = null;
        PanelHelper.clear(this);
        SwingUtilities.invokeLater(() -> { 
            try {
                classToInteractWith
                        .getClass()
                        .getMethod("_removeEverything")
                        .invoke(classToInteractWith);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } 
        });
    }

}
