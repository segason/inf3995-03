package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model.User;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin.FragmentBlackList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin.FragmentUsersList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;

public class UserListener implements RecyclerUserTouchHelperListener, ComponentsListener {

    private final ListUserAdapter adapter;
    private Context context;
    private String fragmentType;
    public UserListener(ListUserAdapter adapter,String fragment,Context context) {
        this.adapter = adapter;
        fragmentType = fragment;
        this.context = context;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        final String POST = "POST";
        if (viewHolder instanceof ListUserAdapter.MyViewHolder) {
            if(fragmentType.equals(context.getString(R.string.users_list)) ) {
                restRequest(ListMusic.view.getResources().getString(R.string.block_user),POST,
                        position,context);
                adapter.removeItem(viewHolder.getAdapterPosition());
            }else if(fragmentType.equals(context.getString(R.string.black_list))){
                restRequest(ListMusic.view.getResources().getString(R.string.unblock_user),POST,
                        position,context);
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        }
    }
    private void restRequest(String url,String POST,int position, Context context){
        CommunicationRest communication = new CommunicationRest(
                url,
                POST,
                ListMusic.view);
        HashMap map = new HashMap();
        map.put(context.getString(R.string.ip), adapter.getUsers().get(position).getIp());
        map.put(context.getString(R.string.mac), adapter.getUsers().get(position).getMac());
        map.put(context.getString(R.string.user_name), adapter.getUsers().get(position).getName());
        communication.send(map);
        final String GET = "GET";
        CommunicationRest communicationUpdate = new CommunicationRest(
                ListMusic.view.getResources().getString(R.string.list_users),
                GET,
                ListMusic.view,
                FragmentUsersList.listenerUsers, FragmentBlackList.listenerUsers
        );
        communicationUpdate.send(null);
    }
    @Override
    public void onMoved(RecyclerView.ViewHolder viewHolder, int positionStart, int positionEnd) {
        if (viewHolder instanceof ListUserAdapter.MyViewHolder) {
           // adapter.onItemMove(positionStart, positionEnd);
        }
    }

    private void fillBlackList(JSONArray array, ArrayList<User> users, Context context){
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = (JSONObject) array.get(i);
                if(object.getInt(context.getString(R.string.user_blocked)) == 1) {
                    User user = craftUser(object, context);
                    users.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!adapter.compare(users)) {
            adapter.clear();
            adapter.addAll(users);
        }
    }

    private void fillUsersList(JSONArray array,ArrayList<User> users,Context context){
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = (JSONObject) array.get(i);
                if(object.getInt(context.getString(R.string.user_blocked)) == 0) {
                    User user = craftUser(object, context);
                    users.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!adapter.compare(users)) {
            adapter.clear();
            adapter.addAll(users);
        }
    }
    private User craftUser(JSONObject object,Context context) throws JSONException {
        User user = new User(object.getString(context.getString(R.string.user_name)),
                object.getString(context.getString(R.string.user_ip)),
                object.getString(context.getString(R.string.user_mac)),Integer.toString(object.getInt(context.getString(R.string.user_blocked))).equals("1"));
        return user;
    }

    @Override
    public void update(JSONObject json) {
        JSONArray array = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            array = json.getJSONArray(context.getString(R.string.users_json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array != null && fragmentType.equals(context.getString(R.string.users_list))) {
            fillUsersList(array,users,context);
        }if(array != null && fragmentType.equals(context.getString(R.string.black_list))){
            fillBlackList(array,users,context);
        }
    }
}
