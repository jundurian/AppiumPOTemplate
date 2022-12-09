package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/*
This page contains functions that are specific to the app/screen under test.
For example: We need to test the MyFiles app.
This page/class contains functions that are specific to the MyFiles app, so they can be called by the test classes
inside the 'tests' package.
For that, if necessary, the parametrized functions created in the 'BasePage' class can be called.
 */
public class MyFilesPage extends BasePage {

    private final String appPackage = "appPackage";
    private final String appActivity = "appPackage";
    private final String idSearchIcon = "idSearchIcon";
    private final String idSearchBar = "idSearchBar";
    private final List<String> listResult = new ArrayList<>();
    private final String elementsID = "elementsID";


    /*
    These functions are created here but will be called by the test classes
     */
    public void openMyFiles(){
        openApp(appPackage,appActivity);
    }


    public void searchText(String text){
        click(By.id(idSearchIcon));
        write(By.id(idSearchBar),text);
    }

    /*
    Example of function that do not use any parameterized function of the BasePage class, but still is
    specific to the MyFiles app, so it is created inside the MyFiles Page
     */
    public List<String> getResultsTitleList(){

        List<WebElement> elementsById = findElementsByID(elementsID);
        for (WebElement element: elementsById){

            listResult.add(element.getText());
        }

        return listResult;

    }
}
