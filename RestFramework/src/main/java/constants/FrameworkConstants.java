package constants;

import java.util.Date;
import java.text.SimpleDateFormat;

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

	public static String getDownloadPath() {
		return DOWNLOADPATH;
	}

	public static String getAPIReportpath() {
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		return APIREPORTPATH + date + "\\";
	}

	private static final String GLOBALPROPERTIESPATH = System.getProperty("user.dir")
			+ "\\resources\\config.properties";
	private static final String LOGSPATH = System.getProperty("user.dir") + "\\resources\\logs.log";
	private static final String TESTNGREPORT = System.getProperty("user.dir") + "\\test-output\\index.html";
	private static final String APIREPORTPATH = "C:\\Users\\12343\\Reports";
	private static final String DOWNLOADPATH = System.getProperty("user.home") + "\\Downloads\\";

}
