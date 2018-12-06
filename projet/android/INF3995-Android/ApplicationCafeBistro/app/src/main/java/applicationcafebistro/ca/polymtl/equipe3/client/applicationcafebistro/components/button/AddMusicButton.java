package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.Explorer.Explorer;

/**
 * This button open the explorer activity when we click on it
 */
public class AddMusicButton extends FloatingActionButton implements View.OnClickListener {
    private final Context context;

    public AddMusicButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AddMusicButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
        this.context = context;
        init();
    }

    public AddMusicButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.context, Explorer.class);
        context.startActivity(intent);
    }
}
