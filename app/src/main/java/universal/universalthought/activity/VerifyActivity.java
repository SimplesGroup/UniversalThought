package universal.universalthought.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import universal.universalthought.R;
import universal.universalthought.fundraiser.SignUpActivity;

/**
 * Created by user on 8/24/2018.
 */

public class VerifyActivity extends Fragment {

    @Nullable
    public static VerifyActivity newInstance() {
        VerifyActivity fragment = new VerifyActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_activity, container, false);


        return view;
    }
    }
