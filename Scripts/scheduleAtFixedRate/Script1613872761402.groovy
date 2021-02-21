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

/**
 * https://qiita.com/opengl-8080/items/ee8e926cf75e4d6058a2
 * 
 * sheduleAtFixedRate --- 一定時間ごとに処理を実行する
 */

ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()

AtomicInteger number = new AtomicInteger(0)
service.scheduleAtFixedRate({
	int n = number.getAndIncrement()
	println "begin(${n})"
	sleep(Math.max(1, 3 - n))
	println "end(${n})" 
}, 2, 2, TimeUnit.SECONDS)

int count = 0
while (true) {
	TimeUnit.SECONDS.sleep(1)
	println "count: ${++count}"
}

def sleep(long seconds) {
	try {
		TimeUnit.SECONDS.sleep(seconds)
	} catch (InterruptedException e) {}
}