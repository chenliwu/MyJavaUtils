import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
//        dateTest1();
        dateTest2();
//        String num = "0.032940";
//        Double d = Double.parseDouble(num);
//        BigDecimal bigDecimal = BigDecimal.valueOf(d);
//        System.out.println("bigDecimal:" + bigDecimal);
    }

    /**
     * String转化成BigDecimal
     *
     * @param value
     * @return
     */
    public static BigDecimal stringToBigDecimal(String value) {
        if (value != null && value.length() > 0) {
            return null;
        }
        Double d = Double.parseDouble(value);
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        return bigDecimal;
    }


    public static void dateTest() {
        String dateString = "20180919";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(dateString);
            System.out.println(simpleDateFormat1.format(date));
            System.out.println(date.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dateTest1() {
        String dateString = "20180919";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        try {
            Date date = new Date();
            System.out.println(simpleDateFormat.format(date));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dateTest2() {
        String dateString = "1400";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        try {
            Date date = new Date();
            System.out.println(simpleDateFormat.format(date));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
