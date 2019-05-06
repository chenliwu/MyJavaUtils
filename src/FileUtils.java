import java.io.File;
import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-10-15 12:18
 */
public class FileUtils {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.00");
        System.out.println(bigDecimal.intValue());
//
//        if(args.length > 0){
//            if(isExists(args[0])){
//                System.out.println("file exists");
//            }else{
//                System.out.println("file not exists");
//            }
//        }else{
//            System.out.println("no file path parameter");
//        }
    }

    public static boolean isExists(String path){
        File file = new File(path);
        return file.exists();
    }

}
