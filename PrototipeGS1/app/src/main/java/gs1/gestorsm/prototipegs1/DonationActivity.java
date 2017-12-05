package gs1.gestorsm.prototipegs1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import gs1.gestorsm.prototipegs1.R;

public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
    }

    public void donation(View view){
        Toast.makeText(this, "Dirigido a la pasarela de pago.\nNO IMPLEMENTADO.", Toast.LENGTH_LONG).show();
    }


}
