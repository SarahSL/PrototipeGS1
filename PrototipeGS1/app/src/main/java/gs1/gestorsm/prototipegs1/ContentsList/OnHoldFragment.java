package gs1.gestorsm.prototipegs1.ContentsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Javier on 25/11/2017.
 */

public class OnHoldFragment extends Fragment implements ConnectResponse{

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> onHoldElements =new ArrayList<>();
    Connect con;
    View view;
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hold_on,container,false);
        //HACER LA  MAGIA DEL ACTIVITY
        movieConsult();
        serieConsult();
        return view;
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        AddObjets();
        // CREATE DE ADAPTER WITH ALL THE DATA FROM ALL THE CONSULTS
        adapter = new ArrayAdapter<>(getActivity(),R.layout.fragment_hold_on,R.id.textView2
                , onHoldElements);
        ListView listView = view.findViewById(R.id.on_hold_list);
        listView.setAdapter(adapter);

    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.clear();
    }
    private void movieConsult(){
        con = new Connect();
        // TO DO:  HAY QUE AÑADIR EL ID_USER
        con.setSql("SELECT content.title,contentType.name " +
                "FROM content,contentType " +
                "INNER JOIN movie, waitingList " +
                "WHERE content.id_content = movie.cod_content " +
                "AND waitingList.cod_movie = movie.id_movie " +
                "AND waitingList.cod_user =1 AND contentType.id_contentType=content.cod_contentType ", 0);
        con.delegate = this;
        con.Connect();
    }
    private void serieConsult(){
        con = new Connect();
        // TO DO:  HAY QUE AÑADIR EL ID_USER
        con.setSql("SELECT content.title,contentType.name "+
                "FROM content,contentType "+
                "INNER JOIN serie,season,chapter, waitingList "+
                "WHERE content.id_content = serie.cod_content "+
                "AND serie.id_serie=season.cod_serie "+
                "AND season.id_season=chapter.cod_season "+
                "AND waitingList.cod_chapter = chapter.id_chapter "+
                "AND waitingList.cod_user =1 AND contentType.id_contentType=content.cod_contentType", 0);
        con.delegate = this;
        con.Connect();
    }
    private void AddObjets(){
        for (int i = 0; i < datos.size(); i++) {
            onHoldElements.add(datos.get(i).get(0)+ "\t\t" + datos.get(i).get(1));
        }

    }
}