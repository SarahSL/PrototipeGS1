package gs1.gestorsm.prototipegs1;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.*;


/**
 *
 */
public class Statistic implements ConnectResponse {
    private ArrayList<ArrayList<String>> datos= new ArrayList<>();
    Connect con = new Connect();
    int flag;
    private int viewedMovies;

    private final  int idUser;

    private final int typeUser;

    private final View rootView;

    private final FragmentActivity activity;

    private int viewedChapters = 4;

    private float hoursViewed = 5.4f;

    private int recommendations= 5;

    private float contentAverageScore = 15f;

    private float averageMonthlySeries = 200f;

    private float averageMonthlyMovies=100f;

    public Statistic(int idUser, int typeUser, View rootView, FragmentActivity activity) {
        this.idUser = idUser;
        this.typeUser = typeUser;
        this.rootView = rootView;
        this.activity = activity;
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;

        switch (flag) {
            case 0:
                System.out.println("bicleta " + datos.get(0));
                System.out.println("coche + " + flag + " dayos " + datos.get(0).get(0));
                viewedMovies = Integer.parseInt(datos.get(0).get(0));
                System.out.println(viewedMovies);
                flag=1;
                setViewedChapters();
                break;

            case 1:

                if(datos.size() == 0 || datos.get(0).size() == 0)  {
                    viewedChapters = 0;
                } else {
                    System.out.println("coche + " + flag + " dayos " + datos.get(0).get(0));
                    viewedChapters = Integer.parseInt(datos.get(0).get(0));
                }
                flag=2;
                setHoursViewed();
            break;

            case 2:
                if(datos.size() == 0 || datos.get(0).size() == 0)  {
                    hoursViewed = 0;
                } else {
                    System.out.println("coche + " + flag + " dayos " + datos.get(0).get(0));
                    float duration = 0;
                    for (ArrayList<String> j :
                            datos) {
                        for (String string :
                                j) {
                            duration += Float.parseFloat(string);
                        }
                    }
                    hoursViewed = duration / 60f;
                }
                flag = 3;
                setRecommendations();
                break;
            case 3:
                if(datos.size() == 0 || datos.get(0).size() == 0)  {
                    recommendations = 0;
                } else {
                    System.out.println("coche + " + flag + " dayos " + datos.get(0).get(0));
                    recommendations = Integer.parseInt(datos.get(0).get(0));
                }
                flag = 4;
                setContentAverageScore();
                break;
            case 4:
                if(datos.size() == 0 || datos.get(0).size() == 0) {
                    contentAverageScore = 0;
                }else {
                    System.out.println("coche + " + flag + " dayos " + datos.get(0).get(0));
                    float score = 0;
                    float i = 0;
                    for (ArrayList<String> j :
                            datos) {
                        for (String string :
                                j) {
                            score = Float.parseFloat(string);
                            i++;
                        }
                    }
                    if (score != 0) {
                        contentAverageScore = score / i;
                    } else {
                        contentAverageScore = 0;
                    }
                }
                generateListView();

        }
    }

    private void generateListView() {
        ArrayAdapter<String> adapter;
        ListView listView;
        if(typeUser == 1){
            adapter = new ArrayAdapter<String>(activity,R.layout.list_item_my_stat,R.id.text_view_my_stat,createArray());
            listView = rootView.findViewById(R.id.list_view_my_stats);

        } else {
            adapter = new ArrayAdapter<String>(activity,R.layout.list_item_contact_stat,R.id.text_view_contact_stat,createArray());
            listView = rootView.findViewById(R.id.list_view_contact_stats);

        }
        listView.setAdapter(adapter);

    }

    private ArrayList createArray() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Recommendation: " + getRecommendations());
        arrayList.add("Viewed Movies: " + getViewedMovies());
        arrayList.add("Viewed Series: " + getViewedChapters());
        arrayList.add("Hours Viewed: " + getHoursViewed());
        arrayList.add("Content Score: " + getContentAverageScore());
        arrayList.add("Monthly Series: " + getAverageMonthlySeries());
        arrayList.add("Monthly Movies: " + getAverageMonthlyMovies());
        return arrayList;

    }



    private void setViewedMovies() {

        con.setSql("select count(*) from viewList where cod_user = " + idUser + " and cod_movie is not null",0);
        con.delegate = this;
        con.Connect();
    }

    public void setViewedChapters()  {
        con = new Connect();
        con.setSql("select duration from chapter inner join viewList where  cod_user = "+ idUser+" and id_chapter = cod_chapter",0);
        con.delegate = this;
        con.Connect();


    }

    public void setHoursViewed() {
        con = new Connect();
        con.setSql("select count(*) from viewList where cod_user = "+idUser+" cod_chapter <> null",0);
        con.delegate = this;
        con.Connect();


    }

    public void setRecommendations() {
        con = new Connect();
        con.setSql("select count(*) from recomendation where cod_user=" + idUser,0);
        con.delegate = this;
        con.Connect();
    }

    public void setContentAverageScore() {
        con = new Connect();
        con.setSql("select score from evaluation where cod_user = " + idUser,0);
        con.delegate = this;
        con.Connect();
    }

    public void setAverageMonthlySeries(float averageMonthlySeries){
        this.averageMonthlySeries = averageMonthlySeries;
    }

    public void setAverageMonthlyMovies(float averageMonthlyMovies) {
        this.averageMonthlyMovies = averageMonthlyMovies;
    }

    public int getViewedMovies() {
        return viewedMovies;
    }


    public int getViewedChapters() {
        return viewedChapters;
    }

    public float getHoursViewed() {
        return hoursViewed;
    }

    public int getRecommendations() {
        return recommendations;
    }

    public float getContentAverageScore() {
        return contentAverageScore;
    }

    public float getAverageMonthlySeries() {
        return averageMonthlySeries;
    }

    public float getAverageMonthlyMovies() {
        return averageMonthlyMovies;
    }


    public void calculateStat() {
        flag = 0;
        setViewedMovies();

    }
}
