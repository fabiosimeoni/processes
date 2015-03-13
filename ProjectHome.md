Like an ExecutorService, a Process executes a set of Callables according to a given Policy. Differently from an ExecutorService, a Process encapsulates the Callables and the Policy so that execution can be deferred. This allows Processes to compose with each other and other Callables in arbitrarily complex executions.

Policies concern execution mode (sequential, parallel, scheduled), failure handling (ignore, stop after N, fail after N), success handling mode (stop after N), result management (group, reduce, discard) and blocking mode (blocking, non-blocking).

A strongly-typed internal DSL facilitates the definition of Policies and Processes, e.g.:

```

Collection<Callable<Integer>> tasks = ...
...
//execute all in background grouping the results
Process<Integer,Future<Void>> p = execute(tasks).inSequence().and().discardingResults().notBlocking();

Runnable r1, r2,r3,r4;
...
//execute and tolerate no more than 2 failues
Process<Void,Void> = execute(r1,r2,r3,r4).inParallel().failingOnFailure(2).and().blocking();
```