# Dissemination-Barrier
For a reusable implementation, I have implemented the code in a class to be called easily. For testing the correctness of program any other test programs can be
used. But in my test, I want to use a barrier in a for loop. Without calling await method, there is no specific order for running for loop. For example, when n=6,
we will have 6 threads which can be run as follows:
1. Thread 0 in loop 0 0
2. Thread 1 in loop 0 0
3. Thread 2 in loop 0 0
4. Thread 2 in loop 1 1
5. Thread 2 in loop 2 2
6. Thread 5 in loop 0 0
7. Thread 5 in loop 1 1
8. Thread 5 in loop 2 2
9. Thread 3 in loop 0 0
10. Thread 4 in loop 0 0
11. Thread 4 in loop 1 1
12. Thread 4 in loop 2 2
13. Thread 1 in loop 1 1
14. Thread 1 in loop 2 2
15. Thread 0 in loop 1 1
16. Thread 0 in loop 2 2
17. Thread 3 in loop 1 1
18. Thread 3 in loop 2 2

As you can see, before running all threads in the first iteration of the loop (iteration 0), some of them can pass the first iteration of the loop and start the next iteration.
For example, in the code above, in line 4, thread 2 is in its second iteration (iteration 1) while threads 5, 3 and 4 were not entered to their first iteration. But with
calling await method of the barrier class, the result is as follows:

1. Thread 4 in loop 0 0
2. Thread 5 in loop 0 0
3. Thread 2 in loop 0 0
4. Thread 3 in loop 0 0
5. Thread 0  in loop 0 0
6. Thread 1 in loop 0 0
7. Thread 5 in loop 1 1
8. Thread 3 in loop 1 1
9. Thread 4 in loop 1 1
10. Thread 0 in loop 1 1
11. Thread 1 in loop 1 1
12. Thread 2 in loop 1 1
13. Thread 3 in loop 2 2
14. Thread 1 in loop 2 2
15. Thread 2 in loop 2 2
16. Thread 4 in loop 2 2
17. Thread 0 in loop 2 2
18. Thread 5 in loop 2 2

As shown in the output above, no thread can start the next iteration of the
loop while others have not finished the current iteration.
To impelement the code just give an integer number as an input for n.
