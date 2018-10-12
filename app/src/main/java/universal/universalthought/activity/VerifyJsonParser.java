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

import universal.universalthought.Fragments.SocialFragment;
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
import universal.universalthought.Listinterface;
import universal.universalthought.model.CategoryItemmodel;

public class VerifyJsonParser  {
    String url = "http://universalthought.org/universalthought.php";
    private static List<CategoryItemmodel> productList=new ArrayList<>();
    RequestQueue requestQueue;

    public List<CategoryItemmodel> jsonmethodverify(Context context, final String category, final int reqcount) {
        // productList = new ArrayList<CategoryItemmodel>();
        requestQueue = Volley.newRequestQueue(context);

        StringRequest objectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Data", response.toString());

                try {
                    JSONObject jsondata = new JSONObject(response.toString());
                    JSONArray jsonArray = jsondata.getJSONArray("result");
                    String data=jsonArray.optString(1);
                    Log.e("Response", "newarray" + data.toString());


                    JSONArray array =new JSONArray(data.toString());


                    for (int i = 0; i < array.length(); i++) {
                        JSONObject explrObject = array.getJSONObject(i);
                        Log.e("Response","tets"+explrObject.toString());

                        CategoryItemmodel model = new CategoryItemmodel();
                        model.setId(explrObject.getString("id"));
                        model.setUrl(explrObject.getString("url"));
                        model.setTitleoffundraising(explrObject.getString("title"));
                        Log.e("Data",explrObject.getString("title"));
                        model.setPhoto(explrObject.getString("image"));
                        model.setName(explrObject.getString("name"));
                        model.setDate(explrObject.getString("create_date"));
                        Log.e("Datatitle",explrObject.getString("uimg"));
                        model.setLikecount(explrObject.getString("like_count"));

                        model.setCommentcount(explrObject.getString("comment_count"));
                        model.setUimage(explrObject.getString("uimg"));
                        model.setLiketype(explrObject.getInt("like_type"));
                        model.setAmountraised(explrObject.getString("amount_raised"));
                        model.setRaisingamount(explrObject.getString("raising_amount"));

                        productList.add(model);
                    }

                    verifyList(category);
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
                param.put("category",category);
                param.put("type", "");
                param.put("search_text","");
                param.put("page",String.valueOf(reqcount));
                return param;

            }
        };
        requestQueue.add(objectRequest);

        return productList;
    }
    public List<CategoryItemmodel>verifyList(String qtype){
        Log.e("Data","list"+productList.toString());
        if(qtype.equals("animals")){
            Listinterface listinterfaceverify=new VerifyAnimalsFragment();
            listinterfaceverify.List(productList);

        } else if(qtype.equals("artsmedia")){

            Listinterface listinterface1=new VerifyArtsMediaFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("childrens")){

            Listinterface listinterface1=new VerifyChildrensFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("community")){

            Listinterface listinterface1=new VerifyCommunityFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("education")){

            Listinterface listinterface1=new VerifyEducationFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("elderly")){
            Listinterface listinterface=new VerifyElderlyFragment();
            listinterface.List(productList);
        } else if(qtype.equals("emergenicies")){

            Listinterface listinterface1=new VerifyEmergenciesFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("environment")){

            Listinterface listinterface1=new VerifyEnvironmentFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("humanrights")){

            Listinterface listinterface1=new VerifyHumanRightsFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("medical")){

            Listinterface listinterface1=new VerifyMedicalFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("memorials")){

            Listinterface listinterface1=new VerifyMemorialsFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("ruraldevelopment")){

            Listinterface listinterface1=new VerifyRuralDevelopmentFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("social")){
            Listinterface listinterface=new SocialFragment();
            listinterface.List(productList);
            Listinterface listinterface1=new VerifySocialFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("sports")){

            Listinterface listinterface1=new VerifySportsFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("technology")){

            Listinterface listinterface1=new VerifyTechnologyFragment();
            listinterface1.List(productList);
        }else if(qtype.equals("women")){

            Listinterface listinterface1=new VerifyWomenFragment();
            listinterface1.List(productList);
        } else if(qtype.equals("others")){
            Listinterface listinterface1=new VerifyOthersFragment();
            listinterface1.List(productList);
        }
        return productList;
    }
}
