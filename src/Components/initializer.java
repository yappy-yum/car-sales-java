package Components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import LoginSystem.isLogin;
import LoginSystem.storage;
import LoginSystem.Argon2.Argon;
import LoginSystem.LoginPage.Customer;
import frontPage.FrontPage;
import frontPage.isDarkTheme;

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
    public Customer Customer;

    public initializer() {
        new Window(this);
    }

}
