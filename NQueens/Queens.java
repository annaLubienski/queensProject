package NQueens;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;


public class Queens extends JComponent {
    int row;
    int col;
    public Queens(int row, int col) {
        //this constructs a single queen
        this.row = row; //how far down she is
        this.col = col; //how far over she is
    }
    public void moveOverTo(int col) {
        this.col = col; //moves her left or right to a different location within her designated row
    }
    public String toString() {
        return "(row: " + this.row + ", col: " + this.col + ")";
    }

    public void draw(Graphics g, int n) {
        // default color is Color.BLACK



//        JLabel label = new JLabel();
//        label.setIcon(new ImageIcon("queen.jpg"));
//        g.drawImage(label.createImage(), 0, 0, 10, 10, null );
        g.setColor(Color.red);
        Font myFont = new Font("Courier", Font.BOLD, 40);
        g.setFont(myFont);
        g.drawString("Q", this.col*500/n + 200/n,   this.row*500/n + 250/n);
//        g.fillRect(  this.col*500/n,   this.row*500/n, 10, 10); // background

    }
}
