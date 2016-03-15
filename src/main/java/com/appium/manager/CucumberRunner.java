package com.appium.manager;

import com.appium.utils.CommandPrompt;

import java.io.File;
import java.io.IOException;


/**
 * Created by saikrisv on 04/03/16.
 */
public class CucumberRunner {

    CommandPrompt commandPrompt = new CommandPrompt();
    Process p;
    String compileClasses = System.getProperty("user.dir") + "/target/classes:";
    String compileTestClasses = System.getProperty("user.dir") + "/target/test-classes";
    String classpPath = System.getProperty("user.dir") + "/target/dependency/*:";


    public void triggerParallelCukes(String feature) throws IOException, InterruptedException {
        System.out.println("Distribution triggered");
        System.out.println(System.getProperty("user.dir") + "/libs/cucumber-core-1.2.4.jar:");
        String reportPath = System.getProperty("user.dir") + "/target/" + feature + ".json";
        System.out.println("" + classpPath + "");
        String a = "java -cp " + getAllJars() +
                System.getProperty("user.dir") + "/target/classes:" +
                System.getProperty("user.dir") + "/target/test-classes" +
                " cucumber.api.cli.Main " +
                "--glue com.test.steps " +
                System.getProperty("user.dir") + "/src/test/java/com/cucumber/features/" + feature +
                " --plugin json:" + System.getProperty("user.dir") + "/target/" + "Nexus_5_"+feature + ".json";
        /*a = "/Users/saikrisv/git/AppiumTestDistribution/target/dependency*//*:";
        String[] a1 = new String[]{"java", "-classpath", "\"" + a +"\""+compileClasses+compileTestClasses, "cucumber.api.cli.Main", "--glue classpath:com.test.steps" ,
                System.getProperty("user.dir") + "/src/test/java/com/cucumber/features/" + feature,
                "--plugin json:"+reportPath};
        Thread.sleep(2000);*/
            p = Runtime.getRuntime().exec(a);


        //Wait to get exit value
/*        try {
            p.waitFor();
            final int exitValue = p.waitFor();
            if (exitValue == 0)
                System.out.println("Successfully executed the command: " + a);
            else {
                System.out.println("Failed to execute the following command: " + a + " due to the following error(s):");
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
        }*/
    }


    public String getAllJars() {
        File[] files = new File("/Users/saikrisv/git/AppiumTestDistribution/target/dependency/").listFiles();
        StringBuilder str = new StringBuilder(System.getProperty("user.dir")+"/target/dependency/"+files[0].getName()+":");
        for (File file : files) {
            if (file.isFile()) {
                str.append(System.getProperty("user.dir")+"/target/dependency/"+file.getName()+":");
            }

        }
        return str.toString();

    }
}
