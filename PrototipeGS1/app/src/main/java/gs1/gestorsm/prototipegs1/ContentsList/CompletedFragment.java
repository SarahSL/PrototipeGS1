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
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
        ListView listView = view.findViewById(R.id.completed_list);

        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);
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
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return completedElements.size();
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
            view = getLayoutInflater().inflate(R.layout.activity_content_list_buttons, null);

            TextView name_of_content_list = view.findViewById(R.id.name_of_content_list);
            ImageButton delete = view.findViewById(R.id.delete_content_in_list);
            ImageButton ver = view.findViewById(R.id.go_to_content);
            name_of_content_list.setText(completedElements.get(i));

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String aux = idContentCompleted.get(i);
                    Intent intent = new Intent(getActivity(), ShowPageContent.class);
                    intent.putExtra("aux", aux);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return view;

        }
    }
}
