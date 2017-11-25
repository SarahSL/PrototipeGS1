package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by topema on 25/11/2017.
 */

public class commentContent extends AppCompatActivity implements ConnectResponse {

    Connect con;
    EditText editText;
    Button comentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        editText = findViewById(R.id.editText);
        comentar = findViewById(R.id.commentButton);


    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {

    }
}
