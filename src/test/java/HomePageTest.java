import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.util.List;
import org.testng.annotations.AfterMethod;
public class HomePageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaipldashboard.ccbp.tech/");
    }

    @Test(priority = 1)
    public void homePageHeading(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ipl-logo")));
        Assert.assertTrue(element.isDisplayed(), "IPL logo is not displayed");
        WebElement paragraphEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ipl-dashboard-heading")));
        String text = paragraphEl.getText();
        Assert.assertEquals(text, "IPL Dashboard", "Heading text does not match");
    }

    @Test(priority = 2)
    public void listOfTeams(){
        String[] expectedTitles = {"Royal Challengers Bangalore", "Kolkata Knight Riders", "Kings XI Punjab", "Chennai Super Kings", "Rajasthan Royals", "Mumbai Indians", "Sunrisers Hyderabad", "Delhi Capitals"};
        List<WebElement> teamTitles = driver.findElements(By.cssSelector("ul.teams-list p"));
        for(int i = 0; i < teamTitles.size(); i++){
            WebElement team = teamTitles.get(i);
            String text = team.getText();
            String expected = expectedTitles[i];
            Assert.assertEquals(expected, text, "Order and list of Teams do not match");
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
