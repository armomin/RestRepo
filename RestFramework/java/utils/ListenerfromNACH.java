package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.ConfigurationSpi;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.springframework.util.FileSystemUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import constants.FrameworkConstants;

public final class ListenerfromNACH implements ISuiteListener, ITestListener {
	
	 public ExtentHtmlReporter htmlReporter;
	 public ExtentReports extent;
	 public ExtentTest logger;

	@Override
	public void onStart(ISuite suite) {
		
		File output = new File("./output.txt");
		if (output.exists()) {
			output.mkdir();
		}

		File reportDir = new File(FrameworkConstants.getAPIReportpath());
		File downloadDir = new File(FrameworkConstants.getDownloadPath());

		if (!reportDir.exists()) {
			reportDir.mkdir();
		}

		if (!downloadDir.exists()) {
			downloadDir.mkdir();
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		String dateName = new SimpleDateFormat("MM_dd_HH_mm_SS").format(new Date());
		File logfile = new File(FrameworkConstants.getLogspath());
		File suitelogfile = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_Logs.log");
		File apireportfolder = new File(FrameworkConstants.getAPIReportpath());
		File suiteReport = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_TestNGReport.html");
		File testoutput = new File(FrameworkConstants.getTestOutputFolder());
		File testoutputindex = new File(FrameworkConstants.getTestNGreport());
		try {
			// FileSystemUtils.copyRecursively(testoutput, apireportfolder);

			Files.copy(logfile, suitelogfile);
			Files.copy(new File(FrameworkConstants.getTestNGreport()),
					new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_TestNGReport.html"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		TestUtils.openFile(FrameworkConstants.getTestNGreport());
	}

	public void onTestStart(ITestResult result) {
		Reporter.parentTest= Reporter.extent.createTest(result.getName());
		Reporter.parentTest.assignCategory(result.getMethod().getGroups());
		Log.startTestCase(result.getName());
	}

	public void onTestSucess(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {}
		Reporter.parentTest.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"- Test Passed", ExtentColor.GREEN));
		Log.endTestCase(result.getName());
	}
	
	public void onTestfailure(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {}
		Reporter.parentTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"- Test Failed", ExtentColor.RED));
		Reporter.parentTest.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable().getMessage()+"- Test Failed", ExtentColor.RED));
		Reporter.fail("Failed");
		
		Log.endTestCase(result.getName());
	}
	
	public void onTestSkipped(ITestResult result) {
		if(result.getStatus()==ITestResult.SKIP) {}
		Reporter.parentTest.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"- Test Case Skipped", ExtentColor.BLUE));
		Reporter.parentTest.log(Status.SKIP,MarkupHelper.createLabel(result.getThrowable().getMessage()+"- Test Case Skipped", ExtentColor.BLUE));
	
		
		Log.endTestCase(result.getName());
	}
}
