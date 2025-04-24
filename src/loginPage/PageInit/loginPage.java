package loginPage.PageInit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import Components.Components;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;

public class loginPage extends JPanel {

    JScrollPane pane;
    blur blur;
    Window window;
    Components component;

    /*//////////////////////////////////////////////////////////////
                        Registration Components
    //////////////////////////////////////////////////////////////*/    

    Deque<Object> FirstPageRegister = new LinkedList<>(
        Arrays.asList(
            loginFill.setLabel("First Name:", 530, 80, 110, 40),
            loginFill.FillWithJTextArea(650, 80),

            loginFill.setLabel("Last Name:", 530, 150, 110, 40),
            loginFill.FillWithJTextArea(650, 150),

            loginFill.setLabel("Username:", 530, 220, 110, 40),
            loginFill.FillWithJTextArea(650, 220),

            loginFill.setLabel("Password:", 530, 290, 110, 40),
            loginFill.FillWithJPassWordField(650, 290)
        )
    );

    /*//////////////////////////////////////////////////////////////
                            login Components
    //////////////////////////////////////////////////////////////*/   
     
    Deque<Object> FirstPageLogin = new LinkedList<>(
        Arrays.asList(
            loginFill.setLabel("Username:", 30, 220, 110, 40),
            loginFill.FillWithJTextArea(150, 220),

            loginFill.setLabel("Password:", 30, 290, 110, 40),
            loginFill.FillWithJPassWordField(150, 290)
        )
    ); 

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public loginPage(initializer i, Window w) {
        this.blur = new blur(i.frame);
        this.pane = i.scrollPane;
        this.component = i.component;
        this.window = w;

        background();
    }

    /*//////////////////////////////////////////////////////////////
                            login background
    //////////////////////////////////////////////////////////////*/    

    protected void background() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );

        halfNotice();
        _addComp();
    }

    /*//////////////////////////////////////////////////////////////
                                  half
    //////////////////////////////////////////////////////////////*/    

    protected void halfNotice() {
        JPanel half = loginFill.Panel();
        JLabel header = loginMessage.createWelcomeHeader();
        JTextArea subHeader = loginMessage.createWelcomeSubHeader();
        JButton button = loginMessage.createRL();
        JButton close = loginMessage.createClose();

        add(half);
        half.add(header);
        half.add(subHeader);
        half.add(button);
        half.add(close);

        // Add action listener to the button
        button.addActionListener( _ -> { toggleLoginRegister(header, subHeader, button, half); });
        close.addActionListener( _ -> {
            blur.removeBlur();
            SwingUtilities.invokeLater(() -> {
                window._reloadEverything();
            });
        });
    }

    protected void Fill() {
        
        FillRegister();
        FillLogin();

    }

    protected void FillRegister() {
        for (Object i : FirstPageRegister) {
            ((Component) i).setVisible(true);
        }
    }

    protected void FillLogin() {
        for (Object i : FirstPageLogin) {
            ((Component) i).setVisible(true);
            add((Component) i);
        }
    }

    protected void toggleLoginRegister(JLabel header, JTextArea subHeader, JButton button, JPanel half) {
        if (half.getLocation().x != 0 && half.getLocation().x != 500) return;

        boolean isLogin = half.getLocation().x == 0;
        loginMessage.SwitchMessage(isLogin, header, subHeader, button);

        int shift = isLogin ? 500 : -500;

        int X = half.getLocation().x;
        int Y = half.getLocation().y;

        // Animate the panel movement
        componentAnim anim = new componentAnim(half, X, Y, (X + shift), Y, pane);
        anim.setDuration(1500);
        anim.start();
    }

    protected void addToggleButton() {
        ButtonGroup group = new ButtonGroup();
        String[] roles = {"Admin", "Manager", "Salesman", "Customer"};

        for (String r : roles) {
            JToggleButton dot = createComp.createJToggleButton(
                new Font("SansSerif", Font.PLAIN, 40), 
                r
            );
            group.add(dot);
            add(dot);
        }        
    }

    protected void _addComp() {
        for (Object i : FirstPageRegister) { add((Component) i); }
        for (Object i : FirstPageLogin) { add((Component) i); }

        Fill();
    }

}
