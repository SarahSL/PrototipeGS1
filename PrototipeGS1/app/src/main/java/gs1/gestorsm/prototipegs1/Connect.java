package gs1.gestorsm.prototipegs1;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Jorge on 22/11/2017.
 */

public class Connect extends AsyncTask<String, Void, String> {
    String url = "jdbc:mysql://46.101.178.225:3306/gs1Prototipo";
    String user = "explotacion";
    String pass = "pandeHuevo";
    String sql = "";
    int mode = 0;
    private ArrayList<String> field = new ArrayList<>();
    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    public ConnectResponse delegate = null;


    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String result = "Database connection success\n";
            Statement st = con.createStatement();
            //UPDATE Y DELETE executeUpdate.
            //SELECT executequery.
            if (mode == 0) {


                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                    field.clear();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        field.add(rs.getString(i));
                    }
                    data.add(new ArrayList<String>(field));

                }
            } else {
                st.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result, data);
    }


    public void Connect() {
        this.execute();
    }

    public void setSql(String sql, int mode) {
        this.sql = sql;
        this.mode = mode;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
}