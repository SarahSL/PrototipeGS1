package gs1.gestorsm.prototipegs1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by topema on 27/11/2017.
 */

public class ContentScore extends AppCompatActivity implements ConnectResponse{

    Connect con;
    ImageButton button1,button2,button3,button4,button5;
    Button goback,punctuate;
    String codUsuario,codContenido,puntuacion,nombreContenido;
    TextView text,text2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);

        goback=findViewById(R.id.goback);
        punctuate=findViewById(R.id.punctuate);

        text=findViewById(R.id.textView2);
        text2=findViewById(R.id.textView4);

        codContenido="1";
        codUsuario="1";

        text.setText(nombreContenido);      //Asignar nombre de contenido!!

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion="1";
                text2.setText("punctuation: " + puntuacion);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion="2";
                text2.setText("punctuation: " + puntuacion);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion="3";
                text2.setText("punctuation: " + puntuacion);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion="4";
                text2.setText("punctuation: " + puntuacion);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntuacion="5";
                text2.setText("punctuation: " + puntuacion);
            }
        });

        punctuate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(puntuacion.isEmpty()){
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "There is no punctuation", Toast.LENGTH_SHORT);
                toast1.show();
            }else {
                puntuar(codUsuario, codContenido, puntuacion);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "succesfull evaluation", Toast.LENGTH_SHORT);
                toast1.show();
            }
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ENLACE VUELTA ATRAS
            }
        });
    }


    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {

    }

    public void puntuar(String usuario, String contenido, String puntuacion) {
        con = new Connect();
        con.setSql("INSERT INTO evaluation (cod_user, cod_content, score) values("+usuario+", "+contenido+ ", " +puntuacion+")", 1);
        con.delegate = this;
        con.Connect();
    }

}
