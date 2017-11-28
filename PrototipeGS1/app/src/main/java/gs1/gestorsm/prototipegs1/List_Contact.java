package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by Cynthia on 24/11/2017.
 */

public class List_Contact  extends AppCompatActivity implements AdapterView.OnItemClickListener{
    EditText friend;
    Button searchFriend;
    ListView myFriends;
    String[] amiguis;
    ImageView imagen;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_friend);
        searchFriend = (Button) findViewById(R.id.buscarMiContacto);
        friend = (EditText) findViewById(R.id.miContacto);
        myFriends= (ListView) findViewById(R.id.listaMisContactos);

        URL src= new URL();
        imagen= (ImageView) findViewById(R.id.imagen);
        imagen.
        //Click sobre amigo
        myFriends.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        //aqui irán los registros de la consulta a la BD
        amiguis= new String[]{ "Geraldo" , "Jorge"};
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, amiguis);
        myFriends.setAdapter(adapter);

        searchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (friend.getText().toString().trim().length() > 0 ) {
                    String newString = friend.getText().toString().trim();

                    //Aqui iria el select con el where name=newString y me crea la lista con los registros devueltos en la consulta
                    switch(newString) {
                        case "Geraldo":
                            amiguis = new String[]{
                                    "Geraldo"};
                            //Esto es para actualizar la lista con los valores de la busqueda
                            adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, amiguis);
                            myFriends.setAdapter(adapter);
                            break;
                        case "Jorge":
                            amiguis = new String[]{
                                    "Jorge"};
                            adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, amiguis);
                            myFriends.setAdapter(adapter);
                            break;
                        default:
                            //Si la String con la consulta de la busqueda no devuelve nada muestra esto
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "No se ha encontrado ningún amigo con ese nombre", Toast.LENGTH_SHORT);

                            toast1.show();
                            break;
                    }

                }else{
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "No se ha escrito ningun nombre de contacto", Toast.LENGTH_SHORT);

                    toast1.show();

                    //Como no se ha buscado nada se muestra la lista con todos los contactos
                    amiguis= new String[]{ "Geraldo" , "Jorge"};
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, amiguis);
                    myFriends.setAdapter(adapter);

                }



            }
        });
    }


    @Override
    public void onItemClick (AdapterView < ?> parent, View view,int position, long id){
        //segundo: texto a mostrar
        //Toast.makeText(getApplicationContext(), "holi", Toast.LENGTH_SHORT).show();

        // esta clase (la actual), la clase a la que queremos ir
        // IR A UN ACTIVITY, HAY QUE AÑADIR LA ACTIVITY EN EL MANIFEST
        Intent i = new Intent(view.getContext(), ProfileFriend.class);
        i.putExtra("nombreAmigo", amiguis[position]);
        startActivity(i);
    }


}

