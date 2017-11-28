package gs1.gestorsm.prototipegs1.ContentsList;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import gs1.gestorsm.prototipegs1.Login;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;
import gs1.gestorsm.prototipegs1.SearchContent;

/**
 * Created by Sarah on 25/11/2017.
 */
public class ShowContent extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    MySession g = MySession.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //AÑADIR TODOS LOS FRAGMENTS
        adapter.addFragment(new CurrentlyWatchingFragment(),"Currently watching");
        adapter.addFragment(new PlanToWatchFragment(),"Plan to watch");
        adapter.addFragment(new CompletedFragment(),"Completed");
        adapter.addFragment(new OnHoldFragment(),"On hold");

        viewPager.setAdapter(adapter);



    }

}
