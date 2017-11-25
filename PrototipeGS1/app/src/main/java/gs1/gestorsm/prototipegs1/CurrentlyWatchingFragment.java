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

public class CurrentlyWatchingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watching_currently,container,false);
        //HACER LA  MAGIA DEL ACTIVITY
        TextView textView = view.findViewById(R.id.currently_watching);
        textView.setText("JSADOASDOJASDAS");
        return view;
    }
}
