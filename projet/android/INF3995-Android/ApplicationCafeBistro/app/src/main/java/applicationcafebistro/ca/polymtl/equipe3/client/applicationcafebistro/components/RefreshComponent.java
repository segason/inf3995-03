package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button.SoundButton;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.snackbar.VolumeSnackBar;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.DeviceInformation;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;

public class RefreshComponent extends SwipeRefreshLayout implements ComponentsListener, SwipeRefreshLayout.OnRefreshListener {
    private Context context;
    public RefreshComponent(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public RefreshComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){
        setOnRefreshListener(this);
        setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent));
        if(DeviceInformation.isAdmin)
            getVolume(this, this);
    }


    @Override
    public void onRefresh(){
        getVolume(this, this);
    }

    private void getVolume(View view, ComponentsListener componentsListener){
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.get_volume),
                getResources().getString(R.string.GET),
                view,
                componentsListener);
        communication.send();
    }
    @Override
    public void update(JSONObject json) {
        try {
            //boolean muteStatus = json.getString(getResources().getString(R.string.sourdine)).equals("1");
            int currentVolume = json.getInt(getResources().getString(R.string.volume));
            VolumeSeekBar volumeSeekBar = ((Activity)context).findViewById(R.id.seekBar);
            SoundButton soundButton= ((Activity)context).findViewById(R.id.floating_sound_button);
            if(volumeSeekBar != null && volumeSeekBar.getProgress() != currentVolume){
                volumeSeekBar.setInitialized(false);
                getVolume(this, volumeSeekBar);
            }

            soundButton.setVolumeStatusIcon(currentVolume);
            VolumeSnackBar.make(ListMusic.view, context, Integer.toString(currentVolume), 3000, soundButton.getTag());
            VolumeSnackBar.show();
            setRefreshing(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
