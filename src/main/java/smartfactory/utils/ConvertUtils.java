package smartfactory.utils;

public class ConvertUtils {
    public static String convertDate(String date) {
        String convertDate = "";
        convertDate = date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
        return convertDate;
    }
}
