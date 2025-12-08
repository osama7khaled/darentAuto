package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;

public class Go {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;

    public static String testRunId;
    private static String mainTab;

    // ---------------------------------------------------------
    // INITIALIZE
    // ---------------------------------------------------------
    public static void initialize(WebDriver webDriver, JavascriptExecutor javascriptExecutor, WebDriverWait webWait) {
        driver = webDriver;
        js = javascriptExecutor;
        wait = webWait;
    }

    // ---------------------------------------------------------
    // CLICK ACTIONS (3-level fallback)
    // ---------------------------------------------------------
    public static void click(WebElement element) {
        try {
            element.click(); // 1) normal click
        } catch (Exception e1) {
            try {
                js.executeScript("arguments[0].click();", element); // 2) JS click
            } catch (Exception e2) {
                new Actions(driver).moveToElement(element).click().perform(); // 3) Actions click
            }
        }
    }

    public static void actionClick(WebElement element) {
        new Actions(driver).click(element).perform();
    }

    public static void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public static void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    // ---------------------------------------------------------
    // SCROLLING
    // ---------------------------------------------------------
    public static void scrollDown(int pixels) {
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    public static void scrollUp(int pixels) {
        js.executeScript("window.scrollBy(0, arguments[0]);", -pixels);
    }

    public static void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ---------------------------------------------------------
    // FRAME HANDLING
    // ---------------------------------------------------------
    public static void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public static void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public static void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    public static void switchToMainFrame() {
        driver.switchTo().defaultContent();
    }

    // ---------------------------------------------------------
    // TABS MANAGEMENT
    // ---------------------------------------------------------
    public static void setMainTab() {
        mainTab = driver.getWindowHandle();
    }

    public static void switchToTab(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }

    public static void switchToLastTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static void closeTabAndSwitchBack() {
        driver.close();
        driver.switchTo().window(mainTab);
    }

    // ---------------------------------------------------------
    // INPUT UTILITIES
    // ---------------------------------------------------------
    public static void clearText(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public static void paste(WebElement element) {
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    // ---------------------------------------------------------
    // SCREENSHOTS
    // ---------------------------------------------------------
    public static void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "screenshots/" + name + "_" + timestamp() + ".png";
            FileUtils.copyFile(src, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takeFullPageScreenshot(String name) {
        try {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(150))
                    .takeScreenshot(driver);

            ImageIO.write(screenshot.getImage(), "PNG",
                    new File("screenshots/" + name + "_" + timestamp() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String timestamp() {
        return new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
    }

    // ---------------------------------------------------------
    // WAIT HELPERS
    // ---------------------------------------------------------
    public static WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean isVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ---------------------------------------------------------
    // JAVA SCRIPT HELPERS
    // ---------------------------------------------------------
    public static void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public static void jsType(WebElement element, String text) {
        js.executeScript("arguments[0].value='" + text + "'", element);
    }

    public static Object executeJS(String script) {
        return js.executeScript(script);
    }

    // ---------------------------------------------------------
    // TABLE HELPERS
    // ---------------------------------------------------------
    public static WebElement getCell(int row, int col) {
        String xpath = "//table//tr[" + row + "]//td[" + col + "]";
        return driver.findElement(By.xpath(xpath));
    }

    public static int findRowByText(String text) {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        for (int i = 1; i <= rows.size(); i++) {
            WebElement row = rows.get(i - 1);
            if (row.getText().contains(text)) return i;
        }
        return -1;
    }

    // ---------------------------------------------------------
    // NAVIGATION HELPERS
    // ---------------------------------------------------------
    public static void refresh() {
        driver.navigate().refresh();
    }

    public static String getUrl() {
        return driver.getCurrentUrl();
    }

    public static void back() {
        driver.navigate().back();
    }

    public static void click(By locator, int seconds) {
        WebElement element = Finder.visible(locator, seconds);
        element.click();
    }

    /**
     * Type text into element by locator with explicit wait
     */
    public static void type(By locator, String text, int seconds) {
        WebElement element = Finder.visible(locator, seconds);
        element.clear();
        element.sendKeys(text);
    }


}
