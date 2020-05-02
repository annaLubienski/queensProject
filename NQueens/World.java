package NQueens;
import Homework_8.Buggs;

import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.TimerTask;

public class World extends JComponent{
    int n;
    ArrayList<Queens> quarray;

    Timer watch;

    public World(int n) {
        this.n = n; //this is the number of queens to be placed on an nxn board
        this.watch = new Timer();
//        watch.scheduleAtFixedRate(new Action(), 1000, 300);


        quarray = new ArrayList<Queens>(); //this arraylist keeps track of the queens we have placed so far
//        System.out.println(solutionFound(0)); //calling the method that will find a solution
//        System.out.println("Final quarray: " + quarray);
    }



    public boolean safe(Queens a) {
    //this method tests if the queen we are currently trying to place is in a safe position
        for (int i = 0; i < quarray.size() - 1; i++) { //we want to check the current queen against each one already in the array (in the rows above her)
            //checking if she's in the same column as another queen (we already know that they won't be in the same row)
            if (a.col == quarray.get(i).col){
                return false;
            }
            //checking if she's in the same diagonal as another queen
            if (Math.abs(a.row - quarray.get(i).row) == Math.abs(quarray.get(i).col-a.col)) {
                return false;
            }
        }
        return true; //if she passed both tests, the current queen is in a safe position
    }

    public boolean solutionFound(int row){
        /*
        This is a recursive method, used to actually find a solution. When this method first called (row = 0),
        we will start with the top row of the board, and try placing a queen in the top row. If she is in
        a safe place, and it is possible to find safe positions for all of the queens below her, then this will
        return true.
        This method takes the argument "int row" because it needs to know what row we're on. So for example, when row = 3,
        we are trying to place the fourth queen within the fourth row in such a way that:
        1. The position she's in is safe from the queens that have already been placed above her.
        2. solutionFound(3 + 1) is true, meaning that the fifth queen has a safe position where solutionFound(4+1) is
        true (which means the sixth queen has a safe position where solutionFound(5+1) is true, etc.)
         */


        if (row == this.n){
            return true;   //If we have made it past the last row in the board, we must have placed all n queens successfully.
        }
        Queens q = new Queens(row, 0);
        quarray.add(q); // creating a new queen all the way to the left of the new row, and adding her to the arrayList
        for (int i = 0; i < this.n; i++) { //for-loop keeps moving the current queen to the right until we find a position that works
            q.moveOverTo(i);
//            watch.wait();
//            watch.schedule(new Action(), 100, 300);
            this.buildBoard(); //this is not necessary. It just prints out the current state of the board.
            repaint();
            Scanner in = new Scanner(System.in);
            System.out.println("Checkpoint: ");
            String p = in.next();
            if (this.safe(q) && solutionFound(row+1)) { //Recursion!
               return true;
            }
        }
        quarray.remove(quarray.size()-1);
        return false;
        /*
        If no position was found within the current row, we will remove this queen from the arrayList and report false.
        Since the current method was called by the queen in the previous row (or the world constructor if this is the first queen),
        returning false lets her know that there is no solution possible if she stays in the position
        she's in right now.
         */

    }

    class Action extends TimerTask{
        public void run() {
            buildBoard();
        }
    }
    public void buildBoard() {
        //This method takes the queens that are in the arrayList called "quarray", and shows their positions in a
        //matrix of 0s and 1s called "board".
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < quarray.size(); i++) {  //i is which row we're creating (how far down)
            ArrayList<Integer> row= new ArrayList<Integer>();
            for (int j=0; j < n; j++){  //First, we fill this new row with zeros
                row.add(0);
            }
            row.set(quarray.get(i).col, 1); //Then, we want to place the queen in this row.
            board.add(row);
        }
        //Prints out the matrix
        for (int i = 0; i < board.size(); i ++) {
            System.out.println(board.get(i));
        }
        System.out.println();
        //We can easily change this method so that it creates an nxn array and THEN puts the queens in.
        //I just like seeing the rows disappearing and reappearing for now.
    }

    public void paintComponent(Graphics g) {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if((i+j)%2 == 0){
                    int m = 4;
                }
//                    squares[i][j].setBackground(Color.white);}
                else {
                    g.setColor(Color.black);
                    g.fillRect(  i*500/n,   j*500/n, 500/n, 500/n);}
//                    squares[i][j].setBackground(Color.black);

//                squares[i][j].addMouseListener(this);
//                c.add(squares[i][j]);
            }
        }

        System.out.println("paint " + quarray);
        for (Queens i : quarray) {
            i.draw(g, n);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Board size: ");
        int n = in.nextInt();
        JFrame frame = new JFrame();
        frame.setVisible(true);
        int width = 500, height = 500;
        frame.setSize(width + 20,height + 40);
        World earth = new World(n);
        frame.add(earth);
//        earth.solutionFound(4);
        earth.solutionFound(0);

        /*
        This main method might be put inside of a BigBang class so that we can keep track of time
        passing since the creation of a world. The BigBang class would be where the user interaction happens.
        Once BigBang gets the input, n, it creates a World that finds a solution for an nxn board,
        and shows its progress a few times per second (visualization).

         */
    }
}
