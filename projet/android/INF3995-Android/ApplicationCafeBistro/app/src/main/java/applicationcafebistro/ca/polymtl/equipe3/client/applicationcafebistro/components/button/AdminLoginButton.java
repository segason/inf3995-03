package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONObject;

import java.util.HashMap;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.DeviceInformation;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;

public class AdminLoginButton extends AppCompatButton
        implements View.OnClickListener, ComponentsListener {
    private String password;
    public AdminLoginButton(Context context) {
        super(context);
        init();
    }

    public AdminLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdminLoginButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String POST = "POST";
        HashMap map = new HashMap();
        map.put(getContext().getString(R.string.username),getContext().getString(R.string.admin_username) );
        map.put(getContext().getString(R.string.password), password);
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.log_admin),
                POST,
                view,
                this);
        communication.send(map);
    }

    @Override
    public void update(JSONObject json) {
            Intent intent = new Intent(getContext(), ListMusic.class);
            DeviceInformation.isAdmin = true;
            intent.putExtra(getContext().getString(R.string.welcome_message_key),
                    getContext().getString(R.string.admin_welcome_message));
            getContext().startActivity(intent);
    }

    public void setLoginText(Editable editText) {
        this.password = editText.toString();
    }
}
