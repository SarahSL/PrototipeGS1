package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jorge on 25/11/2017.
 */

//CAMBIAR POR FILTRACATEGORIA.
public class Logued extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logued);
        //EJEMPLO DONDE RECOGEMOS LA VARIABLE GLOBAL QUE TIENE EL NUMERO
        MySession g = MySession.getInstance();
        System.out.println(g.getId() + " - USER LOGUED");
    }
}