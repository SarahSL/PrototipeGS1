package gs1.gestorsm.prototipegs1.ContentsList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gs1.gestorsm.prototipegs1.Connect;
import gs1.gestorsm.prototipegs1.ConnectResponse;
import gs1.gestorsm.prototipegs1.MySession;
import gs1.gestorsm.prototipegs1.R;

/**
 * Created by Javier on 25/11/2017.
 */

public class EpisodesFragment extends Fragment implements ConnectResponse {
    ArrayList<ArrayList<String>> datos = new ArrayList<>();
    ArrayList<String> textEpisodes = new ArrayList<>();
    View view;
    Connect con;
    String idContent,durationMovie;
    int paso = 0;
    int numCharacter;
    MySession g = MySession.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_episodes, container, false);
        idContent = g.getIdContent_PageContent();

        getType();

        return view;

    }

    @Override
    public void processFinish(String str, ArrayList<ArrayList<String>> datos) {
        this.datos = datos;
        if (paso == 0) {
            setData();
            paso = 1;
            if (textEpisodes.get(0).equals("movie")) {
                textEpisodes.add(1,"1");
                getDataForMovie();
            } else if (textEpisodes.get(0).equals("serie")) {
                getDatasForSerie();
            }

        } else if (paso == 1) {
            setData();
            if (textEpisodes.get(0).equals("serie")) {
                paso = 2;
                numCharacter = Integer.parseInt(textEpisodes.get(1));
                numCharacter++;
                textEpisodes.add(1, "" + numCharacter + "");
                getSeasons();
            }else{
                durationMovie=textEpisodes.get(2);

                TextView textView = view.findViewById(R.id.episodes_TextView);
                textView.setText("Number of episodes:\n"+textEpisodes.get(1)+"\nDuration:\n"+textEpisodes.get(2));
            }
        }else if (paso == 2){
            setData();
                //EL ULTIMO DE SERIE
            TextView textView = view.findViewById(R.id.episodes_TextView);
            textView.setText("Next episode:\n"+textEpisodes.get(1)+"\nDate of realeased:\n'21/04/2018'"+"\nSeasons\n"+textEpisodes.get(3));
        }


    }
    private void getSeasons(){
        con = new Connect();
        con.setSql("Select COUNT(*) from season inner join content,serie where season.cod_serie = serie.id_serie " +
                        "and serie.cod_content = content.id_content and content.id_content ="+ idContent, 0);
        con.delegate = this;
        con.Connect();
    }
    private void setData() {
        textEpisodes.add(datos.get(0).get(0));
    }

    private void getDatasForSerie() {
        con = new Connect();
        con.setSql("Select Max(chapter.numCharacter) from chapter inner join content,serie,season,contentType " +
                "where content.id_content = serie.cod_content " +
                "and serie.id_serie = season.cod_serie " +
                "and season.id_season = chapter.cod_season " +
                "and content.cod_contentType = contentType.id_contentType " +
                "and content.id_content=" + idContent, 0);
        con.delegate = this;
        con.Connect();

    }

    private void getDataForMovie() {
        con = new Connect();
        con.setSql("select movie.duration from movie inner join content where movie.cod_content = content.id_content and content.id_content = " + idContent, 0);
        con.delegate = this;
        con.Connect();
    }

    private void getType() {
        con = new Connect();
        con.setSql("Select contentType.name from content,contentType where contentType.id_contentType = content.cod_contentType and content.id_content =" + idContent, 0);
        con.delegate = this;
        con.Connect();
    }
}
