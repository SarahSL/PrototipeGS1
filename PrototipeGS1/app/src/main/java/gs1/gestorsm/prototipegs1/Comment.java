package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by topema on 27/11/2017.
 */

public class Comment extends AppCompatActivity implements ConnectResponse {
    Connect con;
    EditText editText;
    TextView content;
    Button comenta, back;
    String usuario, contenido, comentario;

    public Comment(String usuario, String contenido) {
        this.usuario = usuario;
        this.contenido = contenido;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentary);

        editText = findViewById(R.id.editText);
        comenta = findViewById(R.id.commentButton);
        //back = findViewById(R.id.buttonBack);

        comenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = "1";                    //AQUI CODIGOS DE USUARIO Y CONTENIDO PERTINENTES!!!!
                contenido = "1";
                comentario = editText.getText().toString();
                if (comentario.isEmpty()) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                           "There is no commentary", Toast.LENGTH_SHORT);
                    toast1.show();
                } else {
                    comment(usuario, contenido, comentario);
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                           "Content commented", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ENLACE PARA VOLVER A DONDE SEA


            }
        });
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
    }

    public void comment(String usuario, String contenido, String comentario) {
        con = new Connect();
        con.setSql("INSERT INTO comment (cod_user, cod_content, comment) values(" + usuario + ", " + contenido + ", " + comentario + ")", 1);
        con.delegate = this;
        con.Connect();
    }
}
