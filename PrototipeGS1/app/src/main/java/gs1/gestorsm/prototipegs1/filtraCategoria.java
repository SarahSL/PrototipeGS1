package gs1.gestorsm.prototipegs1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * Created by topema on 25/11/2017.
 */

public class filtraCategoria extends AppCompatActivity implements ConnectResponse  {

    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<ArrayList<String>> contents = new ArrayList<>();
    Connect con = new Connect();
    TextView category1, category2, category3, title1,title2,title3,sinopsis1,sinopsis2,sinopsis3;
    int punterolocal=0,punteroglobal=0;
    int[] x = new int[3];

    private ViewFlipper mViewFlipper, mViewFlipper2;
    private Context mContext;
    private float initialX,initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryfilter);
        mContext = this;
        mViewFlipper = findViewById(R.id.flipper1);
        mViewFlipper2 = findViewById(R.id.flipper2);
        mViewFlipper2.setVisibility(View.INVISIBLE);

        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);




        //con.setSql("Select * from user");
        //Mode 0 - Select
        //Mode 1 - Update y Delete
        con.setSql("SELECT * from category", 0);
        con.delegate = this;
        con.Connect();


    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        initialY = touchevent.getY();

        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialY < 609){
                    if (initialX > finalX) {
                        // mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));
                        if(punterolocal==2){
                            if(punteroglobal==11) punteroglobal=0;
                            switch(mViewFlipper.getDisplayedChild()){
                                case 0:     //categoria0
                                    category2.setText(datos.get(punteroglobal).get(1));
                                    break;
                                case 1:     //categoria1
                                    category3.setText(datos.get(punteroglobal).get(1));
                                    break;
                                case 2:     //categoria2
                                    category1.setText(datos.get(punteroglobal).get(1));
                                    break;
                            }
                        }else{
                            punterolocal++;
                        }
                        punteroglobal++;

                        con.setSql("SELECT" + datos.get(punteroglobal).get(0) + "from category", 0);
                        con.delegate = this;
                        con.Connect();

                        mViewFlipper.showNext();
                    } else {
                        //mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_left));

                        if(punterolocal==0){
                            if(punteroglobal==0) punteroglobal=10;
                            switch(mViewFlipper.getDisplayedChild()){
                                case 0:     //categoria0
                                    category3.setText(datos.get(punteroglobal).get(1));
                                    break;
                                case 1:     //categoria1
                                    category1.setText(datos.get(punteroglobal).get(1));
                                    break;
                                case 2:     //categoria2
                                    category2.setText(datos.get(punteroglobal).get(1));
                                    break;
                            }
                        }else{
                            punterolocal--;
                        }
                        punteroglobal--;

                        con.setSql("SELECT name from category", 0);
                        con.delegate = this;
                        con.Connect();

                        mViewFlipper.showPrevious();
                    }
                }else if(initialY > 609){
                    if (initialX > finalX) {
                        // mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));

                        mViewFlipper2.showNext();
                    } else {
                        //mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_left));

                        mViewFlipper2.showPrevious();
                    }
                }
                break;
        }
        return false;
    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        if(datos.isEmpty()) {
            this.datos = datos;
            printCategories();
        }else{
            this.contents = datos;
        }
    }

    public void printCategories() {
        category1.setText(datos.get(0).get(1));
        category2.setText(datos.get(1).get(1));
        category3.setText(datos.get(2).get(1));



        for (int i = 0; i <11 ; i++) {
            System.out.println(datos.get(i).get(1));

        }
    }
}
