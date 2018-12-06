package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.DeviceInformation;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.FragmentCommonList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.FragmentPersonalList;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;


/**
 * this class is a service which call the get list endpoint in the rest api
 */
public class ServiceGetList extends Service {
    private static final long DEFAULT_SYNC_INTERVAL = 5000;
    private static final String TAG = ServiceGetList.class.getSimpleName();
    private Handler mHandler;

    private final Runnable runnableService = new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "run");

            syncData();
            mHandler.postDelayed(runnableService, DEFAULT_SYNC_INTERVAL);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        mHandler.post(runnableService);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private synchronized void syncData() {
        final String GET = "GET";
        if (DeviceInformation.isAdmin) {
            getFile(GET, getResources().getString(R.string.list_music_admin), "");
        } else {
            getFile(GET, getResources().getString(R.string.list_music), Integer.toString(DeviceInformation.idUser));
        }
    }

    private void getFile(String GET, String url, String id) {
        if (!id.equals("")) {
            CommunicationRest communication = new CommunicationRest(
                    url + id,
                    GET,
                    ListMusic.view,
                    FragmentCommonList.listenerMusic, FragmentPersonalList.listenerMusic
            );
            communication.send(null);
        } if(id.equals("")) {
            CommunicationRest communication = new CommunicationRest(
                    url,
                    GET,
                    ListMusic.view,
                    FragmentCommonList.listenerMusic
            );
            communication.send(null);
        }
    }


    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(runnableService);
        super.onDestroy();
    }
}