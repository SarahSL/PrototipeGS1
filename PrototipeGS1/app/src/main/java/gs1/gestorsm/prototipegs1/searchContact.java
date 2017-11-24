package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        String [] prueba = {"Cynthia", "Sarah"};
        adaptador.add(prueba[0]);
        adaptador.add(prueba[1]);
        lista = (ListView)findViewById(R.id.listaBuscar);
        lista.setAdapter(adaptador);
    }
}
