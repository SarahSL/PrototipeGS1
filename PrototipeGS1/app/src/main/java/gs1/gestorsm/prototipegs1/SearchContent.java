package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by topema on 25/11/2017.
 */

public class SearchContent extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    Connect con = new Connect();
    ArrayAdapter<String> adaptador;

    EditText texto;
    Button search;

    ListView results;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentsearch);


        texto = findViewById(R.id.searchText);
        search = findViewById(R.id.searchButton);
        results = findViewById(R.id.searchResult);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,result);

        results.setAdapter(adaptador);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamada();
                adaptador.addAll(result);
                results.setAdapter(adaptador);
                adaptador.clear();
                result.clear();
            }
        });
    }

    public void rellenaLista() {
        Iterator<String> it =  result.iterator();
        while(it.hasNext()){
            adaptador.add(it.toString());
        }

    }

    private void llamada() {
        con = new Connect();
        con.setSql("SELECT content.title FROM content WHERE content.title LIKE '%"+texto.getText()+"%'", 0);
        con.delegate = this;
        con.Connect();
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        copy();
    }

    private void copy() {
        for (ArrayList<String> list : datos)
        {
            for (String num : list)
            {
                result.add(num);
            }
        }

    }
}
