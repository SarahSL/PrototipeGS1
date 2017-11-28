package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cynthia on 21/11/2017.
 */

public class searchContact  extends AppCompatActivity implements ConnectResponse {
    Button buscar;
    EditText cuadro;

    String filtrado;
    MySession g = MySession.getInstance();
    final int idUser=Integer.parseInt(g.getId());
    Connect con;
    ArrayList<ArrayList<String>>datos = new ArrayList<>();

    ArrayList<String> misDatos;
    ArrayList<String> idDatos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_search);

        buscar = (Button) findViewById(R.id.botonBuscar);
        cuadro= (EditText) findViewById(R.id.cuadroBuscar);

        //Click boton buscar
        buscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            filtrado= cuadro.getText().toString().trim();
            if(filtrado.length()<=0){
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "NNo contact name has been written", Toast.LENGTH_SHORT);

                toast1.show();
            }else{
                 conexion();

            }

        }
        });
        //BOTON LOGOUT Y USERNAME
        TextView username = findViewById(R.id.username_text);
        username.setText(g.getUsernameLoged());
        ImageButton search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SearchContent.class);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.setId(null);
                g.setUsernameLoged("");
                System.out.println(g.getId());
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivity(intent);
            }
        });

    }

    public void conexion(){

        con = new Connect();
        //USO AQUI EL ID DEL USUARIO
        con.setSql("SELECT user.userName, user.id_user FROM user WHERE id_user NOT IN (SELECT cod_contact from contact where cod_user="+idUser+") and id_user<>"+idUser+" and user.userName='"+filtrado+"'", 0 );
        con.delegate=this;
        con.Connect();
    }
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        actualizarAmigos();
        ListView lista = (ListView) findViewById(R.id.listaBuscar);
        CustomAdapter adapter = new CustomAdapter();
        lista.setAdapter(adapter);
    }

    public void actualizarAmigos(){
        misDatos= new ArrayList<>();
        idDatos= new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) {
            misDatos.add(datos.get(i).get(0));
            idDatos.add(datos.get(i).get(1));
        }
        if(misDatos.size()<=0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "No users to show", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    public void conexion2(int i){
        con = new Connect();
        //USO AQUI EL ID DEL USUARIO
        con.setSql("insert into contact (cod_user, cod_contact) values('" + idUser+ "', '"+ idDatos.get(i) + "')", 1 );
        con.delegate=this;
        con.Connect();
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return misDatos.size();
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
            view = getLayoutInflater().inflate(R.layout.contact_searched, null);
            TextView texto = (TextView) view.findViewById(R.id.nombreContact);
            Button agregar = (Button) view.findViewById(R.id.agregarContact);

            //nombre del usuario
            texto.setText(misDatos.get(i).toString());

            agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext(), misDatos.get(i), Toast.LENGTH_SHORT).show();
                    conexion2(i);
                    notifyDataSetChanged();

                }
            });

            return view;
        }
    }
}
