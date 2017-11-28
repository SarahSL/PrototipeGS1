package gs1.gestorsm.prototipegs1.ContentsList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Sarah on 25/11/2017.
 */

public class ShowPageContent extends AppCompatActivity implements ConnectResponse, AdapterView.OnItemSelectedListener {
    private SectionsPageAdapter mSectionsPageAdapter;
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    private ViewPager mViewPager;
    String idContent;
    Connect con;
    Spinner spinner;
    ArrayList<String> addToList = new ArrayList<>();
    MySession g = MySession.getInstance();
    ArrayList<String> contentDatas = new ArrayList<>();
    int paso = 0;
    int lista = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page_show);
        Intent intent = getIntent();
        idContent = intent.getStringExtra("aux");
        g.setIdContent_PageContent(idContent);

//TAB
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
//SIPNNER
        spinner = (Spinner) findViewById(R.id.spinner_ContentPage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Add_to_list, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //GETDATAS
        getDatas();
        //TO DO_ FIX THE IMAGE
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new SinopsisFragment(), "Sinopsis");
        adapter.addFragment(new EpisodesFragment(), "Episodes");
        adapter.addFragment(new SocialFragment(), "Social");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if (paso == 0) {
            setAllDatas();

            TextView textView = findViewById(R.id.show_page_content_text);
            textView.setText(contentDatas.get(0));
            TextView nameP = findViewById(R.id.name_of_platform);
            nameP.setText(contentDatas.get(2));
            TextView linkP = findViewById(R.id.link_for_platform);
            linkP.setText(contentDatas.get(3));
        } else if (paso == 1) {
            setDataForAdd();
            paso = 2;
            if (addToList.get(0).equals("serie")) {
                ifSerie();
            } else {
                ifMovie();
            }
        } else if (paso == 2) {

        }


    }

    private void ifSerie() {
        switch (lista) {
            case 1:
            case 2:
            case 3:
            case 4:

        }


        if (idContent != null) {
            con = new Connect();
            con.setSql("", 0);
            con.delegate = this;
            con.Connect();
        }
    }

    private void ifMovie() {
        if (idContent != null) {
            con = new Connect();
            con.setSql("", 0);
            con.delegate = this;
            con.Connect();
        }
    }

    private void setDataForAdd() {
        addToList.add(datos.get(0).get(0));
    }


    private void getDatas() {
        if (idContent != null) {
            con = new Connect();
            con.setSql("Select content.title,content.image,platform.name,platform.link " +
                    "from content " +
                    "Inner join platform " +
                    "where platform.id_platform = content.cod_platform " +
                    "and content.id_content=" + idContent, 0);
            con.delegate = this;
            con.Connect();
        }
    }

    private void setAllDatas() {
        contentDatas.add(datos.get(0).get(0));
        contentDatas.add(datos.get(0).get(1));
        contentDatas.add(datos.get(0).get(2));
        contentDatas.add(datos.get(0).get(3));

    }

    private void getType() {
        if (idContent != null) {
            con = new Connect();
            con.setSql("Select contentType.name from content,contentType where contentType.id_contentType = content.cod_contentType and content.id_content =" + idContent, 0);
            con.delegate = this;
            con.Connect();
        }
    }

    //SPINNER ACTIVITYS
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 1: //CW
                lista = 1;
                getType();
                paso = 1;
                break;
            case 2: //PTW
                break;
            case 3: //C
                break;
            case 4:  //OH
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
