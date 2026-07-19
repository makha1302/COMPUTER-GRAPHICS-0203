import java.awt.*;
import javax.swing.*;

public class Lab03_67050203_PolygonTest extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int yPoly[] = { 150, 250, 325, 375, 400, 275, 100 };
        int xPoly[] = { 150, 100, 125, 225, 325, 375, 300 };

        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);

        g.setColor(Color.BLACK);
        g.drawPolygon(poly);
        g.setColor(Color.RED);

        g.drawLine(xPoly[0], yPoly[0], xPoly[2], yPoly[2]);
        g.drawLine(xPoly[0], yPoly[0], xPoly[3], yPoly[3]);
        g.drawLine(xPoly[0], yPoly[0], xPoly[4], yPoly[4]);
        g.drawLine(xPoly[0], yPoly[0], xPoly[5], yPoly[5]);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Triangulation - Exercise 2");
        Lab03_67050203_PolygonTest panel = new Lab03_67050203_PolygonTest();

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}