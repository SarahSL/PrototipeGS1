package gs1.gestorsm.prototipegs1;

/**
 * Created by Jorge on 25/11/2017.
 */

public class MySession {
    private static MySession instance;
    private static String test;

    private MySession() {
    }

    public static synchronized MySession getInstance() {
        if (instance == null) {
            instance = new MySession();
        }
        return instance;
    }

    public String getId() {
        return MySession.test;
    }

    public void setId(String t) {
        MySession.test = t;
    }
}
