package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import static android.widget.SearchView.*;

/**
 * Created by topema on 25/11/2017.
 */

public class SearchContent extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();

    ArrayList<String> res = new ArrayList<String>();

    SearchView search;
    ListView results;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentsearch);

        search = findViewById(R.id.searchView);
        results = findViewById(R.id.searchResult);
    }

    public boolean onQueryTextSubmit(String text) {
        Toast.makeText(this, "Searching for " + text, Toast.LENGTH_LONG).show();
        return false;
    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
    }
}
