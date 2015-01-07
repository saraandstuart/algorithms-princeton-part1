/**
 * 18th September 2014
 * 
 * This class performs a series of computational experiments of Percolation.
 * 
 * Run it from command line by typing java PercolationStats N T where N is the 
 * size of grid and T is the number of computational experiments
 * 
 * @author Stuart Shannon
 * 
 */
public class PercolationStats {

    // Holds number of open sites at percolation / total number of sites
    private double[] percolationThresholds;
    private int numberOfExperiments;

    /**
     * Perform T independent computational experiments on an N-by-N grid
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException(
                    "N or T was less than zero, N=" + N + ", T=" + T);
        }
        numberOfExperiments = T;
        percolationThresholds = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            int numOpenSites = 0;

            while (!perc.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int col = StdRandom.uniform(N) + 1;
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    numOpenSites++;
                }
            }
            percolationThresholds[i] = ((double) numOpenSites) / (N*N);
        }

    }

    /**
     * Sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(percolationThresholds);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        if (numberOfExperiments == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(percolationThresholds);
    }   

    /**
     * Returns lower bound of the 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - confidenceInterval();
    }

    /**
     * Returns upper bound of the 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + confidenceInterval();
    }

    private double confidenceInterval() {
        return (1.96 * stddev()) / Math.sqrt(numberOfExperiments);
    }

    /**
     * Test client
     * @param args
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);         // N-by-N percolation system
        int T = Integer.parseInt(args[1]);         // T experiments

        PercolationStats percStats = new PercolationStats(N, T);

        StdOut.println("mean = " + percStats.mean());
        StdOut.println("stddev = " + percStats.stddev());
        StdOut.println("95% confidence interval = " 
                + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }
}