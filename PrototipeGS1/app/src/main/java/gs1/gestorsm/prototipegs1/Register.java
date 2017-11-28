package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jorge on 25/11/2017.
 */

public class Register extends AppCompatActivity implements ConnectResponse {


    public static final int PICK_IMAGE = 1;
    Uri uri = null;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        System.out.println(output);
        onBackPressed();
    }

    public void imgButton(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView a = findViewById(R.id.imgUserView);
                a.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registrar(View view) {
        Connect con = new Connect();
        ImageManager imageManager = new ImageManager();
        EditText user = findViewById(R.id.userReg);
        EditText pass1 = findViewById(R.id.passReg1);
        EditText pass2 = findViewById(R.id.passReg2);
        EditText name = findViewById(R.id.nameReg);
        if (bitmap != null) {
            if (pass1.getText().toString().equals(pass2.getText().toString())) {
                //con.setSql("INSERT INTO user(userName,password,name) values('" + user.getText() + "','" + pass1.getText().toString() + "','" + name.getText() + "')", 1);
                con.setSql("INSERT INTO user(userName,password,name,userImage) values('" + user.getText() + "','" + pass1.getText().toString() + "','" + name.getText() + "','" + imageManager.imgToBase64(bitmap) + "')", 1);
                con.delegate = this;
                con.Connect();
            } else {
                Toast.makeText(this, "La contraseñas introducidas no coinciden.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe de elegir una imagen de usuario.", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        onBackPressed();
    }

}