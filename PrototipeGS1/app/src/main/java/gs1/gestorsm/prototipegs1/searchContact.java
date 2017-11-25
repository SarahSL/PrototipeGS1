package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cynthia on 21/11/2017.
 */

public class searchContact  extends AppCompatActivity {
    Button buscar;
    EditText cuadro;
    String[] ListElements = new String[]{
            "Cynthia",
            "Sarah"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_search);
        buscar = (Button) findViewById(R.id.botonBuscar);
        final ListView lista = (ListView) findViewById(R.id.listaBuscar);

        //Click elemento lista
       // lista.setOnItemClickListener((AdapterView.OnItemClickListener) this);


        //Click boton buscar
        buscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //POner en la otra clase :D

            CustomAdapter adapter = new CustomAdapter();
            lista.setAdapter(adapter);
           /* Crear lista simple
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ListElements);
            lista.setAdapter(adapter);
            */
        }
        });

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ListElements.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.contact_searched,null);
            TextView texto = (TextView) view.findViewById(R.id.nombreContact);
            Button agregar = (Button) view.findViewById(R.id.agregarContact);

            texto.setText(ListElements[i]);

            agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext(), ListElements[i], Toast.LENGTH_SHORT).show();
                    ListElements = new String[]{
                            "Sarah"};
                    notifyDataSetChanged();

                }
            });

            return view;
        }
    }


    /*
    Este añadiendo: implements AdapterView.OnItemClickListener hago que al hacer click en un elemento
    vaya a un activity
    @Override
    public void onItemClick (AdapterView < ?> parent, View view,int position, long id){
        //segundo: texto a mostrar
        //Toast.makeText(getApplicationContext(), "holi", Toast.LENGTH_SHORT).show();
        // esta clase (la actual), la clase a la que queremos ir


        // IR A UN ACTIVITY, HAY QUE AÑADIR LA ACTIVITY EN EL MANIFEST
        Intent i = new Intent(view.getContext(), searchedContact.class);
        startActivity(i);
    }
    */
}
