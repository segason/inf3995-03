package equipe4.tp4.components.buttons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import equipe4.tp4.activities.DisplayTestActivity;
import equipe4.tp4.application.Application;

/**
 * Created by moguef on 2018-09-19.
 */

public class LaunchTestButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    private Context context;
    private Bundle fragmentBundle;
    /**
     * The section number for the fragment owning this button.
     */
    private static final String EXTRA_MESSAGE = "message";
    private static final String URL_KEY = "url";

    public LaunchTestButton(Context context){
        super(context);
        this.context = context;
        init();
    }

    public LaunchTestButton(Context context,AttributeSet attrs) {
        super(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
        this.context = context;
        init();
    }

    public LaunchTestButton(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    /**
     * Initialize the onClick Listener
     */
    private void init(){
        setOnClickListener(this);
    }

    /**
     * Manage the event onClick.
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Application.getAppContext(), DisplayTestActivity.class);
        String testId = fragmentBundle.getString(URL_KEY);
        intent.putExtra(EXTRA_MESSAGE, testId);
        context.startActivity(intent);
    }


    /**
     *
     * @param bundle
     */
    public void setBundle(Bundle bundle){
        this.fragmentBundle = bundle;
    }
}
