/**
 * Author: Gabriel Maguire
 * Date: 9/22/2020
 * 
 * This code was written for an Operating Systems course assignment. This class contains the
 * diagnostic functions for analyzing the job scheduling simulations.
 */

import java.util.List;

public class Diagnostics {
    
    /**
     * This method calculates and prints the following relevant diagnostic information for the job
     * scheduling simulation.
     * @param jobList   List of jobs in the schedular
     * @param MIN_GRANULARITY   Minimum time slice of the system
     * @param systemCompletionTime  Total system runtime
     * @param numPreempts   Number of job preemptions
     * @param numContextSwitches    Number of context switches
     */
    public static void printDiagnostics(List<Job> jobList, int MIN_GRANULARITY, int systemCompletionTime, int numPreempts, int numContextSwitches) {
        System.out.println("\n--- Diagnostics ---");
        System.out.printf("Preemptions: %d\n", numPreempts);
        System.out.printf("Context switches: %d\n\n", numContextSwitches);
        int responseTime[] = new int[jobList.size()];
        int burstCompletion[] = new int[jobList.size()];
        for (int i = 0; i < jobList.size(); i++) {
            System.out.println(jobList.get(i).name);
            System.out.printf("\tArrival/Start/Complete : %d/%d/%d\n", jobList.get(i).timeArrival, jobList.get(i).timeStarted, jobList.get(i).timeCompleted);
            responseTime[i] = jobList.get(i).timeStarted - jobList.get(i).timeArrival;
            System.out.printf("\tResponse time: %d\n", responseTime[i]);
            burstCompletion[i] = jobList.get(i).timeBurst;
        }
        System.out.printf("\nAverage response time: %f\n", findAverage(responseTime));
        System.out.printf("Average burst-completion time: %f\n", findAverage(burstCompletion));
        System.out.printf("%% time spent context switching: %f\n", ((numContextSwitches * MIN_GRANULARITY) / (double)systemCompletionTime) * 100);
    }

    public static double findAverage(int x[]) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        return (sum / (double)x.length);
    }

    /**
     * This method prints a command line graphical representation of the job scheduling output.
     * @param chart StringBuilder containing sequence of job ids.
     * @param jobs  List of input jobs.
     */
    public static void printChart(StringBuilder chart, List<Job> jobs) {
        int numLayers = jobs.size() + 1;
        char ids[] = new char[numLayers];
        ids[0] = '0';
        String layeredChart[] = new String[numLayers];
        for (int i = 1; i < numLayers; i++) {
            ids[i] = jobs.get(i-1).id;
        }
        for (int i = 0; i < numLayers; i++) {
            StringBuilder partialChart = new StringBuilder(chart);
            for (int j = 0; j < partialChart.length(); j++) {
                if (partialChart.charAt(j) != ids[i]) {
                    partialChart.setCharAt(j, ' ');
                }
            }
            String header = "Job " + i + " | ";
            layeredChart[i] = header + partialChart.toString();
        }
        char chartTop[] = new char[layeredChart[0].length()];
        for (int i = 0; i < chartTop.length; i++) {
            chartTop[i] = '_';
        }
        System.out.println(chartTop);
        for (int i = 0; i < numLayers; i++) {
            System.out.println(layeredChart[i]);
        }
    }

}
