package ru.cian.selenium;

/**
 * Created by k.grigorchuk on 07.03.2018.
 */



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.interactions.SourceType;


public class InteractionExcel {

    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(String file, List<Advert> list) throws FileNotFoundException, IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Result Advert");

        int rownum = 0;
        Cell cell;

        // Нумерация начинается с нуля
        Row row = sheet.createRow(rownum);


        for(Advert adv : list){
            rownum++;
            row = sheet.createRow(rownum);
            // Metro (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(adv.getMetro());
            // Price (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(adv.getPrice());
            // CountRooms (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(adv.getCountRooms());
            // AreaRooms (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(adv.getAreaRooms());
            // Floors (D)
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(adv.getFloors());
        }

        workbook.write(new FileOutputStream(file));
        workbook.close();

    }



    @SuppressWarnings("deprecation")
    public static Iterator<Cell> readFromExcel(String file) throws IOException{
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Settings");
        HSSFRow row = myExcelSheet.getRow(1);

        Iterator<Cell> cell = row.iterator();

        myExcelBook.close();
        return cell;


    }

}
