package gs1.gestorsm.prototipegs1.ContentsList;

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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
 * Created by Javier on 25/11/2017.
 */

public class ShowPageContent extends AppCompatActivity implements ConnectResponse{
    private SectionsPageAdapter mSectionsPageAdapter;
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    private ViewPager mViewPager;
    String idContent;
    Connect con;
    MySession g = MySession.getInstance();
    ArrayList<String> contentDatas=new ArrayList<>();


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
        Spinner spinner = (Spinner) findViewById(R.id.spinner_ContentPage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Add_to_list,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //GETDATAS
        getDatas();
        //TO DO_ FIX THE IMAGE
    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //AÑADIR TODOS LOS FRAGMENTS
        adapter.addFragment(new SinopsisFragment(),"Sinopsis");
        adapter.addFragment(new EpisodesFragment(),"Episodes");
        adapter.addFragment(new SocialFragment(),"Social");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        setAllDatas();

        TextView textView = findViewById(R.id.show_page_content_text);
        textView.setText(contentDatas.get(0));
        TextView nameP = findViewById(R.id.name_of_platform);
        nameP.setText(contentDatas.get(2));
        TextView linkP = findViewById(R.id.link_for_platform);
        linkP.setText(contentDatas.get(3));
       // new DownloadImageTask((ImageView) findViewById(R.id.imageView_contentPage)).execute(contentDatas.get(1));



    }
    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }*/
    private void getDatas(){
        con = new Connect();
        con.setSql("Select content.title,content.image,platform.name,platform.link " +
                "from content " +
                "Inner join platform " +
                "where platform.id_platform = content.cod_platform " +
                "and content.id_content="+idContent+"", 0);
        con.delegate = this;
        con.Connect();
    }
    private void setAllDatas(){
        contentDatas.add(datos.get(0).get(0));
        contentDatas.add(datos.get(0).get(1));
        contentDatas.add(datos.get(0).get(2));
        contentDatas.add(datos.get(0).get(3));

    }
}
