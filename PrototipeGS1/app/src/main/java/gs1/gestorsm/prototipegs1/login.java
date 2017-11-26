package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jorge on 25/11/2017.
 */

public class Login extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    MySession g = MySession.getInstance();
    boolean logued = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void onResume() {
        super.onResume();
        g.setId("");
        cleanEditText();
    }

    public void cleanEditText() {
        EditText user = findViewById(R.id.userText);
        EditText pass = findViewById(R.id.passText);
        user.setText("");
        pass.setText("");
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if (datos.size() > 0) {
            logued = true;
            g.setId(String.valueOf(datos.get(0).get(0)));
        }
        prueba();
    }

    public void login(View view) {
        logued = false;
        Connect con = new Connect();
        EditText user = findViewById(R.id.userText);
        EditText pass = findViewById(R.id.passText);
        con.setSql("Select user.id_user from user where user.userName='" + user.getText() + "' and user.password='" + pass.getText() + "'", 0);
        con.delegate = this;
        con.Connect();
    }

    public void prueba() {
        if (logued) {
            Intent i = new Intent(this, Logued.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Error en los datos introducidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}