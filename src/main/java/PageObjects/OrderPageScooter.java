package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPageScooter {
    private WebDriver driver;

    public OrderPageScooter (WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы  1ой страница заказа
    //Поле Имя
    private final By firstName = By.xpath(".//input[contains(@placeholder,'Имя')]");

    //Поле Фамилия
    private final By secondName = By.xpath(".//input[contains(@placeholder,'Фамилия')]");

    //Поле Адрес
    private final By address = By.xpath(".//input[contains(@placeholder,'Адрес')]");

    //Поле Станция
    private final By metroStation = By.xpath(".//input[contains(@placeholder,'Станция')]"); // вводим сюда значение

    //Поле Телефон
    private final By phone = By.xpath(".//input[contains(@placeholder,'Телефон')]");

    //Кнопка далее
    private final By firstButtonNext = By.xpath(".//button[contains(text(),'Далее')]");


    //Локаторы 2ой страницы заказа
    //Когда привезти самокат (пока через Div -class )
    private final By dateWhenToBring = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //Срок аренды (через Div - class)
    private final By rentalDate = By.className("Dropdown-placeholder");

    // Цвет самоката
    private final By colorBlack = By.id("black");
    private final By colorGrey = By.id("grey");

    //Коммент
    private final By comment= By.xpath("//input[@placeholder='Комментарий для курьера']");

    //Кнопка далее
    private final By secondButtonNext = By.xpath("//div[@class ='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка ДА
    private final By buttonYes = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //Окно - заказ оформлен
    private final By windowOrderCompleted = By.xpath(".//div[starts-with(@class, 'Order_ModalHeader')]");


    // Методы для взаимодействия с локаторами
    // Вводим значение в поле Имя
    public void inputToFirstName (String fName) {
        driver.findElement(firstName).click();
        driver.findElement(firstName).sendKeys(fName);
    }

    // Вводим значение в поле Фамилия
    public void inputToSecondName (String sName) {
        driver.findElement(secondName).click();
        driver.findElement(secondName).sendKeys(sName);
    }

    // Вводим значение в поле Адрес
    public void inputToAddress (String addr) {
        driver.findElement(address).click();
        driver.findElement(address).sendKeys(addr);
    }

    // Вводим значение в поле Метро
    public void inputToMetroStation (String metro) {
        driver.findElement(metroStation).click();
        driver.findElement(metroStation).sendKeys(metro);
        driver.findElement(By.xpath(".//div[text()='" + metro + "']")).click();
    }

    // Вводим значение в поле Телефон
    public void inputToPhone (String number) {
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys(number);
    }

    // Тапаем по кнопке Далее
    public void tapToFirstButtonNext () {
        driver.findElement(firstButtonNext).click();
    }

    // Объединяем все методы для первой страницы ввода значений вместе с тапом по кнопке в 1 метод
    public void orderPageFirstInput (String fName,String sName,String addr,String metro, String number) {
        inputToFirstName(fName);
        inputToSecondName(sName);
        inputToAddress(addr);
        inputToMetroStation(metro);
        inputToPhone(number);
        tapToFirstButtonNext();
    }

    // Вводим значение в поле Когда привезти самокат
    public void inputDateToBring (String date) {
        driver.findElement(dateWhenToBring).click();
        driver.findElement(dateWhenToBring).sendKeys(date);
        driver.findElement(dateWhenToBring).sendKeys(Keys.ENTER);

    }

    // Вводим значение в поле Срок аренды
    public void inputToRentalDate (String time) {
        driver.findElement(rentalDate).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + time + "']")).click();
    }

    // Тапаем на чек-бокс цвета самоката (Черный)
    public void tapOnBlackCheckBox () {
        driver.findElement(colorBlack).click();
    }

    // Вводим значение в поле Комментарий
    public void inputToComment (String comm) {
        driver.findElement(comment).click();
        driver.findElement(comment).sendKeys(comm);
    }

    // Тапаем по кнопке заказать
    public void tapToSecondButtonNext () {
        driver.findElement(secondButtonNext).click();
    }

    // Объединяем все методы для второй страницы ввода значений вместе с тапом по кнопке в 1 метод
    public void orderPageSecondInput (String date,String time,String comm) {
        inputDateToBring(date);
        inputToRentalDate(time);
        inputToComment(comm);
        tapToSecondButtonNext();
    }

    // Тапаем по кнопке ДА
    public void tapToButtonYes () {
        driver.findElement(buttonYes).click();
    }

    // Проверяем что появилось окна подтверждения заказа
    public boolean waitForWindowOrderCompleted () {
        return driver.findElement(windowOrderCompleted).getText().contains("Заказ оформлен");
    }
}

