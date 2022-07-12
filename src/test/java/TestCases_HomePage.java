import PageObjects.HomePageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static PageObjects.HomePageScooter.URL_YANDEX;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestCases_HomePage {

    private WebDriver driver;
    private final int number;

    public TestCases_HomePage (int number) {
        this.number = number;
    }

    // Подстовляем все 8 вопросов и ответов (через индексы массивов)
    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {0},{1},{2},{3},
                {4},{5},{6},{7}
        };
    }

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver" ,  "E:/programs/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_YANDEX);
    }

    @Test
    public void testAccordionButton() {
        HomePageScooter objHomePage = new HomePageScooter(driver);

        objHomePage.waitToLoadPage();
        objHomePage.scrollAndClickToAccordionButton(number);

        assertEquals("Text not found or doesn't match",objHomePage.ANSWERS[number], objHomePage.getAccordionButtonsText(number));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}

