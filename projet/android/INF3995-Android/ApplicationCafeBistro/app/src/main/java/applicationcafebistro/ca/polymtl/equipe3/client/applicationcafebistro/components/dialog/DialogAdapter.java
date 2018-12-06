package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;

import java.util.Objects;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.button.AdminChangePassword;

public class DialogAdapter {
    private Dialog dialog;
    private AdminChangePassword adminChangePasswordButton;
    public DialogAdapter(Dialog dialog){
        this.dialog = dialog;
        init();
        prepareDialog();
    }
    private void init(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.change_password_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppearDialogAnimation;
    }

    private void prepareDialog(){
        adminChangePasswordButton = dialog.findViewById(R.id.admin_change_password);
        final EditText adminLastPassword = dialog.findViewById(R.id.admin_last_password);
        final EditText adminNewPassword = dialog.findViewById(R.id.admin_new_password);
        adminLastPassword.clearFocus();
        adminNewPassword.clearFocus();
        dialog.show();
        adminLastPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adminChangePasswordButton.setLastPasswordText(adminLastPassword.getText());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        adminNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adminChangePasswordButton.setNewPasswordText(adminNewPassword.getText());
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
