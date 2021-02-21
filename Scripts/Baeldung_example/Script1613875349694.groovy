import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService

/**
 * https://www.baeldung.com/java-executor-service-tutorial
 * 
 * ExecutorService is a JDK API that simplifies running tasks in
 * asynchronous mode. Generally speaking, ExecutorService automatically
 * provides a pool of threads and an API for assigning tasks to it.
 */

//ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor()
ExecutorService executorService = Executors.newFixedThreadPool(10)

Closure closure = {
	WebUI.openBrowser('')
	WebUI.navigateToUrl('http://demoaut.katalon.com/')
	WebUI.comment("I was called!")
	WebUI.delay(1)
	WebUI.closeBrowser()
}

Callable<String> callableTask = {
	TimeUnit.MILLISECONDS.sleep(300)
	closure.call()
	return "Task's execution"
}

List<Callable<String>> callableTasks = new ArrayList<>()
callableTasks.add(callableTask)
callableTasks.add(callableTask)
callableTasks.add(callableTask)

List<Future<String>> futures = executorService.invokeAll(callableTasks)

for (ft in futures) {
	String result = null
	try {
		/* calling the get() method while the task is still running
		 * will cause execution to block until tha task properly 
		 * executes and the result is available. */
		result = ft.get()
	} catch (InterruptedException | ExecutionException e) {
		e.printStackTrace()
	}
}

executorService.shutdown()
try {
	if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
		executorService.shutdownNow()
	}
} catch (InterruptedException e) {
	executorService.shutdownNow()
}

