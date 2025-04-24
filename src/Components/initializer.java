package Components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import frontPage.FrontPage;
import frontPage.isDarkTheme;
import loginPage.isLogin;
import loginPage.storage;
import loginPage.Argon2.Argon;

public class initializer {
    
    public JFrame frame = new JFrame();
    public isLogin isLogin = new isLogin();
    public isDarkTheme isDarkTheme = new isDarkTheme();
    public Argon Argon = new Argon();
    public storage storage = new storage();
    
    public JScrollPane scrollPane;
    public Components component;
    public SwitchThemeComp switchThemeComp;
    public FrontPage frontPage;

    public initializer() {
        new Window(this);
    }

}
