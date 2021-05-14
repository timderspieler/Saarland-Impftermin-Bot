package de.timsander.main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.timsander.jframe_pages.Bestaetigung;
import de.timsander.jframe_pages.CodeInput;
import de.timsander.jframe_pages.Startseite;
import de.timsander.jframe_pages.Warnung;

public class Main {
	
	public static City c;
	public static String browser = "none";
	public static int max_times = 10000;
	public static int times = 0;
	
	public static Startseite start;
	public static WebDriver driver;
	
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
				Startseite.suche_panel.setText("Suche nach freien Terminen in " + c.getName() + "... (" + times + "/" + max_times + ")");
				
			}
			
			if (times >= max_times) {
				reached = true;
			}
			
		}
		
		times = 0;
		Startseite.progressBar.setIndeterminate(false);
		Startseite.suche_panel.setText("(Suche inaktiv)");
		Startseite.start_button.setSelected(false);
		
		if (!found) {
			
			Startseite.failure(start);
			Thread.sleep(5000);
			driver.close();
			
		} else {
			
			Toolkit.getDefaultToolkit().beep();
			
			String telmail = Startseite.txtEmailTelefon.getText();
			
			if (Startseite.auto_login.isSelected() && telmail.length() > 0) {
				
				Thread.sleep(500);
				
				clickForward(driver);
				
				Thread.sleep(1000);
				
				insertEmailTelefon(driver, telmail);
				
				Thread.sleep(500);
				
				clickSendCode(driver);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							CodeInput fr = new CodeInput();
							fr.setVisible(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			} else {
				
				Startseite.success(start);
				
			}
			
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
			warnung("Stadt Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	private static void clickForward(WebDriver d) {
		
		WebElement el = d.findElement(By.xpath("//*[@id=\"logged-in-area\"]/div/div[2]/div[2]/button[2]"));
		
		if (el == null) {
			warnung("Weiter Button konnte nicht gefunden werden!");
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
			warnung("Zurück Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	public static void insertCode(WebDriver d, String code) {
		
		String path = "//*[@id=\"session_token\"]";
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			warnung("Input Feld konnte nicht gefunden werden!");
			return;
		}
		
		el.sendKeys(code);
		
	}
	
	public static void sendNewCode(WebDriver d) throws InterruptedException {
		
		String path = "/html/body/main/div[1]/form/div[2]/a";
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			warnung("Zurück Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
		Thread.sleep(1000);
		
		insertEmailTelefon(d, Startseite.txtEmailTelefon.getText());
		
		Thread.sleep(500);
		
		clickSendCode(d);
		
	}
	
	public static void login(WebDriver d) {
		
		String path = "/html/body/main/div[1]/form/div[2]/button";
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			warnung("Login Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	public static boolean isCodeValid(WebDriver d) {
		
		String path = "/html/body/main/div[1]/p[3]";
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el != null) {
			if (el.getAttribute("innerHTML").contains("Der eingegebene Code ist nicht gültig.")) {
				return false;
			}
		}
		
		return true;
		
	}
	
	private static void clickSendCode(WebDriver d) {
		
		String path = "//*[@id=\"login-form-fields\"]/div[3]/input";
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			warnung("'Code Senden' Button konnte nicht gefunden werden!");
			return;
		}
		
		el.click();
		
	}
	
	private static void insertEmailTelefon(WebDriver d, String telemail) {
		
		String path = ""; //"//*[@id=\"login-form-fields\"]/div[1]/input[1]";
		
		if (telemail.contains("@")) {
			path = "//*[@id=\"login-form-fields\"]/div[1]/input[1]";
		} else {
			path = "//*[@id=\"login-form-fields\"]/div[2]/div/input";
		}
		
		WebElement el = d.findElement(By.xpath(path));
		
		if (el == null) {
			warnung((telemail.contains("@") ? "E-Mail" : "Tel") +" Feld konnte nicht gefunden werden!");
			return;
		}
		
		el.sendKeys(telemail);
		
	}
	
	public static void warnung(String msg) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Warnung fr = new Warnung(msg);
					fr.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
