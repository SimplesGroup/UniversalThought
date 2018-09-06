package universal.universalthought.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;


import universal.universalthought.Fragments.Verify.VerifyAnimalsFragment;
import universal.universalthought.Fragments.Verify.VerifyArtsMediaFragment;
import universal.universalthought.Fragments.Verify.VerifyChildrensFragment;
import universal.universalthought.Fragments.Verify.VerifyCommunityFragment;
import universal.universalthought.Fragments.Verify.VerifyEducationFragment;
import universal.universalthought.Fragments.Verify.VerifyElderlyFragment;
import universal.universalthought.Fragments.Verify.VerifyEmergenciesFragment;
import universal.universalthought.Fragments.Verify.VerifyEnvironmentFragment;
import universal.universalthought.Fragments.Verify.VerifyHumanRightsFragment;
import universal.universalthought.Fragments.Verify.VerifyMedicalFragment;
import universal.universalthought.Fragments.Verify.VerifyMemorialsFragment;
import universal.universalthought.Fragments.Verify.VerifyOthersFragment;
import universal.universalthought.Fragments.Verify.VerifyRuralDevelopmentFragment;
import universal.universalthought.Fragments.Verify.VerifySocialFragment;
import universal.universalthought.Fragments.Verify.VerifySportsFragment;
import universal.universalthought.Fragments.Verify.VerifyTechnologyFragment;
import universal.universalthought.Fragments.Verify.VerifyWomenFragment;

import universal.universalthought.R;
import universal.universalthought.fundraiser.SignUpActivity;

/**
 * Created by user on 8/24/2018.
 */

public class VerifyActivity extends Fragment {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout mcCollapsingToolbarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private VerifyPageAdapter adapter;

    @Nullable
    public static VerifyActivity newInstance() {
        VerifyActivity fragment = new VerifyActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_fragment, container, false);
        coordinatorLayout=(CoordinatorLayout)view.findViewById(R.id.root_coordinator);
        mcCollapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar_layout);
        tabLayout=(TabLayout)view.findViewById(R.id.tabs);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
adapter=new VerifyPageAdapter(getChildFragmentManager());
tabLayout.setupWithViewPager(viewPager);
tabLayout.setTabsFromPagerAdapter(adapter);
setupviewpager(viewPager);

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

        return view;
    }
    private void setupviewpager(ViewPager viewPager){
        VerifyPageAdapter verifyPageAdapter=new VerifyPageAdapter(getChildFragmentManager());

        verifyPageAdapter.AddFragment(new VerifyAnimalsFragment(), "ANIMALS");
        verifyPageAdapter.AddFragment(new VerifyArtsMediaFragment(), "ARTS & MEDIA");
        verifyPageAdapter.AddFragment(new VerifyChildrensFragment(), "CHILDRENS");
        verifyPageAdapter.AddFragment(new VerifyCommunityFragment(), "COMMUNITY");
        verifyPageAdapter.AddFragment(new VerifyEducationFragment(), "EDUCATION");
        verifyPageAdapter.AddFragment(new VerifyElderlyFragment(), "ELDERLY");
        verifyPageAdapter.AddFragment(new VerifyEmergenciesFragment(), "EMERGENCIES");
        verifyPageAdapter.AddFragment(new VerifyEnvironmentFragment(), "ENVIRONMENT");
        verifyPageAdapter.AddFragment(new VerifyHumanRightsFragment(), "HUMAN RIGHTS");
        verifyPageAdapter.AddFragment(new VerifyMedicalFragment(), "MEDICAL");
        verifyPageAdapter.AddFragment(new VerifyMemorialsFragment(), "MEMORIAL");
        verifyPageAdapter.AddFragment(new VerifyRuralDevelopmentFragment(), "RURAL DEVELOPMENT");
        verifyPageAdapter.AddFragment(new VerifySocialFragment(), "SOCIAL");
        verifyPageAdapter.AddFragment(new VerifySportsFragment(), "SPORTS");
        verifyPageAdapter.AddFragment(new VerifyTechnologyFragment(), "TECHNOLOGY");
        verifyPageAdapter.AddFragment(new VerifyWomenFragment(), "WOMEN");
        verifyPageAdapter.AddFragment(new VerifyOthersFragment(), "OTHERS");

        viewPager.setAdapter(verifyPageAdapter);
    }
    public class VerifyPageAdapter extends FragmentPagerAdapter{
        private final List<Fragment>mfragmentlist=new ArrayList<>();
        private final List<String>mfragmenttitlelist=new ArrayList<>();


        @Override
        public Fragment getItem(int position) {
            return mfragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return mfragmentlist.size();
        }

        public void AddFragment(Fragment fragment,String title){
            this.mfragmentlist.add(fragment);
            this.mfragmenttitlelist.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mfragmenttitlelist.get(position);
        }

        public VerifyPageAdapter(FragmentManager fm) {
            super(fm);
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
