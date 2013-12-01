package de.ohmen.fxgame.test.helper;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * This basic class runner ensures that JavaFx is running and then wraps all the runChild() calls 
 * in a Platform.runLater().  runChild() is called for each test that is run.  By wrapping each call
 *  in the Platform.runLater() this ensures that the request is executed on the JavaFx thread.
 *  http://awhite.blogspot.de/2013/04/javafx-junit-testing.html
 */
public class JavaFxJUnit4ClassRunner extends BlockJUnit4ClassRunner
{
    /**
     * Constructs a new JavaFxJUnit4ClassRunner with the given parameters.
     * 
     * @param clazz The class that is to be run with this Runner
     * @throws InitializationError Thrown by the BlockJUnit4ClassRunner in the super()
     */
    public JavaFxJUnit4ClassRunner(final Class<?> clazz) throws InitializationError
    {
        super(clazz);
        
        JavaFxJUnit4Application.startJavaFx();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier)
    {
        // Create a latch which is only removed after the super runChild() method
        // has been implemented.
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                // Call super to actually do the work
                JavaFxJUnit4ClassRunner.super.runChild(method, notifier);
                
                // Decrement the latch which will now proceed.
                latch.countDown();
            }
        });
        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            // Waiting for the latch was interruped
            e.printStackTrace();
        }
    }
}