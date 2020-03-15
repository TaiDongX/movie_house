package com.ws.Util;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatUtil {
    public static DateTimeFormatter dfd = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static DateTimeFormatter dff = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dfm = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter dft = DateTimeFormat.forPattern("HH:mm:ss");
    public static DateTimeFormatter dft1 = DateTimeFormat.forPattern("HH:mm");
    public static DateTimeFormatter dfn = DateTimeFormat.forPattern("yyyyMMdd");
    public static DateTimeFormatter dfn1 = DateTimeFormat.forPattern("dd-MM-yyyy");
    public static DateTimeFormatter dfd_en_1;
    public static DateTimeFormatter dfd_en_2;
    public static DateTimeFormatter dfd_en_11;
    public static DateTimeFormatter dfd_en_21;
    public static DateTimeFormatter dfd_en_12;
    public static DateTimeFormatter dfd_en_22;
    public static DateTimeFormatter dfd_en_3;
    public static DateTimeFormatter dfd_en_4;
    public static DateTimeFormatter dfd_en_5;
    public static DateTimeFormatter dfd_en_51;
    public static DateTimeFormatter dfd_en_6;
    public static DateTimeFormatter dfd_en_61;
    public static DateTimeFormatter dfd_en_62;

    public DateFormatUtil() {
    }

    public static Date parseTime(String in) {
        if (in == null) {
            return new Date();
        } else {
            in = in.trim();
            String prefix = null;
            Pattern p = Pattern.compile("今天|昨天|前天|\\d+(个星期|天|分钟|小时)前");
            Matcher m = p.matcher(in);
            if (m.find()) {
                prefix = m.group();
                in = in.replaceAll(prefix, "");
            }

            in = in.trim();
            Date date = new Date();
            String yyyyMMdd = Calendar.getInstance().get(1) + "-" + (Calendar.getInstance().get(2) + 1) + "-" + Calendar.getInstance().get(5);
            int shiftValue = 0;
            Pattern patternTimeZone = Pattern.compile("(?<h>[+-]\\d{2}):?(?<m>\\d{2})$");
            Matcher matcherTimeZone = patternTimeZone.matcher(in);
            if (matcherTimeZone.find()) {
                shiftValue += Integer.parseInt(matcherTimeZone.group("h")) * 60 * 60 * 1000;
                shiftValue += Integer.parseInt(matcherTimeZone.group("m")) * 60 * 1000;
            }

            in = in.replaceAll("日", "").replaceAll("年|月", "-").replaceAll("/", "-").replaceAll("\\.", "-").replaceAll("T", " ").replaceAll("Z", "").replaceAll("(?<h>[+-]\\d{2}):?(?<m>\\d{2})$", "");
            if (in.matches("\\d{9,10}")) {
                return new Date(Long.parseLong(in + "000"));
            } else if (in.matches("\\d{12,13}")) {
                return new Date(Long.parseLong(in));
            } else if (in.matches("\\d{1,2}-\\d{1,2}-\\d+")) {
                return dfn1.parseDateTime(in).toDate();
            } else {
                Date date1;
                long newtime;
                if (in.matches("[A-Za-z]{3,4} \\d{1,2}, \\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2} (AM|PM)")) {
                    date1 = new Date(in);
                    newtime = date1.getTime() + (long)shiftValue;
                    return new Date(newtime);
                } else if (in.matches("\\d{2,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                    date1 = dff.parseDateTime(in).toDate();
                    newtime = date1.getTime() + (long)shiftValue;
                    return new Date(newtime);
                } else if (in.matches("\\d{2,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
                    date1 = dfm.parseDateTime(in).toDate();
                    newtime = date1.getTime() + (long)shiftValue;
                    return new Date(newtime);
                } else if (in.matches("\\d{2,4}-\\d{1,2}-\\d{1,2}")) {
                    return dfd.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2}-\\d{1,2}")) {
                    return dfd.parseDateTime(String.valueOf(Calendar.getInstance().get(1)) + '-' + in).toDate();
                } else if (in.matches("\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{2}")) {
                    date1 = dff.parseDateTime(String.valueOf(Calendar.getInstance().get(1)) + '-' + in).toDate();
                    newtime = date1.getTime() + (long)shiftValue;
                    return new Date(newtime);
                } else if (in.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                    date1 = dff.parseDateTime(yyyyMMdd + " " + in).toDate();
                    newtime = date1.getTime() + (long)shiftValue + getShiftValue(prefix);
                    return new Date(newtime);
                } else if (in.matches("\\d{1,2}:\\d{2}")) {
                    date1 = dff.parseDateTime(yyyyMMdd + " " + in + ":00").toDate();
                    newtime = date1.getTime() + (long)shiftValue + getShiftValue(prefix);
                    return new Date(newtime);
                } else if (in.matches("\\d{8}")) {
                    return dfn.parseDateTime(in).toDate();
                } else if (in.matches("\\w+ +\\d{1,2} *, +\\d{4}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_1.parseDateTime(in).toDate();
                } else if (in.matches("\\w+ +\\d{1,2} *, +\\d{2}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_2.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2} +\\w+ *, +\\d{4}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_11.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2} +\\w+ *, +\\d{2}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_21.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2} +\\w+ +\\d{4}")) {
                    in = in.replaceAll(" +", " ");
                    return dfd_en_12.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2} +\\w+ +\\d{2}")) {
                    in = in.replaceAll(" +", " ");
                    return dfd_en_22.parseDateTime(in).toDate();
                } else if (in.matches("\\w+ +\\d{1,2} +\\d{4}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_3.parseDateTime(in).toDate();
                } else if (in.matches("\\w+ +\\d{1,2} +\\d{2}")) {
                    in = in.replaceAll(" +,", ",").replaceAll(" +", " ");
                    return dfd_en_4.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2}-\\w+-\\d{2}")) {
                    return dfd_en_5.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2}-\\w+-\\d{4}")) {
                    return dfd_en_51.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2}/-\\d{4}")) {
                    return dfd_en_6.parseDateTime(in).toDate();
                } else if (in.matches("\\w+ \\d{4}")) {
                    return dfd_en_61.parseDateTime(in).toDate();
                } else if (in.matches("\\d{4} \\w+")) {
                    return dfd_en_62.parseDateTime(in).toDate();
                } else if (in.matches("\\d{1,2} \\w+")) {
                    return dfd_en_12.parseDateTime(in + " " + Calendar.getInstance().get(1)).toDate();
                } else if (in.matches("\\w+ \\d{1,2}")) {
                    return dfd_en_3.parseDateTime(in + " " + Calendar.getInstance().get(1)).toDate();
                } else {
                    return prefix != null ? new Date((new Date()).getTime() + getShiftValue(prefix)) : date;
                }
            }
        }
    }

    private static long getShiftValue(String prefix) {
        long v = 0L;
        if (prefix != null && !prefix.equals("今天")) {
            if (prefix.equals("昨天")) {
                v = -86400000L;
            } else if (prefix.equals("前天")) {
                v = -172800000L;
            } else {
                int n;
                if (prefix.matches("\\d+天前")) {
                    n = Integer.parseInt(prefix.replaceAll("天前", ""));
                    v = (long)(-n * 24 * 60 * 60 * 1000);
                } else if (prefix.matches("\\d+小时前")) {
                    n = Integer.parseInt(prefix.replaceAll("小时前", ""));
                    v = (long)(-n * 60 * 60 * 1000);
                } else if (prefix.matches("\\d+分钟前")) {
                    n = Integer.parseInt(prefix.replaceAll("分钟前", ""));
                    v = (long)(-n * 60 * 1000);
                } else if (prefix.matches("\\d+个星期前")) {
                    n = Integer.parseInt(prefix.replaceAll("个星期前", ""));
                    v = (long)(-n * 7 * 24 * 60 * 60 * 1000);
                } else if (prefix.matches("\\d+个月前")) {
                    n = Integer.parseInt(prefix.replaceAll("个月前", ""));
                    v = (long)(-n) * 30L * 24L * 60L * 60L * 1000L;
                } else if (prefix.matches("\\d+年前")) {
                    n = Integer.parseInt(prefix.replaceAll("年前", ""));
                    v = (long)(-n) * 365L * 24L * 60L * 60L * 1000L;
                }
            }
        }

        return v;
    }

    public static Date modify(Date date, int val) {
        return new Date(date.getTime() + (long)(val * 3600 * 1000));
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(x);
        return buffer.array();
    }

    static {
        dfd_en_1 = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);
        dfd_en_2 = DateTimeFormat.forPattern("MMM dd, yy").withLocale(Locale.US);
        dfd_en_11 = DateTimeFormat.forPattern("dd MMM, yyyy").withLocale(Locale.US);
        dfd_en_21 = DateTimeFormat.forPattern("dd MMM, yy").withLocale(Locale.US);
        dfd_en_12 = DateTimeFormat.forPattern("dd MMM yyyy").withLocale(Locale.US);
        dfd_en_22 = DateTimeFormat.forPattern("dd MMM yy").withLocale(Locale.US);
        dfd_en_3 = DateTimeFormat.forPattern("MMM dd yyyy").withLocale(Locale.US);
        dfd_en_4 = DateTimeFormat.forPattern("MMM dd yy").withLocale(Locale.US);
        dfd_en_5 = DateTimeFormat.forPattern("dd-MMM-yy").withLocale(Locale.US);
        dfd_en_51 = DateTimeFormat.forPattern("dd-MMM-yyyy").withLocale(Locale.US);
        dfd_en_6 = DateTimeFormat.forPattern("MM/yyyy").withLocale(Locale.US);
        dfd_en_61 = DateTimeFormat.forPattern("MMM yyyy").withLocale(Locale.US);
        dfd_en_62 = DateTimeFormat.forPattern("yyyy MMM").withLocale(Locale.US);
    }
}
