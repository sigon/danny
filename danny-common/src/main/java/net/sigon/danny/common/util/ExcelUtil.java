package net.sigon.danny.common.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 石光 部门：移动研发部-掌上京东
 *         Date: 14-4-18
 *         Time: 下午4:33
 *         To change this template use File | Settings | File Templates.
 * @copyright Copyright 2014-XXXX JD.COM All Right Reserved
 */

public class ExcelUtil {
    private final static Logger log = Logger.getLogger(ExcelUtil.class);
    /**
     * �ļ�Ŀ¼�ָ���峣����ֹlinux��windows��һ��
     */
    public final static String EXCEL_SUFFIX_2003 = ".xls";

    public final static int MAX_BATCH_NUMS = 65536;

    /**
     * ��ݵ�ǰ������������
     *
     * @param preffix ǰ׺
     * @param suffix  ��׺
     * @return
     */
    public static String createFileName(String preffix, String suffix) {
        //��װ�ļ����
        return new StringBuffer(preffix).append("-").
                append(DateUtil.date2String(new Date())).
                append("-").append(suffix).
                append(EXCEL_SUFFIX_2003).toString();
    }
    public static void exportExcel(String fileName, List<String[]> contents) throws Exception{
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile() ;
        }
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            int rowNum = 0;    //要写的行
            if(CollectionUtils.isNotEmpty(contents)){
                for(String[] strArr:contents){
                    putRow(sheet, rowNum ++, strArr);
                }
            }
            workbook.write();
            workbook.close();    //一定要关闭, 否则没有保存Excel
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    private static void putRow(WritableSheet ws, int rowNum, String[] cells) throws RowsExceededException, WriteException {
        for(int j=0; j<cells.length; j++) {//写一行
            Label cell = new Label(j, rowNum, ""+cells[j]);
            ws.addCell(cell);
        }
    }
    public static Integer getExcelRows(File file){
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(file);
            Sheet sheet = wb.getSheet(0);
            Integer rows = sheet.getRows();
            //workbook.write();
            wb.close();    //一定要关闭, 否则没有保存Excel
            return rows;
        } catch (Exception e) {
            log.error("ExcelUtil ERROR", e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }
    public static List parseExcel(File file, Map<String, String> map, Class clazz){
        //File file = new File(destFile);
        try {
            Workbook wb = Workbook.getWorkbook(file);
            Integer sheetCount = wb.getNumberOfSheets();
            List result = new ArrayList();
            for(Integer i = 0 ; i < sheetCount; i ++){
                Sheet sheet = wb.getSheet(i);
                result.addAll(parseSheet(sheet, map, clazz));
            }
            wb.close();    //一定要关闭, 否则没有保存Excel
            return result;
        } catch (Exception e) {
            log.error("ExcelUtil ERROR", e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    private static List parseSheet(Sheet sheet, Map<String, String> map, Class clazz){
        int columns = sheet.getColumns();
        List<String> fieldStr = new ArrayList<String>();
        List<String[]> dataList = new ArrayList<String[]>();
        for(Integer i = 0 ; i < columns; i ++){
            String field = sheet.getCell(i, 0).getContents();
            String f = map.get(field);
            fieldStr.add(f);
        }
        for(Integer i = 1 ; i < sheet.getRows(); i ++){
            String [] data = new String[columns];
            for(Integer j = 0 ; j < columns; j ++){
                data[j] = sheet.getCell(j, i).getContents();
            }
            dataList.add(data);
        }
        return ReflectUtils.parseArray(fieldStr, dataList, clazz);
    }
}
