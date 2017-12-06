package universal.universalthought.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.R;



public class TabsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;

    public TabsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);

        adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        setupViewPager(viewPager);

        // Inflate the layout for this fragment
        return rootView;
    }
    private void setupViewPager(ViewPager viewPager) {
        FeedViewPagerAdapter adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MessagesFragment(), "EDUCATION");
        adapter.addFragment(new MessagesFragment(), "EMERGENCIES");
        adapter.addFragment(new MessagesFragment(), "MEDICAL");
        adapter.addFragment(new MessagesFragment(), "ANIMALS");
        adapter.addFragment(new MessagesFragment(), "CHILDRENS");
        adapter.addFragment(new MessagesFragment(), "SPORTS");
        adapter.addFragment(new MessagesFragment(), "MEMORIALS");
        adapter.addFragment(new MessagesFragment(), "COMMUNITY");
        adapter.addFragment(new MessagesFragment(), "ELDERLY");
        adapter.addFragment(new MessagesFragment(), "ARTS & MEDIA");

        viewPager.setAdapter(adapter);
    }

    class FeedViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public FeedViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
