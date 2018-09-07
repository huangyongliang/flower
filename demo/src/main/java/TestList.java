import java.util.ArrayList;
import java.util.List;

/**
 * @author yryub25075
 * @date 2018/9/3
 **/
public class TestList {

    public static void main(String[] args) {
        List<String> list  = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list.subList(0,1));
        boolean b = list.removeAll(list.subList(0,1));
        System.out.println(b);
        System.out.println(list);

    }
}
