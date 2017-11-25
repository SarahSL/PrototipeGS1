package gs1.gestorsm.prototipegs1;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private RelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.layout);
        configureNavigationDrawer();
        configureToolbar();

        /*Intent intent = new Intent(this,SearchContent.class);
        startActivity(intent);*/

/*        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        /*//con.setSql("Select * from user");
        //Mode 0 - Select
        //Mode 1 - Update y Delete
        con.setSql("INSERT INTO `evaluation`(`cod_user`, `cod_content`, `score`) VALUES (2,2,2)", 1);
        con.delegate = this;
        con.Connect();
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.empty, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_action_dehaze);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    public void configureNavigationDrawer() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Fragment f = null;
                int itemId = menuItem.getItemId();

                if (itemId == R.id.refresh) {
                    f = new RefreshFragment();
                } else if (itemId == R.id.stop) {
                  //  f = new StopFragment();
                }

                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, f);
                    transaction.commit();
                    drawer_layout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId) {
            // Android home
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                return true;

            // manage other entries if you have it ...
        }

        return true;
    }
}





/*
    private void setToolbar(){
        Toolbar tb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_dehaze);
    }

    private void configureNavigationDrawer(){
        drawerlayour = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment f = null;
                int ItemId = item.getItemId();

                if(ItemId == R.id.refresh){
                    f = new RefreshFragment();
                }else if(itemId == R.id.stop){
                    f = new StopFragment();
                }

                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case android.R.id.home:
            //Acción android.R.id.home

                break;
        }
        return false;
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
