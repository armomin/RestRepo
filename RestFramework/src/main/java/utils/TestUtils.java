package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TestUtils {
	public static boolean openFile(String FilePath) {
		try {
			File file = new File(FilePath);

			Desktop desktop = Desktop.getDesktop();
			if (!Desktop.isDesktopSupported()) {

				return false;
			}

			if (file.exists())
				desktop.open(file);
			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	

}
