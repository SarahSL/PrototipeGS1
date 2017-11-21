package gs1.gestorsm.prototipegs1;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * 
 */
public class Statistic {

    /**
     * Default constructor
     */
    Connection conexionMySQL;
    public Statistic() {
/*         conexionMySQL = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance ();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    public void setViewedMovies(int viewedMovies) {
        this.viewedMovies = viewedMovies;
    }

    public void setSeriesCompleted(int seriesCompleted) {
        this.seriesCompleted = seriesCompleted;
    }

    public void setViewedChapters(int viewedChapters) {
        this.viewedChapters = viewedChapters;
    }

    public void setHoursViewed(float hoursViewed) {
        this.hoursViewed = hoursViewed;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }

    public void setContentAverageScore(float contentAverageScore) {
        this.contentAverageScore = contentAverageScore;
    }

    public void setAverageMonthlySeries(float averageMonthlySeries) {
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

    /**
     * 
     */

    private int viewedMovies = 1;

    /**
     * 
     */
    private int seriesCompleted = 2;

    /**
     * 
     */
    private int viewedChapters = 4;

    /**
     * 
     */
    private float hoursViewed = 5.4f;

    /**
     * 
     */
    private int recommendations= 5;

    /**
     * 
     */
    private float contentAverageScore = 15f;

    /**
     * 
     */
    private float averageMonthlySeries = 200f;

    /**
     * 
     */
    private float averageMonthlyMovies=100f;





}