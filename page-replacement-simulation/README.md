# Operating systems simulation assignment #2

## SAMPLE OUTPUT

```text
*===========================================
*========== Constant Distribution ==========
*===========================================
Reference string: [8, 10, 4, 7, 4, 11, 4, 2]
        Mean: 6.25
        Variance: 9.19

FIFO Page Replacement Simulation
8  - Miss : 8
10 - Miss : 8  10
4  - Miss : 8  10 4
7  - Miss : 10 4  7
4  - Hit  : 10 4  7  
11 - Miss : 4  7  11
4  - Hit  : 4  7  11
2  - Miss : 7  11 2

Clock Page Replacement Simulation
8  - Miss : 8 (1)
10 - Miss : 8 (1)   10(1)
4  - Miss : 8 (1)   10(1)   4 (1)
7  - Miss : 10(0)   4 (0)   7 (1)
4  - Hit  : 10(0)   4 (1)   7 (1)
11 - Miss : 4 (1)   7 (1)   11(1)
4  - Hit  : 4 (1)   7 (1)   11(1)
2  - Miss : 7 (0)   11(0)   2 (1)


*===========================================
*======== Exponential Distribution =========
*===========================================
Reference string: [3, 0, 0, 0, 0, 2, 0, 0]
        Mean: 0.63
        Variance: 1.23

FIFO Page Replacement Simulation
3  - Miss : 3
0  - Miss : 3  0 
0  - Hit  : 3  0
0  - Hit  : 3  0
0  - Hit  : 3  0
2  - Miss : 3  0  2
0  - Hit  : 3  0  2
0  - Hit  : 3  0  2

Clock Page Replacement Simulation
3  - Miss : 3 (1)
0  - Miss : 3 (1)   0 (1)
0  - Hit  : 3 (1)   0 (1)
0  - Hit  : 3 (1)   0 (1)
0  - Hit  : 3 (1)   0 (1)
2  - Miss : 3 (1)   0 (1)   2 (1)
0  - Hit  : 3 (1)   0 (1)   2 (1)
0  - Hit  : 3 (1)   0 (1)   2 (1)


*===========================================
*========= Segmented Distribution ==========
*===========================================
Reference string: [9, 7, 7, 5, 8, 7, 5, 6]
        Mean: 6.75
        Variance: 1.69

FIFO Page Replacement Simulation
9  - Miss : 9
7  - Miss : 9  7 
7  - Hit  : 9  7
5  - Miss : 9  7  5
8  - Miss : 7  5  8
7  - Hit  : 7  5  8
5  - Hit  : 7  5  8
6  - Miss : 5  8  6

Clock Page Replacement Simulation
9  - Miss : 9 (1)
7  - Miss : 9 (1)   7 (1)
7  - Hit  : 9 (1)   7 (1)
5  - Miss : 9 (1)   7 (1)   5 (1)
8  - Miss : 7 (0)   5 (0)   8 (1)
7  - Hit  : 7 (1)   5 (0)   8 (1)
5  - Hit  : 7 (1)   5 (1)   8 (1)
6  - Miss : 5 (0)   8 (0)   6 (1)





*******************************************
**** FIFO vs Clock Algorithm Testing *****
*******************************************

Reduction in page misses for a constant distribution:
        0.07718 %

Reduction in page misses for an exponential distribution:
        0.68738 %

Reduction in page misses for a segmented distribution:
        0.08750 %
```
