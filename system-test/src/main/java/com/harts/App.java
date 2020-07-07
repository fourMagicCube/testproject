package com.harts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.harts.utils.CityUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Workbook workbook = App.readExcel("/home/harts/Documents/运费汇总分析.xlsx");
        Sheet sheet = workbook.getSheetAt(1);
        int count = 274;
        Row row = null;
        Cell cell = null;
        XSSFWorkbook cityWorkbook = new XSSFWorkbook();
        Sheet sheet2 = cityWorkbook.createSheet();
        String cellstring = null;
        for (int i = 2; i < count; i++) {
            row = sheet.getRow(i);
            cell = row.getCell(12);
            cellstring = getCellFormatValue(cell);
            sheet2.createRow(i)
                    .createCell(0)
                    .setCellValue(CityUtils.findObjectProvince(cellstring)+cellstring+"市");

        }
        writeExcel("/home/harts/Documents/test.xlsx", cityWorkbook);
        // System.out.println(getCellFormatValue(cell));

        // System.out.println(CityUtils.findObjectProvince("武汉"));
    }

    public static Workbook readExcel(String fileName) {
        Workbook wb = null;
        if (fileName == null) {
            return null;
        }
        String extString = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                System.out.println("进入了xssfWorkbook");
                return wb = new XSSFWorkbook(is);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static void writeExcel(String fileName, XSSFWorkbook cityWorkbook) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
            cityWorkbook.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * format表格内容
     *
     * @param cell
     * @return
     * @author 吕小布 2018年10月26日
     */
    public static String getCellFormatValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }
        return cellValue;
    }
}