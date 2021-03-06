package universal.universalthought.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import universal.universalthought.R;
import universal.universalthought.model.ResponseDataModel;

public class OtpDialogFragment extends DialogFragment {
	EditText txtname;
	Button btnDone;
	static String DialogboxTitle;
	public static final String mypreference = "mypref";
	SharedPreferences sharedpreferences;
	RequestQueue queue;
	String URL_SIGNUP="http://www.simples.in/universalthought/universalthought.php";
	public  static String USERNAME="username";
	String mail,id,mobileno;
	String strtext;
	public interface InputNameDialogListener {
		void onFinishInputDialog(String inputText);
		}

	//---empty constructor required
	public OtpDialogFragment() {
		
		}
	//---set the title of the dialog window
	public void setDialogTitle(String title) {
	DialogboxTitle = title;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
		
		View view = inflater.inflate(
				R.layout.otpdialogfragment, container);
		sharedpreferences = getActivity(). getSharedPreferences(mypreference,
				Context.MODE_PRIVATE);
		queue = Volley.newRequestQueue(getActivity());
				//---get the EditText and Button views
				txtname = (EditText) view.findViewById(R.id.txtName);
				btnDone = (Button) view.findViewById(R.id.btnDone);

		ResponseDataModel a = new ResponseDataModel(mail,mobileno,id);


 //		 strtext = getArguments().getString("id");
	//	Log.e("Response",strtext);

		//---event handler for the button
				btnDone.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View view) {
					
						//---gets the calling activity
						//InputNameDialogListener activity = (InputNameDialogListener) getActivity();
						//activity.onFinishInputDialog(txtname.getText().toString());
						Registration();
                        dismiss();
						//---dismiss the alert
						//dismiss();
					}
				});
				
				//---show the keyboard automatically
				txtname.requestFocus();
				getDialog().getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				
				//---set the title for the dialog
				getDialog().setTitle(DialogboxTitle);
		
		return view;	
	}

	private void Registration(){
		StringRequest request=new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				String[] array = response.split(",");
				SharedPreferences.Editor editor=sharedpreferences.edit();
				editor.putString(USERNAME,"name");
				editor.commit();
				Log.e("Response",response);

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String,String> params = new HashMap<String, String>();
				ResponseDataModel a = new ResponseDataModel(mail,mobileno,id);

				params.put("Key","UniversalThought");
				params.put("rType","UserLoginOtp");
				params.put("User_id","9");
				params.put("Otp","1234");


				return params;
			}

		};
		queue.add(request);
	}
}
