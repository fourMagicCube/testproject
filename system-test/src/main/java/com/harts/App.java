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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Workbook workbook = App.readExcel("/home/hcl/下载/运费汇总分析.xlsx");
        Sheet sheet = workbook.getSheetAt(1);
        int count = 274;
        Row row = null;
        Cell cell = null;
        XSSFWorkbook cityWorkbook = new XSSFWorkbook();
        Sheet sheet2 = cityWorkbook.createSheet();
        String cellstring = null;
        System.out.println(isMergedRegion(sheet,3,11));
        System.out.println(getMergedRegionValue(sheet,3,11));
        /*for (int i = 0; i < count; i++) {
            row = sheet.getRow(i);
            cell = row.getCell(11);
            cellstring = getCellFormatValue(cell);

        }*/
        /*for (int i = 2; i < count; i++) {
            row = sheet.getRow(i);
            cell = row.getCell(12);
            cellstring = getCellFormatValue(cell);
            sheet2.createRow(i)
                    .createCell(0)
                    .setCellValue(CityUtils.findObjectProvince(cellstring)+cellstring+"市");

        }*/
//        writeExcel("/home/hcl/文档/test.xlsx", cityWorkbook);
        // System.out.println(getCellFormatValue(cell));

        // System.out.println(CityUtils.findObjectProvince("武汉"));
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {

                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellFormatValue(fCell);
                }
            }
        }

        return null;
    }

    /**
     * 判断合并了行
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private static boolean isMergedRow(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row == firstRow && row == lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断sheet页中是否含有合并单元格
     * @param sheet
     * @return
     */
    private boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0 ? true : false;
    }


    /**
     * 合并单元格
     * @param sheet
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     */
    private static void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
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