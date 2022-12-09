package core;

/*
Class with functions common to all tests.
Actions that will happen before/after each test regardless of the page.
They do not need to be called
 */

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

import static core.InitializeDriver.getDriver;
import static core.InitializeDriver.killDriver;

public class BaseTest extends BasePage{

    /*
    After each test, of each class, a screenshot will be taken
     */
    @AfterEach
    public void screenshotAfterEachTest(TestInfo testInfo) throws IOException {

        File screenshotAs = getDriver().getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshotAs, new File("target/screenshots/"+testInfo.getDisplayName()+".png"));
    }

    /*
    After all the test, among all the classes, are run, the driver will be closed
     */
    @AfterAll
    public static void killDriverAfterAllTestsAreDone(){
        killDriver();
    }
}
