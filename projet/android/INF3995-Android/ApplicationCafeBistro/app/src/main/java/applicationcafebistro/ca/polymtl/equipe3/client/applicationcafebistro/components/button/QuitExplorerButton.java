package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.Explorer.Explorer;

/**
 * This button is use to quit the explorer activity
 */
public class QuitExplorerButton extends FloatingActionButton implements View.OnClickListener {

    public QuitExplorerButton(Context context) {
        super(context);
        init();
    }

    public QuitExplorerButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
        init();
    }

    public QuitExplorerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Explorer.instance != null) {
            try {
                Explorer.instance.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
