package Helper.RoundedBorder;

import javax.swing.border.Border;
import java.awt.Color;

/**
 * 
 * this class calls the {@link #roundedBorder} class and return 
 * the created rounded border shape in `Border` data type
 * 
 * <p>
 * 
 * this class is useful when you want to create a rounded border
 * for a component that act as argument to be passed to
 * 
 * @author yappy-yum
 * 
 */
public class roundedBorderFactory {
    
    /**
     * help calling {@link #roundedBorder} class, and return 
     * Border data type, since there's no way a constructor
     * can return something
     * 
     * @param radius border curve, 0 means no curve or sharp
     * @param borderColor border line color, use null for transparent
     * @param fillColor inner fill color, use null for transparent
     * @return Border data type
     * 
     */
    public Border create(
        int radius, 
        Color borderColor,
        Color fillColor
    ) {
        return new roundedBorder(
            radius, 
            borderColor,
            fillColor
        );
    }
}
