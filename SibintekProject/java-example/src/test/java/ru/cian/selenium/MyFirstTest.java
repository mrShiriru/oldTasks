package ru.cian.selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by k.grigorchuk on 07.03.2018.
 */

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private String settingExcel = "settings.xls" ;


    @Before
    public void start (){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest() throws IOException, InterruptedException {

        Iterator<Cell> cells = InteractionExcel.readFromExcel(settingExcel);

        List<String> cells2 = new LinkedList<String>();

        while (cells.hasNext())
        {
            cells2.add(cells.next().toString());
        }


        driver.get("https://www.cian.ru/");
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > span > input")).sendKeys(cells2.get(0));
        driver.findElement(By.cssSelector("div.c-filters-field___2PXuG.undefined > div > div > span > input")).sendKeys(cells2.get(1));
        driver.findElement(By.cssSelector(" div.c-filters-form-buttons___2SF4G > button")).click();

        //заглушка
        sleep(10000);


        List<WebElement> metroList = driver.findElements(By.cssSelector("div.underground--2Yfq9 > a"));
        List<WebElement> priceList = driver.findElements(By.cssSelector("div.header--2lxlC"));
        List<WebElement> countRoomsList = driver.findElements(By.cssSelector("div.header--NemOm"));
        List<WebElement> areaRoomsList = driver.findElements(By.cssSelector("div.header--1WFWC"));
        List<WebElement> floorsList = driver.findElements(By.cssSelector("div.header--1ZTfS"));

        List<Advert> advertList = new ArrayList<Advert>();

        for (int i=0 ; i < countRoomsList.size(); i++){
            Advert advert = new Advert(metroList.get(i).getAttribute("textContent"), priceList.get(i).getAttribute("textContent"),
                    countRoomsList.get(i).getAttribute("textContent"),areaRoomsList.get(i).getAttribute("textContent"),
                    floorsList.get(i).getAttribute("textContent"));

            System.out.println("метро: "+ metroList.get(i).getAttribute("textContent") + " прайс: "+ priceList.get(i).getAttribute("textContent") +
                            "колличество комнат: " + countRoomsList.get(i).getAttribute("textContent") + "площадь: " + areaRoomsList.get(i).getAttribute("textContent")+
                    " этаж: " + floorsList.get(i).getAttribute("textContent"));

            advertList.add(advert);
        }

        //заглушка
        sleep(5000);


        InteractionExcel.writeIntoExcel("Adverts.xls",advertList );
    }

    @After
    public void finish(){
        driver.quit();
        driver = null;
    }



}

