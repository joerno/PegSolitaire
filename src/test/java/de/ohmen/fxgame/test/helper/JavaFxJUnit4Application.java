package de.ohmen.fxgame.test.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.stage.Stage;

/** 
 * This is the application which starts JavaFx.  It is controlled through the startJavaFx() method.
 * http://awhite.blogspot.de/2013/04/javafx-junit-testing.html
 */
public class JavaFxJUnit4Application extends Application
{
    /**
     * Flag stating if javafx has started. Static so that it
     * is shared across all instances.
     */
    private static boolean started;
    
    /**
     * Start JavaFx.
     */
    static void startJavaFx()
    {
        if (started)
        {
            return;
        }
        
        started = true;
        
        /**
         * The executor which starts JavaFx.
         */
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        // Start the java fx application
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                JavaFxJUnit4Application.launch();
            }
        });

        // Pause briefly to give FX a chance to start
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Launch.
     */
    static void launch()
    {
        Application.launch();
    }

    /**
     * An empty start method.
     * 
     * @param stage The stage
     */
    @Override
    public final void start(final Stage stage)
    {
        // Empty
    }
}