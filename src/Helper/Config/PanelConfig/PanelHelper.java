package Helper.Config.PanelConfig;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Helper.blur;

public class PanelHelper {

    /*//////////////////////////////////////////////////////////////
                       Auto-Resize JPanel Height
    //////////////////////////////////////////////////////////////*/    

    /**
     * Helper method to resize a JPanel to fit its content
     * 
     * <p>
     * 
     * Only call this right after all the components has been added
     * 
     * @param panel JPanel to be resized
     * 
     */
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

    /*//////////////////////////////////////////////////////////////
                             Remove JPanel
    //////////////////////////////////////////////////////////////*/    

    public static void clear(blur blur, JPanel panel) {

        if (blur != null) {
            blur.removeBlur();
            blur = null;
        }

        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        
    }

    public static void clear(blur blur, JPanel panel, Object classToInteractWith, String _method) {

        clear(blur, panel);

        SwingUtilities.invokeLater(() -> {
            try 
            {
                classToInteractWith
                        .getClass()
                        .getMethod(_method)
                        .invoke(classToInteractWith);
            } 
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException LOVE) 
            {
                LOVE.printStackTrace(); 
            }
        });

    }
}
