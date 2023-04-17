package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import constants.FrameworkConstants;

public class Listener2 implements ISuiteListener, ITestListener{

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

		if (!reportDir.exists()) {
			reportDir.mkdir();
		}

		htmlReporter = new ExtentHtmlReporter(FrameworkConstants.getpExtentReportPath());
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "pavan");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Testing"); // name of the report

		htmlReporter.config().setTheme(Theme.STANDARD);

	}
	@Override
	public void onFinish(ISuite suite) {
		String dateName = new SimpleDateFormat("MM_dd_HH_mm_SS").format(new Date());
		File logfile = new File(FrameworkConstants.getLogspath());
		File suitelogfile = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_Logs.log");
		File apireportfolder = new File(FrameworkConstants.getAPIReportpath());
		File suiteReport = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_ExtentReport.html");
		File testoutput = new File(FrameworkConstants.getTestOutputFolder());
		File testoutputindex = new File(FrameworkConstants.getTestNGreport());
		File extentreport = new File(FrameworkConstants.getpExtentReportPath());
		try {

			Files.copy(logfile, suitelogfile);
			Files.copy(extentreport, suiteReport);

		} catch (IOException e) {
			e.printStackTrace();
		}
		TestUtils.openFile(FrameworkConstants.getpExtentReportPath());
		extent.flush();
	}

	public void onTestStart(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.assignCategory(tr.getMethod().getGroups());
		Log.startTestCase(tr.getName());
	}

	public void onTestSuccess(ITestResult tr) {

		logger.assignCategory(tr.getMethod().getGroups());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		Log.endTestCase(tr.getName());

	}

	public void onTestFailure(ITestResult tr) {

		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(tr.getThrowable().getMessage() + "- Test Failed", ExtentColor.RED));

		Log.endTestCase(tr.getName());
	}

	public void onTestSkipped(ITestResult tr) {

		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		Reporter.parentTest.log(Status.SKIP,
				MarkupHelper.createLabel(tr.getThrowable().getMessage() + "- Test Case Skipped", ExtentColor.BLUE));

		Log.endTestCase(tr.getName());
	}

	

}