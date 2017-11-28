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
import android.widget.Toast;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;
/**
 * Created by Sarah on 25/11/2017.
 */
public class OnHoldFragment extends Fragment implements ConnectResponse{

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> onHoldElements =new ArrayList<>();
    ArrayList<String> idContentOnHold = new ArrayList<>();
    ArrayList<String> idLista = new ArrayList<>();
    CustomAdapter adapter;
    Connect con;
    View view;
    String idUser;
    MySession g=MySession.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hold_on,container,false);
        idUser=g.getId();
        movieConsult();
        serieConsult();
        return view;
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        AddObjets();
        ListView listView = view.findViewById(R.id.on_hold_list);
         adapter=new CustomAdapter();
        listView.setAdapter(adapter);

    }


    @Override
    public void onStop() {
        super.onStop();
        onHoldElements.clear();
    }
    private void movieConsult(){
        con = new Connect();
        con.setSql("SELECT content.title,contentType.name,content.id_content,waitingList.id_waitingList " +
                "FROM content,contentType,waitingList " +
                "INNER JOIN movie " +
                "WHERE content.id_content = movie.cod_content " +
                "AND waitingList.cod_movie = movie.id_movie " +
                "AND waitingList.cod_user ="+idUser+" AND contentType.id_contentType=content.cod_contentType ", 0);
        con.delegate = this;
        con.Connect();
    }
    private void serieConsult(){
        con = new Connect();
        con.setSql("SELECT content.title,contentType.name,content.id_content,waitingList.id_waitingList,chapter.title "+
                "FROM content,contentType,chapter, waitingList "+
                "INNER JOIN serie,season "+
                "WHERE content.id_content = serie.cod_content "+
                "AND serie.id_serie=season.cod_serie "+
                "AND season.id_season=chapter.cod_season "+
                "AND waitingList.cod_chapter = chapter.id_chapter "+
                "AND waitingList.cod_user ="+idUser+" AND contentType.id_contentType=content.cod_contentType", 0);
        con.delegate = this;
        con.Connect();
    }
    private void AddObjets(){
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).get(1).equals("serie")) {
                onHoldElements.add(datos.get(i).get(0) + "-" + datos.get(i).get(4) + "\t\t" + datos.get(i).get(1));
            } else {
                onHoldElements.add(datos.get(i).get(0) + "\t\t" + datos.get(i).get(1));
            }
            idContentOnHold.add(datos.get(i).get(2));
            idLista.add(datos.get(i).get(3));
        }

    }
    class CustomAdapter extends BaseAdapter implements ConnectResponse{

        @Override
        public int getCount() {
            return onHoldElements.size();
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
            name_of_content_list.setText(onHoldElements.get(i));

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String aux = idContentOnHold.get(i);
                    Intent intent = new Intent(getActivity(), ShowPageContent.class);
                    intent.putExtra("aux", aux);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!idLista.isEmpty()) {
                        borrarContenido(i);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
            return view;

        }

        @Override
        public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
            Toast.makeText(getContext(), "Element was deleted from the list", Toast.LENGTH_LONG);
        }
        public void borrarContenido(int typeContent) {
            con = new Connect();
            onHoldElements.remove(typeContent);
            con.setSql("Delete from waitingList where cod_user=" + idUser + " and id_waitingList=" + idLista.get(typeContent), 1);
            con.delegate = this;
            con.Connect();
        }
    }
}
