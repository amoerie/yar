package util;

import java.awt.*;

/**
 This class simplifies the use of the GridBagConstraints
 class. Just use GridBagConstraints as you would normally, but type GBC instead of GridBagConstraints.
 Also, this class provides builder-type setters so you can chain set methods.
 If you don't specifically call a set method for certain attributes, its default values will be used.

 Per method, some explanatory text was added with the courtesy of Oracle.
 Original document to be found here: http://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 @see GridBagConstraints
 @see GridBagLayout
 * @author moerie
 */
public class GBC extends GridBagConstraints
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8823639310742681162L;

	/**
     Constructs a GBC with a given gridx and gridy position and
     all other grid bag constraint values set to the default.
     Specify the row and column at the upper left of the component.
     The leftmost column has address gridx=0 and the top row has address gridy=0.
     Use GBC.RELATIVE (the default value) to specify that the component be placed
     just to the right of (for gridx) or just below (for gridy) the component that was added to the container just
     before this component was added. We recommend specifying the gridx and gridy values for each component rather
     than just using GBC.RELATIVE; this tends to result in more predictable layouts.
     @param gridx the gridx position
     @param gridy the gridy position
     */
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     Sets the cell spans.
     Specify the number of columns (for gridwidth) or rows (for gridheight) in the component's display area.
     These constraints specify the number of cells the component uses, not the number of pixels it uses.
     The default value is 1.
     Use GBC.REMAINDER to specify that the component be
     the last one in its row (for gridwidth) or column (for gridheight).
     Use GBC.RELATIVE to specify that the component be the next to last one
     in its row (for gridwidth) or column (for gridheight).
     We recommend specifying the gridwidth and gridheight values for each component
     rather than just using GBC.RELATIVE and GBC.REMAINDER;
     this tends to result in more predictable layouts.
     Note: GridBagLayout does not allow components to span multiple rows unless the component
     is in the leftmost column or you have specified positive gridx and gridy values for the component.
     @param gridwidth the cell span in x-direction
     @param gridheight the cell span in y-direction
     @return this object for further modification
     */
    public GBC setSpan(int gridwidth, int gridheight)
    {
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        return this;
    }

    /**
     Sets the anchor.
     Used when the component is smaller than its display area to determine where (within the area) to place the component.
     Valid values (defined as GBC constants) are CENTER (the default), PAGE_START, PAGE_END,
     LINE_START, LINE_END, FIRST_LINE_START, FIRST_LINE_END, LAST_LINE_END, and LAST_LINE_START.
     Here is a picture of how these values are interpreted in a container that has the default,
     left-to-right component orientation.<br/>
     FIRST_LINE_START	PAGE_START	FIRST_LINE_END<br/>
     LINE_START	        CENTER	    LINE_END<br/>
     LAST_LINE_START	PAGE_END	LAST_LINE_END<br/>
     @param anchor the anchor value
     @return this object for further modification
     */
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     Sets the fill direction.
     Used when the component's display area is larger than the component's requested size to determine whether and
     how to resize the component. Valid values (defined as GBC constants)
     include NONE (the default), HORIZONTAL (make the component wide enough to fill its display area horizontally,
     but do not change its height), VERTICAL (make the component tall enough to fill its display area vertically,
     but do not change its width), and BOTH (make the component fill its display area entirely).
     @param fill the fill direction
     @return this object for further modification
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     Sets the cell weights.
     Specifying weights is an art that can have a significant impact on the appearance
     of the components a GridBagLayout controls.
     Weights are used to determine how to distribute space among columns (weightx) and among rows (weighty);
     this is important for specifying resizing behavior.
     Unless you specify at least one non-zero value for weightx or weighty,
     all the components clump together in the center of their container.
     This is because when the weight is 0.0 (the default),
     the GridBagLayout puts any extra space between its grid of cells and the edges of the container.

     Generally weights are specified with 0.0 and 1.0 as the extremes: the numbers in between are used as necessary.
     Larger numbers indicate that the component's row or column should get more space.
     For each column, the weight is related to the highest weightx specified for a component within that column,
     with each multicolumn component's weight being split somehow between the columns the component is in.
     Similarly, each row's weight is related to the highest weighty specified for a component within that row.
     Extra space tends to go toward the rightmost column and bottom row.
     @param weightx the cell weight in x-direction
     @param weighty the cell weight in y-direction
     @return this object for further modification
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     Sets the insets of this cell.
     Specifies the external padding of the component --
     the minimum amount of space between the component and the edges of its display area.
     The value is specified as an Insets object. By default, each component has no external padding.
     @param distance the spacing to use in all directions
     @return this object for further modification
     */
    public GBC setInsets(int distance)
    {
        this.insets = new java.awt.Insets(
                distance, distance, distance, distance);
        return this;
    }

    /**
     Sets the insets of this cell.
     Specifies the external padding of the component --
     the minimum amount of space between the component and the edges of its display area.
     The value is specified as an Insets object. By default, each component has no external padding.
     @param top the spacing to use on top
     @param left the spacing to use to the left
     @param bottom the spacing to use on the bottom
     @param right the spacing to use to the right
     @return this object for further modification
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new java.awt.Insets(
                top, left, bottom, right);
        return this;
    }

    /**
     Sets the insets of this cell.
     Specifies the external padding of the component --
     the minimum amount of space between the component and the edges of its display area.
     The value is specified as an Insets object. By default, each component has no external padding.
     @param insets the insets object containing the top, left, bottom and right padding
     @return this object for further modification
     */
    public GBC setInsets(Insets insets) {
        this.insets = insets;
        return this;
    }

    /**
     Sets the internal padding.
     Specifies the internal padding: how much to add to the size of the component.
     The default value is zero.
     The width of the component will be at least its minimum width plus ipadx*2 pixels,
     since the padding applies to both sides of the component.
     Similarly, the height of the component will be at least its minimum height plus ipady*2 pixels.
     @param ipadx the internal padding in x-direction
     @param ipady the internal padding in y-direction
     @return this object for further modification
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
