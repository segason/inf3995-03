package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.snackbar;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;

/**
 * This class is used to display success message
 */

public class SnackBarSuccess {

    private static Snackbar snackbar;

    /**
     * This method is used to build success message
     * @param view
     * @param context
     * @param text
     * @param duration
     */
    public static void make(View view, Context context, String text, int duration) {
        snackbar = Snackbar.make(view, text, duration);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_information, 0, 0, 0);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.snackbar_icon_padding));
        snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.colorSuccess));
    }

    /**
     * this method is used to display success message
     */
    public static void show() {
        snackbar.show();
    }
}
