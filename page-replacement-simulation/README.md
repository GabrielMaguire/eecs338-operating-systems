# eecs338hw2
Operating systems simulation assignment #2.


# SAMPLE OUTPUT:
>*===========================================<br>
>*========== Constant Distribution ==========<br>
>*===========================================<br>
>Reference string: [8, 10, 4, 7, 4, 11, 4, 2]<br>
>        Mean: 6.25<br>
>        Variance: 9.19<br>
><br>
>FIFO Page Replacement Simulation<br>
>8  - Miss : 8<br>
>10 - Miss : 8  10<br>
>4  - Miss : 8  10 4<br>
>7  - Miss : 10 4  7<br>
>4  - Hit  : 10 4  7  <br>
>11 - Miss : 4  7  11<br>
>4  - Hit  : 4  7  11<br>
>2  - Miss : 7  11 2<br>
><br>
>Clock Page Replacement Simulation<br>
>8  - Miss : 8 (1)<br>
>10 - Miss : 8 (1)   10(1)<br>
>4  - Miss : 8 (1)   10(1)   4 (1)<br>
>7  - Miss : 10(0)   4 (0)   7 (1)<br>
>4  - Hit  : 10(0)   4 (1)   7 (1)<br>
>11 - Miss : 4 (1)   7 (1)   11(1)<br>
>4  - Hit  : 4 (1)   7 (1)   11(1)<br>
>2  - Miss : 7 (0)   11(0)   2 (1)<br>
><br>
><br>
>*===========================================<br>
>*======== Exponential Distribution =========<br>
>*===========================================<br>
>Reference string: [3, 0, 0, 0, 0, 2, 0, 0]<br>
>        Mean: 0.63<br>
>        Variance: 1.23<br>
><br>
>FIFO Page Replacement Simulation<br>
>3  - Miss : 3<br>
>0  - Miss : 3  0<br> 
>0  - Hit  : 3  0<br>
>0  - Hit  : 3  0<br>
>0  - Hit  : 3  0<br>
>2  - Miss : 3  0  2<br>
>0  - Hit  : 3  0  2<br>
>0  - Hit  : 3  0  2<br>
><br>
>Clock Page Replacement Simulation<br>
>3  - Miss : 3 (1)<br>
>0  - Miss : 3 (1)   0 (1)<br>
>0  - Hit  : 3 (1)   0 (1)<br>
>0  - Hit  : 3 (1)   0 (1)<br>
>0  - Hit  : 3 (1)   0 (1)<br>
>2  - Miss : 3 (1)   0 (1)   2 (1)<br>
>0  - Hit  : 3 (1)   0 (1)   2 (1)<br>
>0  - Hit  : 3 (1)   0 (1)   2 (1)<br>
><br>
><br>
>*===========================================<br>
>*========= Segmented Distribution ==========<br>
>*===========================================<br>
>Reference string: [9, 7, 7, 5, 8, 7, 5, 6]<br>
>        Mean: 6.75<br>
>        Variance: 1.69<br>
><br>
>FIFO Page Replacement Simulation<br>
>9  - Miss : 9<br>
>7  - Miss : 9  7<br> 
>7  - Hit  : 9  7<br>
>5  - Miss : 9  7  5<br>
>8  - Miss : 7  5  8<br>
>7  - Hit  : 7  5  8<br>
>5  - Hit  : 7  5  8<br>
>6  - Miss : 5  8  6<br>
><br>
>Clock Page Replacement Simulation<br>
>9  - Miss : 9 (1)<br>
>7  - Miss : 9 (1)   7 (1)<br>
>7  - Hit  : 9 (1)   7 (1)<br>
>5  - Miss : 9 (1)   7 (1)   5 (1)<br>
>8  - Miss : 7 (0)   5 (0)   8 (1)<br>
>7  - Hit  : 7 (1)   5 (0)   8 (1)<br>
>5  - Hit  : 7 (1)   5 (1)   8 (1)<br>
>6  - Miss : 5 (0)   8 (0)   6 (1)<br>
><br>
><br>
><br>
><br>
><br>
>*******************************************<br>
>**** FIFO vs Clock Algorithm Testing *****<br>
>*******************************************<br>
><br>
>Reduction in page misses for a constant distribution:<br>
>        0.07718 %<br>
><br>
>Reduction in page misses for an exponential distribution:<br>
>        0.68738 %<br>
><br>
>Reduction in page misses for a segmented distribution:<br>
>        0.08750 %<br>