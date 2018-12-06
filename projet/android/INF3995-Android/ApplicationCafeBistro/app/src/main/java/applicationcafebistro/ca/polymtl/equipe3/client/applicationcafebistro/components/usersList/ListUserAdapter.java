package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model.User;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> implements UserTouchHelperAdapter {
    private final ArrayList<User> cartListUser;

    public ListUserAdapter() {
        this.cartListUser = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User user = cartListUser.get(position);
        holder.name.setText("Nom :" + user.getName());
        holder.ip.setText("Adresse ip :" + user.getIp());
        holder.mac.setText("Adresse mac :" + user.getMac());
        if(user.getIsBlocked()){
            holder.viewBackground.setBackgroundResource(R.color.colorSuccess);
            holder.textView.setText(R.string.unban);
        }

    }

    @Override
    public int getItemCount() {
        return cartListUser.size();
    }

    public void removeItem(int position) {
        cartListUser.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDismiss(int position) {
        cartListUser.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
    }

    public void clear() {
        cartListUser.clear();
    }

    public void addAll(ArrayList<User> users) {
        cartListUser.addAll(users);
        notifyDataSetChanged();
    }

    public ArrayList<User> getUsers() {
        return cartListUser;
    }

    public boolean compare(ArrayList<User> users) {
        return users.equals(cartListUser);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, ip, mac;
        public final RelativeLayout userItemView, viewBackground;
        private TextView textView;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            ip = view.findViewById(R.id.ip);
            mac = view.findViewById(R.id.mac);
            userItemView = view.findViewById(R.id.view_foreground);
            viewBackground = view.findViewById(R.id.view_background);
            textView = view.findViewById(R.id.user_text_view);
        }
    }
}
