package core;

// Class that takes care of initialize and reuse the same driver during the execution

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

import static java.time.Duration.ofSeconds;

public class InitializeDriver {


    private static AndroidDriver driver;

    // Check if driver exists. If not, create the driver, else, just return the existing one.
    public static AndroidDriver getDriver(){
        if (driver==null){
            createDriver();
        }
        return driver;
    }

    // Initiate driver and Appium capabilities
    private static void createDriver(){

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("12")
                .skipDeviceInitialization()
                .skipServerInstallation()
                .setNewCommandTimeout(ofSeconds(80));

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(ofSeconds(20));
    }

    // If driver exists, it kills it and assign null
    public static void killDriver(){
        if (driver != null){
            driver.quit();
            driver=null;
        }
    }
}
