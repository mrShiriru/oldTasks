package ru.cian.selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//import static java.lang.Thread.sleep;

/**
 * Created by k.grigorchuk on 07.03.2018.
 */

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private String settingExcel = "settings.xls";
    private String foundAdverts = "div.wrapper--1Z8Nz";


    @Before
    public void start (){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest() throws IOException {

        Iterator<Cell> cells = InteractionExcel.readFromExcel(settingExcel);

        List<String> cells2 = new LinkedList<String>();

        while (cells.hasNext())
        {
            cells2.add(cells.next().toString());
        }


        driver.get("https://www.cian.ru/");

        //выбор типа объявления
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-operations___3Ya2n > span > button")).click();
        driver.findElement(By.xpath(".//*[text()='"+cells2.get(0)+"']/..")).click();

        //выбор типа жилья
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-property___sa2-5 > div > button")).click();
        driver.findElement(By.xpath(".//*[text()='"+cells2.get(1)+"']/..")).click();


        //цена от
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > span > input")).sendKeys(cells2.get(3));
        //цена до
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > div > span > input")).sendKeys(cells2.get(4));

        //выбор города
        driver.findElement(By.cssSelector("#c_filters-suggest_input")).clear();
        driver.findElement(By.cssSelector("#c_filters-suggest_input")).sendKeys(cells2.get(5));
        isElementPresent(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-region___1NHU5 > div > div > div > div > div:nth-child(1)"));
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-region___1NHU5 > div > div > div > div > div:nth-child(1)")).click();


        //кнопка Найти
        driver.findElement(By.cssSelector(" div.c-filters-form-buttons___2SF4G > button")).click();


        if (isElementPresent(By.cssSelector("div.offerDetails--1uFZ8")) == true){

        //поиск объявлений по запросу. Необходимо для исключения дополнительных предложений
        WebElement offerDetailsList = driver.findElement(By.cssSelector(foundAdverts));


        List<WebElement> offerDetailsList2 = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8"));

        List<Advert> advertList = new ArrayList<Advert>();

        List<WebElement> addressList = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8 > div:nth-child(1)"));
        List<WebElement> priceList = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8 > div:nth-child(2)"));
        List<WebElement> roomList = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8 > div:nth-child(3)"));
        List<WebElement> areaList = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8 > div:nth-child(4)"));
        List<WebElement> floorList = offerDetailsList.findElements(By.cssSelector("div.offerDetails--1uFZ8 > div:nth-child(5)"));


        for (int i=0 ; i < offerDetailsList2.size(); i++){

            String address = addressList.get(i).getAttribute("innerText");
            String price = priceList.get(i).getAttribute("innerText");
            String room = roomList.get(i).getAttribute("innerText");
            String area = areaList.get(i).getAttribute("innerText");
            String floor = floorList.get(i).getAttribute("innerText");

            Advert advert = new Advert(address,price,room,area,floor);

            System.out.println(address);

            advertList.add(advert);
        }

            InteractionExcel.writeIntoExcel("Adverts.xls",advertList );

        }

    }


    @Test
    public void tesSaveATableView() throws IOException, InterruptedException {
        Iterator<Cell> cells = InteractionExcel.readFromExcel(settingExcel);

        List<String> cells2 = new LinkedList<String>();

        while (cells.hasNext()) {
            cells2.add(cells.next().toString());
        }


        driver.get("https://www.cian.ru/");

        //выбор типа объявления
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-operations___3Ya2n > span > button")).click();
        driver.findElement(By.xpath(".//*[text()='" + cells2.get(0) + "']/..")).click();

        //выбор типа жилья
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-property___sa2-5 > div > button")).click();
        driver.findElement(By.xpath(".//*[text()='" + cells2.get(1) + "']/..")).click();


        //цена от
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > span > input")).sendKeys(cells2.get(3));
        //цена до
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > div > span > input")).sendKeys(cells2.get(4));

        //выбор города
        driver.findElement(By.cssSelector("#c_filters-suggest_input")).clear();
        driver.findElement(By.cssSelector("#c_filters-suggest_input")).sendKeys(cells2.get(5));
        isElementPresent(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-region___1NHU5 > div > div > div > div > div:nth-child(1)"));
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.c-filters-field-region___1NHU5 > div > div > div > div > div:nth-child(1)")).click();


        //кнопка Найти
        driver.findElement(By.cssSelector(" div.c-filters-form-buttons___2SF4G > button")).click();


        if (isElementPresent(By.cssSelector("div.offerDetails--1uFZ8")) == true) {
            driver.findElement(By.cssSelector("#frontend-serp > div > div.wrapper--3kCWf > div.tabs--m84GG > div > a:nth-child(3)")).click();

            isElementPresent(By.cssSelector("#content > div > div > div.js-serp-list > div.serp-list-container > div.cf_serp_footer > div:nth-child(1) > div > div:nth-child(2) > span > a > span"));
            driver.findElement(By.cssSelector("#content > div > div > div.js-serp-list > div.serp-list-container > div.cf_serp_footer > div:nth-child(1) > div > div:nth-child(2) > span > a > span")).click();

            //sleep(5000);
        }
    }


    public boolean isElementPresent(By locator){
        try{
            wait.until((WebDriver d )-> d.findElement(locator));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }


    @After
    public void finish(){
        driver.quit();
        driver = null;

    }
}
