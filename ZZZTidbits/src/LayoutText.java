import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LayoutText {

    public static void main(String[] args) {
        new LayoutText();
    }

    public LayoutText() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private String text;

        public TestPane() {
            text = "hi";
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            FontRenderContext context = g2d.getFontRenderContext();
            Font font = new Font("Arial", Font.BOLD, 48);
            TextLayout txt = new TextLayout(text, font, context);

            Rectangle2D bounds = txt.getBounds();
            int x = (int) ((getWidth() - (int) bounds.getWidth()) / 2);
            int y = (int) ((getHeight() - (bounds.getHeight() - txt.getDescent())) / 2);
            y += txt.getAscent() - txt.getDescent();

            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawString(text, x, y);

        }
    }

}