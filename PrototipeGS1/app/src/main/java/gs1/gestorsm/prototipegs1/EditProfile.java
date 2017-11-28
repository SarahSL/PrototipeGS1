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
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class EditProfile extends AppCompatActivity implements ConnectResponse {

    public static final int PICK_IMAGE = 1;
    MySession g = MySession.getInstance();
    ImageManager imageManager = new ImageManager();
    Bitmap bitmap = null;
    Uri uri = null;
    int flag = 0;
    boolean readyUpdate = false;
    boolean changeImage = false;

    Switch thrillerSw;
    Switch romanticSw;
    Switch actionSw;
    Switch horrorSw;
    Switch scyfiSw;
    Switch westernSw;
    Switch animeSw;
    Switch comedySw;
    Switch dramaSw;
    Switch suspenseSw;
    Switch documentarySw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        declareControls();
        g.setId("1");
        Connect con = new Connect();
        con.setSql("Select userName, name, userImage from user where id_user =" + g.getId(), 0);
        con.delegate = this;
        con.Connect();
    }

    public void declareControls() {
        thrillerSw = findViewById(R.id.thrillerSw);
        romanticSw = findViewById(R.id.romanticSw);
        actionSw = findViewById(R.id.actionSw);
        horrorSw = findViewById(R.id.horrorSw);
        scyfiSw = findViewById(R.id.scifySw);
        westernSw = findViewById(R.id.westernSw);
        animeSw = findViewById(R.id.animeSw);
        comedySw = findViewById(R.id.comedySw);
        dramaSw = findViewById(R.id.dramaSw);
        suspenseSw = findViewById(R.id.suspenseSw);
        documentarySw = findViewById(R.id.documentarySw);
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        if (flag == 0) {
            readProfile(datos);
            Connect con = new Connect();
            con.setSql("Select id_category from category inner join user, preference where user.id_user=preference.cod_user and " +
                       "preference.cod_category=category.id_category and " +
                       "user.id_user=" + g.getId(), 0);
            con.delegate = this;
            con.Connect();
            flag = 1;
        } else if (flag == 1) {
            fillPreferences(datos);
            readyUpdate = true;
            flag = 2;
        } else if (flag == 2) {
            erasePreferences();
            flag = 3;
        } else if (flag == 3) {
            updatePreferences();
            flag = 4;
        } else if (flag == 4) {
            onBackPressed();
        }
    }

    public void erasePreferences() {
        Connect con = new Connect();
        con.setSql("DELETE FROM preference where cod_user=" + g.getId(), 1);
        con.delegate = this;
        con.Connect();
    }

    public void fillPreferences(ArrayList<ArrayList<String>> datos) {
        if (datos.size() > 0) {
            for (int i = 0; i < datos.size(); i++) {
                switch (datos.get(i).get(0)) {
                    case "1":
                        thrillerSw.setChecked(true);
                        break;
                    case "2":
                        romanticSw.setChecked(true);
                        break;
                    case "3":
                        actionSw.setChecked(true);
                        break;
                    case "4":
                        horrorSw.setChecked(true);
                        break;
                    case "5":
                        scyfiSw.setChecked(true);
                        break;
                    case "6":
                        westernSw.setChecked(true);
                        break;
                    case "7":
                        animeSw.setChecked(true);
                        break;
                    case "8":
                        comedySw.setChecked(true);
                        break;
                    case "9":
                        dramaSw.setChecked(true);
                        break;
                    case "10":
                        suspenseSw.setChecked(true);
                        break;
                    case "11":
                        documentarySw.setChecked(true);
                        break;
                }
            }
        }
    }

    public void readProfile(ArrayList<ArrayList<String>> datos) {
        EditText userReg = findViewById(R.id.userReg);
        EditText nameReg = findViewById(R.id.nameReg);
        ImageView imgUserView = findViewById(R.id.imgUserView);

        userReg.setText(datos.get(0).get(0));
        nameReg.setText(datos.get(0).get(1));
        String strImage = datos.get(0).get(2);
        if (strImage != null) {
            imageManager.setStringImgtoImageView(imgUserView, datos.get(0).get(2));
        }
    }

    public void updateProfile(View view) {
        if (readyUpdate) {
            EditText userReg = findViewById(R.id.userReg);
            EditText passReg1 = findViewById(R.id.passReg1);
            EditText passReg2 = findViewById(R.id.passReg2);
            EditText nameReg = findViewById(R.id.nameReg);
            String pass1 = passReg1.getText().toString();
            String pass2 = passReg2.getText().toString();
            String name = nameReg.getText().toString();
            String user = userReg.getText().toString();
            if (!pass1.isEmpty() && !name.isEmpty() && !user.isEmpty() && (pass1.equals(pass2))) {
                Connect con = new Connect();
                System.out.println(changeImage);
                if (changeImage) {
                    con.setSql("UPDATE user SET userName='" + user + "',password='" + pass1 + "',name = '" + name + "',userImage='" + imageManager.imgToBase64(bitmap) + "' WHERE id_user=" + g.getId(), 1);
                } else {
                    con.setSql("UPDATE user SET userName='" + user + "',password='" + pass1 + "',name = '" + name + "' WHERE id_user=" + g.getId(), 1);
                }
                con.delegate = this;
                con.Connect();
            } else {
                Toast.makeText(this, "Error al introducir los datos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Un segundito que terminamos de cargar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePreferences() {
        ArrayList<Integer> preferences = checkSw();
        String buildSql = "";
        for (int i = 0; i < preferences.size(); i++) {
            buildSql += "(" + g.getId() + "," + preferences.get(i) + ")";
            if (i < preferences.size() - 1) {
                buildSql += ",";
            }
        }
        Connect con = new Connect();
        con.setSql("INSERT INTO preference(cod_user,cod_category) VALUES " + buildSql, 1);
        con.delegate = this;
        con.Connect();
    }


    public ArrayList<Integer> checkSw() {
        ArrayList<Integer> preferences = new ArrayList<>();
        if (thrillerSw.isChecked()) preferences.add(1);
        if (romanticSw.isChecked()) preferences.add(2);
        if (actionSw.isChecked()) preferences.add(3);
        if (horrorSw.isChecked()) preferences.add(4);
        if (scyfiSw.isChecked()) preferences.add(5);
        if (westernSw.isChecked()) preferences.add(6);
        if (animeSw.isChecked()) preferences.add(7);
        if (comedySw.isChecked()) preferences.add(8);
        if (dramaSw.isChecked()) preferences.add(9);
        if (suspenseSw.isChecked()) preferences.add(10);
        if (documentarySw.isChecked()) preferences.add(11);
        return preferences;
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
            changeImage = true;
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

    public void back(View view) {
        onBackPressed();
    }
}
