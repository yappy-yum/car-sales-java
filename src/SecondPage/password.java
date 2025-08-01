package SecondPage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.createComp;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.DropdownPanel;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.login.Profile;
import Helper.login.loginFill;
import LoginSystem.storage;
import SecondPage.EmployeePage.unverifiedDB.VerifyCust;

public class password extends DropdownPanel {

    blur blur;
    initializer i;
    Window w;
    SwitchThemeComp S;
    String username;
    storage storage;

    JButton close;
    JButton check;

    loginFill.createLabelAndLongJPasswordField Password = new loginFill.createLabelAndLongJPasswordField("Password:", 30, 20);

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public password(initializer i, Window w, String username) {
        this.i = i;
        this.w = w;
        this.S = i.switchThemeComp;
        this.blur = new blur(i.frame);
        this.username = username;
        this.storage = i.storage;

        _createButtons();
        _addComp();

    }

    /*//////////////////////////////////////////////////////////////
                             Add to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _addComp() {
        Password.label.setVisible(true);
        Password.button.setVisible(true);
        Password.passwordField.setVisible(true);
        check.setVisible(true);
        close.setVisible(true);

        add(Password.label);
        add(Password.button);
        add(Password.passwordField);
        add(check);
        add(close);

    }

    void _createButtons() {
        check = createComp.createJButton(
            "Submit", 
            100, 80, 
            100, 40, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 15)
        );
        check.addActionListener( _ -> { _checkPassword(); });

        close = createComp.createJButton(
            "Close", 
            250, 80, 
            100, 40, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 15)
        );
        close.addActionListener( _ -> { _close(); SwingUtilities.invokeLater(() -> { w._loadDBPage(); }); });
    }

    void _close() {
        PanelHelper.clear(blur, this);
    }

    void _checkPassword() {
        String password = String.valueOf(Password.passwordField.getPassword());

        ImageIcon docs = storage._decryptDocsImage(username, password);
        ImageIcon face = storage._decryptFaceImage(username, password);

        Profile.seeProfile profile = storage.searchUser(username);
        helpStoreComp._startDropDown(
            i, 
            () -> i.VerifyCust = new VerifyCust(i, w, username, face, docs, profile), 
            () -> i.VerifyCust,
            1000, 500
        );

    }


    
}
