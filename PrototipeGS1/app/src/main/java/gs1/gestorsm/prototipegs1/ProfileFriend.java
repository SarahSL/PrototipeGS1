package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cynthia on 24/11/2017.
 */

public class ProfileFriend extends AppCompatActivity {

    TextView nombre;
    Button verLista;
    Button verEstadistica;
    Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_profile);

        nombre = (TextView) findViewById(R.id.nameFriend);

        verLista = (Button) findViewById(R.id.verListas);
        verEstadistica = (Button) findViewById(R.id.verEstadisticas);
        borrar = (Button) findViewById(R.id.borrarContacto);

        Bundle bundle= getIntent().getExtras();
        String titleName= bundle.getString("nombreAmigo");

        nombre.setText(titleName);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), List_Contact.class);
                startActivity(i);
            }
        });


    }
}
