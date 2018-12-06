package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONObject;

import java.util.HashMap;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.ComponentsListener;

public class AdminChangePassword extends AppCompatButton
        implements View.OnClickListener, ComponentsListener {

    private String oldPassword;
    private String newPassword;

    public AdminChangePassword(Context context) {
        super(context);
        init();
    }

    public AdminChangePassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdminChangePassword(Context context, AttributeSet attrs, int defStyleAttr) {
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
        map.put(getContext().getString(R.string.old),oldPassword );
        map.put(getContext().getString(R.string.newpassword), newPassword);
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.admin_password),
                POST,
                view,
                this);
        communication.send(map);
    }

    @Override
    public void update(JSONObject json) {

    }

    public void setLastPasswordText(Editable editText) {
        this.oldPassword = editText.toString();
    }

    public void setNewPasswordText(Editable editText) {
        this.newPassword = editText.toString();
    }
}
