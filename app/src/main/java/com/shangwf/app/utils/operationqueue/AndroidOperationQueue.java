package com.shangwf.app.utils.operationqueue;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by burt on 2016. 5. 1..
 */
public class AndroidOperationQueue {
    private Bundle          bundle = null;
    private HandlerThread   operationHandlerThread = null;
    private Handler         operationHandlerThreadHandler = null;
    private LinkedBlockingQueue<AndroidOperation> operationQueue = new LinkedBlockingQueue<>();
    private boolean         isRunning = false;
    private String          name = null;
    private int             priority = Process.THREAD_PRIORITY_DEFAULT;

    /**
     * Construct AndroidOperationQueue with name and default thread priority as Process.THREAD_PRIORITY_DEFAULT
     * @param name AndroidOperationQueue's name
     */
    public AndroidOperationQueue(String name) {
        this(name, Process.THREAD_PRIORITY_DEFAULT);
    }

    /**
     * Construct AndroidOperationQueue with name and thread priority
     * @param name AndroidOperationQueue's name
     * @param priority AndroidOperation's priority. you should use Process's THREAD_PRIORITY.
     */
    public AndroidOperationQueue(String name, int priority) {
        this.name = name;
        this.priority = priority;
        bundle = new Bundle();
        operationHandlerThread = new HandlerThread(name, priority);
    }

    /**
     * Start Android Operation Queue
     */
    public synchronized void start() {

        if(isRunning == true) return;

        isRunning = true;
        if(operationHandlerThread == null) {
            operationHandlerThread = new HandlerThread(name, priority);
        }
        operationHandlerThread.start();
        operationHandlerThreadHandler = new Handler(operationHandlerThread.getLooper());
        for(AndroidOperation op : operationQueue ){
            op.queueing(operationHandlerThreadHandler);
        }
        operationQueue.clear();
    }

    public boolean isActivated() {
        return isRunning;
    }

    /**
     * Stop Android Operation Queue and remove all operations
     */
    public synchronized void stop() {

        if(isRunning == false) return;

        isRunning = false;
        removeAllOperations();
        operationHandlerThread.quit();
        operationHandlerThread = null;
        operationHandlerThreadHandler = null;
        bundle.clear();
    }

    /**
     * add operation to OperationQueue at the end
     * @param operation The operation that will be executed.
     * @return return true if the operation was successfully placed in to the operation queue. Returns false on failure.
     */
    public boolean addOperation(Operation operation) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return false;
            return operationHandlerThreadHandler.post(new AndroidOperation(this, operation));
        } else {
            return operationQueue.add(new AndroidOperation(this, operation));
        }
    }

    /**
     * add operation to OperationQueue. The Operation will be executed on the next iteration through the operation queue.
     * @param operation The operation that will be executed.
     * @return return true if the operation was successfully placed in to the operation queue. Returns false on failure.
     */
    public boolean addOperationAtFirst(Operation operation) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return false;

            return operationHandlerThreadHandler.postAtFrontOfQueue(new AndroidOperation(this, operation));
        } else {
            return operationQueue.add(new AndroidOperation(this, operation, AndroidOperation.Type.ATFIRST, null, 0));
        }
    }

    /**
     * add operation to OperationQueue, to be run at a specific time given by uptimeMillis. { @link android.os.SystemClock.uptimeMillis }
     * @param operation operation The operation that will be executed.
     * @return return true if the operation was successfully placed in to the operation queue. Returns false on failure.
     */
    public boolean addOperationAtTime(Operation operation, long uptimeMillis) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return false;

            return operationHandlerThreadHandler.postAtTime(new AndroidOperation(this, operation), uptimeMillis);
        } else {
            return operationQueue.add(new AndroidOperation(this, operation, AndroidOperation.Type.ATTIME, null, uptimeMillis));
        }
    }

    /**
     * add operation to OperationQueue, to be run at a specific time given by uptimeMillis. { @link android.os.SystemClock.uptimeMillis }
     * @param operation operation The operation that will be executed.
     * @return return true if the operation was successfully placed in to the operation queue. Returns false on failure.
     */
    public boolean addOperationAtTime(Operation operation, Object token, long uptimeMillis) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return false;

            return operationHandlerThreadHandler.postAtTime(new AndroidOperation(this, operation), token, uptimeMillis);
        } else {
            return operationQueue.add(new AndroidOperation(this, operation, AndroidOperation.Type.ATTIME_WITH_TOKEN, token, uptimeMillis));
        }
    }

    /**
     * add operation to OperationQueue, to be run after the specific time given by delayTimeMillis. { @link android.os.SystemClock.uptimeMillis }
     * @param operation operation operation The operation that will be executed.
     * @param delayTimeMillis
     * @return return true if the operation was successfully placed in to the operation queue. Returns false on failure.
     */
    public boolean addOperationAfterDelay(Operation operation, long delayTimeMillis) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return false;

            return operationHandlerThreadHandler.postDelayed(new AndroidOperation(this, operation), delayTimeMillis);
        } else {
            return operationQueue.add(new AndroidOperation(this, operation, AndroidOperation.Type.DELAY, null, delayTimeMillis));
        }
    }

    /**
     * Remove any pending Operations that are in OperationQueue
     * @param operation
     */
    public void removeOperation(Operation operation) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return ;
            operationHandlerThreadHandler.removeCallbacks(new AndroidOperation(this, operation));
        } else {
            operationQueue.remove(new AndroidOperation(this, operation));
        }
    }

    /**
     * Remove any pending Operations with token that are in OperationQueue
     * @param operation
     * @param token
     */
    public void removeOperations(Operation operation, Object token) {
        if(isRunning) {
            if(operationHandlerThreadHandler == null)
                return;
            operationHandlerThreadHandler.removeCallbacks(new AndroidOperation(this, operation), token);
        } else {
            operationQueue.remove(new AndroidOperation(this, operation, AndroidOperation.Type.NORMAL, token, 0));
        }
    }

    /**
     * Remove all pending Operations that are in OperationQueue
     */
    public void removeAllOperations() {
        if(operationHandlerThreadHandler != null) {
            operationHandlerThreadHandler.removeCallbacksAndMessages(null);
        }
        operationQueue.clear();
    }

    /**
     * Return bundle
     */
    public Bundle getBundle() {
        return bundle;
    }

}
