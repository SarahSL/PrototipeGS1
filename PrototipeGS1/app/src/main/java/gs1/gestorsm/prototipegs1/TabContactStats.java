package gs1.gestorsm.prototipegs1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geraldo on 20/11/2017.
 */

public class TabContactStats extends Fragment implements ConnectResponse{
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    Connect con = new Connect();
    View rootView;
    private int flag= 0;
    private ArrayList<String> arrayList;
    private Spinner spinner;
    private Map<String, Integer> map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.contactstats_tab, container, false);
       //getContact();
        doSpinner();
        //getIdContact();
        setActionSpinner();

        return rootView;
    }

    private void setActionSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // int k = map.get(spinner.getAdapter().getItem(i));
                Statistic statistic = new Statistic();
                //statistic.calculateStat();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add((String) spinner.getAdapter().getItem(i));
                arrayList.add("Recommendation: " + statistic.getRecommendations());
                arrayList.add("Viewed Movies: " + statistic.getViewedMovies());
                arrayList.add("Viewed Series: " + statistic.getViewedChapters());
                arrayList.add("Monthly Movies: " + statistic.getAverageMonthlyMovies());
                arrayList.add("Hours Viewed: " + statistic.getHoursViewed());
                arrayList.add("Content Score: " + statistic.getContentAverageScore());
                arrayList.add("Series Completed: " + statistic.getSeriesCompleted());
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_contact_stat,R.id.text_view_contact_stat,arrayList);
                ListView listView = rootView.findViewById(R.id.list_view_contact_stats);
                listView.setAdapter(adapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        switch (flag){
            case 0:
                doSpinner();
                setActionSpinner();

            case 1:
                doMap();
        }

    }

    private void doMap() {
         map = new HashMap<>();
                int i=0;
        for (ArrayList<String> j:
        datos){
            for (String string :
                    j) {
                map.put(arrayList.get(i),Integer.parseInt(string));
                i++;
            }
        }
    }

    private void doSpinner() {
         spinner = rootView.findViewById(R.id.contact_spinner);
        arrayList = new ArrayList<>();
        /*for (ArrayList<String> j:
        datos){
            for (String string :
                    j) {
                arrayList.add(string);
            }
        }*/
        arrayList.add("alo");
        arrayList.add("aloha");
        ArrayAdapter<String>adapter= new ArrayAdapter<String>(getActivity(),R.layout.list_item_name_contact_stat,R.id.text_view_name_contact_stat,arrayList);
        spinner.setAdapter(adapter);
    }

    public void getContact() {
        con.setSql("Select userName from user inner join contact where id_user = cod_contact and cod_user = 1",0);
        con.delegate = this;
        con.Connect();

    }

    public void getIdContact() {
        con.setSql("Select id_user from user inner join contact where id_user = cod_contact and cod_user = 1",0);
        con.delegate = this;
        con.Connect();

    }
}
