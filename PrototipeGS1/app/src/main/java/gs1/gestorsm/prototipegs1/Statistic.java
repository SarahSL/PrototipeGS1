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
public class Statistic {


    Connection conexionMySQL;
    DatabaseMetaData metaData;
    private Statement statement;
    private ResultSet resultSet;

    public Statistic() /*throws SQLException*/ {
         /*conexionMySQL = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexionMySQL = DriverManager.getConnection(
                    "jdbc:mysql://46.101.178.225:3306/gs1Prototipo",
                    "explotacion",
                    "pandeHuevo");

           // metaData = conexionMySQL.getMetaData();
        } catch (ClassNotFoundException e) {
            System.out.println("Excepcion 3");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Excepcion 4");
            e.printStackTrace();
        }
        try {
            calculateStat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexionMySQL.close();*/
    }

    private void calculateStat() throws SQLException {
        //statement = conexionMySQL.createStatement();
        //setViewedMovies();
        //resultSet = statement.executeQuery("select count(*) from viewlist");
        //if (resultSet.next()) System.out.println(resultSet.getLong(0) + "coche");
        //System.out.println(resultSet.toString());
        //viewedMovies = Integer.parseInt(resultSet.toString());
        //ResultSet resultSet = conexionMySQL.setS.getColumns(null,null,"tabla00",null);
        //resultSet.getMetaData().get*/

    }

    public void setViewedMovies() throws SQLException {
        resultSet = statement.executeQuery("select count(*) from viewlist where cod_movie <> null");
        viewedMovies = viewedMovies;
    }

    public void setSeriesCompleted(int seriesCompleted) throws SQLException {
        resultSet = statement.executeQuery("select count(*) from viewlist where cod_chapter <> null");
        this.seriesCompleted = seriesCompleted;
    }

    public void setViewedChapters(int viewedChapters)  throws SQLException{
        resultSet = statement.executeQuery("select count(*) from viewlist where cod_chapter <> null");
        this.viewedChapters = viewedChapters;
    }

    public void setHoursViewed(float hoursViewed) throws SQLException {
        this.hoursViewed = hoursViewed;
    }

    public void setRecommendations(int recommendations) throws SQLException {
        resultSet = statement.executeQuery("select count(*) from recommendation ");
        this.recommendations = recommendations;
    }

    public void setContentAverageScore(float contentAverageScore) throws SQLException{
        this.contentAverageScore = contentAverageScore;
    }

    public void setAverageMonthlySeries(float averageMonthlySeries) throws SQLException{
        this.averageMonthlySeries = averageMonthlySeries;
    }

    public void setAverageMonthlyMovies(float averageMonthlyMovies) throws SQLException{
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