/**
 * Author: Gabriel Maguire
 * Date: 9/20/2020
 * 
 * This code was written for an Operating Systems course assignment. It is intended to simulate job
 * scheduling and processing using a priority-based method of preemption.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation_b extends TimerTask {

    final static int ONE_SECOND = 1000;
    final static int SYSTEM_SECONDS = 5;
    final static int MIN_GRANULARITY = 100;

    final static int JOB_1_DELAY = 0;
    final static int JOB_2_DELAY = ONE_SECOND;
    final static int JOB_3_DELAY = 2 * ONE_SECOND;
    
    final static int JOB_1_LENGTH = 2 * ONE_SECOND;
    final static int JOB_2_LENGTH = 500;
    final static int JOB_3_LENGTH = 500;

    private static int numPreempts = 0;
    private static int numContextSwitches = 0;
    private static int elapsedTime = 0;
    private static int systemCompletionTime = 0;
    
    private static boolean contextSwitch = false;

    private static Job job1 = new Job("Job 1", '1', 1, JOB_1_LENGTH);
    private static Job job2 = new Job("Job 2", '2', 0, JOB_2_LENGTH);
    private static Job job3 = new Job("Job 3", '3', 1, JOB_3_LENGTH);

    private static List<Job> jobList = new ArrayList<Job>(
        List.of(job1, job2, job3)
    );

    public Job currentJob = null;

    public static StringBuilder chart = new StringBuilder();
    private static PriorityQueue<Job> pQueue = new PriorityQueue<Job>(); // Used to simulate the ready job queue

    public static void main(String[] args) {

        TimerTask timerTask = new Simulation_b();
        Timer timer = new Timer();
        System.out.println("--- Priority Preemption Simulation ---");
        System.out.println("STARTED");
        timer.scheduleAtFixedRate(timerTask, 0, MIN_GRANULARITY);

        try {
            Thread.sleep(SYSTEM_SECONDS * ONE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer.cancel();
        try {
            Thread.sleep(ONE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("STOPPED");

        Diagnostics.printChart(chart, jobList);
        Diagnostics.printDiagnostics(jobList, MIN_GRANULARITY, systemCompletionTime, numPreempts, numContextSwitches);

    }

    /**
     * This method contains the framework for simulating the addition and processing of jobs on a set
     * schedule. This method is called every MIN_GRANULARITY (ms).
     */
    @Override
    public void run() {
        // Add new jobs to job queue on a set schedule
        switch (elapsedTime) {
            case JOB_1_DELAY:
                addJob(job1);
                break;
            case JOB_2_DELAY:
                addJob(job2);
                break;
            case JOB_3_DELAY:
                addJob(job3);
                break;
            default:
                break;
        }

        if (currentJob != null && !contextSwitch) {
            executeJob();
        } else if (currentJob != null) { 
            chart.append('0');
            contextSwitch = false;
        }

        elapsedTime += MIN_GRANULARITY;
    }

    /**
     * This method adds a job to the job schedular. If the added job has a higher priority (0 - HIGH)
     * than the current job (1 - LOW), then a context switch occurs and the current job is added back
     * into the ready queue and the new job becomes the current job.
     * @param job Job to add to the schedular.
     */
    private void addJob(Job job) {
        System.out.printf("Added: %s @ %d\n", job.name, elapsedTime);
        job.timeArrival = elapsedTime;
        if (currentJob == null) {
            currentJob = job;
            numContextSwitches++;
            contextSwitch = true;
        } else if (job.priority < currentJob.priority) {
            pQueue.add(currentJob);
            currentJob = job;
            numPreempts++;
            numContextSwitches++;
            contextSwitch = true;
        } else {
            pQueue.add(job);
        }
    }

    /**
     * This method simulates the execution of a job over a time slice of MIN_GRANULARITY. If a job
     * is completed, the next job in the ready queue is started.
     * @param job Current job to be executed.
     */
    private void executeJob() {
        if (currentJob.timeRemaining > 0) {
            currentJob.timeRemaining -= MIN_GRANULARITY;
            chart.append(currentJob.id);
            if (!currentJob.started) {
                System.out.printf("Started: %s @ %d\n", currentJob.name, elapsedTime);
                currentJob.started = true;
                currentJob.timeStarted = elapsedTime;
            }
        } else {
            System.out.printf("Completed: %s @ %d\n", currentJob.name, elapsedTime);
            currentJob.timeCompleted = elapsedTime;
            if (pQueue.peek() != null) {
                currentJob = pQueue.poll();
                numContextSwitches++;
                chart.append('0');
            } else {
                currentJob = null;
                systemCompletionTime = elapsedTime;
            }
        }
    }

}