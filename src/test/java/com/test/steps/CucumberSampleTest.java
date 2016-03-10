package com.test.steps;

import com.appium.manager.ParallelThread;
import org.junit.Test;

/**
 * Created by saikrisv on 10/03/16.
 */
public class CucumberSampleTest {

    @Test
    public void testCukesParallel(){
        ParallelThread parallelThread = new ParallelThread();
        try {
            parallelThread.runner("com.test.steps");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
