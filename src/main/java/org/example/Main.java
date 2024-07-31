package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://monkeytype.com/");

		try {
			WebElement rejectCookies = driver.findElement(By.className("rejectAll"));
			rejectCookies.click();
			WebElement textInput = driver.findElement(By.id("wordsInput"));
			Thread.sleep(1500);


			while(true){
				try {
					WebElement activeWord = driver.findElement(By.cssSelector(".word.active"));
					WebElement letter = activeWord.findElement(By.cssSelector("letter:not(.correct)"));
					textInput.sendKeys(letter.getText());
				} catch (NoSuchElementException e) {
					textInput.sendKeys(" ");
				} catch (ElementNotInteractableException _e){
					break;
				}
			}

/*
			while(true){
				try {
					WebElement activeWord = driver.findElement(By.cssSelector(".word.active"));
					// uncomment this and remove sleep for infinite speed
					//textInput.sendKeys(activeWord.getText() + " ");

					textInput.sendKeys(activeWord.getText());
					textInput.sendKeys(" ");
					Thread.sleep(100);
				} catch (ElementNotInteractableException _e) {
					break;
				}
			}
 */

			System.out.println("Enter anything to quit:");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}