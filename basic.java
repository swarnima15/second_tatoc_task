package tatoc;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class basic {

	public static void main(String[] args) throws InterruptedException {
		//TASK:1
		WebDriver driver=new FirefoxDriver();
		 driver.get("http://10.0.1.86/");
		driver.get("http://10.0.1.86/tatoc");
		driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<WebElement> n=(List<WebElement>) js.executeScript("return document.querySelectorAll('div.greenbox')");
		n.get(0).click();
	
		//TASK:2
		String box1 = (String) js.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#answer').className;");
		String box2 = (String) js.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#child').contentWindow.document.getElementById('answer').getAttribute('class');");
		while(!box1.equals(box2))
		{
			js.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[0].click();");
			Thread.sleep(3000);
			box2=(String)js.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#child').contentWindow.document.getElementById('answer').getAttribute('class');");
		}		
		js.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[1].click();");

       //TASK:3
		Actions action = new Actions(driver);
		List<WebElement> Sourcelocator= (List<WebElement>) js.executeScript(" return document.querySelectorAll('div#dragbox');");
		List<WebElement> Destinationlocator = (List<WebElement>) js.executeScript(" return document.querySelectorAll('div#dropbox');");
		action.dragAndDrop(Sourcelocator.get(0), Destinationlocator.get(0)).build().perform();
		n =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		n.get(0).click();

		//TASk:4
		n=(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		n.get(0).click();
		String  handle= driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		n=(List<WebElement>) js.executeScript("return document.querySelectorAll('#name')");
		n.get(0).sendKeys("Swarnima");
		List <WebElement> ss =(List<WebElement>) js.executeScript("return document.querySelectorAll('#submit')");
		ss.get(0).click();
		driver.switchTo().window(handle);
		n =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		n.get(1).click();
		
		//TASK:5
		n =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		n.get(0).click();
		ss =(List<WebElement>) js.executeScript("return document.querySelectorAll('#token')");
		String val_1 =ss.get(0).getText();
		String[] val=val_1.split(": ");
		Cookie token = new Cookie("Token", val[1]);
		driver.manage().addCookie(token);
		n.get(1).click();
		Thread.sleep(2000);
		driver.quit();
	}

}
