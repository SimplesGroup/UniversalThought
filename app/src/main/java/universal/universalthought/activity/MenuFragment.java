package universal.universalthought.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.R;
import universal.universalthought.adapter.NavigationDrawerAdapter;
import universal.universalthought.model.NavDrawerItem;

public class MenuFragment extends Fragment {

    ImageView profile_image;
    TextView profile_name;
    RecyclerView recyclerView;
MenuAdapter adapter;
List<Model>list=new ArrayList<>();
SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String MYUSERID= "myprofileid";
    public static final String USERNAME= "myprofilename";
    public static final String USERIMAGE= "myprofileimage";
    public static final String USERMAILID= "myprofileemail";

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }
    public MenuFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        sharedPreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        String userimage=sharedPreferences.getString(USERIMAGE,"");
        String username=sharedPreferences.getString(USERNAME,"");
            profile_image=(ImageView)view.findViewById(R.id.profile_image);
            profile_name=(TextView)view.findViewById(R.id.username_text);
            recyclerView=(RecyclerView)view.findViewById(R.id.menuList);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Model one=new Model();
            one.setTitle("EDUCATION");
            list.add(one);
        Model two=new Model();
        two.setTitle("EMERGENCIES");
        list.add(two);
        Model three=new Model();
        three.setTitle("MEDICAL");
        list.add(three);
        Model four=new Model();
        four.setTitle("ANIMALS");
        list.add(four);
        Model five=new Model();
        five.setTitle("CHILDREN");
        list.add(five);
        Model six=new Model();
        six.setTitle("SPORTS");
        list.add(six);
        Model seven=new Model();
        seven.setTitle("MEMORIALS");
        list.add(seven);
        Model eight=new Model();
        eight.setTitle("COMMUNITY");
        list.add(eight);
        Model nine=new Model();
        nine.setTitle("ELDERLY");
        list.add(nine);
        Model ten=new Model();
        ten.setTitle("ARTS & MEDIA");
        list.add(ten);
        adapter=new MenuAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }


class Model{
        String title,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{
        private LayoutInflater inflater;
        List<Model>menulist=new ArrayList<>();
        private Context context;
        @Override
        public int getItemCount() {
            return menulist.size();
        }
        public MenuAdapter(Context context, List<Model> data) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.menulist = data;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,final int position) {
            Model model=menulist.get(position);
            holder.title.setText(model.getTitle());
holder.title.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragment = new TabsFragment();
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
        Bundle b = new Bundle();
        String pos = String.valueOf(position);
        b.putString("pos", pos);
        fragment.setArguments(b);
    }
});
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
            }
        }
    }
}
