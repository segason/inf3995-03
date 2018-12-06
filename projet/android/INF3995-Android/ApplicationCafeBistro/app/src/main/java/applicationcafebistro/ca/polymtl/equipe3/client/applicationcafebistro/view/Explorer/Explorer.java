package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.Explorer;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.SendMusicService;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.list.ListMusicAdapter;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.list.MusicListener;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.list.RecyclerMusicTouchHelper;

/**
 * this Activity is used to choice a mp3 file in the repository of the device
 */
public class Explorer extends AppCompatActivity {

    public static Explorer instance;
    ListExplorerAdapter adapter ;
    ArrayList<File> files;
    /**
     * Représente le service chargé d'envoyer la musique sur le serveur
     */
    private SendMusicService sendMusicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explorer);
        files = new ArrayList<>();
        instance = this;
        sendMusicService = new SendMusicService(getApplicationContext());
    }

    @Override
    protected void onStart(){
        super.onStart();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView listFileView = findViewById(R.id.recycler_file);
        adapter = new ListExplorerAdapter();
        listFileView.setLayoutManager(new GridLayoutManager(this, 2));
        listFileView.setItemAnimator(new DefaultItemAnimator());
        listFileView.setAdapter(adapter);
        recursiveScan(Environment.getExternalStorageDirectory());
        adapter.addAll(files);
    }

    public void recursiveScan(File f) {
        for (File file : f.listFiles()) {
            if (file.isDirectory())
                recursiveScan(file);
            if (file.isFile() && file.getPath().endsWith(".mp3")) {
                files.add(file);
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }

    public void chooseItem(final File file) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirmation_message) + " '" + file.getName() + "' ?");
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = file.getName();
                sendMusicService.sendMusic(file, title);
                finish();
            }
        });

        builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setTitle(R.string.Confirmation);
        builder.create().show();

    }
}