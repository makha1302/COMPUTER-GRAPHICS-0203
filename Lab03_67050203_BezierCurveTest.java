import java.awt.*;
import javax.swing.*;

public class Lab03_67050203_BezierCurveTest extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // int x1 = 100, y1 = 400;
        // int x2 = 200, y2 = 100;
        // int x3 = 400, y3 = 100;
        // int x4 = 500, y4 = 400;

        int x1 = 500, y1 = 400; // สลับเอาจุด 4 มาเป็นจุด 1
        int x2 = 200, y2 = 100;
        int x3 = 400, y3 = 100;
        int x4 = 100, y4 = 400; // สลับเอาจุด 1 มาเป็นจุด 4

        // int x1 = 100, y1 = 400;
        // int x2 = 400, y2 = 100; // สลับเอาจุด 3 มาเป็นจุด 2
        // int x3 = 200, y3 = 100; // สลับเอาจุด 2 มาเป็นจุด 3
        // int x4 = 500, y4 = 400;


        g2.setColor(Color.RED);
        g2.fillOval(x1 - 4, y1 - 4, 8, 8);
        g2.fillOval(x2 - 4, y2 - 4, 8, 8);
        g2.fillOval(x3 - 4, y3 - 4, 8, 8);
        g2.fillOval(x4 - 4, y4 - 4, 8, 8);

        g2.setColor(Color.BLUE);

        int steps = 1000;
        int prevX = x1;
        int prevY = y1;

        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;

            double tempX = Math.pow(1 - t, 3) * x1 +
                    3 * t * Math.pow(1 - t, 2) * x2 +
                    3 * Math.pow(t, 2) * (1 - t) * x3 +
                    Math.pow(t, 3) * x4;

            double tempY = Math.pow(1 - t, 3) * y1 +
                    3 * t * Math.pow(1 - t, 2) * y2 +
                    3 * Math.pow(t, 2) * (1 - t) * y3 +
                    Math.pow(t, 3) * y4;

            int x = (int) Math.round(tempX);
            int y = (int) Math.round(tempY);

            if (i > 0) {
                g2.drawLine(prevX, prevY, x, y);
            }

            prevX = x;
            prevY = y;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bezier Curve - Exercise 1");
        Lab03_67050203_BezierCurveTest panel = new Lab03_67050203_BezierCurveTest();

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}