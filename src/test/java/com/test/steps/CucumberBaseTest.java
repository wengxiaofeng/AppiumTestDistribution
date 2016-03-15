package com.test.steps;

import com.appium.manager.AppiumParallelTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

public class CucumberBaseTest extends AppiumParallelTest{
	
	@Before
    public void beforeClass() throws Exception {
        System.out.println("Inside Before");
        startAppiumServer(getClass().getSimpleName());
        driver = startAppiumServerInParallel();
    }

    @After
    public void afterClass() throws InterruptedException, IOException {
        getDriver().quit();
        killAppiumServer();
    }
    
    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }


}
