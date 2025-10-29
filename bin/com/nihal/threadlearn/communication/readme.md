

without proper communication mechanisms thread might end up in inefficient busy-waiting states, leading to wastage of cpu resources and potential deadlocks.

To solve this issue, we have few methods(which can only be called in syncronized method/block) -
1. wait
2. notify
3. notifyAll




How Semaphores Work  
Semaphores act as a signaling mechanism to manage concurrent processes that need to access shared data or resources.   
Initialization: A semaphore is initialized with a specific integer value.   
Wait Operation: When a process wants to enter a critical section, it performs a Wait() operation.   
If the semaphore's value is positive, it is decremented, and the process proceeds into the critical section.   
If the value is zero or negative, the process is blocked and added to a waiting queue until the semaphore's value becomes positive again.  
Signal Operation: When a process exits a critical section, it performs a Signal() operation.   
This operation increments the semaphore's value.   
If there are any processes waiting in the queue, one of them is unblocked and allowed to proceed.   
Types of Semaphores   
Counting Semaphores: These can take on any non-negative integer value.  
Binary Semaphores: These are a special case of counting semaphores that can only take on values 0 or 1.  