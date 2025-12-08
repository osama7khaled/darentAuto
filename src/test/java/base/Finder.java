package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Finder {

    private static WebDriver driver;
    private static WebDriverWait wait;

    // -------------------------------------------------------------
    // INITIALIZE
    // -------------------------------------------------------------
    public static void initialize(WebDriver webDriver, WebDriverWait webWait) {
        driver = webDriver;
        wait = webWait;
    }

    // -------------------------------------------------------------
    // BASIC WAITS
    // -------------------------------------------------------------
    public static void waitForElementToBePresentBy(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not found in DOM");
        }
    }

    public static void waitForElementToBeVisibleBy(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeInVisibleBy(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeClickableBy(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // -------------------------------------------------------------
    // BASIC LOCATORS
    // -------------------------------------------------------------
    public static WebElement getById(String id, boolean isClickable) {
        By locator = By.id(id);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByName(String name, boolean isClickable) {
        By locator = By.name(name);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByCssSelector(String selector, boolean isClickable) {
        By locator = By.cssSelector(selector);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByClassName(String className, boolean isClickable) {
        By locator = By.className(className);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    // -------------------------------------------------------------
    // XPATH LOCATORS
    // -------------------------------------------------------------
    public static WebElement getByXpath(String xpath, @Optional boolean isClickable) {
        By locator = By.xpath(xpath);
        waitForElementToBeVisibleBy(locator);
        if (Boolean.TRUE.equals(isClickable)) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static List<WebElement> getListByXpath(String xpath) {
        By locator = By.xpath(xpath);
        waitForElementToBeVisibleBy(locator);
        return driver.findElements(locator);
    }

    // -------------------------------------------------------------
    // TEXT LOCATORS
    // -------------------------------------------------------------
    public static WebElement getByExactText(String text, @Optional String tagName, @Optional boolean isClickable) {
        String tag = (tagName != null && !tagName.isEmpty()) ? tagName : "*";
        String xpath = "//" + tag + "[text()='" + text + "']";
        return getByXpath(xpath, isClickable);
    }

    public static WebElement getByPartialText(String text, @Optional String tagName, @Optional boolean isClickable) {
        String tag = (tagName != null && !tagName.isEmpty()) ? tagName : "*";
        String xpath = "//" + tag + "[contains(text(),'" + text + "')]";
        return getByXpath(xpath, isClickable);
    }

    public static WebElement getByPartialText(String text, @Optional String tagName, int index, @Optional boolean isClickable) {
        String tag = (tagName != null && !tagName.isEmpty()) ? tagName : "*";
        String xpath = "(//" + tag + "[contains(text(),'" + text + "')])[" + index + "]";
        return getByXpath(xpath, isClickable);
    }

    // -------------------------------------------------------------
    // INDEX HELPERS
    // -------------------------------------------------------------
    public static WebElement getByClassWithIndex(String tag, String className, int index, @Optional boolean isClickable) {
        String xpath = "(//" + tag + "[@class='" + className + "'])[" + index + "]";
        return getByXpath(xpath, isClickable);
    }

    public static WebElement getByNameWithIndex(String tag, String name, int index, @Optional boolean isClickable) {
        String xpath = "(//" + tag + "[@name='" + name + "'])[" + index + "]";
        return getByXpath(xpath, isClickable);
    }

    public static WebElement getElementByPartialTextWithIndex(String tag, String partialText, int index, @Optional boolean isClickable) {
        String xpath = "(//" + tag + "[contains(text(),'" + partialText + "')])[" + index + "]";
        return getByXpath(xpath, isClickable);
    }

    // -------------------------------------------------------------
    // PARENT → CHILD
    // -------------------------------------------------------------
    public static WebElement getByXpathInParent(String xpath, WebElement parent, @Optional boolean isClickable) {
        if (Boolean.TRUE.equals(isClickable)) waitForElementToBeClickable(parent);
        return parent.findElement(By.xpath(xpath));
    }

    // -------------------------------------------------------------
    // RELATIVE LOCATORS
    // -------------------------------------------------------------
    public static WebElement getByAboveElement(String tag, WebElement element, boolean isClickable) {
        By locator = with(By.tagName(tag)).below(element);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByBelowElement(String tag, WebElement element, boolean isClickable) {
        By locator = with(By.tagName(tag)).above(element);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByLeftElement(String tag, WebElement element, boolean isClickable) {
        By locator = with(By.tagName(tag)).toRightOf(element);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    public static WebElement getByRightElement(String tag, WebElement element, boolean isClickable) {
        By locator = with(By.tagName(tag)).toLeftOf(element);
        waitForElementToBeVisibleBy(locator);
        if (isClickable) waitForElementToBeClickableBy(locator);
        return driver.findElement(locator);
    }

    // -------------------------------------------------------------
    // TABLE HELPERS
    // -------------------------------------------------------------
    public static int getRowNumberContainsText(String text) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr"));

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> childCells = getAllChildrenByXpath("//tr[" + i + "]");
            for (WebElement cell : childCells) {
                if (cell.getText().equals(text)) return i;
            }
        }
        return 0;
    }

    public static WebElement getElementInRowUnderHeader(String headerText, int rowNum) {

        String headerXpath = "//table//th[contains(text(), '" + headerText + "')]";
        WebElement headerElement = getByXpath(headerXpath, false);

        List<WebElement> headers = driver.findElements(By.xpath("//table//th"));
        int colIndex = headers.indexOf(headerElement) + 1;

        return getByXpath("//table//tr[" + rowNum + "]//td[" + colIndex + "]", false);
    }

    // -------------------------------------------------------------
    // OTHER TOOLS
    // -------------------------------------------------------------
    public static List<WebElement> getAllChildrenByXpath(String xpath) {
        return getByXpath(xpath, false).findElements(By.xpath("./child::*"));
    }

    public static boolean elementIsVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement getInputByTitle(String title) {
        return getByXpath("//input[@title='" + title + "']", false);
    }

    public static WebElement visible(By locator, int seconds) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return w.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
