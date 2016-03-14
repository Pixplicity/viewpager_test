package com.pixplicity.viewpagertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static class PageFragment extends Fragment {

        public static final String ARG_POSITION = "position";

        public static PageFragment create(int position) {
            PageFragment fragment = new PageFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_position, container, false);
            TextView tvPosition = (TextView) view.findViewById(R.id.tv_position);
            tvPosition.setText(getString(R.string.position, getArguments().getInt(ARG_POSITION) + 1));
            return view;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        assert pager != null;

        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return PageFragment.create(position);
            }

        };
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        // Makes it impossible to drag to the previous page
        pager.setPageMargin(600);

        // Makes it impossible to drag to the next page
        pager.setPageMargin(-600);
    }

}
