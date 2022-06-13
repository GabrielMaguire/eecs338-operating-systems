/**
 * Author: Gabriel Maguire
 * Date: 10/18/2020
 * 
 * This code was written to simulate FIFO and second-change page replacement
 * algorithms for an operating systems course.
 */

import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final int NUM_EPOCHS = 10;

    private static final int NUM_PAGES = 1000000;
    private static final int SAMPLE_NUM_PAGES = 8;
    private static final int PAGE_RANGE = 12;
    private static final int MEM_PAGE_SIZE = 3;

    private static int[] referenceString;

    public static void main(String[] args) {
        
        System.out.println("===========================================");
        System.out.println("========== Constant Distribution ==========");
        System.out.println("===========================================");
        createConstantDistribution(SAMPLE_NUM_PAGES);
        printReferenceString();
        runDemo();

        System.out.println("===========================================");
        System.out.println("======== Exponential Distribution =========");
        System.out.println("===========================================");
        createExponentialDistribution(SAMPLE_NUM_PAGES);
        printReferenceString();
        runDemo();

        System.out.println("===========================================");
        System.out.println("========= Segmented Distribution ==========");
        System.out.println("===========================================");
        createSegmentedDistribution(SAMPLE_NUM_PAGES);
        printReferenceString();
        runDemo();


        System.out.println("\n\n");


        System.out.println("*******************************************");
        System.out.println("***** FIFO vs Clock Algorithm Testing *****");
        System.out.println("*******************************************\n");
        
        System.out.printf("Reduction in page misses for a constant distribution:\n\t%.5f %%\n\n", runDiagnostics("Constant"));
        System.out.printf("Reduction in page misses for an exponential distribution:\n\t%.5f %%\n\n", runDiagnostics("Exponential"));
        System.out.printf("Reduction in page misses for a segmented distribution:\n\t%.5f %%\n\n", runDiagnostics("Segmented"));

    }

    /**
     * This method initializes an integer array of evenly distributed
     * random values between 0 and PAGE_RANGE-1.
     * @param length Length of the random array
     */
    private static void createConstantDistribution(int length) {
        referenceString = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int address = random.nextInt(PAGE_RANGE);
            referenceString[i] = address;
        }
    }

    /**
     * This method initializes an integer array with an exponential
     * distribution on numbers between 0 and PAGE_RANGE-1.
     * @param length Length of the random array
     */
    private static void createExponentialDistribution(int length) {
        referenceString = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int address = (int)(Math.log(1-random.nextDouble()) / -1);
            if (address < PAGE_RANGE) {
                referenceString[i] = address;
            } else  {
                referenceString[i] = PAGE_RANGE - 1;
            }            
        }
    }

    /**
     * This method initializes a random integer array with a segmented
     * distribution function. There is a 70% chance that a given random
     * variable takes a value between 3 < k < 10, and a 30% chance that
     * a random variable takes a value as represented by the previous
     * exponential distribution.
     * @param length Length of the random array
     */
    private static void createSegmentedDistribution(int length) {
        referenceString = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int rand = (int)(random.nextDouble() * 100);
            if (rand > 30) {
                int address = random.nextInt(6) + 4;
                referenceString[i] = address;
            } else {
                int address = (int)(Math.log(1-random.nextDouble()) / -1);
                if (address < PAGE_RANGE) {
                    referenceString[i] = address;
                } else  {
                    referenceString[i] = PAGE_RANGE - 1;
                } 
            }
        }
    }

    /**
     * This method gives a visual representation of the FIFO and
     * second-chance page replacement algorithms on a small sample dataset.
     */
    private static void runDemo() {
        FifoSim fifoDemo = new FifoSim(MEM_PAGE_SIZE);
        ClockSim clockDemo = new ClockSim(MEM_PAGE_SIZE);

        System.out.println("FIFO Page Replacement Simulation");
        for (int i = 0; i < SAMPLE_NUM_PAGES; i++) {
            String result = fifoDemo.accessMemory(referenceString[i]);
            System.out.printf("%-2d - %-5s: ", referenceString[i], result);
            fifoDemo.printReplacementQueue();
        }

        System.out.println("\nClock Page Replacement Simulation");
        for (int i = 0; i < SAMPLE_NUM_PAGES; i++) {
            String result = clockDemo.accessMemory(referenceString[i]);
            System.out.printf("%-2d - %-5s: ", referenceString[i], result);
            clockDemo.printReplacementQueue();
        }
        System.out.println("\n");
    }

    /**
     * This method calculates the average performance increase seen when
     * switching from a FIFO to second-change page replacement algorithm.
     * The algorithms are compared over the three different probability
     * distributions described above.
     * @param distributionType
     * @return
     */
    private static double runDiagnostics(String distributionType) {
        FifoSim fifoSim;
        ClockSim clockSim;
        int[] fifoMissArr = new int[NUM_EPOCHS];
        int[] clockMissArr = new int[NUM_EPOCHS];
        for (int i = 0; i < NUM_EPOCHS; i++) {
            switch (distributionType) {
                case "Constant":
                    createSegmentedDistribution(NUM_PAGES);
                    break;
                case "Exponential":
                    createExponentialDistribution(NUM_PAGES);
                    break;
                case "Segmented":
                    createSegmentedDistribution(NUM_PAGES);
                    break;
                default:
                    break;
            }
            fifoSim = new FifoSim(MEM_PAGE_SIZE);
            int fifoMissCount = 0;
            for (int j = 0; j < NUM_PAGES; j++) {
                String result = fifoSim.accessMemory(referenceString[j]);
                if (result.equals("Miss")) {
                    fifoMissCount++;
                }
            }
            fifoMissArr[i] = fifoMissCount;
    
            clockSim = new ClockSim(MEM_PAGE_SIZE);
            int clockMissCount = 0;
            for (int j = 0; j < NUM_PAGES; j++) {
                String result = clockSim.accessMemory(referenceString[j]);
                if (result.equals("Miss")) {
                    clockMissCount++;
                }
            }
            clockMissArr[i] = clockMissCount;
        }
        int[] diffArr = new int[NUM_EPOCHS];
        for (int i = 0; i < NUM_EPOCHS; i++) {
            diffArr[i] = fifoMissArr[i] - clockMissArr[i];
        }
        double result = (mean(diffArr) / NUM_PAGES) * 100;
        return result;
    }

    private static void printReferenceString() {
        System.out.println("Reference string: " + Arrays.toString(referenceString));
        double mean = mean(referenceString);
        double variance = variance(referenceString, mean);
        System.out.printf("\tMean: %.2f\n\tVariance: %.2f\n\n", mean, variance);
    }

    private static double mean(int[] array) {
        return Arrays.stream(array).average().getAsDouble();
    }

    private static double variance(int[] array, double mean) {
        double sqDiff = 0; 
        for (int i = 0; i < array.length; i++) {
            sqDiff += (array[i] - mean) * (array[i] - mean); 
        }
        return (double)sqDiff / array.length; 
    } 

}