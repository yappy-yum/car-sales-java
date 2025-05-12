package Helper.Comp;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class wordWrap extends StyledEditorKit {

    /** The custom {@link ViewFactory} responsible for producing word-wrapping views. */
    private final ViewFactory defaultFactory = new WrapColumnFactory();

    /**
     * Returns the custom {@link ViewFactory} that produces views supporting word wrapping.
     *
     * @return the custom ViewFactory
     */
    @Override
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }

    /**
     * A custom {@link ViewFactory} that creates views with support for word wrapping.
     * <p>
     * This factory overrides the creation of {@link LabelView} with a custom {@link WrapLabelView}
     * that allows horizontal shrinking, thus enabling wrapping.
     */
    static class WrapColumnFactory implements ViewFactory {

        /**
         * Creates a view based on the type of the given {@link Element}.
         *
         * @param elem the element to create a view for
         * @return a view that handles layout and rendering of the element, with support for wrapping
         */
        public View create(Element elem) {
            String kind = elem.getName();

            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(elem); // Enables wrapping
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem); // Default paragraph view
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS); // Section containing paragraphs
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem); // Embedded components (e.g., buttons)
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem); // Embedded icons
                }
            }

            // Fallback to default label view if type is unknown
            return new LabelView(elem);
        }
    }

    /**
     * A customized {@link LabelView} that allows word wrapping by returning a minimum horizontal span of 0.
     * <p>
     * In default behavior, a {@code LabelView} requires the full width of its content to be displayed
     * on one line. By overriding the minimum span on the X axis to zero, this allows it to break lines
     * and wrap words when space is constrained.
     */
    static class WrapLabelView extends LabelView {

        /**
         * Constructs a new {@code WrapLabelView} for the specified element.
         *
         * @param elem the element to represent
         */
        public WrapLabelView(Element elem) {
            super(elem);
        }

        /**
         * Returns the minimum span along an axis.
         * <p>
         * This is overridden to return {@code 0} for the X axis, enabling the layout manager to
         * wrap the view horizontally when needed.
         *
         * @param axis the axis (either {@link View#X_AXIS} or {@link View#Y_AXIS})
         * @return the minimum span along the specified axis
         * @throws IllegalArgumentException if the axis is invalid
         */
        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0; // Allows wrapping by not forcing a minimum width
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis); // Keep default vertical behavior
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }
}