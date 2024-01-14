package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();

        Map<String, List<XSSFRow>> rowMap = new HashMap<String, List<XSSFRow>>();

        try {
            // 获取文件流
//            FileInputStream fileInputStream = new FileInputStream("/Users/weixiurui/Downloads/temps/kjjh.xlsx");
            FileInputStream fileInputStream = new FileInputStream("/home/greatwall/桌面/网点机构差错情况统计表-非警示类/temps/yyfxjc.xlsx");

            // 获取工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

            // 获取sheet
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            // 获取数据
            for (int i = 2; i < xssfSheet.getLastRowNum(); i++) {
                // 获取第i行数据
                XSSFRow xssfRow = xssfSheet.getRow(i);
                String key = xssfRow.getCell(1).toString() + "-" + xssfRow.getCell(2).toString();
                List<XSSFRow> rowList = rowMap.get(key);
                if (rowList == null) {
                    rowList = new ArrayList<XSSFRow>();
                }
                rowList.add(xssfRow);
                rowMap.put(key, rowList);
            }

            // 关闭读入流
            fileInputStream.close();

            // 循环消费数据，输入文件
            for (String key : rowMap.keySet()) {
                // 生成excel文件
                main.createExcel(key, rowMap.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成excel文件
     * @param rowList
     */
    public void createExcel(String key, List<XSSFRow> rowList) {
        try {
            // 创建一个工作簿
            XSSFWorkbook workbook = new XSSFWorkbook("/home/greatwall/桌面/网点机构差错情况统计表-非警示类/temps/template.xlsx");
            // 创建一个sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            workbook.setSheetName(0, key);
            workbook.removeSheetAt(1);
            workbook.removeSheetAt(2);

            for (int i = 0; i < rowList.size(); i++) {
                copyRow(rowList.get(i), sheet.createRow(i+2));
            }

            //生成Excel文件（使用io流）,注：文件后缀要与excel版本对应
//            FileOutputStream fileOutputStream = new FileOutputStream("/Users/weixiurui/Downloads/temps/kjjh/网点机构差错情况统计表（非警示类）-kjjh-" + key + ".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream("/home/greatwall/桌面/网点机构差错情况统计表-非警示类/temps/yyfxjc/网点机构差错情况统计表（非警示类）-yyfxjc-" + key + ".xlsx");
            //写入
            workbook.write(fileOutputStream);
            //文件写完后关闭流
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制行
     * @param sourceRow    来源行
     * @param targetRow    目标行
     */
    public void copyRow(XSSFRow sourceRow, XSSFRow targetRow) {
        // 复制源Excel文件的一行数据到目标Excel文件的一行
        for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
            Cell sourceCell = sourceRow.getCell(j);
            Cell targetCell = targetRow.createCell(j);
            if (sourceCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                targetCell.setCellValue(sourceCell.getNumericCellValue());
            } else {
                targetCell.setCellValue(sourceCell.getStringCellValue());
            }
        }
    }
}