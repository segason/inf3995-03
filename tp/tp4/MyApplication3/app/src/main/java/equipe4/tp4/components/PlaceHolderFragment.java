package equipe4.tp4.components;

/**
 * Created by moguef on 2018-09-19.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import equipe4.tp4.R;
import equipe4.tp4.components.buttons.LaunchTestButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceHolderFragment extends Fragment {
    private static final String URL_KEY = "url";
    private static final String POSITION_KEY = "position";

    public PlaceHolderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceHolderFragment newInstance(String url, int position) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();

        args.putString(URL_KEY, url);
        args.putInt(POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = rootView.findViewById(R.id.titleHomePage);
        LaunchTestButton launcherTestButton = rootView.findViewById(R.id.testButton);
        launcherTestButton.setBundle(this.getArguments());
        textView.setText(getString(R.string.section_format, getArguments().getInt(POSITION_KEY)));
        return rootView;
    }
}
