package gs1.gestorsm.prototipegs1;

import java.util.*;

/**
 * 
 */
public class Statistic {

    /**
     * Default constructor
     */
    public Statistic() {
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