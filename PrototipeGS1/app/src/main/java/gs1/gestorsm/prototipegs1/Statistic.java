package gs1.gestorsm.prototipegs1;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 */
public class Statistic implements ConnectResponse {
    private ArrayList<ArrayList<String>> datos= new ArrayList<>();
    Connect con = new Connect();
    int flag;
    private int viewedMovies;

    private int seriesCompleted = 2;

    private int viewedChapters = 4;

    private float hoursViewed = 5.4f;

    private int recommendations= 5;

    private float contentAverageScore = 15f;

    private float averageMonthlySeries = 200f;

    private float averageMonthlyMovies=100f;

    public Statistic()  {

    }
    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        switch (flag) {
            case 0:
            viewedMovies = Integer.parseInt(datos.get(0).get(0));
            flag=1;
                setViewedChapters();

            case 1:
                viewedChapters = Integer.parseInt(datos.get(0).get(0));
                setHoursViewed();
                flag=2;
            case 2:
                float duration = 0;
                for (ArrayList<String> j :
                        datos) {
                    for (String string :
                            j) {
                        duration += Float.parseFloat(string);
                    }
                }
                hoursViewed = duration/60f;
                flag = 3;
                setRecommendations();
            case 3:
                recommendations = Integer.parseInt(datos.get(0).get(0));
                setContentAverageScore();
                flag = 4;
            case 4:
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
                if (score!= 0){
                    contentAverageScore = score/i;
                } else {
                    contentAverageScore = 0;
                }
        }
    }


    public void setViewedMovies() {

        con.setSql("select count(*) from viewlist where cod_user = 1 and cod_movie <> null",0);
        con.delegate = this;
        con.Connect();

    }

    public void setSeriesCompleted() {

        this.seriesCompleted = seriesCompleted;
    }

    public void setViewedChapters()  {
        con.setSql("select duration from chapter inner join viewlist where id_chapter = cod_chapter",0);
        con.delegate = this;
        con.Connect();

        this.viewedChapters = viewedChapters;
    }

    public void setHoursViewed() {
        con.setSql("select count(*) from viewlist where cod_chapter <> null",0);
        con.delegate = this;
        con.Connect();

        this.hoursViewed = hoursViewed;
    }

    public void setRecommendations() {
        con.setSql("select count(*) from recomendation where cod_user=1",0);
        con.delegate = this;
        con.Connect();

        this.recommendations = recommendations;
    }

    public void setContentAverageScore() {

        con.setSql("select score from evaluation where cod_user = 1",0);
        con.delegate = this;
        con.Connect();

        this.contentAverageScore = contentAverageScore;
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

    public int getSeriesCompleted() {
        return seriesCompleted;
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
