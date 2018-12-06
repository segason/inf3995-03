package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model.Music;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model.User;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.DeviceInformation;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin.FragmentBlackList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin.FragmentUsersList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;

/**
 * This class is used to listen events in the listView(recycler)
 * We implement delete (onSwiped), reverse (onMoved) and insert (update) function
 */
public class MusicListener implements RecyclerMusicTouchHelperListener, ComponentsListener {

    private final ListMusicAdapter adapter;
    private String fragmentType;

    public MusicListener(ListMusicAdapter adapter, String fragment) {
        this.adapter = adapter;
        fragmentType = fragment;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        final String DELETE = "DELETE";
        if (viewHolder instanceof ListMusicAdapter.MyViewHolder && position != 0) {
            if (!DeviceInformation.isAdmin) {
                CommunicationRest communication = new CommunicationRest(
                        ListMusic.view.getResources().getString(R.string.delete_music) + Integer.toString(DeviceInformation.idUser) + "/" +
                                adapter.getMusics().get(position).getId(),
                        DELETE,
                        ListMusic.view);
                communication.send();
                adapter.removeItem(viewHolder.getAdapterPosition());
            } else {
                CommunicationRest communication = new CommunicationRest(
                        ListMusic.view.getResources().getString(R.string.delete_music_admin) +
                                adapter.getMusics().get(position).getId(),
                        DELETE,
                        ListMusic.view);
                communication.send();
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        }
    }

    @Override
    public void onMoved(RecyclerView.ViewHolder viewHolder, int positionStart, int positionEnd) {
        if (DeviceInformation.isAdmin && (positionStart != 0 || positionEnd != 0)) {
            final String POST = "POST";
            Map body = new HashMap<String, Integer>();
            body.put("une", adapter.getMusics().get(positionStart).getId());
            body.put("autre", adapter.getMusics().get(positionEnd).getId());
            if (viewHolder instanceof ListMusicAdapter.MyViewHolder) {
                CommunicationRest communication = new CommunicationRest(
                        ListMusic.view.getResources().getString(R.string.reverse),
                        POST,
                        ListMusic.view);
                communication.send(body);
                adapter.onItemMove(positionStart, positionEnd);
            }
        }
    }

    private void fillCommonList(JSONArray array, ArrayList<Music> musics, Context context) {
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = (JSONObject) array.get(i);
                User user;
                if (!DeviceInformation.isAdmin) {
                    user = new User(object.getString(context.getString(
                            R.string.suggest_by_json)), Integer.parseInt(object.getString(
                            context.getString(R.string.user_id))));
                } else {
                    user = new User(object.getString(context.getString(
                            R.string.suggest_by_json)), object.getString(context.getString(
                            R.string.user_ip)), object.getString(context.getString(
                            R.string.user_mac)), Integer.parseInt(object.getString(context.getString(
                            R.string.user_id))));
                }
                Music music = craftMusic(object, user, context);
                musics.add(music);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!adapter.compare(musics)) {
            adapter.clear();
            adapter.addAll(musics);
        }
    }

    private void fillPrivateList(JSONArray array, ArrayList<Music> musics, Context context) {
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = (JSONObject) array.get(i);
                User user = new User(object.getString(context.getString(
                        R.string.suggest_by_json)), Integer.parseInt(object.getString(
                        context.getString(R.string.user_id))));
                if (user.getId() == DeviceInformation.idUser) {
                    Music music = craftMusic(object, user, context);
                    musics.add(music);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!adapter.compare(musics)) {
            adapter.clear();
            adapter.addAll(musics);
        }
    }

    private Music craftMusic(JSONObject object, User user, Context context) throws JSONException {
        Music music = new Music(object.getInt(context.getString(R.string.no_json)),
                object.getString(context.getString(R.string.title_json)),
                object.getString(context.getString(R.string.artist_json)),
                object.getString(context.getString(R.string.duration_json)),
                user, object.getString(context.getString(R.string.owner_json)).equals("1"));
        return music;
    }

    @Override
    public void update(JSONObject json) {
        JSONArray array = null;
        Context context = ListMusic.view.getContext();
        ArrayList<Music> musics = new ArrayList<>();
        try {
            array = json.getJSONArray(context.getString(R.string.chansons_json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array != null && fragmentType.equals(context.getString(R.string.common_list))) {
            fillCommonList(array, musics, context);
        }
        if (array != null && fragmentType.equals(context.getString(R.string.personal_list))) {
            fillPrivateList(array, musics, context);
        }
    }
}
