package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button.SoundButton;


public class VolumeSeekBar extends android.support.v7.widget.AppCompatSeekBar implements SeekBar.OnSeekBarChangeListener, ComponentsListener {
    private Context context;
    public static SoundButton soundButton;
    private int prevProgress;
    private boolean isInitialized = false;

    public VolumeSeekBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public VolumeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public VolumeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init(){
        this.setOnSeekBarChangeListener(this);
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.get_volume) ,
                getResources().getString(R.string.GET),
                this,
                this);
        communication.send();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        prevProgress = seekBar.getProgress();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(isInitialized){
            int progress = seekBar.getProgress();
            if(soundButton == null){
                soundButton = ((Activity)context).findViewById(R.id.floating_sound_button);
                this.soundButton.setVolumeStatusIcon(progress);
            }
            else{
                this.soundButton.setVolumeStatusIcon(progress);
            }
            int diff = progress - prevProgress;
            if(diff > 0){
                increaseVolume(diff);
            }
            else if (diff < 0){
                decreaseVolume(Math.abs(diff));
            }
            prevProgress = progress;
        }
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    private void increaseVolume(int percent){
        HashMap map = new HashMap();
        map.put(getContext().getString(R.string.pc), percent);
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.increase_volume) + Integer.toString(percent) ,
                getResources().getString(R.string.POST),
                this,
                this);
        communication.send(map);
    }

    private void decreaseVolume(int percent){
        HashMap map = new HashMap();
        map.put(getContext().getString(R.string.pc), percent);
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.decrease_volume) + Integer.toString(percent) ,
                getResources().getString(R.string.POST),
                this,
                this);
        communication.send(map);
    }
    @Override
    public void update(JSONObject json) {
        try {
            if(!isInitialized){
                int volume = json.getInt("volume");
                this.setProgress(volume);
                isInitialized = true;
                prevProgress = this.getProgress();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
