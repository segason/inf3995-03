package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;

public class SoundButton extends FloatingActionButton implements View.OnClickListener, ComponentsListener {
    private final Context context;

    @Override
    public Integer getTag() {
        return tag;
    }

    private Integer tag;
    private Integer previousTag;
    private Integer progress;
    private CommunicationRest communicationRest;
    private boolean isInitialized;

    public SoundButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SoundButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
        this.context = context;
        init();
    }

    public SoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        this.setOnClickListener(this);
        initVolume();
    }

    public void setVolumeStatusIcon(int progress){
        this.progress = progress;
        if(progress < getContext().getResources().getInteger(R.integer.third) && progress > 0){
            this.setImageResource(R.drawable.ic_volume_third);
            tag = R.drawable.ic_volume_third;
        }
        else if (progress >= getContext().getResources().getInteger(R.integer.third) && progress < getContext().getResources().getInteger(R.integer.two_thirds)){
            this.setImageResource(R.drawable.ic_volume_two_third);
            tag = R.drawable.ic_volume_two_third;
        }
        else if (progress >= getContext().getResources().getInteger(R.integer.two_thirds)){
            this.setImageResource(R.drawable.ic_volume_full);
            tag = R.drawable.ic_volume_full;
        }
        else if (progress == 0){
            this.setImageResource(R.drawable.ic_volume_muted);
            tag = R.drawable.ic_volume_muted;
        }
    }


    @Override
    public void onClick(View view) {
        if(tag.equals(R.drawable.ic_volume_muted)){
            unmute(view);
        }
        else{
            mute(view);
        }
    }



    public void mute(View view){
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.mute),
                getResources().getString(R.string.POST),
                view,
                this);
        communication.send();
    }

    public void unmute(View view){
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.unmute),
                getResources().getString(R.string.POST),
                view,
                this);
        communication.send();
    }

    private void initVolume(){
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.get_volume) ,
                getResources().getString(R.string.GET),
                this,
                this);
        communication.send();
    }

    @Override
    public void update(JSONObject json) {
        try {
            if(!isInitialized) {
                isInitialized = true;
                this.setVolumeStatusIcon(json.getInt(getResources().getString(R.string.volume)));
            }
            else if(tag.equals(R.drawable.ic_volume_muted)){
                if(progress.equals(0)){
                    tag = R.drawable.ic_volume_third;
                    this.setImageResource(R.drawable.ic_volume_third);
                }
                else{
                    this.setImageResource(previousTag);
                    tag = previousTag;
                }
            }
            else{
                previousTag = tag;
                this.setImageResource(R.drawable.ic_volume_muted);
                tag = R.drawable.ic_volume_muted;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}