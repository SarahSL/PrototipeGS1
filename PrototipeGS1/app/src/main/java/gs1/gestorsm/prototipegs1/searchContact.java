package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cynthia on 21/11/2017.
 */

public class searchContact  extends AppCompatActivity {
    Button buscar;
    TextView titulo;
    ListView lista;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_search);
        buscar = (Button) findViewById(R.id.botonBuscar);
        titulo = (TextView) findViewById(R.id.tituloBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               prueba();
            }
        });



    }
    private void prueba(){


        ArrayList<String>  elemento = new ArrayList<>();
        String [] prueba = {"Cynthia", "Sarah"};
        elemento.add(prueba[0]);
        elemento.add(prueba[1]);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this,R.layout.contact_search,R.id.tituloBuscar);
        lista = (ListView)findViewById(R.id.listaBuscar);
        lista.setAdapter(adaptador);
    }
}
