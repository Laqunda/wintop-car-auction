package com.wintop.ms.carauction.util.utils;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-03
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ClassName: ExcelUtil
 * @Description: Excel导入导出工具类
 * @author 周宣
 * @date 2016-11-8 下午7:16:11
 *
 */
public class ExcelUtil {
    private static final Logger logger = Logger.getLogger(ExcelUtil.class);

    /**
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     * @param @param is
     * @param @param excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException{
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        }else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     * @param @param workbook
     * @param @param sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     */
    public static Sheet getSheet(Workbook workbook, int sheetIndex){
        return workbook.getSheetAt(0);
    }

    /**
     * @Title: importDataFromExcel
     * @Description: 将sheet中的数据保存到list中，
     * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
     * 2、在action调用此方法时，需声明
     *     private File excelFile;上传的文件
     *     private String excelFileName;原始文件的文件名
     * 3、页面的file控件name需对应File的文件名
     * @param @param vo javaBean
     * @param @param is 输入流
     * @param @param excelFileName
     * @param @return
     * @return List<Object>
     * @throws
     */
    public static  List<T> importDataFromExcel(T vo, InputStream is, String excelFileName){
        List<T> list = new ArrayList<T>();
        try {
            //创建工作簿
            Workbook workbook = ExcelUtil.createWorkbook(is, excelFileName);
            //创建工作表sheet
            Sheet sheet = ExcelUtil.getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                Field[] fields = vo.getClass().getDeclaredFields();
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue()?"":cell.getStringCellValue();

                    Field field = fields[index];
                    String fieldName = field.getName();
                    String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method setMethod = vo.getClass().getMethod(methodName, new Class[]{String.class});
                    setMethod.invoke(vo, new Object[]{value});
                    index++;
                }
                if (ExcelUtil.isHasValues(vo)) {//判断对象属性是否有值
                    list.add(vo);
                    vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                }

            }
        } catch (Exception e) {
            logger.error(e);
        }finally{
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                logger.error(e2);
            }
        }
        return list;

    }

    /**
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     * @param @param object
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean isHasValues(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && "".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                logger.error(e);
            }

        }
        return flag;

    }

    public static  <T> void exportDataToExcel(List<T> list,String[] headers,String title,OutputStream os){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = ExcelUtil.getCellStyle(workbook);
        //生成一个字体
        HSSFFont font =ExcelUtil.getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+1);
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName,new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    if(null == value)
                        value ="";
                    cell.setCellValue(value.toString());

                }
            } catch (Exception e) {
                logger.error(e);
            }
        }

        try {
            workbook.write(os);
        } catch (Exception e) {
            logger.error(e);
        }finally{
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }

    }

    /**
     * @Title: getCellStyle
     * @Description: 获取单元格格式
     * @param @param workbook
     * @param @return
     * @return HSSFCellStyle
     * @throws
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GOLD.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;
    }

    /**
     * @Title: getFont
     * @Description: 生成字体样式
     * @param @param workbook
     * @param @return
     * @return HSSFFont
     * @throws
     */
    public static HSSFFont getFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short)12);
        return font;
    }

    public boolean isIE(HttpServletRequest request){
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0?true:false;
    }

    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

    public static HSSFWorkbook createStartExcel(String sheetName,String[] headers){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet("sheet");
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(20);
        //生成一个样式
        HSSFCellStyle style = ExcelUtil.getCellStyle(workbook);
        //生成一个字体
        HSSFFont font =ExcelUtil.getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);
        //表格第一行的字体样式
        HSSFCellStyle headStyle = ExcelUtil.getHeadCellStyle(workbook);
        //设置合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) headers.length-1));
        //表格第一行
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeightInPoints(25);
        HSSFCell cell1 = row1.createCell(0);   //创建一个单元格
        cell1.setCellStyle(headStyle);
        cell1.setCellValue(sheetName);
        cell1.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        cell1.getCellStyle().setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);


        //生成表格标题
        HSSFRow row2 = sheet.createRow(1);
        row2.setHeight((short)300);
        HSSFCell cell = null;
        for (int i = 0; i < headers.length; i++) {
            cell = row2.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        return workbook;
    }

    public static HSSFCellStyle getHeadCellStyle(HSSFWorkbook workbook){
        //表格第一行的字体样式
        HSSFCellStyle style1 = workbook.createCellStyle();
        HSSFFont font2 = workbook.createFont();
        font2.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font2.setFontHeightInPoints((short) 14);                 //字体大小
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        //把字体应用到当前的样式
        style1.setFont(font2);
        return style1;
    }
}