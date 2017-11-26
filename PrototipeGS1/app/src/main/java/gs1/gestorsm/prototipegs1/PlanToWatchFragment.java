package gs1.gestorsm.prototipegs1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Javier on 25/11/2017.
 */

public class PlanToWatchFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch_to_plan,container,false);
        //HACER LA  MAGIA DEL ACTIVITY
        TextView textView = view.findViewById(R.id.plan_to_watch);
        textView.setText("JSADOASDOJASDAS");
        return view;
    }
}
