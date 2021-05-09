package de.timsander.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Loader {

	public static URL chrome;
	public static URL firefox;

	public Loader() {
		try {
			loadChrome();
			loadFirefox();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadChrome() throws IOException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("de/timsander/drivers/chromedriver.exe");
		File f = new File("CovidBotFiles");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		File chromeDriver = new File("CovidBotFiles" + File.separator + "chromedriver.exe");
		if (!chromeDriver.exists()) {
			chromeDriver.createNewFile();
			FileUtils.copyURLToFile(resource, chromeDriver);
		}
		
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());

	}

	public void loadFirefox() throws IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("de/timsander/drivers/geckodriver.exe");
		File f = new File("CovidBotFiles");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		File chromeDriver = new File("CovidBotFiles" + File.separator + "geckodriver.exe");
		if (!chromeDriver.exists()) {
			chromeDriver.createNewFile();
			FileUtils.copyURLToFile(resource, chromeDriver);
		}
		
		System.setProperty("webdriver.gecko.driver", chromeDriver.getAbsolutePath());

	}

}
