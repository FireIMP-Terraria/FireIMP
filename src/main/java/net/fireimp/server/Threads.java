package net.fireimp.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Threads {
    /**
     * Main thread.
     *
     * This thread will run and pass actions on to the appropriate thread.
     * This thread itself should not run any intensive code, in order to remain on 30TPS.
     */
    public static final ScheduledExecutorService mainThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * Entities (and players):
     *
     * Applying velocity, AI, collisions and such.
     */
    public static final ExecutorService entityService = Executors.newFixedThreadPool(2);

    /**
     * Worlds
     *
     * Loading, saving sections.
     * Sending sections over network.
     * Generation.
     */
    public static final ExecutorService worldService = Executors.newFixedThreadPool(1);

    /**
     * Async.
     *
     * Performing any type of async task.
     * The amount of threads is dynamic to let every task run at once.
     * But when tasks ends, the thread attached to it can be reused by another.
     */
    public static final ExecutorService asyncService = Executors.newCachedThreadPool();
}
