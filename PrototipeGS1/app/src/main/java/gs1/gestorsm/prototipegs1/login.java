package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jorge on 25/11/2017.
 */

public class login extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    boolean logued = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if (datos.size() > 0) {
            System.out.println("aksjlasjdalkdsja");
            logued = true;
        }
        prueba();
    }

    public void login(View view) {
        logued = false;
        Connect con = new Connect();
        EditText user = findViewById(R.id.userText);
        EditText pass = findViewById(R.id.passText);
        con.setSql("Select * from user where user.userName='" + user.getText() + "' and user.password='" + pass.getText() + "'", 0);
        con.delegate = this;
        con.Connect();
    }

    // Al presionar el botón de precios saltamos a la activity correspondiente.
    public void prueba() {
        if (logued) {
            Intent i = new Intent(this, logueado.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Error en los datos introducidos", Toast.LENGTH_SHORT).show();
        }
    }
}
