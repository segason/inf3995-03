package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button.IdentificationButton;

public class Identification extends AppCompatActivity {
    private IdentificationButton identificationButton;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = getApplicationContext();
        identificationButton = findViewById(R.id.identificationButton);
        final EditText text = findViewById(R.id.login);
        LinearLayout top = findViewById(R.id.top_animation);
        LinearLayout bottom = findViewById(R.id.bottom_animation);
        Animation upToDown = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        Animation downToUp = AnimationUtils.loadAnimation(this, R.anim.down_to_up);
        top.setAnimation(upToDown);
        bottom.setAnimation(downToUp);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                identificationButton.setLoginText(text.getText());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
