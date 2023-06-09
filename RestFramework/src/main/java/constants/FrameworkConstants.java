package constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class FrameworkConstants {

	private FrameworkConstants() {

	}

	public static String getGlobalpropertiespath() {
		return GLOBALPROPERTIESPATH;
	}

	public static String getLogspath() {
		return LOGSPATH;
	}

	public static String getTestNGreport() {
		return TESTNGREPORT;
	}

	public static String getTestOutputFolder() {
		return TESTOUTPUTFOLDER;
	}

	public static String getDownloadPath() {
		return DOWNLOADPATH;
	}

	public static String getAPIReportpath() {
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		return APIREPORTPATH + date + "\\" ;
	}

	public static String getAPIReportIndexpath() {
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		return APIREPORTPATH + date + "\\index.html";
	}

	public static String getpExtentReportPath() {
		return EXTENTREPORT;
	}

	public static String getLogPropertiesPath() {
		return LOGPROPERTIESPATH;
	}

	public static String getLog4jxmlPath() {
		return LOG4JXMLPATH;
	}

	private static final String GLOBALPROPERTIESPATH = System.getProperty("user.dir")
			+ "\\resources\\config.properties";
	private static final String LOGSPATH = System.getProperty("user.dir") + "\\resources\\logs.log";
	private static final String TESTOUTPUTFOLDER = System.getProperty("user.dir") + "\\test-output";
	private static final String TESTNGREPORT = System.getProperty("user.dir") + "\\test-output\\index.html";
	private static final String EXTENTREPORT = System.getProperty("user.home") + "\\ApiReport\\myreport.html";
	private static final String APIREPORTPATH = System.getProperty("user.home") + "\\ApiReport\\";
	private static final String DOWNLOADPATH = System.getProperty("user.home") + "\\Downloads\\";
	private static final String LOGPROPERTIESPATH = System.getProperty("user.dir") + "\\resources\\log4j.properties";
	private static final String LOG4JXMLPATH = System.getProperty("user.dir") + "\\resources\\log4j.xml";
}
