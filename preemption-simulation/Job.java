/**
 * Author: Gabriel Maguire
 * Date: 9/20/2020
 * 
 * This code was written for an Operating Systems course assignment. This class represents
 * a job or process that an operating system will execute.
 */

public class Job implements Comparable<Job> {
    
    String name;
    char id; // Short-hand char to represent job when graphing ('0' is reserved)
    int priority; // Priority: 0x00 - HIGH, 0x01 - LOW
    int timeBurst; // Total job execution time
    int timeRemaining; // Remaining job execution time
    boolean started = false;
    int timeArrival, timeStarted, timeCompleted;

    public Job(String name, char id, int priority, int timeBurst) {
        this.name = name;
        this.id = id;
        this.priority = priority;
        this.timeBurst = timeBurst;
        this.timeRemaining = timeBurst;
    }

    @Override
    public int compareTo(Job job) {
        return this.priority - job.priority;
    }

}