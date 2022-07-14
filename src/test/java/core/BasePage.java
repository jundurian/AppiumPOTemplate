package core;

/*
This class contains parametrized basic functions that will be user by all the Pages.
 */

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static core.InitializeDriver.getDriver;
import static io.appium.java_client.android.nativekey.AndroidKey.BACK;
import static io.appium.java_client.android.nativekey.AndroidKey.ENTER;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BasePage {

    private final WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
    private static final PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public void scroll(double start, double end) {

        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int y_init = (int) (size.height * start);
        int y_end = (int) (size.height * end);

        Sequence scroll = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), x, y_init))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(ofMillis(500), viewport(), x, y_end))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));

        getDriver().perform(Arrays.asList(scroll));
    }

    public void scrollDown() {
        scroll(0.9, 0.1);
    }

    public void scrollUp() {
        scroll(0.1, 0.9);
    }

    public void longPressElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 3000
        ));
    }

    public void click(By by) {
        wait.until(visibilityOfElementLocated(by)).click();
    }

    public void clickByText(String text) {
        wait.until(visibilityOfElementLocated(By.xpath("//*[@text='" + text + "']"))).click();

    }

    public void clickByAccessibilityID(String accessibility_id) {
        WebElement elementByAccessibilityID = findElementByAccessibilityID(accessibility_id);
        elementByAccessibilityID.click();
    }

    public void write(By by, String text) {
        wait.until(visibilityOfElementLocated(by)).clear();
        wait.until(visibilityOfElementLocated(by)).click();
        wait.until(visibilityOfElementLocated(by)).sendKeys(text);
    }

    public void waitElementByText(String text){
        wait.until(visibilityOfElementLocated(By.xpath("//*[@text='" + text + "']")));
    }

    public void waitUntilElementThatContainsText(String text){
        wait.until(visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + text + "')]")));
    }

    public void waitElementThatContainsTextDisappears(String text) {
        wait.until(invisibilityOfElementLocated(By.xpath("//*[contains(@text,'" + text + "')]")));
    }

    public void setImplicityWait(int time_seconds){
        getDriver().manage().timeouts().implicitlyWait(ofSeconds(time_seconds));
    }

    public void pressEnter() {
        getDriver().pressKey(new KeyEvent(ENTER));
    }

    public void pressBackKey() {
        getDriver().pressKey(new KeyEvent(BACK));
    }

    public void openApp(String appPackage, String appActivity) {
        getDriver().startActivity(new Activity(appPackage, appActivity));
    }

    public WebElement getElementByText(String text){
        setImplicityWait(3);
        WebElement elementByXPATH = findElementByXPATH("//*[@text='" + text + "']");
        setImplicityWait(10);
        return elementByXPATH;
    }

    public WebElement getElementContainsText(String text) {
        setImplicityWait(3);
        WebElement elementByXPATH = findElementByXPATH("//*[contains(@text,'" + text + "')]");
        setImplicityWait(10);
        return elementByXPATH;
    }

    public void hideVirtualKeyboard() {
        getDriver().hideKeyboard();
    }

    public WebElement findElementByID(String id) {
        return getDriver().findElement(By.id(id));
    }

    public List<WebElement> findElementsByID(String id) {
        return getDriver().findElements(By.id(id));
    }

    public WebElement findElementByXPATH(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public List<WebElement> findElementsByXPATH(String xpath) {
        return getDriver().findElements(By.xpath(xpath));
    }

    public WebElement findElementByAccessibilityID(String access_id) {
        return getDriver().findElement(By.cssSelector("[content-desc='" + access_id + "']"));
    }

    public WebElement findElementByCSS(String css){
        return getDriver().findElement(By.cssSelector(css));
    }

    public List<WebElement> findElementsByCSS(String css){
        return getDriver().findElements(By.cssSelector(css));
    }

}
