package gs1.gestorsm.prototipegs1.ContentsList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Javier on 25/11/2017.
 */

public class SinopsisFragment extends Fragment implements ConnectResponse{
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> textSinopsis  = new ArrayList<>();
    View view;
    Connect con;
    String idContent;
    MySession g = MySession.getInstance();
    TextView sinopsisTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sinopsis, container, false);

        idContent = g.getIdContent_PageContent();
        getSinopsis();

        return view;

    }
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        setSinopsis();

        sinopsisTextView = view.findViewById(R.id.sinopsis_textVIew);
        sinopsisTextView.setText(textSinopsis.get(0));
    }

    private void setSinopsis() {
        for (int i = 0; i < datos.size(); i++) {
            textSinopsis.add(datos.get(i).get(0));
        }
    }

    private void getSinopsis(){

        con = new Connect();
        // TO DO:  HAY QUE AÑADIR EL ID_USER
        con.setSql("Select content.synopsis from content where content.id_content="+idContent+"", 0);
        con.delegate = this;
        con.Connect();
    }
}
