package gs1.gestorsm.prototipegs1.ContentsList;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import gs1.gestorsm.prototipegs1.R;
/**
 * Created by Sarah on 25/11/2017.
 */
public class ShowContent extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
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
