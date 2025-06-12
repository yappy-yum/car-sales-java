package Helper.Config.PanelConfig;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 
 * Whole Page Paint
 * 
 */
public abstract class GradientPanel extends JPanel {

    /*//////////////////////////////////////////////////////////////
                                 Colors
    //////////////////////////////////////////////////////////////*/    
    
    private static final Color BLACK = Color.decode("#1a1919");
    private static final Color DARK_BLUE = new Color(25, 25, 128);
    private static final Color WHITE = Color.decode("#f5e6f3");
    private static final Color PINK = new Color(255, 192, 203);

    /*//////////////////////////////////////////////////////////////
                           Abstract Variable
    //////////////////////////////////////////////////////////////*/    

    public abstract boolean isDarkTheme();

    /*//////////////////////////////////////////////////////////////
                             PaintComponent
    //////////////////////////////////////////////////////////////*/    

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D G2D = (Graphics2D) G;

        G2D.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // determine the gradient colors
        Color topColor = isDarkTheme() ? BLACK : WHITE;
        Color bottomColor = isDarkTheme() ? DARK_BLUE : PINK;

        // setup gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, topColor, 
            getWidth(), getHeight(), bottomColor
        );  

        // apply gradient
        G2D.setPaint(gradient);
        G2D.fillRect(0, 0, getWidth(), getHeight());
        
    }

}
