package Helper.Comp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import javafx.embed.swing.JFXPanel;

public class createScroll {

    /*//////////////////////////////////////////////////////////////
                                keyboard
    //////////////////////////////////////////////////////////////*/    
 
    /**
     * apply the ability to move the JScrollPane with the 
     * <b> keyboard up and down key </b>
     * 
     * @param scrollPane JScrollPane scroll bar
     * 
     */
    public static void keyboardScroll(JScrollPane scrollPane) {
        scrollPane.setFocusable(true);
        scrollPane.setRequestFocusEnabled(true);

        SwingUtilities.invokeLater(scrollPane::requestFocus);

        scrollPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int currentPosition = scrollPane.getVerticalScrollBar().getValue();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> scrollPane.getVerticalScrollBar().setValue(currentPosition - 20);
                    case KeyEvent.VK_DOWN -> scrollPane.getVerticalScrollBar().setValue(currentPosition + 20);
                }
            }
        });
    }

    /*//////////////////////////////////////////////////////////////
                              mouse wheel
    //////////////////////////////////////////////////////////////*/    

    /**
     * apply the ability to move the JScrollPane with the 
     * <b> mouse scroll wheel </b>, on the JScrollPane
     * 
     * @param scrollPane JScrollPane scroll bar
     * 
     */
    public static void mouseScroll(JScrollPane scrollPane) {
        scrollPane.addMouseWheelListener(e -> {
            int currentPosition = scrollPane.getVerticalScrollBar().getValue();
            int change = e.getWheelRotation() * 20;
            scrollPane.getVerticalScrollBar().setValue(currentPosition + change);
        });
    }

    /**
     * apply the ability to move the JScrollPane with the 
     * <b> mouse scroll wheel </b>, on the JFXPanel
     * 
     * @param panel JSFXPanel video panel
     * @param scrollPane JScrollPane scroll bar
     * 
     */
    public static void mouseScroll(JFXPanel panel, JScrollPane scrollPane) {
        SwingUtilities.invokeLater(() -> {
            panel.addMouseWheelListener(e -> {
                int currentPosition = scrollPane.getVerticalScrollBar().getValue();
                int change = e.getWheelRotation() * 20;
                scrollPane.getVerticalScrollBar().setValue(currentPosition + change);
            });
        });
    }

}