package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean sem = true;
        if (sem) {
            Intent tabActivity = new Intent(getApplicationContext(), TabActivity.class);
            startActivity(tabActivity);
            //System.out.println("as");
        } else {
            Intent tabActivity = new Intent(getApplicationContext(), DonationActivity.class);
            startActivity(tabActivity);
        }


    }
}
