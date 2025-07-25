

without proper communication mechanisms thread might end up in inefficient busy-waiting states, leading to wastage of cpu resources and potential deadlocks.

To solve this issue, we have few methods(which can only be called in syncronized method/block) -
1. wait
2. notify
3. notifyAll




