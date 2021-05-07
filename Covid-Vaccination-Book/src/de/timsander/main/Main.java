package de.timsander.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		int max_times = 60;
		
//		System.setProperty("webdriver.gecko.driver", ".\\lib\\drivers\\geckodriver.exe");
//		
//		WebDriver driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://impfen-saarland.de/service/waitlist_entries");
		
		boolean found = false;
		int times = 0;
		
		Thread.sleep(3000);
		
		while (!found) {
			
			clickCity(driver, City.LEBACH_NIGHT);
			
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
				
			}
			
			if (times >= max_times) {
				found = true;
			}
			
		}
		
		Thread.sleep(5000);
		
		driver.close();
		
	}
	
	private static void selectDate(WebDriver d) {
		
		WebElement we = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/div[2]"));
		
		if (we == null) {
			System.err.println("No dates to select!");
			return;
		}
		
		we.click();
		
		we = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/div[2]/button[2]"));
		we.click();
		
	}
	
	private static boolean isAvailable(WebDriver d) {
		
		WebElement we = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/h5"));
		
		if (we != null) {
			if (we.getAttribute("innerHTML").equals("Keine Termine verfügbar.")) {
				return false;
			}
		}
		
		if (we != null) {
			if (we.getAttribute("innerHTML").equals("Impftermine auswählen")) {
				return true;
			}
		}
		
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
