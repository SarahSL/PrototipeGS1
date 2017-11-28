package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
    private ArrayList<String> arrayList2;
    private Spinner spinner;
    private Map<String, Integer> map = new HashMap<>();
    private int idContact;
    private int idUser = 2; // Variable global

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.contactstats_tab, container, false);
        getContacts();
        return rootView;
    }

    public void getContacts() {
        con.setSql("Select userName,id_user from user inner join contact where id_user = cod_contact and cod_user = "+idUser,0);
        con.delegate = this;
        con.Connect();

    }
    private void setActionSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    idContact = map.get(spinner.getAdapter().getItem(pos));
                    calculateStat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void calculateStat() {
        setViewedMovies();
    }

    private void setViewedMovies() {
        flag = 1;
        con = new Connect();
        con.setSql("select count(*) from viewList where cod_user = "+ idContact +" and cod_movie is not null", 0);
        con.delegate = this;
        con.Connect();
    }

    public void setViewedChapters() {
        con = new Connect();
        con.setSql("select count(*) from viewList where cod_user = "+ idContact +" and cod_chapter is not null", 0);
        con.delegate = this;
        con.Connect();

    }

    public void setHoursViewed() {
        con = new Connect();
        con.setSql("select duration from chapter inner join viewList where  cod_user = "+ idContact +" and id_chapter = cod_chapter", 0);
        con.delegate = this;
        con.Connect();


    }

    public void setRecommendations() {
        con = new Connect();
        con.setSql("select count(*) from recommendation where cod_user= " + idContact, 0);
        con.delegate = this;
        con.Connect();
    }

    public void setContentAverageScore() {
        con = new Connect();
        con.setSql("select score from evaluation where cod_user = "+ idContact, 0);
        con.delegate = this;
        con.Connect();
    }


    @Override
    public void processFinish(String output, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        int res;
        switch (flag){
            case 0:
                flag=1;
                doSpinner();
                setActionSpinner();

            break;

            case 1:
                arrayList2 = new ArrayList<>();
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));
                arrayList2.add("Viewed Movies: " + res);
                flag++;
                setViewedChapters();
                break;

            case 2:
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));
                arrayList2.add("Viewed Chapters: " + res);
                flag++;
                setHoursViewed();
                break;

            case 3:

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
                arrayList2.add("Hours Viewed: " + duration);
                flag ++;
                setRecommendations();
                break;
            case 4:
                if (datos.size() == 0 || datos.get(0).size() == 0) res = 0;
                else res = Integer.parseInt(datos.get(0).get(0));

                arrayList2.add("Recommendations: " + res);
                flag ++;
                setContentAverageScore();
                break;
            case 5:
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
                    } else {
                        averageScore = 0;
                    }
                }
                arrayList2.add("Content Score : " + averageScore);
                arrayList2.add("Monthly Series: " + 15);
                arrayList2.add("Monthly Movies: " + 10);
                generateListView();
        }
    }

    private void generateListView() {
        ArrayAdapter<String> adapter;
        ListView listView;
        adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_contact_stat,R.id.text_view_contact_stat,arrayList2);

        listView = rootView.findViewById(R.id.list_view_contact_stats);
        listView.setAdapter(adapter);
    }


    private void doSpinner() {
        spinner = rootView.findViewById(R.id.contact_spinner);
        arrayList = new ArrayList<>();
        for (ArrayList<String> aL :
                datos) {
            arrayList.add(aL.get(0));
            map.put(aL.get(0),Integer.parseInt(aL.get(1)));
        }
        generateListViewAnything();
        ArrayAdapter<String>adapter= new ArrayAdapter<>(getActivity(),R.layout.list_item_name_contact_stat,R.id.text_view_name_contact_stat,arrayList);
        spinner.setAdapter(adapter);
    }

    private void generateListViewAnything() {
        if(map.isEmpty()){
            ArrayAdapter<String> adapter;
            ListView listView;
            ArrayList<String>arrayList = new ArrayList<>();
            arrayList.add(" Nada que mostrar");
            adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_contact_stat,R.id.text_view_contact_stat,arrayList);

            listView = rootView.findViewById(R.id.list_view_contact_stats);
            listView.setAdapter(adapter);
        }

    }

}
