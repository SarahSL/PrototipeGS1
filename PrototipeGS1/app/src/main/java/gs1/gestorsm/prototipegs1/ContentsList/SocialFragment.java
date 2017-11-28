package gs1.gestorsm.prototipegs1.ContentsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Comment;
import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.ContentScore;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Topeman on 25/11/2017.
 */

public class SocialFragment extends Fragment implements ConnectResponse {

    Connect con;
    EditText editText;
    TextView content;
    Button comenta,valoration;
    String usuario, contenido, comentario;
    MySession g = MySession.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        editText = view.findViewById(R.id.editText);
        comenta = view.findViewById(R.id.commentButton);
        valoration = view.findViewById(R.id.valoration);
        comenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = g.getId();                    //AQUI CODIGOS DE USUARIO Y CONTENIDO PERTINENTES!!!!
                contenido = g.getIdContent_PageContent();
                comentario = editText.getText().toString();
                if (comentario.isEmpty()) {
                    Toast toast1 =
                            Toast.makeText(getContext(),
                                    "There is no commentary", Toast.LENGTH_SHORT);
                    toast1.show();
                } else {
                    comment(usuario, contenido, comentario);
                    Toast toast1 =
                            Toast.makeText(getContext(),
                                    "Content commented", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
        valoration.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ContentScore.class);
                startActivity(intent);
            }
        });
        return view;

    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
    }

    public void comment(String usuario, String contenido, String comentario) {
        con = new Connect();
        con.setSql("INSERT INTO comment (cod_user, cod_content, comment) values(" + usuario + ", " + contenido + ", " + comentario + ")", 1);
        con.delegate = this;
        con.Connect();
        System.out.println(editText.getText());
    }
}
