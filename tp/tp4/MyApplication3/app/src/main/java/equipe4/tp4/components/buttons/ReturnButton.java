package equipe4.tp4.components.buttons;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by moguef on 2018-09-19.
 */

public class ReturnButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    private Context context;

    public ReturnButton(Context context){
        super(context);
        init();
        this.context = context;
    }

    public ReturnButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
        init();
        this.context = context;
    }

    public ReturnButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        this.context = context;
    }

    /**
     * Initialize the onclick listener
     */
    private void init(){
        setOnClickListener(this);
    }

    /**
     * finish the activity when we click on this button. When we finish this activity, we back to the
     * previously activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        ((Activity) context).finish();
    }
}
