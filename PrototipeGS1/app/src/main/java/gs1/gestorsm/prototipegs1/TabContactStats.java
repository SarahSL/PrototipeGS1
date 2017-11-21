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

public class TabContactStats extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contactstats_tab, container, false);
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
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_contact_stat, R.id.text_view_contact_stat, arrayList);
        ListView listView = rootView.findViewById(R.id.list_view_contact_stats);
        listView.setAdapter(adapter);

        return rootView;
    }
}
