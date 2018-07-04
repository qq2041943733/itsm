package com.run.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Description excel处理
 * @author
 * @Date 2018年6月21日 下午2:20:01
 * @version 1.0.0
 */
public class ExcelDeal {

    public static List<String> getValue(String path, int startRowPositon, int startCellPositon) throws Exception {
        List<String> singleCell = getPoistData(path, startRowPositon, startCellPositon);
        return singleCell;
    }

    public static XSSFWorkbook readExcel(String path) throws Exception {
        File file = new File(path);
        return new XSSFWorkbook(file);
    }

    public static List<String> getPoistData(String path, int startRowPositon, int startCellPositon) throws Exception {
        XSSFWorkbook readExcel = readExcel(path);
        XSSFSheet sheet = getSheet(readExcel, 0);// 读第一个sheet
        return getData(sheet, startRowPositon, startCellPositon);
    }

    private static List<String> getData(XSSFSheet sheet, int startRowPositon, int startCellPositon) {
        List<String> list = new ArrayList<String>();
        for (short i = (short) (startRowPositon - 1); i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(startCellPositon - 1);
            list.add(getValue(cell));
        }
        return list;
    }

    public static XSSFSheet getSheet(XSSFWorkbook workBook, int sheetPoistIndex) {
        return workBook.getSheetAt(sheetPoistIndex);
    }

    @SuppressWarnings("deprecation")
    public static String getValue(XSSFCell cell) {
        switch (cell.getCellType()) {
        case XSSFCell.CELL_TYPE_NUMERIC:
            return cell.getNumericCellValue() + "";
        case XSSFCell.CELL_TYPE_STRING:
            return cell.getRichStringCellValue().getString();
        case XSSFCell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        }
        return "";
    }

    public static void main(String[] args) {
        String path = "F:/goodsId.xlsx";
        int startRowPositon = 2;
        int startCellPositon = 2;
        try {
            List<String> value = getValue(path, startRowPositon, startCellPositon);
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
