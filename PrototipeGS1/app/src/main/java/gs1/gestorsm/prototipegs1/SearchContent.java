package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

import gs1.gestorsm.prototipegs1.ContentsList.ShowPageContent;


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
    int paso = 0;

    String aux ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);


        texto = findViewById(R.id.searchText);
        search = findViewById(R.id.searchButton);
        results = findViewById(R.id.searchResult);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);

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
        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                paso = 1;
                searchIdContent(i);

            }
        });
    }

    public void rellenaLista() {
        Iterator<String> it = result.iterator();
        while (it.hasNext()) {
            adaptador.add(it.toString());
        }

    }

    private void searchIdContent(int i ) {
        con = new Connect();
        System.out.println(result.get(i));
        con.setSql("SELECT content.id_content FROM content WHERE content.title="+"'"+result.get(i)+"'", 0);
        con.delegate = this;
        con.Connect();
    }

    private void llamada() {
        con = new Connect();
        //con.setSql("SELECT content.title FROM content WHERE content.title LIKE '%" + texto.getText() + "%'", 0);

        con.setSql("SELECT content.title FROM content WHERE content.title ='" + texto.getText().toString().trim()+"'", 0);
        con.delegate = this;
        con.Connect();
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if (paso == 0) {
            copy();
        } else if (paso == 1) {
            aux = datos.get(0).get(0);
            Intent intent = new Intent(this, ShowPageContent.class);
            intent.putExtra("aux", aux);
            startActivity(intent);

            paso = 2;
        }

    }

    private void copy() {
        for (ArrayList<String> list : datos) {
            for (String num : list) {
                result.add(num);
            }
        }

    }

}
