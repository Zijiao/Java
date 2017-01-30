/**
 *@author jingli430
 *
 * @perform T independent computational experiments on an N-by-N grid.
 * Monte Carlo simulation to test the threshold to get percolated
 * repeat the test T times 
 * @param N
 * @param T
 * @version final
 */

public class PercolationStats {
 private double[] fractions; //the fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
 private int testTimes; // T

 /**
  * perform T independent computational experiments on an N-by-N grid.
  * Initialize the stats systems.
  * @param N
  * @param T
  */
 public PercolationStats(int N, int T) {
  if (N <= 0 || T <= 0) {
   throw new java.lang.IllegalArgumentException("invalid input of N or T!");
  }
  fractions = new double[T];
  testTimes = T;
  for (int n = 0; n < testTimes; n++) { // repeat test T times
      Percolation perc = new Percolation(N); // 1) reset Percolation for every test 2) local variable will save memory space.
   double openSiteCount = 0.0; // ***IMPORTANT*** must set as double, not int here. OR you need cast it to double later 
   while (!perc.percolates()) {  // randomly open a site if still not percolates
    int i = StdRandom.uniform(1, N+1); //randomly generate i and j
    int j = StdRandom.uniform(1, N+1);
    if (!perc.isOpen(i, j)) {
     perc.open(i, j);
     openSiteCount++;
    }
   }
   double fraction = openSiteCount / (N * N); //store test result
   fractions[n] = fraction;
  }
 }
 
 /**
  * sample mean of percolation threshold.
  * @return
  */
 public double mean() {
  return StdStats.mean(fractions);
 }
 
 /**
  * sample standard deviation of percolation threshold.
  * @return
  */
 public double stddev() {
  if (testTimes == 1) return Double.NaN; // stddev is undefined when T = 1;
  return StdStats.stddev(fractions);
 }
 /**
  * returns lower bound of the 95% confidence interval.
  * @return
  */
 public double confidenceLo() {
  double lowerBound =  mean() - (1.96 * stddev() / Math.sqrt(testTimes));
  return lowerBound;
 }
 /**
  * returns upper bound of the 95% confidence interval.
  * @return
  */
 public double confidenceHi() {
  double upperBound =  mean() + (1.96 * stddev() / Math.sqrt(testTimes));
  return upperBound;
 }
 
 /**
  * main to test all the features.
  * @param args
  */
 public static void main(String[] args) {  // test client, described below
  //int N = StdIn.readInt();
  //int T = StdIn.readInt();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
  PercolationStats percStats = new PercolationStats(N, T);
  StdOut.println("mean                    = " + percStats.mean());
  StdOut.println("stddev                  = " + percStats.stddev());
  StdOut.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());
 }   
 
}