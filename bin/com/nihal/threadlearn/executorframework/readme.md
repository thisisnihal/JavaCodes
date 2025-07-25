```java
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<Integer> future = executorService.submit(() -> 47); // if it return nothin then use '?' in place of data type inside angular brackets. this submit take runnable or callable. runnable doesnt returns anything but callable does

    if (future.isDone()) {
        System.out.println("Task is done!");
    }
    try {
        System.out.println(future.get()); // 42
    } catch (Exception e) {
    }
    executorService.shutdown();
```  

we have created a single thread using a static method of `Executors` and then assigned it to a varibale of type `ExecutorService`(interface).  
 
[refer to doc]  
`executorService` Submits a Runnable task for execution and returns a `Future` representing that task. The Future's get method will return null upon successful completion.
Parameters: task - the task to submit
Returns: a Future representing pending completion of the task


```java
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Runnable runnable = () -> "hello"; // ERROR. why? - lambda exp returns hello string; since the method 'run' inside Runnable interface have return type 'void' so it cant return anything other than void.
    
    Callable<String> callable = () -> "hello"; // no error, since the method 'call' inside Callable interface have Generic return type say 'V'. and it is also a funtional interface so we can use lambda exp as we used in case of Runnable
    
    Future<Integer> future = executorService.submit(runnable);  // error

    Future<Integer> future = executorService.submit(callable);  // okay. the future returns the something

    if (future.isDone()) {
        System.out.println("Task is done!");
    }
    try {
        System.out.println(future.get()); // 42
    } catch (Exception e) {
    }
    executorService.shutdown();
```  

```java

class CallableTask implements Callable {
    @Override
    public Object call() throws Exception { // here we are able to throw exception in method signature but in case of run method from Runnable we were not able to do that(we used try_catch).
        Thread.sleep(10);
        return 1;
    }
}
```  

```java
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    Future<?> future = executorService.submit(() -> System.out.println("hellow"), "success");  // we are passing runnable as Runnable doesnt return anything, we have pass some value in result as second param, which string "success" here.

    if (future.isDone()) {
        System.out.println("Task is done!");
    }
    try {
        System.out.println(future.get()); // 42
    } catch (Exception e) {
    }
    executorService.shutdown();
```