package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;

import constants.FrameworkConstants;

public final class Listener implements ISuiteListener, ITestListener {

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
		String dateName = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
		File logfile = new File(FrameworkConstants.getLogspath());
		File suitelogfile = new File(FrameworkConstants.getAPIReportpath() + dateName + "Logs.log");
		File report = new File(FrameworkConstants.getTestNGreport());
		File suiteReport = new File(FrameworkConstants.getAPIReportpath() + dateName + "TestNgReport.html");
		try {
			FileUtils.copyFile(logfile, suitelogfile);
			FileUtils.copyFile(report, suiteReport);
		} catch (IOException e) {
			e.printStackTrace();
		}

		TestUtils.openFile(FrameworkConstants.getTestNGreport());
	}

	public void onTestStart(ITestResult result) {
		Log.startTestCase(result.getName());
	}

	public void onTestSucess(ITestResult result) {
		Log.endTestCase(result.getName());
	}
}
