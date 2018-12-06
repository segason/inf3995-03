package equipe4.tp4.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import equipe4.tp4.R;
import equipe4.tp4.components.PlaceHolderFragment;
import equipe4.tp4.components.VerticalViewPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        VerticalViewPage mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(3000);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        /**
         * getItem is called to instantiate the fragment for the given page.
         * Return a PlaceHolderFragment (defined as a static inner class below)
         */
        @Override
        public Fragment getItem(int position) {
            int id = (position % 3 ) + 1;
            String url = "http://132.207.89.35/test"+id;
            return PlaceHolderFragment.newInstance(url, position % 3);
        }

        /**
         *
         * @param position
         * @return position of item
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * Used to show a maximum of Integer.MAX_VALUE pages.
         * @return the maximum number of pages
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
    }
}
