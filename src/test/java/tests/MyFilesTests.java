package tests;

/*
This class, in fact, contains all the tests.
The idea here is that only really necessary code is done inside each test. Only specific stuff that are specific to
a test.
Everything else is done, and called, inside the Pages.
 */

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MyFilesPage;

import java.util.List;

// BaseTest must be extended so this class can access its functions

public class MyFilesTests extends BaseTest {

    // Is necessary to instance the specific page in order to access its functions
    private final MyFilesPage myFilesPage = new MyFilesPage();

    private List<String> resultsTitleList;

    @BeforeEach
    public void openMyFiles(){
        // With the page class instanced, just access the functions inside the tests
        myFilesPage.openMyFiles();
    }

    @Test
    public void MyFilesTest(){

        // With the page class instanced, just access the functions inside the tests
        myFilesPage.searchText("bb9");
        resultsTitleList = myFilesPage.getResultsTitleList();

        Assertions.assertTrue(resultsTitleList.contains("Bb9"));

    }
}
