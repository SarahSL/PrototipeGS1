package gs1.gestorsm.prototipegs1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,SearchContent.class);
        startActivity(intent);
/*
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

        /*//con.setSql("Select * from user");
        //Mode 0 - Select
        //Mode 1 - Update y Delete
        con.setSql("INSERT INTO `evaluation`(`cod_user`, `cod_content`, `score`) VALUES (2,2,2)", 1);
        con.delegate = this;
        con.Connect();
*/
/*    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case android.R.id.home;
            //Acción android.R.id.hom
                break;


        }
    }*/
/*
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        //trabajo();
    }

    public void trabajo(){
        TextView user = (TextView)findViewById(R.id.tv);
        user.setText(datos.get(0).get(1));
    }*/
}