import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/')

// get current system time
long s = System.currentTimeMillis()

WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor j = (JavascriptExecutor)driver
j.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 800);")

System.out.println("Time elapsed is:" + (System.currentTimeMillis()- s));

WebUI.closeBrowser()