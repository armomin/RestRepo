package utils;

import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import constants.FrameworkConstants;

public class Reporter {
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public static void generatereport() {

		try {
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
				extent.createTest(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void pass(String message) {

		logger.pass(message);
	}

	public static void info(String message) {

		logger.info(message);
	}

	public static void fail(String message) {

		logger.fail(message);
	}

}
