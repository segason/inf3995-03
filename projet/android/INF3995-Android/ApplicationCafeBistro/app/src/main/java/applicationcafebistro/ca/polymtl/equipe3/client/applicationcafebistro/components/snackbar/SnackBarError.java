package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.snackbar;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;

/**
 * This class is used to display an error message in the app.
 */
public class SnackBarError {

    private static Snackbar snackbar;

    /**
     * This method build the error message
     *
     * @param view
     * @param context
     * @param text
     * @param duration
     */
    public static void make(View view, Context context, String text, int duration) {
        if (view != null) {
            snackbar = Snackbar.make(view, text, duration);
            View snackbarView = snackbar.getView();
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_information, 0, 0, 0);
            textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.snackbar_icon_padding));
            snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.colorError));
        }
    }

    /**
     * this method display the error message
     */
    public static void show() {
        snackbar.show();
    }
}
