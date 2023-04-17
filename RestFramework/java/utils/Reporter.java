package utils;

import java.io.File;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import constants.FrameworkConstants;

public class Reporter {
	private Reporter() {
	}

	public static ExtentReports extent;
	static ExtentTest parentTest;
	static ExtentHtmlReporter htmlReporter;
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	public static void generateReport() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			htmlReporter = new ExtentHtmlReporter(FrameworkConstants.getAPIReportpath());
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle(reportFileName);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(reportFileName);
			htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		}
	}

	public static void endReport() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}
	}

	public static void pass(String message) {

		parentTest.pass(message);
	}

	public static void info(String message) {

		parentTest.info(message);
	}

	public static void fail(String message) {

		parentTest.fail(message);
	}

	public static void skip(String message) {

		parentTest.skip(message);
	}

}
