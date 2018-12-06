package equipe4.tp4.application;

import android.content.Context;

/**
 * Created by moguef on 2018-09-16.
 */

public class Application extends android.app.Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Application.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Application.context;
    }
}
