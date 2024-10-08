package service;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageLabirint {


    public WebDriver driver;
public MainPageLabirint(){
    PageFactory.initElements(driver,this);
    this.driver = new ChromeDriver();
    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    driver.manage().window().maximize();
}
//https://www.labirint.ru/
    //Найти книги по Java
    //Добавить 5 книг в корзину
    //Проверить что в корзине 5 книг
@FindBy(xpath = "//*[@id=\"search-field\"]")
public static By actualSearch;


    public static By getTextActualSearch() {
        return actualSearch;
    }
}
