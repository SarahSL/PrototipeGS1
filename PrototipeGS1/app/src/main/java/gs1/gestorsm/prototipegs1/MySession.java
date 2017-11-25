package gs1.gestorsm.prototipegs1;

/**
 * Created by Jorge on 25/11/2017.
 */

public class MySession {
    private static MySession instance;
    private static String idUserLogged;
    private static String idContent_PageContent;

    private MySession() {
    }

    public  String getIdContent_PageContent() {
        return idContent_PageContent;
    }

    public  void setIdContent_PageContent(String idContent_PageContent) {
        MySession.idContent_PageContent = idContent_PageContent;
    }

    public void setId(String t) {
        MySession.idUserLogged = t;
    }

    public String getId() {
        return MySession.idUserLogged;
    }

    public static synchronized MySession getInstance() {
        if (instance == null) {
            instance = new MySession();
        }
        return instance;
    }
}
