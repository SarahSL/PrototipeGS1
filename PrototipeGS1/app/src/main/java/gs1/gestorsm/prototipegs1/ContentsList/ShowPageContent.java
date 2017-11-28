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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import gs1.gestorsm.prototipegs1.Login;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;
import gs1.gestorsm.prototipegs1.SearchContent;

/**
 * Created by Sarah on 25/11/2017.
 */

public class ShowPageContent extends AppCompatActivity implements ConnectResponse, AdapterView.OnItemSelectedListener {
    private SectionsPageAdapter mSectionsPageAdapter;
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    private ViewPager mViewPager;
    String idContent, idUser;
    String idCap = "1";
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
//PARTE DE ARRIBA
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


        Intent intent = getIntent();
        idContent = intent.getStringExtra("aux");
        g.setIdContent_PageContent(idContent);
        idUser = g.getId();

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
            if (addToList.get(0).equals("serie")) {
                paso = 2;
                ifEstaYserie();
            } else {
                paso = 2;
                whatMovieIam();
            }
        } else if (paso == 2) {
            System.out.println(datos.size());
            if (addToList.get(0).equals("serie")) {
                if (datos.size() == 0) {
                    paso = 6;
                    Toast.makeText(this, "Adding to the list", Toast.LENGTH_SHORT).show();
                    insertarEnListSerie();
                } else {
                    Toast.makeText(this, "It's already in the list", Toast.LENGTH_SHORT).show();
                }
            } else {
                paso = 3;
                setDataForAdd();
                ifEStaYMovie();
            }
        } else if (paso == 3) {
            if (datos.size() == 0) {
                Toast.makeText(this, "Adding to the list", Toast.LENGTH_SHORT).show();
                paso = 5;
                insertarEnListMovie();
            } else {
                Toast.makeText(this, "It's already in the list", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void whatMovieIam() {
        if (idContent != null) {
            con = new Connect();
            con.setSql("Select movie.id_movie from movie where movie.cod_content=" + idContent, 0);
            con.delegate = this;
            con.Connect();
        }
    }

    private void insertarEnListMovie() {
        switch (lista) {
            case 1:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO watchingList(cod_user,cod_movie,cod_chapter) Values(" + idUser +","+ addToList.get(1) + ",null)", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 2:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO desiredList(cod_user,cod_movie,cod_chapter) Values(" + idUser +","+ addToList.get(1) + ",null)", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 3:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO viewList(cod_user,cod_movie,cod_chapter) Values(" + idUser +","+ addToList.get(1) + ",null)", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 4:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO waitingList(cod_user,cod_movie,cod_chapter) Values(" + idUser +","+ addToList.get(1) + ",null)", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;

        }
    }

    private void insertarEnListSerie() {
        switch (lista) {
            case 1:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO watchingList(cod_user,cod_movie,cod_chapter) Values(" + idUser + ",null," + idCap + ")", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 2:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO desiredList(cod_user,cod_movie,cod_chapter) Values(" + idUser + ",null," + idCap + ")", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 3:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO wiewList(cod_user,cod_movie,cod_chapter) Values(" + idUser + ",null," + idCap + ")", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 4:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("INSERT INTO waitingList(cod_user,cod_movie,cod_chapter) Values(" + idUser + ",null," + idCap + ")", 1);
                    con.delegate = this;
                    con.Connect();
                }
                break;

        }
    }

    private void ifEstaYserie() {
        switch (lista) {
            case 1:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select watchingList.id_watchingList from watchingList " +
                            "inner join chapter,user,serie,season,content " +
                            "where content.id_content=" + idContent +
                            " and content.id_content = serie.cod_content " +
                            "and serie.id_serie = season.cod_serie " +
                            "and season.id_season = chapter.cod_season " +
                            "and chapter.id_chapter =" + idCap +
                            " and chapter.id_chapter = watchingList.cod_chapter " +
                            "and watchingList.cod_user = user.id_user and user.id_user =" + idUser, 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 2:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select desiredList.id_desiredList from desiredList " +
                            "inner join chapter,user,serie,season,content " +
                            "where content.id_content=" + idContent +
                            " and content.id_content = serie.cod_content " +
                            "and serie.id_serie = season.cod_serie " +
                            "and season.id_season = chapter.cod_season " +
                            "and chapter.id_chapter =" + idCap +
                            " and chapter.id_chapter = desiredList.cod_chapter " +
                            "and desiredList.cod_user = user.id_user and user.id_user =" + idUser, 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 3:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select viewList.id_viewList from viewList " +
                            "inner join chapter,user,serie,season,content " +
                            "where content.id_content=" + idContent +
                            " and content.id_content = serie.cod_content " +
                            "and serie.id_serie = season.cod_serie " +
                            "and season.id_season = chapter.cod_season " +
                            "and chapter.id_chapter =" + idCap +
                            " and chapter.id_chapter = viewList.cod_chapter " +
                            "and viewList.cod_user = user.id_user and user.id_user =" + idUser, 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 4:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select waitingList.id_waitingList from waitingList " +
                            "inner join chapter,user,serie,season,content " +
                            "where content.id_content=" + idContent +
                            " and content.id_content = serie.cod_content " +
                            "and serie.id_serie = season.cod_serie " +
                            "and season.id_season = chapter.cod_season " +
                            "and chapter.id_chapter =" + idCap +
                            " and chapter.id_chapter = waitingList.cod_chapter " +
                            "and waitingList.cod_user = user.id_user and user.id_user =" + idUser, 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;

        }
    }

    private void ifEStaYMovie() {
        switch (lista) {
            case 1:
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select watchingList.cod_movie from watchingList inner join user,movie,content "+
                            "where user.id_user = watchingList.cod_user "+
                            "and user.id_user="+idUser+
                            " and content.id_content ="+idContent+
                            " and movie.id_movie=" + addToList.get(1) +
                            " and watchingList.cod_movie = movie.id_movie", 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 2: //PTW
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select desiredList.cod_movie from desiredList inner join user,movie,content "+
                            "where user.id_user = desiredList.cod_user "+
                            "and user.id_user="+idUser+
                            " and content.id_content ="+idContent+
                            " and movie.id_movie=" + addToList.get(1) +
                            " and desiredList.cod_movie = movie.id_movie", 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 3: //completed
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select viewList.cod_movie from viewList inner join user,movie,content "+
                            "where user.id_user = viewList.cod_user "+
                            "and user.id_user="+idUser+
                            " and content.id_content ="+idContent+
                            " and movie.id_movie=" + addToList.get(1) +
                            " and viewList.cod_movie = movie.id_movie", 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
            case 4: //OH
                if (idContent != null) {
                    con = new Connect();
                    con.setSql("Select waitingList.cod_movie from waitingList inner join user,movie,content "+
                            "where user.id_user = waitingList.cod_user "+
                            "and user.id_user="+idUser+
                            " and content.id_content ="+idContent+
                            " and movie.id_movie=" + addToList.get(1) +
                            " and waitingList.cod_movie = movie.id_movie", 0);
                    con.delegate = this;
                    con.Connect();
                }
                break;
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
        EditText editText = findViewById(R.id.number_chapter);
        idCap = editText.getText().toString();
        switch (i) {
            case 1: //CW
                lista = 1;
                paso = 1;
                getType();
                break;
            case 2: //PTW
                lista = 2;
                paso = 1;
                getType();
                break;
            case 3: //C
                lista = 3;
                paso = 1;
                getType();
                break;
            case 4:  //OH
                lista = 4;
                paso = 1;
                getType();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
