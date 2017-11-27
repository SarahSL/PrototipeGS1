package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by topema on 25/11/2017.
 */

public class CommentContent extends AppCompatActivity implements ConnectResponse {

    Connect con;
    EditText editText;
    Button comenta, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        editText = findViewById(R.id.editText);
        comenta = findViewById(R.id.commentButton);
        back = findViewById(R.id.buttonBack);

        comenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment();
            }
        });
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {

    }

    public void comment() {
        con = new Connect();
        con.setSql("INSERT INTO comment (cod_user, cod_content,comment) values(1,1,"+editText.getText()+")", 1);
        con.delegate = this;
        con.Connect();
    }
}
