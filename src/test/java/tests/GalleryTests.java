package tests;

/*
This class, in fact, contains all the tests.
The idea here is that only really necessary code is done inside each test. Only specific stuff that are specific to
a test.
Everything else is done, and called, inside the Pages.
 */

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GalleryPage;

// BaseTest must be extended so this class can access its functions
public class GalleryTests extends BaseTest {

    // Is necessary to instance the specific page in order to access its functions
    private final GalleryPage galleryPage = new GalleryPage();

    @Test
    public void galleryTest(){

        // With the page class instanced, just access the functions inside the tests
        galleryPage.openGallery();
        galleryPage.insertTextInsideGallery("Text to insert");

        Assertions.assertTrue(true);
    }
}
