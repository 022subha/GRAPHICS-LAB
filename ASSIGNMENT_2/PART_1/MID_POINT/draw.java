import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class draw extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 20;
  Button b1 = new Button(" + ");
  Button b2 = new Button(" - ");

  public void init() {
    setBackground(new Color(232, 249, 253));
    b1.setBackground(new Color(31, 70, 144));
    b2.setBackground(new Color(255, 229, 180));
    add(b1);
    add(b2);
    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;
    drawGrid(g);
    drawXaxis(g);
    drawYaxis(g);
    drawOriginCircle(g);

    drawLine(g, 0, 0, 700, 900);
    drawLine(g, 0,0,-900,700);
    drawLine(g, 700,-500,0,0);
    drawLine(g,-100,-100,10,0);
  }

  //Function to draw origin
  public void drawOriginCircle(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(originX - 5, originY - 5, 10, 10);
  }

  //Function for plotting points
  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
      originX + (x * gap) - gap / 2,
      originY - (y * gap) - gap / 2,
      gap,
      gap
    );
  }

  //Function to draw X-axis
  public void drawXaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(0, originY - 2, width, 4);
  }

  //Function to draw Y-axis
  public void drawYaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(originX - 2, 0, 4, height);
  }

  // Function to draw the Grid
  public void drawGrid(Graphics g) {
    drawHorizontalLines(g);
    drawVeritcalLines(g);
  }

  //Function to draw the horizontal lines of the grid
  public void drawHorizontalLines(Graphics g) {
    g.setColor(Color.YELLOW);
    for (int i = originX; i <= width; i += gap) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = originX; i >= 0; i -= gap) {
      g.drawLine(i, 0, i, height);
    }
  }

  //Function to draw the vertical lines of the grid
  public void drawVeritcalLines(Graphics g) {
    g.setColor(Color.YELLOW);
    for (int i = originY; i <= height; i += gap) {
      g.drawLine(0, i, width, i);
      // add coordinate text
    }
    for (int i = originY; i >= 0; i -= gap) {
      g.drawLine(0, i, width, i);
    }
  }

  //Function for the buttons
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) zoom(10);
    if (e.getSource() == b2) zoom(-10);
  }

  //Function for the mousewheel
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  //Function for the zoom in feature
  public void zoom(int i) {
    if (gap + i >= 1 && gap + i <= 300) {
      gap += i;
      repaint();
    }
  }

//   public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
//     int x = x1;
//     int y = y1;
//     double m = (double)Math.abs(y2 - y1) / Math.abs(x2 - x1);

//     if (m <= 1) {
//       double p = ((double)1/2) - m;
//       plotPoint(g,x,y,Color.green);
//       while (x<x2) {
//         x++;
//         if (p < 0) {
          
          
//           y = y + 1;
//           p = p + 1 - m;
//           plotPoint(g, x, y, Color.green);
//         } else{
          
//           p = p - m;
//           plotPoint(g, x, y, Color.green);
//         }
//       }
//     }
//     else{
//       double p=1-((double)m/2);
//       plotPoint(g,x,y,Color.green);
//       while (x<x2){
//         y++;
//         if (p < 0) {
//           p = p + 1;
//           plotPoint(g, x, y, Color.green);
//         } else{
//           x++;
//           p = p - m+1;
//           plotPoint(g, x, y, Color.green);
//         }
//       }
//     }
//  }


public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
  int x = x1;
  int y = y1;
  double m = (double)(y2 - y1) / (x2 - x1);
if(m>=0){
  if (m <= 1) {
    double p = ((double)1/2) - m;
    plotPoint(g,x,y,Color.green);
    while (x<x2) {
      x++;
      if (p < 0) {
        
        
        y = y + 1;
        p = p + 1 - m;
        plotPoint(g, x, y, Color.green);
      } else{
        
        p = p - m;
        plotPoint(g, x, y, Color.green);
      }
    }
  }
  else{
    double p=1-((double)m/2);
    plotPoint(g,x,y,Color.green);
    while (x<x2){
      y++;
      if (p < 0) {
        p = p + 1;
        plotPoint(g, x, y, Color.green);
      } else{
        x++;
        p = p - m+1;
        plotPoint(g, x, y, Color.green);
      }
    }
  }
}
else{
  if (Math.abs(m) <= 1) {
    double p = ((double)1/2) + m;
    plotPoint(g,x,y,Color.green);
    while (x>x2) {
      x--;
      if (p < 0) {
        
        
        y = y + 1;
        p = p + 1 + m;
        plotPoint(g, x, y, Color.green);
      } else{
        
        p = p + m;
        plotPoint(g, x, y, Color.green);
      }
    }
  }
  else{
    double p=1+((double)m/2);
    plotPoint(g,x,y,Color.green);
    while (x<x2){
      y--;
      if (p < 0) {
        p = p + 1;
        plotPoint(g, x, y, Color.green);
      } else{
        x++;
        p = p + m+1;
        plotPoint(g, x, y, Color.green);
      }
    }
  }
}
}
  
}

