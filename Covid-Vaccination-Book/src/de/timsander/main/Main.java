package de.timsander.main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import de.timsander.jframe_pages.Progress;
import de.timsander.jframe_pages.Startseite;

public class Main {
	
	public static City c;
	public static String browser = "none";
	public static int max_times = 10000;
	public static int times = 0;
	
	public static Progress p;
	public static Startseite start;
	
	public static void main(String[] args) {
		
		new Loader();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start = new Startseite();
					start.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static class Task extends Thread {
		
		@Override
		public void run() {
			try {
				Main.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void start() throws InterruptedException {
		
		WebDriver driver = null;
		
		try {
			System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			return;
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://impfen-saarland.de/service/waitlist_entries");
		
		boolean found = false;
		boolean reached = false;
		
		Thread.sleep(3000);
		
		while (!found && !reached) {
			
			clickCity(driver, c);
			
			Thread.sleep(1);
			
			clickForward(driver);
			
			Thread.sleep(500);
			
			if (isAvailable(driver)) {
				
				selectDate(driver);
				found = true;
				
			} else {
				
				Thread.sleep(35);
				clickBack(driver, false);
				times++;
				Progress.progressBar.setValue(times);
				Progress.progressString.setText("Suche nach freien Terminen in " + c.getName() + "... (" + times + "/" + max_times + ")");
				
			}
			
			if (times >= max_times) {
				reached = true;
			}
			
		}
		
		times = 0;
		Progress.progressBar.setValue(times);
		p.dispose();
		
		if (!found) {
			Startseite.failure(start);
			Thread.sleep(5000);
			driver.close();
		} else {
			Startseite.success(start);
		}
		
	}
	
	private static void selectDate(WebDriver d) {
		
		WebElement we = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/div[2]"));
		
		if (we == null) {
			System.err.println("No dates to select!");
			return;
		}
		
		we.click();
		
		clickForward(d);
		
	}
	
	private static boolean isAvailable(WebDriver d) {
		
//		WebElement we = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/h5"));
		WebElement we = d.findElement(By.xpath("/html/body/main/div[1]/div/div[2]/h5"));
		
		if (we != null) {
			if (we.getAttribute("innerHTML").contains("Keine Termine verfügbar.")) {
				return false;
			}
		}
		
		if (we != null) {
			if (we.getAttribute("innerHTML").contains("Impftermine auswählen")) {
				System.out.println("Dates available!");
				return true;
			}
		}
		
		System.out.println("Wait i skipped smth!");
		return false;
		
	}
	
	private static void clickCity(WebDriver d, City c) {
		
		WebElement el = d.findElement(By.xpath(c.getPath()));
		
		if (el == null) {
			System.err.println("Stadt Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	private static void clickForward(WebDriver d) {
		
		WebElement el = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/div[2]/button[2]"));
		
		if (el == null) {
			System.err.println("Weiter Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	private static void clickBack(WebDriver d, boolean withnumber) {
		
		String path = "//*[@id=\"logged-in-area\"]/div/div[2]/div/button";
		
		if (withnumber) {
			path = "//*[@id=\"logged-in-area\"]/div/div[2]/div[2]/button[1]";
		}
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			System.err.println("Zurück Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
}
