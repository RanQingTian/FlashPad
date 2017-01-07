package equipment;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class StringUtil {
    public static boolean emptyCheck(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        return true;
    }
}
