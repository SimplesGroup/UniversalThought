package universal.universalthought.activity;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import universal.universalthought.model.CategoryItemmodel;

/**
 * Created by user on 8/30/2018.
 */

public class CheckClass {
    String url = "http://www.simples.in/universalthought/universalthought.php";
    private List<CategoryItemmodel> productList;
    RequestQueue requestQueue;

    public List<CategoryItemmodel> jsonmethod(Context context, final String category, final int reqcount) {
        productList = new ArrayList<CategoryItemmodel>();
        requestQueue = Volley.newRequestQueue(context);

        StringRequest objectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Data", response.toString());

                try {
                    JSONObject jsondata = new JSONObject(response.toString());
                    JSONArray jsonArray = jsondata.getJSONArray("result");

                    Log.e("Responsess", "data" + jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);

                        CategoryItemmodel model = new CategoryItemmodel();
                        model.setId(explrObject.getString("id"));
                        model.setUrl(explrObject.getString("url"));
                        model.setTitleoffundraising(explrObject.getString("title"));
                        Log.e("Data", explrObject.getString("title"));
                        model.setPhoto(explrObject.getString("image"));
                        model.setName(explrObject.getString("name"));
                        model.setAmountraised(explrObject.getString("amount_raised"));
                        model.setRaisingamount(explrObject.getString("raising_amount"));

                        productList.add(model);
                    }
                    //  adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("Key","UniversalThought");
                param.put("rType","alldata");
                param.put("category","all");
                param.put("type", "");
                param.put("search_text","");
                param.put("page",String.valueOf(reqcount));
                return param;

            }
        };
        requestQueue.add(objectRequest);

        return productList;
    }

}
