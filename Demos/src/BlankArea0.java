import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class BlankArea0 extends JLabel {
    Dimension minSize = new Dimension(100, 50);

    public BlankArea0(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}