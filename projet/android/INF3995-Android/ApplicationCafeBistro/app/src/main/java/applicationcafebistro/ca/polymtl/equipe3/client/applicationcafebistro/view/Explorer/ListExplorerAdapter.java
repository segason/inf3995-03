package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.Explorer;

import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model.User;

public class ListExplorerAdapter extends RecyclerView.Adapter<ListExplorerAdapter.MyViewHolder>{
    private final ArrayList<File> cardFiles;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position, List<Object> payloads) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Explorer.instance.chooseItem(cardFiles.get(position));
            }
        });
        super.onBindViewHolder(holder, position, payloads);
    }

    public ListExplorerAdapter() {
        this.cardFiles = new ArrayList<>();
    }

    public void addAll(ArrayList<File> files) {
        cardFiles.addAll(files);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("EXPLORER FILE : ", "CREATE VIEW");

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_explorer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final File file = cardFiles.get(position);
        holder.file_name.setText(file.getName());
    }

    @Override
    public int getItemCount() {
        return cardFiles.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView file_name;

        MyViewHolder(View view) {
            super(view);
            file_name = view.findViewById(R.id.file_name);
        }
    }
}