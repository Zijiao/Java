/*
 * @ Zijiao Chen
 * 
 *  hw 3 for Princeton Algo 1 on Coursera
 * 
 *  A brute force approach to examine 4 points at a time and checks whether they all lie on the same line segment
 * 
 * http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 * 
 */

import java.util.Arrays;

public class Brute {
    public static void main (String args[]) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
    
 
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point point = new Point(x, y);
            points[i] = point;
            point.draw();           
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    double slope1 = points[i].slopeTo(points[j]);
                    double slope2 = points[i].slopeTo(points[k]);
                    if (slope1 == slope2) {
                        for (int l = k + 1; l < N; l++) {
                            double slope3 = points[i].slopeTo(points[l]);
                            if (slope1 == slope3) {
                                StdOut.println(points[i].toString() + " -> "
                                    + points[j].toString() + " -> " + points[k].toString() 
                                    + " -> " + points[l].toString());
                            points[i].drawTo(points[l]);
                            }
                        }
                    }
                }
            }
        }
        StdDraw.show(0);
    } 
}



