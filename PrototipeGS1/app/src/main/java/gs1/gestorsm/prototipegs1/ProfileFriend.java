package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.ContentsList.ShowContent;

/**
 * Created by Cynthia on 24/11/2017.
 */

public class ProfileFriend extends AppCompatActivity implements ConnectResponse{

    TextView nombreAmigo;
    TextView nombre;
    Button verLista;
    Button verEstadistica;
    Button borrar;
    ImageView imagen;

    Connect con;
    ArrayList<ArrayList<String>>datos = new ArrayList<>();
    ArrayList<String> todo = new ArrayList<>();
    String nombreCompleto;
    MySession g = MySession.getInstance();
    //ID del usuario que está logueado
    int idUser=Integer.parseInt(g.getId());

    //ID DEL CONTACTO QUE ESTAMOS VIENDO
    String id;
    String ruta;
    String titleName;

    int camino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_profile);

        nombreAmigo = (TextView) findViewById(R.id.nameFriend);
        nombre = (TextView) findViewById(R.id.name);


        verLista = (Button) findViewById(R.id.verListas);
        verEstadistica = (Button) findViewById(R.id.verEstadisticas);
        borrar = (Button) findViewById(R.id.borrarContacto);
        imagen = (ImageView) findViewById(R.id.imagen);

        Bundle bundle= getIntent().getExtras();
        titleName= bundle.getString("nombreAmigo");

        nombreAmigo.setText(titleName);
        camino=0;
        con = new Connect();
        //USO AQUI EL ID DEL USUARIO
        con.setSql("select id_user,name,userImage from user where userName='" + titleName + "'", 0 );

        con.delegate= this;
        con.Connect();

        //Este es para ver las listas del usuario
        verLista.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent  intent = new Intent(view.getContext(), ShowContent.class);
                startActivity(intent);
            }
        });

        //Este es para ver las estadísticas del usuario
        verEstadistica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camino=1;
                conexionBorrar();
                Intent i = new Intent(view.getContext(), List_Contact.class);
                startActivity(i);
            }
        });


    }

    public void conexionBorrar(){
        con = new Connect();
        //USO AQUI EL ID DEL USUARIO
        con.setSql("delete from contact where contact.cod_contact="+id+ " and cod_user="+ idUser, 1 );
        con.delegate= this;
        con.Connect();
    }
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if(camino==0){
            datosUsuario();
        }
    }

    public void datosUsuario() {

            nombreCompleto=datos.get(0).get(1);

            //MI VARIABLE CON EL ID ACTUAL DE MI CONTACTO
            id=datos.get(0).get(0);

            ruta=datos.get(0).get(2);

            nombre.setText(nombreCompleto);

            ImageManager a = new ImageManager();
            a.setStringImgtoImageView(imagen, ruta);


    }
}
