package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Jorge on 25/11/2017.
 */

public class Register extends AppCompatActivity implements ConnectResponse {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {

    }
}