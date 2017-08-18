package com.hsd.common.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private enum ReturnTypeEnum {
        MAP, LIST;

        private ReturnTypeEnum() {
        }
    }

    public static Map<String, List> readExcelIsMap(String filePath, boolean title) throws Exception {
        return readExcel(filePath, title, ReturnTypeEnum.MAP);
    }

    public static Map<String, List> readExcelIsList(String filePath, boolean title) throws Exception {
        return readExcel(filePath, title, ReturnTypeEnum.LIST);
    }

    private static Map<String, List> readExcel(String filePath, boolean title, ReturnTypeEnum returnType) throws Exception {
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(filePath);

            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }

            Map<String, List> result = new HashMap<>();
            result.put("titles", new ArrayList());
            result.put("datas", new ArrayList());

            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                List sheetList = new ArrayList();//对应sheet页
                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    Row row = sheet.getRow(j);
                    if (row == null) continue;
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    if (title) {
                        if (j == 0) {//第一行是标题行
                            for (int k = 0; k < cellSize; k++) {
                                Cell cell = row.getCell(k);
                                result.get("titles").add(cell.toString());
                            }
                        } else {//其他行是数据行
                            sheetList.add(readSheet(returnType, row));
                        }
                    } else {
                        sheetList.add(readSheet(returnType, row));
                    }
                }
                result.get("datas").addAll(sheetList);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    private static Object readSheet(ReturnTypeEnum returnType, Row row) {
        int cellSize = row.getLastCellNum();
        switch (returnType) {
            case MAP: {//map
                Map rowMap = new HashMap();//对应一个数据行
                for (int k = 0; k < cellSize; k++) {
                    Cell cell = row.getCell(k);
                    Object value = null;
                    if (cell != null) value = getCell(cell);
                    rowMap.put(k, value);
                }
                return rowMap;
            }
            case LIST: {//list
                List rowList = new ArrayList();//对应一个数据行
                for (int k = 0; k < cellSize; k++) {
                    Cell cell = row.getCell(k);
                    Object value = null;
                    if (cell != null) value = getCell(cell);
                    rowList.add(value);
                }
                return rowList;
            }
        }
        return null;
    }

    private static Object getCell(Cell cell) {
        Object obj = null;
        try {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    obj = new DecimalFormat("#").format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    obj = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    obj = cell.getBooleanCellValue();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    obj = cell.getCellFormula();
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    obj = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    obj = "#Error#";
                    break;
                default:
                    obj = cell.toString();
                    break;
            }
        } catch (Throwable e) {
            obj = cell.toString();
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Map<String, List> result = readExcelIsList("d:/org_user.xlsx", true);
        result.get("datas").forEach(map -> {
            System.out.println(map);
        });
    }
}
