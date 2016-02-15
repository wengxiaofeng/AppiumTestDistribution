package com.test.site;

import com.appium.manager.AppiumParallelTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

public class UserBaseTest extends AppiumParallelTest {

    @BeforeMethod()
    public void startApp(Method name) throws Exception {
        driver = startDriverInstance();
        startLogResults(name.getName());
    }

    @AfterMethod()
    public void killServer(ITestResult result) {
        endLogTestResults(result);
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @BeforeClass()
    public void beforeClass() throws Exception {
        startAppiumServer(getClass().getSimpleName());
    }

    @AfterClass()
    public void afterClass() throws InterruptedException, IOException {
        System.out.println("After Class" + Thread.currentThread().getId());
        killAppiumServer();
    }


}
