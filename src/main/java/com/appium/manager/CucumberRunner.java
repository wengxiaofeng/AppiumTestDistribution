package com.appium.manager;

import com.appium.utils.CommandPrompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by saikrisv on 04/03/16.
 */
public class CucumberRunner {

    CommandPrompt commandPrompt = new CommandPrompt();
    Process p;

    public void triggerParallelCukes(String feature) throws IOException, InterruptedException {
        System.out.println("Distribution triggered");
        System.out.println(System.getProperty("user.dir") + "/libs/cucumber-core-1.2.4.jar:");
        String compileClasses=System.getProperty("user.dir") + "/target/classes:";
        String compileTestClasses=System.getProperty("user.dir") + "/target/test-classes";
        String classpPath = System.getProperty("user.dir") + "/target/dependency/*:";
        String reportPath= System.getProperty("user.dir") + "/target/" + feature + ".json";
        System.out.println("" + classpPath + "");
        String a = "java -cp \"" + classpPath + "\"" +
                System.getProperty("user.dir") + "/target/classes:" +
                System.getProperty("user.dir") + "/target/test-classes" +
                " cucumber.api.cli.Main " +
                "--glue com.test.steps " +
                System.getProperty("user.dir") + "/src/test/java/com/cucumber/features/" + feature +
                " --plugin json:" + System.getProperty("user.dir") + "/target/" + feature + ".json";
        System.out.println(a);

        String[] a1 = new String[]{ "java", "-cp", classpPath,compileClasses+compileTestClasses, "cucumber.api.cli.Main --glue com.test.steps",
                System.getProperty("user.dir") + "/src/test/java/com/cucumber/features/" + feature,
                "--plugin json:", reportPath};
        Thread.sleep(2000);

        final String[] command = a1;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        //Wait to get exit value
        try {
            p.waitFor();
            final int exitValue = p.waitFor();
            if (exitValue == 0)
                System.out.println("Successfully executed the command: " + command);
            else {
                System.out.println("Failed to execute the following command: " + command + " due to the following error(s):");
                try (final BufferedReader b = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                    String line;
                    if ((line = b.readLine()) != null)
                        System.out.println(line);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
  @Test
    public void testApp() throws IOException, InterruptedException {
        triggerParallelCukes("Basket.feature");
    }*/
}
