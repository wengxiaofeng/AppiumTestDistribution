package com.test.steps;

import cucumber.api.java.en.Given;


public class HomePageSteps {
	
//	 protected AppiumDriver driver = new DriverFactory().getDriver();

	@Given("^i'm on application landing page$")
	public void i_m_on_application_landing_page() throws Throwable {
		/*getDriver().findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/digit2")).click();*/
		Thread.sleep(5000);
	}
}
