import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import java.util.List;
public class TeamPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaipldashboard.ccbp.tech/");
    }

    @Test(priority = 1)
    public void testingTeams(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("teams-list")));

        WebElement fourthEl = driver.findElement(By.cssSelector("ul.teams-list a:nth-of-type(4)"));
        fourthEl.click();

        String expectedUrl = "https://qaipldashboard.ccbp.tech/team-matches/CSK";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "URL's do not match");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("team-banner")));
        Assert.assertTrue(element.isDisplayed(), "Team image is not displayed");

        WebElement latestMatch = driver.findElement(By.cssSelector("div.latest-match-details-1 p:first-child"));
        String text = latestMatch.getText();

        Assert.assertEquals(text, "Royal Challengers Bangalore", "Latest match team name does not match");

        List<WebElement> recentMatches = driver.findElements(By.cssSelector("ul.recent-matches-list li"));
        int count = recentMatches.size();
        int expectedCount = 13;
        Assert.assertEquals(count, expectedCount, "Count does not match");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
