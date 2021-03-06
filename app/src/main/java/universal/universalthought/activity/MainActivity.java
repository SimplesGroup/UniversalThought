package universal.universalthought.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import universal.universalthought.R;
import universal.universalthought.fundraiser.BasicInformation;
import universal.universalthought.fundraiser.StoriesFragment;
import universal.universalthought.fundraiser.StoriesPage;


public class MainActivity extends AppCompatActivity  {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    CoordinatorLayout mCoordinator;
    ImageView menuimage;


    ImageButton menu_butt,help_butt,story_butt,verify_butt,fundraiser_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        menu_butt=(ImageButton)findViewById(R.id.btn_menu);
        help_butt=(ImageButton)findViewById(R.id.btn_help);
        story_butt=(ImageButton)findViewById(R.id.btn_stories);
        verify_butt=(ImageButton)findViewById(R.id.btn_verify);
        fundraiser_butt=(ImageButton)findViewById(R.id.btn_fundraiser);

        menu_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedfrag=null;
                selectedfrag=MenuFragment.newInstance();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_body, selectedfrag);
                transaction.commit();
                menu_butt.setBackgroundResource(R.color.white);
                help_butt.setBackgroundResource(R.color.footerbackred);
                story_butt.setBackgroundResource(R.color.footerbackred);
                verify_butt.setBackgroundResource(R.color.footerbackred);
                fundraiser_butt.setBackgroundResource(R.color.footerbackred);
                menu_butt.setImageResource(R.mipmap.menured);
                help_butt.setImageResource(R.mipmap.helpwhite);
                story_butt.setImageResource(R.mipmap.storywhite);
                verify_butt.setImageResource(R.mipmap.verifywhite);
                fundraiser_butt.setImageResource(R.mipmap.fundrraiserwhite);

            }
        });
help_butt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment selectedfrag=null;
        selectedfrag=TabsFragment.newInstance();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_body, selectedfrag);
        transaction.commit();
        menu_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setBackgroundResource(R.color.white);
        story_butt.setBackgroundResource(R.color.footerbackred);
        verify_butt.setBackgroundResource(R.color.footerbackred);
        fundraiser_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setImageResource(R.mipmap.helpred);
        menu_butt.setImageResource(R.mipmap.menuwhite);
        story_butt.setImageResource(R.mipmap.storywhite);
        verify_butt.setImageResource(R.mipmap.verifywhite);
        fundraiser_butt.setImageResource(R.mipmap.fundrraiserwhite);
    }
});


verify_butt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment selectedfrag=null;
        selectedfrag= VerifyActivity.newInstance();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_body, selectedfrag);
        transaction.commit();
        menu_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setBackgroundResource(R.color.footerbackred);
        story_butt.setBackgroundResource(R.color.footerbackred);
        verify_butt.setBackgroundResource(R.color.white);
        fundraiser_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setImageResource(R.mipmap.helpwhite);
        menu_butt.setImageResource(R.mipmap.menuwhite);
        story_butt.setImageResource(R.mipmap.storywhite);
        verify_butt.setImageResource(R.mipmap.verifyred);
        fundraiser_butt.setImageResource(R.mipmap.fundrraiserwhite);

    }
});
story_butt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment selectedfrag=null;
        selectedfrag= StoriesFragment.newInstance();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_body, selectedfrag);
        transaction.commit();
        menu_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setBackgroundResource(R.color.footerbackred);
        story_butt.setBackgroundResource(R.color.white);
        verify_butt.setBackgroundResource(R.color.footerbackred);
        fundraiser_butt.setBackgroundResource(R.color.footerbackred);
        story_butt.setImageResource(R.mipmap.storyred);
        help_butt.setImageResource(R.mipmap.helpwhite);
        menu_butt.setImageResource(R.mipmap.menuwhite);
        verify_butt.setImageResource(R.mipmap.verifywhite);
        fundraiser_butt.setImageResource(R.mipmap.fundrraiserwhite);
    }
});

fundraiser_butt.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),FundraiserActivity.class);
        startActivity(i);
    }
});


        Fragment selectedFragment = null;
        selectedFragment = StoriesFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_body, selectedFragment);
        transaction.commit();
        menu_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setBackgroundResource(R.color.footerbackred);
        help_butt.setBackgroundResource(R.color.footerbackred);
        story_butt.setBackgroundResource(R.color.white);
        verify_butt.setBackgroundResource(R.color.footerbackred);
        fundraiser_butt.setBackgroundResource(R.color.footerbackred);
        story_butt.setImageResource(R.mipmap.storyred);
      /*  mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");*/
       /* mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);*/
     //mToolbar.setLogo(R.drawable.logo);


     //  menuimage = (ImageView) findViewById(R.id.imagemenu);

       /* setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.backarrow);
        mToolbar.setNavigationIcon(drawable);*/
       // mToolbar.setNavigationIcon(R.drawable.logo);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        /*drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
      drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
       drawerFragment.setDrawerListener(this);*/
      /*menuimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "WELCOME",
                        Toast.LENGTH_LONG).show();
                drawerFragment = (FragmentDrawer)
                        getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
                drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
                drawerFragment.setDrawerListener(MainActivity.this);
            }
        });*/
        // display the first navigation drawer view on app launch
        //displayView(0);
    }
    @Override
    public void onBackPressed()                                                                                                                                         {
        finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /*@Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
           // getSupportActionBar().setTitle(title);
        }
    }*/
}