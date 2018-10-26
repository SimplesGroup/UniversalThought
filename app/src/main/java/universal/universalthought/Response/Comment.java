package universal.universalthought.Response;

import android.content.Context;
import android.util.Log;

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

public class Comment {
    RequestQueue requestQueue;

    private String jsondata_story,jsondata_fundraiser;

    public  String UploadComment(final String userid, final String comments, final String storyid, Context context){
        requestQueue= Volley.newRequestQueue(context);
        StringRequest postcomment_request=new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response.toString());
                try {
                    JSONObject object=new JSONObject(response.toString());
                    JSONArray array=object.getJSONArray("result");
                    String data=array.optString(1);
                    jsondata_story=data;

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
                param.put("rType","story_comment");
                param.put("user_id",userid);
                param.put("story_comment",comments);
                param.put("story_id", storyid);

                return param;
            }
        };
        requestQueue.add(postcomment_request);
        return  jsondata_story;
    }
    public String UploadCommentfundraiser(Context context, final String userid, final String comment, final String fundraiserid){
        requestQueue= Volley.newRequestQueue(context);
        StringRequest post_comment_request =new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response.toString());
                try {


                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = object.getJSONArray("result");
                    String data = array.optString(1);
                    jsondata_fundraiser = data;
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
                param.put("rType","fundraiser_comment");
                param.put("user_id",userid);
                param.put("fundraiser_comment",comment);
                param.put("fundraiser_id", fundraiserid);
                return param;
            }
        };
        requestQueue.add(post_comment_request);
        return jsondata_fundraiser;

    }
}
