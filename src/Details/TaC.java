package Details;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;

public class TaC extends JPanel {

    blur blur;
    initializer i;
    Window window;
    SwitchThemeComp S;
    
    public TaC(initializer i, Window window) {
        this.blur = new blur(i.frame);
        this.i = i;
        this.window = window;
        this.S = i.switchThemeComp;

        _background();
        _addHeader();
        _addText();
        _addX();
        S.dummy.add(this);
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

    void _addHeader() {
        JLabel textLabel = createComp.createJLabel(
            "Terms & Conditions", 
            400, 20, 
            300, 50, 
            new Font("Arial", Font.BOLD, 25), 
            Color.BLACK
        );
        textLabel.setVisible(true);
        add(textLabel);
        S.dummy.add(textLabel);
    }

    void _addText() {
        String htmlText = 
        """
            <html>
            <body style='text-align: justify; font-family: Arial; font-size: 15px; margin: 5px;'>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et 
                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip 
                    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore  
                    eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                    mollit anim id est laborum.
                </p>
                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam  
                    rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. 
                    Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores 
                    eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, 
                    adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. 
                    Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi 
                    consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum 
                    qui dolorem eum fugiat quo voluptas nulla pariatur?
                </p>
            </body>
            </html>
        """;

        JEditorPane editorPane = new JEditorPane("text/html", htmlText);
        editorPane.setBounds(20, 70, 950, 450);
        editorPane.setOpaque(false);
        editorPane.setEditable(false);
        editorPane.setFocusable(false);

        add(editorPane);
        S.dummy.add(editorPane);
    }

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X", 
            900, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                PanelHelper.clear(this);
                SwingUtilities.invokeLater(() -> { window._loadFrontPage(); } );
            }
        );
        add(closeButton);
        S.dummy.add(closeButton);
    }


}
