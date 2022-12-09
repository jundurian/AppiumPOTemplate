package pages;

/*
This page contains functions that are specific to the app/screen under test.
For example: We need to test the Gallery app.
This page/class contains functions that are specific to the Gallery app, so they can be called by the test classes
inside the 'tests' package.
For that, if necessary, the parametrized functions created in the 'BasePage' class can be called.
 */

import core.BasePage;
import org.openqa.selenium.By;

// BasePage must be extended so this class can access its functions
public class GalleryPage extends BasePage {

    /*
    All the necessary data that will be passed to the functions can be created as a variable for better maintainability
     */
    private final String idGalleryTextField = "idGalleryTextField";
    private final String appPackage = "appPackage";
    private final String appActivity = "appActivity";


    /*
    These functions are created here but will be called by the test classes
     */
    public void insertTextInsideGallery(String text){
        write(By.id(idGalleryTextField), text);
    }

    public void openGallery(){
        openApp(appPackage,appActivity);
    }

}
