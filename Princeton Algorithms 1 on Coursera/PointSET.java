/*
 *  @Zijiao Chen
 *  
 *  Solution to hw5 from Princeton Algorithms 1 on Coursera
 * 
 *  http://coursera.cs.princeton.edu/algs4/assignments/kdtree.html
 */

public class PointSET {
    
    private final SET<Point2D> set; // the set of points
    
    public PointSET() {
        // construct an empty set of points
        this.set = new SET<Point2D>();
    }
    
    public boolean isEmpty() {
        // is the set empty? 
        return set.isEmpty();
    }
    
    public int size() {
        // number of points in the set 
        return set.size();
    }
    
    public  void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null) {throw new java.util.NoSuchElementException("Points cannot be null!");}
        
        if (!set.contains(p)) {set.add(p);}
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p? 
        return set.contains(p);
    }
    
    public void draw() {
        // draw all points to standard draw 
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);

        for (final Point2D p : set)
            p.draw();
    }
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle 
        final Queue<Point2D> queue = new Queue<Point2D>();
        if (rect == null || isEmpty()) {return queue;}
        
        for (final Point2D p: set) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        if (isEmpty()) {return null;}
        
        Point2D nearest = set.min();
        
        for (final Point2D q: set) {
            if (p.distanceSquaredTo(q) < p.distanceSquaredTo(nearest)) {
                 nearest = q;
            }
        }
        return nearest;
    }
}



