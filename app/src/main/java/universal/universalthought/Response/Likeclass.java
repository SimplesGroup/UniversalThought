package universal.universalthought.Response;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import universal.universalthought.Util.Config;

public class Likeclass {
    RequestQueue requestQueue;
    private String like_story,like_fundraiser;

    public String StoryLike(Context context, final String userid, final String storyid, final int extraid){
        requestQueue= Volley.newRequestQueue(context);
        StringRequest storylike_request=new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response.toString());
                    JSONArray array=object.getJSONArray("result");
                    String data=array.optString(1);
                    like_story=data;

                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>param=new HashMap<>();
                param.put("Key","UniversalThought");
                param.put("rType","story_like");
                param.put("user_id",userid);
                param.put("like_type",String.valueOf(extraid));
                param.put("story_id", storyid);
                return param;
            }
        };
        requestQueue.add(storylike_request);
        return like_story;
    }


    public String FundraiserLike(Context context, final String userid, final String fundid, final int extraid){
        requestQueue= Volley.newRequestQueue(context);
        StringRequest fundraiser_request=new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response.toString());
                    JSONArray array=object.getJSONArray("result");
                    String data=array.optString(1);
                    like_fundraiser=data;

                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>param=new HashMap<>();
                param.put("Key","UniversalThought");
                param.put("rType","fundraiser_like");
                param.put("user_id",userid);
                param.put("like_type",String.valueOf(extraid));
                param.put("fundraiser_id", fundid);
                return param;
            }
        };
        requestQueue.add(fundraiser_request);
        return like_fundraiser;
    }



}
