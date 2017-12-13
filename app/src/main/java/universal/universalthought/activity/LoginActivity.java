package universal.universalthought.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import universal.universalthought.R;
import universal.universalthought.fundraiser.BasicInformation;

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class LoginActivity extends Fragment {

    Button login;

    @Nullable
    public static LoginActivity newInstance() {
        LoginActivity fragment = new LoginActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        login = (Button) view.findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),BasicInformation.class);
                startActivity(i);
            }
        });
        return view;
    }
}
