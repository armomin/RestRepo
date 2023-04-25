package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import constants.FrameworkConstants;

public class Listener implements ISuiteListener, ITestListener {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@Override
	public void onStart(ITestContext suite) {
		File output = new File("./output.txt");
		if (output.exists()) {
			output.mkdir();
		}

		File reportDir = new File(FrameworkConstants.getAPIReportpath());

		if (!reportDir.exists()) {
			reportDir.mkdir();
		}

		if (Objects.isNull(extent)) {
			htmlReporter = new ExtentSparkReporter(FrameworkConstants.getpExtentReportPath());
			extent = new ExtentReports();

			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host name", "localhost");
			extent.setSystemInfo("Environemnt", "QA");
			extent.setSystemInfo("user", "arslan");

			htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
			htmlReporter.config().setReportName("Functional Testing"); // name of the report

			htmlReporter.config().setTheme(Theme.STANDARD);

		}
	}

	@Override
	public void onFinish(ITestContext suite) {
		String dateName = new SimpleDateFormat("MM_dd_HH_mm_SS").format(new Date());
		File logfile = new File(FrameworkConstants.getLogspath());
		File suitelogfile = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_Logs.log");
		File apireportfolder = new File(FrameworkConstants.getAPIReportpath());
		File suiteReport = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_ExtentReport.html");
		File testoutput = new File(FrameworkConstants.getTestOutputFolder());
		File testoutputindex = new File(FrameworkConstants.getTestNGreport());
		File extentreport = new File(FrameworkConstants.getpExtentReportPath());
		try {

			extent.flush();

			Files.copy(logfile, suitelogfile);
			Files.copy(extentreport, suiteReport);

		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.flush();
		TestUtils.openFile(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_ExtentReport.html");

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
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(tr.getThrowable().getMessage() + "- Test Case Skipped", ExtentColor.BLUE));

		Log.endTestCase(tr.getName());
	}

	public void pass(String message) {

		logger.log(Status.PASS, message);
	}

	public void info(String message) {

		logger.log(Status.INFO, message);
	}

	public void fail(String message) {

		logger.log(Status.FAIL, message);
	}
}