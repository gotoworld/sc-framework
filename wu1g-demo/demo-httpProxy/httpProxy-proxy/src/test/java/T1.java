/**
 * Created by Administrator on 2016/12/10.
 */
public class T1 {
    public static void main(String[] args) {
        new Thread(()->{
            A();
            B();
        }).start();
    }
    public static void A(){

    }
    public static void B(){

    }
}
