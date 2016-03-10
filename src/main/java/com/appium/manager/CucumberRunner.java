package com.appium.manager;

import com.appium.utils.CommandPrompt;

import java.io.IOException;


/**
 * Created by saikrisv on 04/03/16.
 */
public class CucumberRunner {

    CommandPrompt commandPrompt = new CommandPrompt();
    Process p ;
    public void triggerParallelCukes(String feature) throws IOException, InterruptedException {
    	System.out.println("Distribution triggered");
    	System.out.println(System.getProperty("user.dir") + "/libs/cucumber-core-1.2.4.jar:");
        String classpPath= System.getProperty("user.dir") + "/target/dependency/*:";
        System.out.println(classpPath);
        String a = "java -cp " + "\'" + classpPath + "\'"+
                System.getProperty("user.dir") + "/target/classes:" +
                System.getProperty("user.dir") + "/target/test-classes" +
                " cucumber.api.cli.Main " +
                "--glue com.test.steps " +
                System.getProperty("user.dir") + "/src/test/java/com/cucumber/features/" + feature +
                " --plugin json:" + System.getProperty("user.dir") + "/target/" + feature + ".json";
        System.out.println(a);
        commandPrompt.runCommand(a);
        Thread.sleep(2000);

    }


/*    @Test
    public void testApp() throws IOException, InterruptedException {
        triggerParallelCukes("Basket.feature");
    }*/
}
