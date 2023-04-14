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
import org.springframework.util.FileSystemUtils;

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
		String dateName = new SimpleDateFormat("MM_dd_HH_mm_SS").format(new Date());
		File logfile = new File(FrameworkConstants.getLogspath());
		File suitelogfile = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_Logs.log");
		File apireportfolder = new File(FrameworkConstants.getAPIReportpath());
		File suiteReport = new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_TestNGReport.html");
		File testoutput = new File(FrameworkConstants.getTestOutputFolder());
		File testoutputindex = new File(FrameworkConstants.getTestNGreport());
		try {
			FileSystemUtils.copyRecursively(testoutput, apireportfolder);
			TestUtils.openFile(FrameworkConstants.getTestNGreport());
			FileUtils.copyFile(logfile, suitelogfile);
			FileUtils.copyFile(new File(FrameworkConstants.getTestNGreport()),
					new File(FrameworkConstants.getAPIReportpath() + "\\" + dateName + "_TestNGReport.html"));

			
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestStart(ITestResult result) {
		Log.startTestCase(result.getName());
	}

	public void onTestSucess(ITestResult result) {
		Log.endTestCase(result.getName());
	}
}
