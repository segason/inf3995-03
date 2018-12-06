package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.snackbar.SnackBarSuccess;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.exception.ManagerException;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.DeviceInformation;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.FileEncoder;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.utils.VolleySingleton;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.ListMusic;

/**
 * this class send a music to the server. We create this instead of used the generic class
 * This class contains some operation which are not used in the other generic class
 */
public class SendMusicService {

    private final String url;
    private final Context context;

    public SendMusicService(Context context) {
        this.context = context;
        this.url = context.getResources().getString(R.string.send_music);
    }

    /**
     * Send a post request with music.mp3 encoded in Base64
     *
     * @param music File  The .mp3 file which we send to the server
     *
     */
    public void sendMusic(File music, String title) {
        final String stringMusic = FileEncoder.encodeFileToBase64(music);
        VolleySingleton volley = VolleySingleton.getInstance(this.context);
        String urlParameter = null;
        try {
            urlParameter = URLEncoder.encode(title, context.getString(R.string.utf8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url +
                Integer.toString(DeviceInformation.idUser) + context.getString(R.string.param_title) +
                urlParameter, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SnackBarSuccess.make(ListMusic.view, context, context.getString(R.string.music_add), 3000);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    int codeError = error.networkResponse.statusCode;
                    String message = new String(error.networkResponse.data);
                    System.out.println(codeError + message);
                    ManagerException managerException = new ManagerException(codeError, message, ListMusic.view);
                    managerException.findError();
                    managerException.display();
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return context.getString(R.string.body_content_json);
            }

            @Override
            public byte[] getBody() {
                try {
                    return stringMusic.getBytes(context.getString(R.string.utf8));
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString;
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
                return null;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volley.addToRequestQueue(stringRequest);
    }
}
