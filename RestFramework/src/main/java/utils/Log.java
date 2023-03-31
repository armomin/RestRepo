package utils;

import org.apache.log4j.Logger;

public final class Log {

	private Log() {

	}

	public static Logger Log = Logger.getLogger(Log.class.getName());

	public static void startTestCase(String sTestCaseName) {
		Log.info("=============================================" + sTestCaseName
				+ "START====================================================================");
	}

	public static void endTestCase(String sTestCaseName) {
		Log.info("=============================================" + sTestCaseName
				+ "END====================================================================");
	}

	public static void failedTestCase(String sTestCaseName) {
		Log.info("=============================================" + sTestCaseName
				+ "FAILED====================================================================");
	}

	public static void skippedTestCase(String sTestCaseName) {
		Log.info("=============================================" + sTestCaseName
				+ "SKIPPED====================================================================");
	}

	public static void info(String message) {

		Log.info(message);
	}

	public static void exception(String msg, Exception e) {

		Log.info(msg, e);
	}

	public static void exception(String msg) {

		Log.info(msg);
	}

	public static void warn(String message) {

		Log.warn(message);
	}

	public static void error(String msg) {

		Log.error(msg);
	}

	public static void fatal(String message) {

		Log.fatal(message);
	}

}
