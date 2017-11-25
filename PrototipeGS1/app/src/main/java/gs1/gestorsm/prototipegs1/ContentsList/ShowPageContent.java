package gs1.gestorsm.prototipegs1.ContentsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Javier on 25/11/2017.
 */

public class ShowPageContent extends AppCompatActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page_show);
        Intent intent = getIntent();
        String extra = intent.getStringExtra("aux");
        System.out.println(extra);
        TextView textView = findViewById(R.id.show_page_content_text);
        textView.setText(extra);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //AÑADIR TODOS LOS FRAGMENTS
        adapter.addFragment(new SinopsisFragment(),"Sinopsis");

        viewPager.setAdapter(adapter);
    }
}
