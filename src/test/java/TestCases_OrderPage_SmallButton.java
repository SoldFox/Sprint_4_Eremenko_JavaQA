import PageObjects.HomePageScooter;
import PageObjects.OrderPageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static PageObjects.HomePageScooter.URL_YANDEX;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestCases_OrderPage_SmallButton {
    private WebDriver driver;

    private final String FNAME;
    private final String SNAME;
    private final String ADDR;
    private final String METRO;
    private final String NUMBER;
    private final String DATE;
    private final String TIME;
    private final String COMM;

    public TestCases_OrderPage_SmallButton(String fname, String sname, String addr, String metro, String number, String date, String time, String comm) {
        this.FNAME = fname;
        SNAME = sname;
        ADDR = addr;
        METRO = metro;
        NUMBER = number;
        DATE = date;
        TIME = time;
        COMM = comm;
    }

    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {"Валентина", "Воробушкина", "ул. Индюкова д.25", "Черкизовская", "88005553535","16.07.2022", "сутки", "Приветы проверяющим =)"},
                {"Георгий", "Пензенский", "ул. Паково д.2", "Лубянка", "880055535350","12.07.2022", "трое суток", ""}
        };
    }

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver" ,  "E:/programs/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_YANDEX);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testOrderWithSmallButton() {

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.clickToOrderButtonSmall();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.orderPageFirstInput(FNAME,SNAME,ADDR,METRO,NUMBER);
        objOrderPage.orderPageSecondInput(DATE,TIME,COMM);
        objOrderPage.tapToButtonYes();

        assertTrue("Window Order Completed - not found!",objOrderPage.waitForWindowOrderCompleted());
    }
}
