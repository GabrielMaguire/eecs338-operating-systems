# ecse338
Introduction to Operating Systems and Concurrent Programming assignment workspace.  

*Simulation_a.java* simulates priority-based preemption in job scheduling.  
*Simulation_b.java* simulates time slice preemption in job scheduling.  

Example output of *Simulation_b.java* with 3 jobs:  
**Job 1** | Priority = 1, Length = 2000ms, Arrival = 0ms  
**Job 2** | Priority = 0, Length = 500ms, Arrival = 1000ms  
**Job 3** | Priority = 1, Length = 500ms, Arrival = 2000ms  

```text
--- Priority Preemption Simulation ---  
STARTED  
Added: Job 1 @ 0  
Added: Job 2 @ 1000  
Completed: Job 2 @ 1600  
Added: Job 3 @ 2000  
Completed: Job 1 @ 2800  
Completed: Job 3 @ 3400  
STOPPED  
___________________________________________  
Job 0 | 0          0      0           0  
Job 1 |  111111111        11111111111  
Job 2 |            222222  
Job 3 |                                33333  
  
--- Diagnostics ---  
Preemptions: 1  
Context switches: 4  
```
