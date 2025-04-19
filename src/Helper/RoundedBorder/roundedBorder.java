package Helper.RoundedBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

/**
 * Class that used to create a rounded border
 * 
 * <p>
 * 
 * example of usage:
 * 
 * <pre>
 * component.setBorder(new roundedBorder(10, Color.BLACK, Color.WHITE));
 * </pre>
 * 
 * @author yappy-yum
 * 
 */
public class roundedBorder implements Border {

    int radius;
    Color borderColor;
    Color fillColor;
    
    public roundedBorder(
        int radius,
        Color borderColor,
        Color fillColor
    ) {
        this.radius = radius;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(
            radius + 1, 
            radius + 1, 
            radius + 2, 
            radius
        );
    }

    @Override
    public boolean isBorderOpaque() { return true; }

    @Override
    public void paintBorder(
        Component c, 
        Graphics g, 
        int x, 
        int y,
        int width, 
        int height
    ) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (fillColor != null) {
            g2d.setColor(fillColor);
            g2d.fillRoundRect(
                x, y, 
                width - 1, height - 1, 
                radius, radius
            );
        }

        g2d.setColor(borderColor);
        g2d.drawRoundRect(
            x, y, 
            width - 1, height - 1, 
            radius, radius
        );
    }
    
}
