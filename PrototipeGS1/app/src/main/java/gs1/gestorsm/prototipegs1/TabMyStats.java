package gs1.gestorsm.prototipegs1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static gs1.gestorsm.prototipegs1.R.*;
import static gs1.gestorsm.prototipegs1.R.layout.*;

/**
 * Created by Geraldo on 20/11/2017.
 */

public class TabMyStats extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createArray();
        View rootView = inflater.inflate(mystats_tab, container, false);
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
        //adapter = new ArrayAdapter<String>(this, list_item_stat, id.text_view_stat,arrayList);
        addTo(arrayList);
    }

    private void addTo(ArrayList<String> arrayList) {
    }
}
