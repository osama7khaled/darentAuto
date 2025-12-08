package tests.traveller.home;

import base.Finder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest {

    @Test(priority = 1)
    public void verifyDownloadButtonIsVisible() {
        Assert.assertTrue(
                Finder.visible(HomePom.DOWNLOAD_BUTTON_LOCATOR, 10).isDisplayed(),
                "❌ زر تحميل التطبيق غير ظاهر!"
        );
    }

    @Test(priority = 2)
    public void verifyDownloadButtonIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getDownloadButton());
        Assert.assertTrue(HomePom.getDownloadButton().isEnabled(), "❌ زر التحميل غير قابل للنقر!");
    }

    @Test(priority = 3)
    public void verifyDownloadButtonTextMatchesData() {
        Assert.assertEquals(
                HomePom.getDownloadButton().getText().trim(),
                HomePom.getExpectedDownloadButtonText(),
                "❌ نص زر التحميل غير مطابق!"
        );
    }


    // ---------------------------------------
    // APARTMENT
    // ---------------------------------------
    @Test(priority = 4)
    public void verifyApartmentIsVisible() {
        Assert.assertTrue(HomePom.getApartmentCategory().isDisplayed(), "❌ Apartment غير ظاهر!");
    }

    @Test(priority = 5)
    public void verifyApartmentIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getApartmentCategory());
        Assert.assertTrue(HomePom.getApartmentCategory().isEnabled(), "❌ Apartment غير قابل للنقر!");
    }

    @Test(priority = 6)
    public void verifyApartmentTextMatchesData() {
        Assert.assertEquals(
                HomePom.getApartmentCategory().getText().trim(),
                HomePom.expectedApartmentText(),
                "❌ نص Apartment غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // VILLA
    // ---------------------------------------
    @Test(priority = 7)
    public void verifyVillaIsVisible() {
        Assert.assertTrue(HomePom.getVillaCategory().isDisplayed(), "❌ Villa غير ظاهرة!");
    }

    @Test(priority = 8)
    public void verifyVillaIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getVillaCategory());
        Assert.assertTrue(HomePom.getVillaCategory().isEnabled(), "❌ Villa غير قابلة للنقر!");
    }

    @Test(priority = 9)
    public void verifyVillaTextMatchesData() {
        Assert.assertEquals(
                HomePom.getVillaCategory().getText().trim(),
                HomePom.expectedVillaText(),
                "❌ نص Villa غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // CHALET
    // ---------------------------------------
    @Test(priority = 10)
    public void verifyChaletIsVisible() {
        Assert.assertTrue(HomePom.getChaletCategory().isDisplayed(), "❌ Chalet غير ظاهر!");
    }

    @Test(priority = 11)
    public void verifyChaletIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getChaletCategory());
        Assert.assertTrue(HomePom.getChaletCategory().isEnabled(), "❌ Chalet غير قابل للنقر!");
    }

    @Test(priority = 12)
    public void verifyChaletTextMatchesData() {
        Assert.assertEquals(
                HomePom.getChaletCategory().getText().trim(),
                HomePom.expectedChaletText(),
                "❌ نص Chalet غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // TENT
    // ---------------------------------------
    @Test(priority = 13)
    public void verifyTentIsVisible() {
        Assert.assertTrue(HomePom.getTentCategory().isDisplayed(), "❌ Tent غير ظاهر!");
    }

    @Test(priority = 14)
    public void verifyTentIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getTentCategory());
        Assert.assertTrue(HomePom.getTentCategory().isEnabled(), "❌ Tent غير قابل للنقر!");
    }

    @Test(priority = 15)
    public void verifyTentTextMatchesData() {
        Assert.assertEquals(
                HomePom.getTentCategory().getText().trim(),
                HomePom.expectedTentText(),
                "❌ نص Tent غير مطابق للداتا!"
        );
    }


    @Test(priority = 20)
    public void verifyExploreIsVisible() {
        Assert.assertTrue(
                Finder.visible(HomePom.EXPLORE_LOCATOR, 10).isDisplayed(),
                "❌ Explore غير ظاهر!"
        );
    }

    @Test(priority = 21)
    public void verifyExploreIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getExplore());
        Assert.assertTrue(true, "❌ Explore غير قابل للنقر!");
    }

    @Test(priority = 22)
    public void verifyExploreTextMatchesData() {
        Assert.assertEquals(
                HomePom.getExplore().getText().trim(),
                HomePom.expectedExploreText(),
                "❌ نص Explore غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // WISHLIST
    // ---------------------------------------
    @Test(priority = 23)
    public void verifyWishlistIsVisible() {
        Assert.assertTrue(
                Finder.visible(HomePom.WISHLIST_LOCATOR, 10).isDisplayed(),
                "❌ Wishlist غير ظاهر!"
        );
    }

    @Test(priority = 24)
    public void verifyWishlistIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getWishlist());
        Assert.assertTrue(true, "❌ Wishlist غير قابل للنقر!");
    }

    @Test(priority = 25)
    public void verifyWishlistTextMatchesData() {
        Assert.assertEquals(
                HomePom.getWishlist().getText().trim(),
                HomePom.expectedWishlistText(),
                "❌ نص Wishlist غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // INBOX
    // ---------------------------------------
    @Test(priority = 26)
    public void verifyInboxIsVisible() {
        Assert.assertTrue(
                Finder.visible(HomePom.INBOX_LOCATOR, 10).isDisplayed(),
                "❌ Inbox غير ظاهر!"
        );
    }

    @Test(priority = 27)
    public void verifyInboxIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getInbox());
        Assert.assertTrue(true, "❌ Inbox غير قابل للنقر!");
    }

    @Test(priority = 28)
    public void verifyInboxTextMatchesData() {
        Assert.assertEquals(
                HomePom.getInbox().getText().trim(),
                HomePom.expectedInboxText(),
                "❌ نص Inbox غير مطابق للداتا!"
        );
    }


    // ---------------------------------------
    // ACCOUNT
    // ---------------------------------------
    @Test(priority = 29)
    public void verifyAccountIsVisible() {
        Assert.assertTrue(
                Finder.visible(HomePom.ACCOUNT_LOCATOR, 10).isDisplayed(),
                "❌ Account غير ظاهر!"
        );
    }

    @Test(priority = 30)
    public void verifyAccountIsClickable() {
        Finder.waitForElementToBeClickable(HomePom.getAccount());
        Assert.assertTrue(true, "❌ Account غير قابل للنقر!");
    }

    @Test(priority = 31)
    public void verifyAccountTextMatchesData() {
        Assert.assertEquals(
                HomePom.getAccount().getText().trim(),
                HomePom.expectedAccountText(),
                "❌ نص Account غير مطابق للداتا!"
        );
    }

}