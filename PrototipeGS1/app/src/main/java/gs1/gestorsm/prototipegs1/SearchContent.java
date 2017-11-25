package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Iterator;

import static android.widget.SearchView.*;

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
                llamada1();
                llamada2();
                llamada3();
                rellenaLista();
            }
        });
    }

    public void rellenaLista() {
        Iterator<String> it =  result.iterator();
        while(it.hasNext()){
            adaptador.add(it.toString());
        }
    }

    private void llamada3() {
        con = new Connect();
        con.setSql("SELECT content.title FROM platform WHERE content.title LIKE '%"+texto.getText()+"%'", 0);
        con.delegate = this;
        con.Connect();
    }

    private void llamada2() {
        con = new Connect();
        con.setSql("SELECT platform.name FROM platform WHERE platform.name LIKE '%"+texto.getText()+"%'", 0);
        con.delegate = this;
        con.Connect();
    }

    private void llamada1() {
        con = new Connect();
        con.setSql("SELECT cast.name FROM cast WHERE cast.name LIKE '%"+texto.getText()+"%'", 0);
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
                System.out.println(num);
            }
        }

    }
}
