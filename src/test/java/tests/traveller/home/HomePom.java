package tests.traveller.home;

import base.Finder;
import base.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePom {

    public static final By DOWNLOAD_BUTTON_LOCATOR =
            By.xpath("//a[contains(@class,'mobile_header_strip')]//span");

    public static final By APARTMENT_LOCATOR =
            By.xpath("//*[@id='headerforPDF']//ul/a[1]/li");

    public static final By VILLA_LOCATOR =
            By.xpath("//*[@id='headerforPDF']//ul/a[2]/li");

    public static final By CHALET_LOCATOR =
            By.xpath("//*[@id='headerforPDF']//ul/a[3]/li");

    public static final By TENT_LOCATOR =
            By.xpath("//*[@id='headerforPDF']//ul/a[4]/li");

    public static final By EXPLORE_LOCATOR =
            By.xpath("/html/body/div[6]/div/div/a[1]");

    public static final By WISHLIST_LOCATOR =
            By.xpath("/html/body/div[6]/div/div/a[2]");

    public static final By INBOX_LOCATOR =
            By.xpath("/html/body/div[6]/div/div/a[3]");

    public static final By ACCOUNT_LOCATOR =
            By.xpath("/html/body/div[6]/div/div/a[4]");


    // ------------------------------------------
    // 🔵 2) Elements (Getter)
    // ------------------------------------------

    public static WebElement getDownloadButton() {
        return Finder.visible(DOWNLOAD_BUTTON_LOCATOR, 10);
    }

    public static WebElement getApartmentCategory() {
        return Finder.visible(APARTMENT_LOCATOR, 10);
    }

    public static WebElement getVillaCategory() {
        return Finder.visible(VILLA_LOCATOR, 10);
    }

    public static WebElement getChaletCategory() {
        return Finder.visible(CHALET_LOCATOR, 10);
    }

    public static WebElement getTentCategory() {
        return Finder.visible(TENT_LOCATOR, 10);
    }

    public static WebElement getExplore() {
        return Finder.visible(EXPLORE_LOCATOR, 10);
    }

    public static WebElement getWishlist() {
        return Finder.visible(WISHLIST_LOCATOR, 10);
    }

    public static WebElement getInbox() {
        return Finder.visible(INBOX_LOCATOR, 10);
    }

    public static WebElement getAccount() {
        return Finder.visible(ACCOUNT_LOCATOR, 10);
    }


    // ------------------------------------------
    // 🔵 3) Expected Text from JSON
    // ------------------------------------------

    public static String getExpectedDownloadButtonText() {
        return Setup.testData.getHomeData().get("downloadApp").asText();
    }

    public static String expectedApartmentText() {
        return Setup.testData.getHomeData().get("categories").get("apartment").asText();
    }

    public static String expectedVillaText() {
        return Setup.testData.getHomeData().get("categories").get("villa").asText();
    }

    public static String expectedChaletText() {
        return Setup.testData.getHomeData().get("categories").get("chalet").asText();
    }

    public static String expectedTentText() {
        return Setup.testData.getHomeData().get("categories").get("tent").asText();
    }

    public static String expectedExploreText() {
        return Setup.testData.getHomeData().get("bottomMenu").get("explore").asText();
    }

    public static String expectedWishlistText() {
        return Setup.testData.getHomeData().get("bottomMenu").get("wishlist").asText();
    }

    public static String expectedInboxText() {
        return Setup.testData.getHomeData().get("bottomMenu").get("inbox").asText();
    }

    public static String expectedAccountText() {
        return Setup.testData.getHomeData().get("bottomMenu").get("account").asText();
    }
}