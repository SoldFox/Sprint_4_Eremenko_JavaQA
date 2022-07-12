package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {
    private WebDriver driver;
    public final static String URL_YANDEX = "https://qa-scooter.praktikum-services.ru/";
    public HomePageScooter (WebDriver driver) {
        this.driver = driver;
    }

    // Массив с ожидаемыми ответами на вопросы из столбца "Вопросы о важном"
    public static final String [] ANSWERS = {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    // Массив локаторов для вопросов из столбца "Вопросы о важном"
    private final By [] LOCATORS_QUESTIONS = {By.xpath(".//div[@class='accordion']/div[1]"),
            By.xpath(".//div[@class='accordion']/div[2]"),
            By.xpath(".//div[@class='accordion']/div[3]"),
            By.xpath(".//div[@class='accordion']/div[4]"),
            By.xpath(".//div[@class='accordion']/div[5]"),
            By.xpath(".//div[@class='accordion']/div[6]"),
            By.xpath(".//div[@class='accordion']/div[7]"),
            By.xpath(".//div[@class='accordion']/div[8]")
    };

    // Массив локаторов для ответов из столбца "Вопросы о важном"
    private final By [] LOCATORS_ANSWERS = {By.xpath(".//div[@id='accordion__panel-0']/p"),
            By.xpath(".//div[@id='accordion__panel-1']/p"),
            By.xpath(".//div[@id='accordion__panel-2']/p"),
            By.xpath(".//div[@id='accordion__panel-3']/p"),
            By.xpath(".//div[@id='accordion__panel-4']/p"),
            By.xpath(".//div[@id='accordion__panel-5']/p"),
            By.xpath(".//div[@id='accordion__panel-6']/p"),
            By.xpath(".//div[@id='accordion__panel-7']/p")
    };

    //Верхняя кнопка заказать (маленькая)
    private final By orderButtonSmall = By.className("Button_Button__ra12g");

    //Нижняя кнопка заказать (большая)
    private final By orderButtonBig = By.xpath( ".//div[@class = 'Home_FinishButton__1_cWm']/button");

    // Ожидание загрузки страницы
    public void waitToLoadPage () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(LOCATORS_QUESTIONS[0]));
    }

    // Скрол к указанному вопросу из столбца "Вопросы о важном" с ожиданием до полного его отображения
    public void scrollToAccordionButtons (int locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(LOCATORS_QUESTIONS[locator]));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(LOCATORS_QUESTIONS[locator]));
    }

    // Клик по раскрывабщей кнопке из столбца "Вопросы о важном"
    public void clickToAccordionButton (int locator) {
        driver.findElement(LOCATORS_QUESTIONS[locator]).click();
    }

    // Объединение методов скрола и клика
    public void scrollAndClickToAccordionButton(int locator) {
        scrollToAccordionButtons(locator);
        clickToAccordionButton(locator);
    }

    // Метод возвращающий текст ответа из столбца "Вопросы о важном" (локаторы берем из массива)
    public String getAccordionButtonsText(int number) {
        return driver.findElement(LOCATORS_ANSWERS[number]).getText();
    }

    // Скрол к нижней кнопке "Заказать" (большой)
    public void scrollToBigButton () {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBig));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonBig));
    }

    // Клик на верхнюю кнопку "Заказать" (маленькую)
    public void clickToOrderButtonSmall (){
        driver.findElement(orderButtonSmall).click();
    }

    // Клик на нижнюю кнопку "Заказать" (большую)
    public void clickToOrderButtonBig (){
        driver.findElement(orderButtonBig).click();
    }
}
