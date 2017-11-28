package gs1.gestorsm.prototipegs1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;


/**
 * Created by Geraldo on 20/11/2017.
 */

public class TabMyStats extends Fragment implements ConnectResponse {
    private ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();
    int flag;
    private ArrayList<String> arrayList = new ArrayList<>();
    private View rootView;
    private int idUser = 1; //Variable global
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.mystats_tab, container, false);
        calculateStat();
        return rootView;
    }
    
    private void calculateStat() {
        setViewedMovies();
    }


    private void setViewedMovies() {

        con.setSql("select count(*) from viewList where cod_user = "+ idUser +" and cod_movie is not null", 0);
        con.delegate = this;
        con.Connect();
    }

    public void setViewedChapters() {
        con = new Connect();
        con.setSql( "select count(*) from viewList where cod_user = "+ idUser +" and cod_chapter is not null", 0);
        con.delegate = this;
        con.Connect();

    }

    public void setHoursViewed() {
        con = new Connect();
        con.setSql("select duration from chapter inner join viewList where cod_user = "+ idUser +" and id_chapter = cod_chapter", 0);
        con.delegate = this;
        con.Connect();


    }

    public void setRecommendations() {
        con = new Connect();
        con.setSql("select count(*) from recommendation where cod_user= " + idUser, 0);
        con.delegate = this;
        con.Connect();
    }

    public void setContentAverageScore() {
        con = new Connect();
        con.setSql("select score from evaluation where cod_user = " + idUser, 0);
        con.delegate = this;
        con.Connect();
    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        int res;
        switch (flag) {
            case 0:
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));
                arrayList.add("Viewed Movies: " + res);
                flag = 1;
                setViewedChapters();
                break;

            case 1:
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));
                arrayList.add("Viewed Chapters: " + res);
                flag = 2;
                setHoursViewed();
                break;

            case 2:
                float duration = 0;
                if (datos.size() == 0 || datos.get(0).size() == 0) {
                    duration = 0;
                } else {
                    for (ArrayList<String> j :
                            datos) {
                        for (String string :
                                j) {
                            duration += Float.parseFloat(string);
                        }
                    }
                    duration = duration / 60f;
                }
                arrayList.add("Hours Viewed: " + duration);
                flag = 3;
                setRecommendations();
                break;
            case 3:
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));

                arrayList.add("Recommendations: " + res);
                flag = 4;
                setContentAverageScore();
                break;
            case 4:
                float averageScore;
                if (datos.size() == 0 || datos.get(0).size() == 0) {
                    averageScore = 0;
                } else {
                    float score = 0;
                    float i = 0;
                    for (ArrayList<String> j :
                            datos) {
                        for (String string :
                                j) {
                            score += Float.parseFloat(string);
                            i++;
                        }
                    }
                    if (score != 0) {
                        averageScore = score / i;
                        System.out.println("Hombre "+ score +" " + i);
                    } else {
                        averageScore = 0;
                    }
                }
                arrayList.add("Content Score : " + averageScore);
                arrayList.add("Monthly Series: " + 15);
                arrayList.add("Monthly Movies: " + 10);
                generateListView();
        }
    }

    private void generateListView() {
        ArrayAdapter<String> adapter;
        ListView listView;

        adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_my_stat,R.id.text_view_my_stat,arrayList);

        listView = rootView.findViewById(R.id.list_view_my_stats);

        listView.setAdapter(adapter);

    }

}
