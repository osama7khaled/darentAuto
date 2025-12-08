package base;

import configs.testdata.TestData;
import configs.testdata.TestDataFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Setup {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static TestData testData;

    public static String selectedLanguage;

    @Test(priority = 1)
    @Parameters({"language", "branch", "browser"})
    public void setUpLocalDriver(String language, String branch, String browser) throws IOException {

        selectedLanguage = language.toLowerCase();

        cleanScreenshotsDirectory();

        // تحميل الداتا بشكل صحيح
        testData = TestDataFactory.getTestData(branch, language);

        initializeLocalDriver(browser);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Finder.initialize(driver, wait);
        Go.initialize(driver, js, wait);
    }

    @Test(priority = 2)
    public void openWebsite() {

        // ✔ الآن تستخدم اللغة الداخلية المخزنة داخل TestData
        driver.get(testData.getBaseUrl());
        driver.manage().window();

        // إغلاق Popups لو ظهرت
        try {
            By moengage = By.cssSelector("#optInText > div");
            if (Finder.elementIsVisible(moengage)) {
                Go.click(moengage, 5);
            }
        } catch (Exception ignored) {}

        try {
            By cookies = By.cssSelector("div.col-md-4.text-right > button#accept-tracking");
            if (Finder.elementIsVisible(cookies)) {
                Go.click(cookies, 5);
            }
        } catch (Exception ignored) {}
    }


    private void initializeLocalDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation",
                        Map.of("deviceName", "iPhone X"));
                driver = WebDriverManager.chromedriver()
                        .capabilities(chromeOptions)
                        .create();
                break;

            case "firefox":
                FirefoxOptions fox = new FirefoxOptions();
                fox.addPreference("general.useragent.override",
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) " +
                                "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile Safari/604.1");
                driver = WebDriverManager.firefoxdriver()
                        .capabilities(fox)
                        .create();
                driver.manage().window().setSize(new Dimension(375, 812));
                break;

            case "edge":

                String path = "C:/Users/munde/IdeaProjects/DarentTest/edgedriver_win64/msedgedriver.exe";
                System.setProperty("webdriver.edge.driver", path);

                Map<String, Object> mobile = new HashMap<>();
                mobile.put("deviceName", "iPhone X");

                EdgeOptions edgeOpt = new EdgeOptions();
                edgeOpt.setExperimentalOption("mobileEmulation", mobile);

                driver = new EdgeDriver(edgeOpt);
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new RuntimeException("Unknown browser: " + browser);
        }
    }


    private void cleanScreenshotsDirectory() {
        File folder = new File("screenshots");

        try {
            if (folder.exists()) {
                FileUtils.deleteDirectory(folder);
            }
            folder.mkdirs();
        } catch (IOException e) {
            throw new RuntimeException("Could not clean screenshots directory", e);
        }
    }
}
