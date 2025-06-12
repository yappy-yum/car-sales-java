package Details;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.DropdownPanel;
import Helper.Config.PanelConfig.PanelHelper;

public class Deletion extends DropdownPanel {
    
    blur blur;
    initializer i;
    Window W;
    Object classToInteractWith;

    public Deletion(initializer i, Window W, Object classToInteractWith) {
        this.classToInteractWith = classToInteractWith;
        this.blur = new blur(i.frame);
        this.i = i;
        this.W = W;

        _addPermissionText();
        _createButtons();

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
    }

    void _removeEverything() {
        String you = i.isLogin.currentProfile.username;

        i.stockInventory.UserDeleted(
            you, 
            i.storage._isUserDeletedASalesman(you)
        );
        i.storage.removeUser(you);


        i.isLogin.currentProfile = null;
        i.isLogin.isLogin = false;

        // PanelHelper.clear(blur, this);
        // SwingUtilities.invokeLater(() -> { 
        //     try {
        //         classToInteractWith
        //                 .getClass()
        //                 .getMethod("_closeEverything")
        //                 .invoke(classToInteractWith);
        //     } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        //         e.printStackTrace();
        //     }     
        // });
        PanelHelper.clear(blur, this, classToInteractWith, "_closeEverything");
    }

    void _removeThisOnly() {
        PanelHelper.clear(blur, this);
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
