package gs1.gestorsm.prototipegs1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



/**
 * Created by Geraldo on 20/11/2017.
 */

public class TabMyStats extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //createArray();
        View rootView = inflater.inflate(R.layout.mystats_tab, container, false);
        Statistic statistic = new Statistic();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Recommendation: " + statistic.getRecommendations());
        arrayList.add("Viewed Movies: " + statistic.getViewedMovies());
        arrayList.add("Viewed Series: " + statistic.getViewedChapters());
        arrayList.add("Monthly Movies: " + statistic.getAverageMonthlyMovies());
        arrayList.add("Hours Viewed: " + statistic.getHoursViewed());
        arrayList.add("Content Score: " + statistic.getContentAverageScore());
        arrayList.add("Series Completed: " + statistic.getSeriesCompleted());
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_my_stat, R.id.text_view_my_stat, arrayList);
        ListView listView = rootView.findViewById(R.id.list_view_my_stats);
        listView.setAdapter(adapter);
        return rootView;
    }

    private void createArray() {
        Statistic statistic = new Statistic();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Recomendation" + statistic.getRecommendations());
        arrayList.add("Viewed Movies" + statistic.getViewedMovies());
        arrayList.add("Viewed Series" + statistic.getViewedChapters());
        arrayList.add("Recomendation" + statistic.getRecommendations());
        arrayList.add("Recomendation" + statistic.getRecommendations());
        ArrayAdapter<String> adapter;
       // adapter = new ArrayAdapter<String>(getActivity(), list_item_my_stat, id.text_view_stat,arrayList);

    }

    private void addTo(ArrayList<String> arrayList) {
    }
}
