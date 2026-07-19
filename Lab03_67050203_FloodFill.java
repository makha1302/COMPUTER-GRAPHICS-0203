import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class Lab03_67050203_FloodFill extends JPanel {

    private static class Point2D {
        int x, y;
        Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public BufferedImage floodFill(BufferedImage img, int startX, int startY, Color targetColor, Color replacementColor) {
        int targetRGB = targetColor.getRGB();
        int replacementRGB = replacementColor.getRGB();

        if (targetRGB == replacementRGB || startX < 0 || startX >= img.getWidth() || startY < 0 || startY >= img.getHeight()) {
            return img;
        }

        if (img.getRGB(startX, startY) != targetRGB) {
            return img;
        }

        Queue<Point2D> q = new LinkedList<>();
        
        img.setRGB(startX, startY, replacementRGB);
        q.add(new Point2D(startX, startY));

        while (!q.isEmpty()) {
            Point2D current = q.poll();
            int cx = current.x;
            int cy = current.y;

            if (cy + 1 < img.getHeight() && img.getRGB(cx, cy + 1) == targetRGB) {
                img.setRGB(cx, cy + 1, replacementRGB);
                q.add(new Point2D(cx, cy + 1));
            }

            if (cy - 1 >= 0 && img.getRGB(cx, cy - 1) == targetRGB) {
                img.setRGB(cx, cy - 1, replacementRGB);
                q.add(new Point2D(cx, cy - 1));
            }

            if (cx + 1 < img.getWidth() && img.getRGB(cx + 1, cy) == targetRGB) {
                img.setRGB(cx + 1, cy, replacementRGB);
                q.add(new Point2D(cx + 1, cy));
            }

            if (cx - 1 >= 0 && img.getRGB(cx - 1, cy) == targetRGB) {
                img.setRGB(cx - 1, cy, replacementRGB);
                q.add(new Point2D(cx - 1, cy));
            }
        }
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage buffer = new BufferedImage(601, 601, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 600, 600);

        int xPoly[] = {150, 250, 325, 375, 400, 275, 100};
        int yPoly[] = {150, 100, 125, 225, 325, 375, 300};
        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
        
        g2.setColor(Color.BLACK);
        g2.drawPolygon(poly);
        g2.dispose();

        buffer = floodFill(buffer, 150, 150, Color.WHITE, Color.GREEN);

        g.drawImage(buffer, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flood Fill - Exercise 3");
        Lab03_67050203_FloodFill panel = new Lab03_67050203_FloodFill();
        
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}