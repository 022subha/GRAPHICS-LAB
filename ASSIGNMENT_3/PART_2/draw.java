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
    setBackground(Color.black);
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
    //drawEllipse(g, 100, 50);
    drawEllipse(g, 60, 50);
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
      originX + (x * gap) - gap / 4,
      originY - (y * gap) - gap / 4,
      gap/2 ,
      gap /2
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
    drawXaxis(g);
    drawYaxis(g);
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

  public void drawEllipse(Graphics g,int rx,int ry){
    int x=0;
    int y=ry;
    double p1=(ry*ry)-(rx*rx*ry)+(double)(rx*rx)/4;
    plotPoint(g, x, y, Color.red);
    plotPoint(g, x, -y, Color.red);
    while(2*ry*ry*x<=2*rx*rx*y){
        if(p1<0){
            x++;
            p1=p1+(2*ry*ry*x)+(ry*ry);
        }
        else{
            x++;
            y--;
            p1=p1+(2*ry*ry*x)+(ry*ry)-(2*rx*rx*y);
        }
        plotPoint(g, x, y, Color.red);
        plotPoint(g, -x, y, Color.red);
        plotPoint(g, x, -y, Color.red);
        plotPoint(g, -x, -y, Color.red);
    }

    double p2=(ry*ry*(x+0.5)*(x+0.5))+((y-1)*(y-1)*rx*rx)-rx*rx*ry*ry;
    while(2*ry*ry*x>2*rx*rx*y && (y!=0)){
        if(p2<0){
            x++;
            y--;
            p2=p2+(2*ry*ry*x)-(2*rx*rx*y)+(rx*rx);
        }
        else{
            
            y--;
            p2=p2-(2*rx*rx*y)+(rx*rx);
        }

        plotPoint(g, x, y, Color.red);
        plotPoint(g, -x, y, Color.red);
        plotPoint(g, x, -y, Color.red);
        plotPoint(g, -x, -y, Color.red);
    }
  }
}
