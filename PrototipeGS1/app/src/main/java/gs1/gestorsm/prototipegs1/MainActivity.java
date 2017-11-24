package gs1.gestorsm.prototipegs1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //con.setSql("Select * from user");
        //Mode 0 - Select
        //Mode 1 - Update y Delete
        con.setSql("INSERT INTO `evaluation`(`cod_user`, `cod_content`, `score`) VALUES (2,2,2)", 1);
        con.delegate = this;
        con.Connect();
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        //trabajo();
    }

    public void trabajo(){
        TextView user = (TextView)findViewById(R.id.tv);
        user.setText(datos.get(0).get(1));
    }
}