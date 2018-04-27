package universal.universalthought.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import universal.universalthought.Fragments.MedicalFragment;
import universal.universalthought.Fragments.MemorialsFragment;
import universal.universalthought.Fragments.SportsFragment;
import universal.universalthought.R;

public class TabsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;
    int pos = 2;
    ImageView fundraiser;


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
        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);

        adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        fundraiser = (ImageView) rootView.findViewById(R.id.btn_fundraiser);
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

        String text= bundle.getString("pos");
     //   String text= "5";
        if(pos==0){

        }else {
            viewPager.setCurrentItem(Integer.parseInt(text));

        }

        fundraiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),FundraiserActivity.class);
                startActivity(i);
            }
        });

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
        adapter.addFragment(new EducationFragment(), "EDUCATION");
        adapter.addFragment(new HomeFragment(), "EMERGENCIES");
        adapter.addFragment(new MedicalFragment(), "MEDICAL");
        adapter.addFragment(new AnimalsFragment(), "ANIMALS");
        adapter.addFragment(new ChildrensFragment(), "CHILDRENS");
        adapter.addFragment(new SportsFragment(), "SPORTS");
        adapter.addFragment(new MemorialsFragment(), "MEMORIALS");
        adapter.addFragment(new CommunityFragment(), "COMMUNITY");
        adapter.addFragment(new ElderlyFragment(), "ELDERLY");
        adapter.addFragment(new ArtsMediaFragment(), "ARTS & MEDIA");

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
