package Helper.Comp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PanelHelper {

    public static void resizeHeightToFit(JPanel panel) {
        panel.setLayout(null); // just in case :D

        int maxY = 0;
        for (Component comp : panel.getComponents()) {
            Rectangle bounds = comp.getBounds();
            maxY = Math.max(maxY, bounds.y + bounds.height);
        }

        int ExtraSpaceBeforeTheEndOfThePage = 5; // optional
        panel.setPreferredSize(
            new Dimension(
                panel.getWidth(), 
                maxY + ExtraSpaceBeforeTheEndOfThePage
            )
        );
        panel.revalidate(); 
        panel.repaint();
    }

    public static void clear(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
}
