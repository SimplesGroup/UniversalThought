package universal.universalthought.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import universal.universalthought.R;
import universal.universalthought.fundraiser.BasicInformation;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class LoginActivity extends Fragment {

    Button login ,fb,googlelogin;

    @Nullable
    public static LoginActivity newInstance() {
        LoginActivity fragment = new LoginActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        login = (Button) view.findViewById(R.id.button_login);
        fb=(Button) view.findViewById(R.id.btnfb) ;
        googlelogin=(Button)view.findViewById(R.id.btngmail) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),BasicInformation.class);
                startActivity(i);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebook=new Intent(getActivity(),FaceBooklogin.class);
                startActivity(facebook);
            }
        });
        googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google=new Intent(getActivity(),GoogleSignin.class);
                startActivity(google);
            }
        });
        return view;
    }
}
