package gs1.gestorsm.prototipegs1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.ContentsList.ShowPageContent;

/**
 * Created by topema on 25/11/2017.
 */

public class FiltrarCategoria extends AppCompatActivity implements ConnectResponse {
    Connect con;
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Button enlace, logout;
    TextView category1, category2, title1, title2, sinopsis1, sinopsis2, results;
    String aux;

    String[] category = {"Thriller", "Romantic", "Action", "Horror", "Scyfi", "Western", "Anime", "Comedy", "Drama", "Suspense", "Documentary"};
    int punteroglobal = 0, punteroglobal2 = 0;

    MySession g = MySession.getInstance();

    private ViewFlipper mViewFlipper, mViewFlipper2;
    private float initialX, initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_filtrar);
        mViewFlipper = findViewById(R.id.flipper1);
        mViewFlipper2 = findViewById(R.id.flipper2);

        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);

        sinopsis1 = findViewById(R.id.sinopsis1);
        sinopsis2 = findViewById(R.id.sinopsis2);

        results = findViewById(R.id.result);

        enlace = findViewById(R.id.enlaceContenido);

        enlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowPageContent.class);
                intent.putExtra("aux", aux);
                startActivity(intent);
            }
        });
//BOTON LOGOUT Y USERNAME
        TextView username = findViewById(R.id.username_text);
        username.setText(g.getUsernameLoged());

        ImageButton search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SearchContent.class);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.setId(null);
                g.setUsernameLoged("");
                System.out.println(g.getId());
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivity(intent);
            }
        });


        printCategories();

        TitleAndSinopsis();
    }

    @Override
    public void onBackPressed() {
        Toast to = Toast.makeText(this, "Logout first please", Toast.LENGTH_LONG);
        to.show();
    }

    private void TitleAndSinopsis() {
        con = new Connect();
        con.setSql("Select content.title, content.synopsis,content.id_content from content inner join " +
                "contentCategory where content.id_content = contentCategory.cod_content " +
                "AND cod_category =" + (punteroglobal + 1), 0);
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
                if (initialY < 609) {
                    if (initialX > finalX) {
                        // mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));

                        datos.clear();
                        TitleAndSinopsis();
                        if (punteroglobal == 10) punteroglobal = 0;
                        switch (mViewFlipper.getDisplayedChild()) {
                            case 0:     //categoria0
                                category2.setText(category[punteroglobal]);
                                punteroglobal++;
                                break;
                            case 1:     //categoria1
                                category1.setText(category[punteroglobal]);
                                punteroglobal++;
                                break;
                        }
                        mViewFlipper.showNext();
                    } else {
                        //mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_left));

                        datos.clear();
                        TitleAndSinopsis();

                        if (punteroglobal == 0) punteroglobal = 10;
                        switch (mViewFlipper.getDisplayedChild()) {
                            case 0:     //categoria0
                                category2.setText(category[punteroglobal]);
                                punteroglobal--;
                                break;
                            case 1:     //categoria1
                                category1.setText(category[punteroglobal]);
                                punteroglobal--;
                                break;
                        }
                        mViewFlipper.showPrevious();

                    }
                } else if (initialY > 609) {
                    if (initialX > finalX) {
                        // mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));
                        datos.clear();

                        if (datos.size() > 1) {
                            if (punteroglobal2 > datos.size()) punteroglobal2 = 0;
                            switch (mViewFlipper2.getDisplayedChild()) {
                                case 0:     //categoria0
                                    category2.setText(datos.get(punteroglobal2).get(0));
                                    category2.setText(datos.get(punteroglobal2).get(1));

                                    break;
                                case 1:     //categoria1
                                    category1.setText(datos.get(punteroglobal2).get(0));
                                    category1.setText(datos.get(punteroglobal2).get(1));
                                    break;
                            }
                            punteroglobal2++;
                            mViewFlipper2.showNext();
                        } else {
                            mViewFlipper2.showNext();
                        }
                    } else {
                        //mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
                        //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_left));

                        datos.clear();

                        if (datos.size() > 1) {
                            if (punteroglobal2 < 0) punteroglobal2 = datos.size();
                            switch (mViewFlipper2.getDisplayedChild()) {
                                case 0:     //categoria0
                                    title2.setText(datos.get(punteroglobal2).get(0));
                                    sinopsis2.setText(datos.get(punteroglobal2).get(1));

                                    break;
                                case 1:     //categoria1
                                    title1.setText(datos.get(punteroglobal2).get(0));
                                    sinopsis1.setText(datos.get(punteroglobal2).get(1));
                                    break;
                            }
                            punteroglobal2--;
                            mViewFlipper2.showPrevious();
                        } else {
                            mViewFlipper2.showPrevious();
                        }
                    }
                }
                break;
        }
        return false;
    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        printTitleSinopsis();
    }

    public void printTitleSinopsis() {
        if (!datos.isEmpty()) {
            //    title1.setText(datos.get(0).get(0));
            //  sinopsis1.setText(datos.get(0).get(1));
            switch (datos.size()) {
                case 0:
                    title1.setText("No existe contenido");
                    sinopsis1.setText("No existe contenido en esta categoría, más adelante se incluirá más contenido");
                    title2.setText("No existe contenido");
                    sinopsis2.setText("No existe contenido en esta categoría, más adelante se incluirá más contenido");
                    aux = null;
                    break;
                case 1:
                    title1.setText(datos.get(0).get(0));
                    sinopsis1.setText(datos.get(0).get(1));
                    title2.setText(datos.get(0).get(0));
                    sinopsis2.setText(datos.get(0).get(1));
                    while (mViewFlipper2.getDisplayedChild() != 0) {
                        mViewFlipper2.showNext();
                    }
                    aux = datos.get(0).get(2);
                    break;
                case 2:
                    title1.setText(datos.get(0).get(0));
                    sinopsis1.setText(datos.get(0).get(1));
                    title2.setText(datos.get(1).get(0));
                    sinopsis2.setText(datos.get(1).get(1));
                    while (mViewFlipper2.getDisplayedChild() != 0) {
                        mViewFlipper2.showNext();

                    }
                    aux = datos.get(1).get(2);
                    break;
                default:
                    title1.setText(datos.get(0).get(0));
                    sinopsis1.setText(datos.get(0).get(1));
                    title2.setText(datos.get(1).get(0));
                    sinopsis2.setText(datos.get(1).get(1));
                    while (mViewFlipper2.getDisplayedChild() != 0) {
                        mViewFlipper2.showNext();
                    }
                    aux = datos.get(1).get(2);
                    break;
            }

        } else {
            title1.setText("No existe contenido");
            sinopsis1.setText("No existe contenido en esta categoría, más adelante se incluirá más contenido");
            title2.setText("No existe contenido");
            sinopsis2.setText("No existe contenido en esta categoría, más adelante se incluirá más contenido");
            aux = null;
        }

        results.setText("results: " + Integer.toString(datos.size()));


    }


    public void printCategories() {
        category1.setText(category[0]);
        category2.setText(category[1]);
        punteroglobal = 0;
    }
}

