package universal.universalthought.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import universal.universalthought.Fragments.AnimalsFragment;
import universal.universalthought.Fragments.ArtsMediaFragment;
import universal.universalthought.Fragments.ChildrensFragment;
import universal.universalthought.Fragments.CommunityFragment;
import universal.universalthought.Fragments.EducationFragment;
import universal.universalthought.Fragments.ElderlyFragment;
import universal.universalthought.Fragments.EmergenciesFragment;
import universal.universalthought.Fragments.EnvironmentFragment;
import universal.universalthought.Fragments.HumanRightsFragment;
import universal.universalthought.Fragments.MedicalFragment;
import universal.universalthought.Fragments.MemorialsFragment;
import universal.universalthought.Fragments.OthersFragment;
import universal.universalthought.Fragments.RuralDevelopmentFragment;
import universal.universalthought.Fragments.SocialFragment;
import universal.universalthought.Fragments.SportsFragment;
import universal.universalthought.Fragments.TechnologyFragment;
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
import universal.universalthought.Fragments.WomenFragment;
import universal.universalthought.Listinterface;
import universal.universalthought.R;
import universal.universalthought.adapter.AnimalsAdapter;
import universal.universalthought.model.CategoryItemmodel;

/**
 * Created by user on 8/30/2018.
 */

public class CheckClass {
    String url = "http://universalthought.org/universalthought.php";
     private static List<CategoryItemmodel> productList=new ArrayList<>();
    RequestQueue requestQueue;


    public List<CategoryItemmodel> jsonmethod(Context context, final String category, final int reqcount) {
       // productList = new ArrayList<CategoryItemmodel>();
        requestQueue = Volley.newRequestQueue(context);

        StringRequest objectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                       model.setName(explrObject.getString("name"));
                      model.setBeneficiaryname(explrObject.getString("beneficiary_name"));
                       model.setDate(explrObject.getString("create_date"));
                       model.setLikecount(explrObject.getString("like_count"));
                      model.setCommentcount(explrObject.getString("comment_count"));
                          model.setUimage(explrObject.getString("uimg"));
                       model.setLiketype(explrObject.getInt("like_type"));
                        Log.e("Data",explrObject.getString("title"));
                        model.setPhoto(explrObject.getString("image"));
                        model.setAmountraised(explrObject.getString("amount_raised"));
                        model.setRaisingamount(explrObject.getString("raising_amount"));

                        productList.add(model);
                    }
                    Modelclass(category);

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
                param.put("user_id","1");
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

    public List<CategoryItemmodel> Modelclass(String qtype){
        Log.e("Data","list"+productList.toString());
        if(qtype.equals("animals")){
            Listinterface listinterface=new AnimalsFragment();
            listinterface.List(productList);

        } else if(qtype.equals("artsmedia")){
            Listinterface listinterface=new ArtsMediaFragment();
            listinterface.List(productList);

        } else if(qtype.equals("childrens")){
            Listinterface listinterface=new ChildrensFragment();
            listinterface.List(productList);

        } else if(qtype.equals("community")){
            Listinterface listinterface=new CommunityFragment();
            listinterface.List(productList);

        } else if(qtype.equals("education")){
            Listinterface listinterface=new EducationFragment();
            listinterface.List(productList);

        } else if(qtype.equals("elderly")){
           Listinterface listinterface=new ElderlyFragment();
            listinterface.List(productList);
        } else if(qtype.equals("emergenicies")){
            Listinterface listinterface=new EmergenciesFragment();
            listinterface.List(productList);

        } else if(qtype.equals("environment")){
            Listinterface listinterface=new EnvironmentFragment();
            listinterface.List(productList);

        }else if(qtype.equals("humanrights")){
            Listinterface listinterface=new HumanRightsFragment();
            listinterface.List(productList);

        }else if(qtype.equals("medical")){
            Listinterface listinterface=new MedicalFragment();
            listinterface.List(productList);

        }else if(qtype.equals("memorials")){
            Listinterface listinterface=new MemorialsFragment();
            listinterface.List(productList);

        }else if(qtype.equals("ruraldevelopment")){
            Listinterface listinterface=new RuralDevelopmentFragment();
            listinterface.List(productList);

        }else if(qtype.equals("social")){
            Listinterface listinterface=new SocialFragment();
            listinterface.List(productList);

        }else if(qtype.equals("sports")){
            Listinterface listinterface=new SportsFragment();
            listinterface.List(productList);

        }else if(qtype.equals("technology")){
            Listinterface listinterface=new TechnologyFragment();
            listinterface.List(productList);

        }else if(qtype.equals("women")){
            Listinterface listinterface=new WomenFragment();
            listinterface.List(productList);

        } else if(qtype.equals("others")){
            Listinterface listinterface=new OthersFragment();
            listinterface.List(productList);

        }else {
            Listinterface listinterfaceverify=new VerifyAnimalsFragment();
            listinterfaceverify.List(productList);
        }



        return productList;
    }


}
