package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;

public class FragmentStatistics extends Fragment implements ComponentsListener {
    private View view;
    private TextView postedSongs, deletedSongs, averageTime, users;

    public FragmentStatistics() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics_fragment, container, false);
        getStatistics();
        return view;
    }

    public void getStatistics(){
        final String GET = "GET";
        CommunicationRest communication = new CommunicationRest(
                getContext().getString(R.string.statistics_url),
                GET,
                view,
                this
        );
        communication.send(null);
    }

    @Override
    public void update(JSONObject json) {
        try {
            postedSongs =  view.findViewById(R.id.posted_songs);
            averageTime =  view.findViewById(R.id.average_time);
            deletedSongs =  view.findViewById(R.id.deleted_songs);
            users = view.findViewById(R.id.distinct_users);
            postedSongs.setText(Integer.toString(json.getInt(getContext().getString(R.string.statistics_songs))));
            averageTime.setText(json.getString(getContext().getString(R.string.statistics_average_time)));
            deletedSongs.setText(Integer.toString(json.getInt(getContext().getString(R.string.statistics_deleted_songs))));
            users.setText(Integer.toString(json.getInt(getContext().getString(R.string.statistics_users))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
