package com.tianchi.staketableedit.utils;

import android.util.SparseArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilsOfPoi {
    private static Workbook createWorkbook() {
        //xls
        return new HSSFWorkbook();
    }

    private static Font creatFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_NORMAL);
        return font;
    }

    private static SparseArray<CellStyle> createBorderedStyle(Workbook workbook) {
        SparseArray<CellStyle> array = new SparseArray<>();
        //第一 二 三行标题
        CellStyle cellStyle0 = workbook.createCellStyle();
        Font font0 = creatFont(workbook);
        font0.setFontHeightInPoints((short) 14);
        font0.setFontName("黑体");
        cellStyle0.setFont(font0);
        cellStyle0.setAlignment(HorizontalAlignment.CENTER);
        cellStyle0.setVerticalAlignment(VerticalAlignment.CENTER);
        array.put(0, cellStyle0);

        //第 五 六 七 八 十行
        CellStyle cellStyle1 = workbook.createCellStyle();
        Font font1 = creatFont(workbook);
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 10);
        cellStyle1.setFont(font1);
        cellStyle1.setAlignment(HorizontalAlignment.LEFT);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setWrapText(true);
        setCellStyle(cellStyle1);
        array.put(1, cellStyle1);

        //第九行
        CellStyle cellStyle2 = workbook.createCellStyle();
        Font font2 = creatFont(workbook);
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 10);
        cellStyle2.setFont(font2);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setWrapText(true);
        setCellStyle(cellStyle2);
        array.put(2, cellStyle2);

        //第十一行
        CellStyle cellStyle3 = workbook.createCellStyle();
        //对齐
        cellStyle3.setAlignment(HorizontalAlignment.CENTER);
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
//        cellStyle3.setAlignment(HorizontalAlignment.CENTER);
//        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font3 = workbook.createFont();
        font3.setFontName("宋体");
        font3.setColor(Font.COLOR_NORMAL);
        cellStyle3.setFont(font3);
        cellStyle3.setWrapText(true);
        setCellStyle(cellStyle3);
        array.put(3, cellStyle3);

        //第 五 行
        CellStyle cellStyle4 = workbook.createCellStyle();
        Font font4 = creatFont(workbook);
        font4.setFontHeightInPoints((short) 10);
        font4.setFontName("宋体");
        cellStyle4.setFont(font1);
        cellStyle4.setAlignment(HorizontalAlignment.LEFT);
        cellStyle4.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle4.setWrapText(true);
        array.put(4, cellStyle4);

        //第 五 行
        CellStyle cellStyle5 = workbook.createCellStyle();
        cellStyle5.setBottomBorderColor((short) 13);
        cellStyle5.setWrapText(true);
        cellStyle5.setAlignment(HorizontalAlignment.LEFT);
        cellStyle5.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        array.put(5, cellStyle5);

        Font font5 = workbook.createFont();
        font5.setFontName("黑体"); // 设置字体为黑体
        font5.setFontHeightInPoints((short) 9); // 设置字体大小为9
        CellStyle cellStyle6 = workbook.createCellStyle();
        cellStyle6.setFont(font5); // 将字体设置到样式中
        cellStyle6.setAlignment(HorizontalAlignment.CENTER);
        cellStyle6.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置边框
        cellStyle6.setBorderTop(BorderStyle.THIN);       // 上边框
        cellStyle6.setBorderBottom(BorderStyle.THIN);   // 下边框
        cellStyle6.setBorderLeft(BorderStyle.THIN);     // 左边框
        cellStyle6.setBorderRight(BorderStyle.THIN);    // 右边框

        // 设置边框颜色
        cellStyle6.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle6.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle6.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle6.setRightBorderColor(IndexedColors.BLACK.getIndex());

        array.put(6,cellStyle6);
        Font font6 = workbook.createFont();
        font6.setFontName("宋体"); // 设置字体为黑体
        font6.setFontHeightInPoints((short) 9); // 设置字体大小为9
        CellStyle cellStyle7 = workbook.createCellStyle();
        cellStyle7.setFont(font6); // 将字体设置到样式中
        cellStyle7.setAlignment(HorizontalAlignment.CENTER);
        cellStyle7.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle7.setBorderTop(BorderStyle.THIN);       // 上边框
        cellStyle7.setBorderBottom(BorderStyle.THIN);   // 下边框
        cellStyle7.setBorderLeft(BorderStyle.THIN);     // 左边框
        cellStyle7.setBorderRight(BorderStyle.THIN);    // 右边框

        // 设置边框颜色
        cellStyle7.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle7.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle7.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle7.setRightBorderColor(IndexedColors.BLACK.getIndex());
        array.put(7,cellStyle7);

        return array;
    }

    /**
     * 设置单元格格式
     *
     * @Params :
     * @author :HaiRun
     * @date :2019/7/2  17:34
     */
    private static void setCellStyle(CellStyle cellStyle3) {
        //重新设置单元格的四边颜色
//        BorderStyle thin = BorderStyle.THIN;
        short blackColor_Index = IndexedColors.BLACK.getIndex();
        cellStyle3.setBottomBorderColor(blackColor_Index);
        cellStyle3.setBorderBottom(BorderStyle.THIN);
        cellStyle3.setTopBorderColor(blackColor_Index);
        cellStyle3.setBorderTop(BorderStyle.THIN);
        cellStyle3.setRightBorderColor(blackColor_Index);
        cellStyle3.setBorderRight(BorderStyle.THIN);
        cellStyle3.setRightBorderColor(blackColor_Index);
        cellStyle3.setBorderLeft(BorderStyle.THIN);
    }
    public static <T> void writeObjListToExcelPrime(List<T> list, String fileName,List<Double> height,List<Double> width) {
        FileInputStream is = null;
        FileOutputStream os = null;
        Workbook wb = null;
        String extString = fileName.substring(fileName.lastIndexOf("."));
        try {
            is = new FileInputStream(new File(fileName));
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {

            } else {
                wb = null;
            }
            if (wb != null) {
                SparseArray<CellStyle> borderedStyle = createBorderedStyle(wb);
                Sheet sheet = wb.getSheetAt(0);
                int numTotal=0;
                int size = list.size();
                for (int i=0;i<size;i++)
                    numTotal+= ((ArrayList<String>)list.get(i)).size();
                setHeaderFooter(sheet,numTotal);
                ArrayList<String> list1 = null;

                for (int i = 0; i < size; i++) {
                    list1 = (ArrayList<String>) list.get(i);
                    int size2 = list1.size();
                    //当前行宽行数默认是2
                    int rowMaxHNum=2;
                    Row row = sheet.createRow(i + 1);
                    int[] maxColumnWidths = new int[size2];

                    for (int j = 0; j < size2; j++) {
                        int rowHeight=list1.get(j).getBytes().length;
                        int columnWidthSheet=(sheet.getColumnWidth(j)-512);
                        //限制为12个字节为值列宽的最大数，不然太宽了
                        int cellWidth = Math.min(rowHeight,12) * 256;
                        //如果当前计算过的列宽大过之前的列宽，覆盖掉之前的列宽
                        maxColumnWidths[j]=Math.max(cellWidth,columnWidthSheet);
                        rowMaxHNum=Math.max(rowMaxHNum,rowHeight%12);

                        Cell cell = row.createCell(j);
                        cell.setCellStyle(borderedStyle.get(7));
                        cell.setCellValue(list1.get(j));
                    }
                    if (width.isEmpty()){
                        for (int colIndex=0;colIndex<maxColumnWidths.length;colIndex++){
                            int widthIndex=maxColumnWidths[colIndex]+512;
                            sheet.setColumnWidth(colIndex,Math.min(widthIndex,255*256));
                        }
                    }
                    //默认16磅行一行
                    if (height.size()==2)
                        row.setHeight((short)(height.get(1)*20));
                    else
                        row.setHeight((short)(10*20*rowMaxHNum));
                }
            }
            os = new FileOutputStream(fileName);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private static void setHeaderFooter(Sheet sheet,int numTotal) {
        // 获取标题和页脚对象
        Header header = sheet.getHeader();
        Footer footer = sheet.getFooter();
        header.setCenter("桩牌测绘成果表");

        // 设置页脚
        footer.setLeft("制表:");
        footer.setCenter("校对:");
        footer.setRight("检查:");

    }
    public static void initExcelPrime(String fileName, List<String> colNameP, String pointName,
                                      List<Double> pWidth,List<Double> height) {

        FileOutputStream outputStream = null;
        int[] maxColumnWidthPs=new int[colNameP.size()];
        Workbook workbook = null;
        try {
            workbook = createWorkbook();
            //设置表格的样式
            SparseArray<CellStyle> borderedStyle = createBorderedStyle(workbook);

            //建立新的point sheet对象 excel表单 P_ALL
            Sheet sheetPoint = workbook.createSheet(pointName);
            //在sheet里创建第一行，参数为行索引  0~65535直接
            Row row1 = sheetPoint.createRow(0);
            if (height.size()==2) {
                row1.setHeight((short) (height.get(0)*20));
            }
            //创建单元格 0~255
            for (int i = 0; i < colNameP.size(); i++) {
                Cell cell = row1.createCell(i);
                cell.setCellStyle(borderedStyle.get(6));
                cell.setCellValue(colNameP.get(i));
                int cellWidth=Math.min(colNameP.get(i).getBytes().length*256,9*256);
                maxColumnWidthPs[i]=Math.max(maxColumnWidthPs[i],cellWidth);
            }

            if (pWidth.isEmpty())
                for (int colIndex=0;colIndex<maxColumnWidthPs.length;colIndex++){
                    int width=maxColumnWidthPs[colIndex]+512;
                    sheetPoint.setColumnWidth(colIndex,Math.min(width,255*256));
                }
            else{
                for (int colIndex=0;colIndex<pWidth.size();colIndex++){
                    sheetPoint.setColumnWidth(colIndex, (int) Math.min(pWidth.get(colIndex)*256,255*256));
                }
            }

            //设置打印时的设置
            PrintSetup printSetup1=sheetPoint.getPrintSetup();
            printSetup1.setPaperSize(PrintSetup.A3_PAPERSIZE);
            printSetup1.setFitWidth((short) 1);
            // 设置为竖向打印（false表示纵向，true表示横向）
            printSetup1.setLandscape(false);
            // 设置打印内容水平居中和垂直居中
            sheetPoint.setHorizontallyCenter(true);
            sheetPoint.setMargin(Sheet.LeftMargin,0.25);
            sheetPoint.setMargin(Sheet.RightMargin,0.25);
            outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        } catch (Exception e) {

        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void initExcel(String fileName, List<String> colNameP, List<String> colNameL, String pointName, String lineName) {
        FileOutputStream outputStream = null;
        int[] maxColumnWidthPs=new int[colNameP.size()];
        int[] maxColumnWidthLs=new int[colNameL.size()];
        Workbook workbook = null;
        try {
            workbook = createWorkbook();
            //设置表格的样式
            SparseArray<CellStyle> borderedStyle = createBorderedStyle(workbook);

            //建立新的point sheet对象 excel表单 P_ALL
            Sheet sheetPoint = workbook.createSheet(pointName);
            //在sheet里创建第一行，参数为行索引  0~65535直接
            Row row1 = sheetPoint.createRow(0);
            //创建单元格 0~255
            for (int i = 0; i < colNameP.size(); i++) {
                Cell cell = row1.createCell(i);
                cell.setCellStyle(borderedStyle.get(2));
                cell.setCellValue(colNameP.get(i));

                int cellWidth=Math.min(colNameP.get(i).getBytes().length*256,9*256);
                maxColumnWidthPs[i]=Math.max(maxColumnWidthPs[i],cellWidth);

            }
            //建立新的point sheet对象 excel表单 L_ALL
            Sheet sheetLine = workbook.createSheet(lineName);
            //在sheet里创建第一行，参数为行索引  0~65535直接
            Row row = sheetLine.createRow(0);
            //创建单元格 0~255
            for (int i = 0; i < colNameL.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(borderedStyle.get(2));
                cell.setCellValue(colNameL.get(i));

                int cellWidth=Math.min(colNameL.get(i).getBytes().length*256,9*256);
                maxColumnWidthLs[i]=Math.max(maxColumnWidthLs[i],cellWidth);
            }

            for (int colIndex=0;colIndex<maxColumnWidthPs.length;colIndex++){
                int width=maxColumnWidthPs[colIndex]+512;
                sheetPoint.setColumnWidth(colIndex,Math.min(width,255*256));
            }
            for (int colIndex=0;colIndex<maxColumnWidthLs.length;colIndex++){
                int width=maxColumnWidthLs[colIndex]+512;
                sheetLine.setColumnWidth(colIndex,Math.min(width,255*256));
            }
            outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        } catch (Exception e) {

        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static <T> void writeObjListToExcel(int sheetIndex, List<T> list, String fileName) {
        FileInputStream is = null;
        FileOutputStream os = null;
        Workbook wb = null;
        String extString = fileName.substring(fileName.lastIndexOf("."));
        try {
            is = new FileInputStream(new File(fileName));
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {

            } else {
                wb = null;
            }
            if (wb != null) {
                SparseArray<CellStyle> borderedStyle = createBorderedStyle(wb);
                Sheet sheet = wb.getSheetAt(sheetIndex);
                ArrayList<String> list1 = null;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list1 = (ArrayList<String>) list.get(i);
                    int size2 = list1.size();
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < size2; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(borderedStyle.get(3));
                        cell.setCellValue(list1.get(j));
                    }
                }
            }
            os = new FileOutputStream(fileName);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
