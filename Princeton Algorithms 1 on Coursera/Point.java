/*************************************************************************
  * 
  * hw3 for Printceton algo 1
  *
 * Name: Zijiao Chen
 * Email: chen_zi_jiao@hltmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 * 
 * http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
 
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double slope1, slope2;
            slope1 = slopeTo(a);
            slope2 = slopeTo(b);
            if (slope1 > slope2) { return 1; }
            else if (slope1 < slope2) { return -1;}
            else { return 0; }
        }
    }


    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y == that.y && this.x == that.x) { return Double.NEGATIVE_INFINITY; }
        else if (this.y == that.y) { return +0.0; }
        else if (this.x == that.x) { return Double.POSITIVE_INFINITY; }
        else { return (double)(that.y - this.y) / (that.x - this.x); }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y > that.y) { return 1; }
        else if (this.y < that.y) { return -1; }
        else {
            if (this.x > that.x) { return 1; }
            else if (this.x < that.x) { return -1; }
            else { return 0; }
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}