import java.awt.*;
import javax.swing.*;

public class Lab02 extends JPanel {
    public static void main(String[] args) {
        Lab02 m = new Lab02();

        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("First Swing");
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(Color.BLACK);
        // naiveLine(g, 100, 100, 400, 200);
        // g.setColor(Color.red);
        // naiveLine(g, 400, 300, 100, 200);
        // g.setColor(Color.blue);
        // naiveLine(g, 100, 100, 200, 400);

        // g.setColor(Color.BLACK);
        // ddaLine(g, 100, 100, 400, 200);
        // g.setColor(Color.red);
        // ddaLine(g, 400, 300, 100, 200);
        // g.setColor(Color.blue);
        // ddaLine(g, 100, 100, 200, 400);

        g.setColor(Color.BLACK);
        bresenhamLine(g, 100, 100, 400, 200);
        g.setColor(Color.GREEN);
        bresenhamLine(g, 400, 300, 100, 200);
        g.setColor(Color.blue);
        bresenhamLine(g, 100, 100, 200, 400);
    }

    public void naiveLine(Graphics g, int x1, int y1, int x2, int y2) {
        // if (x1 > x2) {
        // int tempX = x1;
        // x1 = x2;
        // x2 = tempX;

        // int tempY = y1;
        // y1 = y2;
        // y2 = tempY;
        // }
        float dx = x2 - x1;
        float dy = y2 - y1;
        float b = y1 - (dy / dx) * x1;
        for (int x = x1; x < x2; x++) {
            int y = (int) Math.round(((dy / dx) * x) + b);
            plot(g, x, y);
        }
    }

    public void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {
        if (x1 > x2) {
            int tempX = x1;
            x1 = x2;
            x2 = tempX;

            int tempY = y1;
            y1 = y2;
            y2 = tempY;
        }
        float dx = x2 - x1;
        float dy = y2 - y1;
        float y = y1;
        float x = x1;
        float m = dy / dx;

        if (m <= 1 && m >= 0) {
            for (int currentX = x1; currentX <= x2; currentX++) {
                plot(g, currentX, Math.round(y));
                y = y + m;
            }
        } else if (m <= -1) {
            y = y2 - m;
            for (int currentX = x2; currentX >= x1; currentX--) {
                y = y + m;
                plot(g, currentX, Math.round(y));
            }
        } else if (m > 1) {
            x = x1 - 1 / m;
            for (int currentY = y1; currentY <= y2; currentY++) {
                x = x + 1 / m;
                plot(g, Math.round(x), currentY);
            }
        } else {
            x = x2 - (1 / m);
            for (int currentY = y2; currentY >= y1; currentY--) {
                x = x + 1 / m;
                plot(g, Math.round(x), currentY);
            }
        }
    }

    public void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        boolean isSwap = false;

        if (dy > dx) {
            int temp = dx;
            dx = dy;
            dy = temp;
            isSwap = true;
        }

        int D = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 0; i <= dx; i++) {
            plot(g, x, y);
            if (D >= 0) {
                if (isSwap) {
                    x += sx;
                } else {
                    y += sy;
                }
                D -= 2 * dx;
            }
            if (isSwap) {
                y += sy;
            } else {
                x += sx;
            }
            D += 2 * dy;
        }
    }

    private void plot(Graphics g, int x, int y) {
        g.fillRect(x, y, 2, 2);
    }
}