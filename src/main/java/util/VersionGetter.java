package util;

import java.io.File;
import java.util.Scanner;

public class VersionGetter {

	public static String getVersion(File aip) throws Exception {
		String line = null;
		String version = null;
		String pattern = "\"ProductName\"";
		int start = 0;
		int end = 0;
		switch (aip.getName()) {
		case "QFPAdapterSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QFPAutomatedTesterSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QFPCliSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QFPManualTesterSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QFPRecorderSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QFPTrainerSetup.aip":
			start = 42;
			end = 56;
			break;
		case "QualibrateUpdaterSetup.aip":
			start = 42;
			end = 56;
			break;
		}
		Scanner scanner = new Scanner(aip);
		while (scanner.hasNextLine()) {
			if (scanner.nextLine().contains(pattern) && start != 0 && end != 0) {
				line = scanner.nextLine();
				version = line.substring(start, end);
			}
		}
		scanner.close();
		return version;
	}

}
