package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Cynthia on 24/11/2017.
 */

public class List_Contact  extends AppCompatActivity implements AdapterView.OnItemClickListener, ConnectResponse {
    EditText friend;
    Button searchFriend;
    ListView myFriends;
    String filtro;

    ArrayAdapter<String> adapter;

    ArrayList myFriend;

    Connect con;
    ArrayList<ArrayList<String>>datos = new ArrayList<>();
    MySession g = MySession.getInstance();

    final int idUser=Integer.parseInt(g.getId());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_friend);

        searchFriend = (Button) findViewById(R.id.buscarMiContacto);
        friend = (EditText) findViewById(R.id.miContacto);
        myFriends= (ListView) findViewById(R.id.listaMisContactos);


        //Click sobre amigo
        myFriends.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        con = new Connect();

        con.setSql("select userName from user inner join contact where user.id_user!="+ idUser+" and contact.cod_user="+idUser+" and user.id_user=contact.cod_contact", 0 );
        con.delegate= this;
        con.Connect();




        searchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filtro= friend.getText().toString().trim();

                if(filtro.length()<=0){
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No contact name has been written", Toast.LENGTH_SHORT);

                        toast1.show();
                }else {
                    filtrado();
                }
               }
        });
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        ponerUsuariosLista();



    }


    public void filtrado(){
        con = new Connect();
        con.setSql("select userName from user inner join contact where user.id_user!="+ idUser+" and contact.cod_user="+idUser+" and user.id_user=contact.cod_contact and userName='"+filtro+"'", 0 );
        con.delegate= this;
        con.Connect();
    }

    public void ponerUsuariosLista(){

        myFriend= new ArrayList<String>();

            for (int i = 0; i < datos.size(); i++) {
                myFriend.add(datos.get(i).get(0));
            }

        if(myFriend.size()<=0) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                           "No users to show", Toast.LENGTH_SHORT);

            toast1.show();
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFriend);
            myFriends.setAdapter(adapter);
        }else{
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFriend);
            myFriends.setAdapter(adapter);
        }
    }

        @Override
    public void onItemClick (AdapterView < ?> parent, View view,int position, long id){

        // esta clase (la actual), la clase a la que queremos ir
        // IR A UN ACTIVITY, HAY QUE AÑADIR LA ACTIVITY EN EL MANIFEST
        Intent i = new Intent(view.getContext(), ProfileFriend.class);
        i.putExtra("nombreAmigo", myFriend.get(position).toString());
        startActivity(i);
    }
}

