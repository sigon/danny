package org.danny.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date: 2011-4-29
 * Time: 11:58:50.
 */
public class DateUtil {
    private final static Logger log = LogManager.getLogger(DateUtil.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    public static final String DEFAULT_DATE_FORMATE = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE = "yyyy-MM-dd";

    /**
     * ȡ��ĳ����ʱ����ض���ʾ��ʽ���ַ�
     *
     * @param format ʱ���ʽ
     * @param date   ĳ���ڣ�Date��
     * @return ĳ���ڵ��ַ�
     */
    public static synchronized String getDate2Str(String format, Date date) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date);
    }

    /**
     * ���ض���ʽ��ʱ���ַ�ת��ΪDate����
     *
     * @param format ʱ���ʽ
     * @param str    ĳ���ڵ��ַ�
     * @return ĳ���ڣ�Date��
     */
    public static synchronized Date getStrToDate(String format, String str) {
        simpleDateFormat.applyPattern(format);
        ParsePosition parseposition = new ParsePosition(0);
        return simpleDateFormat.parse(str, parseposition);
    }

    public static String date2String(Date date) {
        return getDate2Str(DEFAULT_DATE_FORMATE, date);
    }

    public static String date2String(Date date, String format) {
        return getDate2Str(format, date);
    }

    /**
     * ����ַ��Ƿ�Ϊ����
     *
     * @param dateTime ʱ���ַ�
     * @param pattern  Eg "yyyy-MM-dd"
     * @return ���ؽ��
     */
    public static boolean isDateTime(String dateTime, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date dt = df.parse(dateTime, pos);
        return !(dt == null);
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            xmlCalendar = dtf.newXMLGregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND), calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));
        } catch (Exception e) {
            log.error("getXMLGregorianCalendar error!", e);
        }
        return xmlCalendar;
    }

    public boolean passTime(Date tempDate, int hour) {
        return !(tempDate == null || hour <= 0) && tempDate.before(getLimitDate(hour));
    }

    /**
     * �õ�nСʱǰʱ��
     *
     * @param hour Сʱ��
     * @return Date
     */
    private Date getLimitDate(int hour) {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - hour * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return cl.getTime();
    }

    /**
     * ��������ʱ�������������yyyy-MM-dd��ʽList����
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<String> dateBetween(Date startDate, Date endDate) throws Exception {
        List list = new ArrayList<String>();
        simpleDateFormat.applyPattern(DATE_FORMATE);
        if (!startDate.equals(endDate)) {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            startCal.setTime(getStrToDate(DATE_FORMATE, getDate2Str(DATE_FORMATE, startDate)));
            endCal.setTime(getStrToDate(DATE_FORMATE, getDate2Str(DATE_FORMATE, endDate)));
            while (startCal.before(endCal)) {
                list.add(simpleDateFormat.format(startCal.getTime()));
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
            list.add(simpleDateFormat.format(startCal.getTime()));
        } else {
            list.add(simpleDateFormat.format(startDate.getTime()));
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(DateUtil.getStrToDate(DateUtil.DATE_FORMATE, "aaa"));
//        System.out.println(DateUtil.date2String(new Date()));
//        Date date1 = new Date();
//        Thread.sleep(10);
        System.out.println(dateBetween(getStrToDate(DATE_FORMAT_1, "2012-08-02 01:01:23"), new Date()));
    }
}
