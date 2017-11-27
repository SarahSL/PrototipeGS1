package gs1.gestorsm.prototipegs1.ContentsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;
/**
 * Created by Sarah on 25/11/2017.
 */
public class CompletedFragment extends Fragment implements ConnectResponse{

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> completedElements =new ArrayList<>();
    Connect con;
    ArrayList<String> idContentCompleted=new ArrayList<>();
    View view;
    ArrayAdapter<String> adapter;
    String idUser;
    MySession g = MySession.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_completed,container,false);
        idUser = g.getId();

        movieConsult();
        serieConsult();
        return view;
    }
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        AddObjets();
        adapter=new ArrayAdapter<>(getActivity(),R.layout.fragment_completed,R.id.textView2
                , completedElements);
        ListView listView = view.findViewById(R.id.completed_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String aux = idContentCompleted.get(i);
                Intent intent = new Intent(getActivity(),ShowPageContent.class);
                intent.putExtra("aux",aux);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        completedElements.clear();
    }
    private void movieConsult(){
        con = new Connect();
        con.setSql("SELECT content.title,contentType.name,content.id_content  "+
                "FROM content,contentType "+
                "INNER JOIN movie, viewList "+
                "WHERE content.id_content = movie.cod_content "+
                "AND viewList.cod_movie = movie.id_movie "+
                "AND viewList.cod_user ="+idUser+" AND contentType.id_contentType = content.cod_contentType", 0);
        con.delegate = this;
        con.Connect();
    }
    private void serieConsult(){
        con = new Connect();
        con.setSql("SELECT content.title, contentType.name,content.id_content " +
                "FROM content,contentType " +
                "INNER JOIN serie,season,chapter, viewList " +
                "WHERE content.id_content = serie.cod_content " +
                "AND serie.id_serie=season.cod_serie " +
                "AND season.id_season=chapter.cod_season " +
                "AND viewList.cod_chapter = chapter.id_chapter " +
                "AND viewList.cod_user ="+idUser+" AND contentType.id_contentType=content.cod_contentType", 0);
        con.delegate = this;
        con.Connect();
    }
    private void AddObjets(){
        for (int i = 0; i < datos.size(); i++) {
            completedElements.add(datos.get(i).get(0)+ "\t\t" + datos.get(i).get(1));

            idContentCompleted.add(datos.get(i).get(2));
        }

    }
}
