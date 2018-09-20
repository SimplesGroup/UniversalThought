package universal.universalthought.fundraiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.Fragments.AnimalsFragment;
import universal.universalthought.Fragments.ArtsMediaFragment;
import universal.universalthought.Fragments.ChildrensFragment;
import universal.universalthought.Fragments.CommunityFragment;
import universal.universalthought.Fragments.EducationFragment;
import universal.universalthought.Fragments.ElderlyFragment;
import universal.universalthought.Fragments.EmergenciesFragment;
import universal.universalthought.Fragments.EnvironmentFragment;
import universal.universalthought.Fragments.HumanRightsFragment;
import universal.universalthought.Fragments.MedicalFragment;
import universal.universalthought.Fragments.MemorialsFragment;
import universal.universalthought.Fragments.OthersFragment;
import universal.universalthought.Fragments.RuralDevelopmentFragment;
import universal.universalthought.Fragments.SocialFragment;
import universal.universalthought.Fragments.SportsFragment;
import universal.universalthought.Fragments.StoriesFragment.AnimalsStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.ArtsMediaStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.ChildrensStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.CommunityStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.EducationStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.ElderlyStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.EmergenciesStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.EnvironmentStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.HumanRightsStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.MedicalStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.MemorialsStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.OthersStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.RuralDevelopmentStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.SocialStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.SportsStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.TechnologyStoriesFragment;
import universal.universalthought.Fragments.StoriesFragment.WomenStoriesFragment;
import universal.universalthought.Fragments.TechnologyFragment;
import universal.universalthought.Fragments.WomenFragment;
import universal.universalthought.R;
import universal.universalthought.fundraiser.StoriesPage;

public class StoriesFragment extends Fragment {
    CoordinatorLayout mCoordinator;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;
    int pos = 2;
    ImageView fundraiser;
    ImageButton stories_butt,verify_butt;
    public static StoriesFragment newInstance() {
        StoriesFragment fragment = new StoriesFragment();
        return fragment;
    }
    public StoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        mCoordinator = (CoordinatorLayout) rootView.findViewById(R.id.root_coordinator);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar_layout);

        adapter = new FeedViewPagerAdapter(getChildFragmentManager());

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        setupViewPager(viewPager);
        // setupTabIcons();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Bundle bundle =  getArguments();
        if(bundle!=null){

            String text= bundle.getString("pos");
            viewPager.setCurrentItem(Integer.parseInt(text));
        }else {

        }

        // Inflate the layout for this fragment
        return rootView;
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.fundraiser);
        tabLayout.getTabAt(1).setIcon(R.drawable.fundraiser);
        tabLayout.getTabAt(2).setIcon(R.drawable.fundraiser);
    }



    private void setupViewPager(ViewPager viewPager) {
        FeedViewPagerAdapter adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AnimalsStoriesFragment(), "ANIMALS");
        adapter.addFragment(new ArtsMediaStoriesFragment(), "ARTS & MEDIA");
        adapter.addFragment(new ChildrensStoriesFragment(), "CHILDRENS");
        adapter.addFragment(new CommunityStoriesFragment(), "COMMUNITY");
        adapter.addFragment(new EducationStoriesFragment(), "EDUCATION");
        adapter.addFragment(new ElderlyStoriesFragment(), "ELDERLY");
        adapter.addFragment(new EmergenciesStoriesFragment(), "EMERGENCIES");
        adapter.addFragment(new EnvironmentStoriesFragment(), "ENVIRONMENT");
        adapter.addFragment(new HumanRightsStoriesFragment(), "HUMAN RIGHTS");
        adapter.addFragment(new MedicalStoriesFragment(), "MEDICAL");
        adapter.addFragment(new MemorialsStoriesFragment(), "MEMORIAL");
        adapter.addFragment(new RuralDevelopmentStoriesFragment(), "RURAL DEVELOPMENT");
        adapter.addFragment(new SocialStoriesFragment(), "SOCIAL");
        adapter.addFragment(new SportsStoriesFragment(), "SPORTS");
        adapter.addFragment(new TechnologyStoriesFragment(), "TECHNOLOGY");
        adapter.addFragment(new WomenStoriesFragment(), "WOMEN");
        adapter.addFragment(new OthersStoriesFragment(), "OTHERS");


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
