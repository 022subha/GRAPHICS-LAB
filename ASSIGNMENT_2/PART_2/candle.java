import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class candle
  extends Applet
  implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 20;
  int temp = 1;
  Button b3 = new Button(" Light Up ");
  Button b4 = new Button(" Put Out ");

  public void init() {
    setBackground(new Color(255, 255, 255));
    b3.setBackground(new Color(31, 70, 144));
    b4.setBackground(new Color(255, 229, 180));
    add(b3);
    add(b4);
    addMouseWheelListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;
    Candle f = new Candle();
    f.drawCandle(g);
  }

  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    if(y>=x*x/10)
    g.fillRect(
      originX + (x * gap) - gap / 2,
      originY - (y * gap) - gap / 2,
      gap,
      gap
    );
  }

  public void actionPerformed(ActionEvent e) {
    Candle c = new Candle();
    if (e.getSource() == b3) c.light_candle();
    if (e.getSource() == b4) c.put_out_candle();
  }

  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  public void zoom(int i) {
    if (gap + i >= 1 && gap + i <= 300) {
      gap += i;
      repaint();
    }
  }

  int round(float n) {
    if (n - (int) n < 0.5) return (int) n;
    return (int) (n + 1);
  }

  void DDALine(Graphics g, int x0, int y0, int x1, int y1, Color c) {
    int dx = (x1 - x0);
    int dy = (y1 - y0);

    int step;
    if (Math.abs(dx) > Math.abs(dy)) {
      step = Math.abs(dx);
    } else {
      step = Math.abs(dy);
    }

    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;
    float x = (float) x0;
    float y = (float) y0;

    for (int i = 0; i < step; i++) {
      plotPoint(g, round(x), round(y), c);
      x += x_incr;
      y += y_incr;
    }
  }

  class Fire {

    int x1;
    int x2;
    int a;

    Fire() {
      x1 = -400;
      x2 = 400;
      a = 600;
    }

    public void paint(Graphics g) {
      drawFire(g);
    }

    public void drawFire(Graphics g) {
      while (x1 != x2) {
        if (a - (x1 * x1) >= 0) {
          int r = (int) (Math.random() * 10);
          Color c1 = new Color(255, 0, 0);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r, c1);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r + 1, c1);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r + 2, c1);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r + 3, c1);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r + 4, c1);
          DDALine(g, 0, 0, x1, (a - (x1 * x1)) / 10 + r + 5, c1);
        }
        x1++;
      }
  //     x1 = -400;
  //     x2 = 400;
  //     while (x1 != x2) {
  //       if (a - (x1 * x1) - 200 >= 0) {
  //         int r = (int) (Math.random() * 10);
  //         Color c1 = new Color(255, 128, 0);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r + 1, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r + 2, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r + 3, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r + 4, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 200) / 10 + r + 5, c1);
  //       }
  //       x1++;
  //     }
  //     x1 = -400;
  //     x2 = 400;
  //     while (x1 != x2) {
  //       if (a - (x1 * x1) - 400 >= 0) {
  //         int r = (int) (Math.random() * 10);
  //         Color c1 = new Color(255, 255, 0);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r + 1, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r + 2, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r + 3, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r + 4, c1);
  //         DDALine(g, 0, 0, x1, (a - (x1 * x1) - 400) / 10 + r + 5, c1);
  //       }
  //       x1++;
  //     }
     }
  }

  class Candle {

    public void paint(Graphics g) {
      drawCandle(g);
    }

    public void drawCandle(Graphics g) {
      if (temp == 1) {
        Fire f = new Fire();
        f.paint(g);
      }
      drawBase(g, new Color(128, 128, 128));
    }

    public void light_candle() {
      temp = 1;
      repaint();
    }

    public void put_out_candle() {
      temp = 0;
      repaint();
    }

    public void drawBase(Graphics g, Color c) {
      g.setColor(c);
      g.fillRect((originX - 50), originY, 100, 600);
    }
  }
}
