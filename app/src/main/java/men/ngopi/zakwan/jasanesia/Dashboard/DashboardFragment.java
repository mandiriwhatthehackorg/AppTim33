package men.ngopi.zakwan.jasanesia.Dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import men.ngopi.zakwan.jasanesia.MainActivity;
import men.ngopi.zakwan.jasanesia.Model.Freelancer;
import men.ngopi.zakwan.jasanesia.R;

public class DashboardFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String TAG = "=====  Dashboard";
    private Context context;



    private FirebaseFirestore firebaseFirestore;
    private RecyclerView postList;
    private FrelanceRecycleViewAdapter adapter;
    private ArrayList<Freelancer> freelancerArrayList;

    private RecyclerView recyclerViewIklan;
    private IklanRecycleViewAdapter adapterIklan;
    private ArrayList<IklanModel> iklanArrayList;


    public DashboardFragment() {
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dahsboard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
//        getPosts();
        initIklanRecycleView();
    }

    void initIklanRecycleView(){
        recyclerViewIklan = (RecyclerView) getView().findViewById(R.id.dashboard_iklan_recycler_view);

        adapterIklan = new IklanRecycleViewAdapter(iklanArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL , false);

        recyclerViewIklan.setLayoutManager(layoutManager);

        recyclerViewIklan.setAdapter(adapterIklan);

        iklanArrayList = new ArrayList<>();
        iklanArrayList.add(new IklanModel(
                "1",
                "https://www.gstatic.com/mobilesdk/160921_mobilesdk/discoverycards/2x/cloudmessaging.png"

        ));

        iklanArrayList.add(new IklanModel(
                "1",
                "https://www.gstatic.com/mobilesdk/160921_mobilesdk/discoverycards/2x/cloudmessaging.png"

        ));

        iklanArrayList.add(new IklanModel(
                "1",
                "https://www.gstatic.com/mobilesdk/160921_mobilesdk/discoverycards/2x/cloudmessaging.png"

        ));

        iklanArrayList.add(new IklanModel(
                "1",
                "https://www.gstatic.com/mobilesdk/160921_mobilesdk/discoverycards/2x/cloudmessaging.png"

        ));

        adapterIklan.addItem(iklanArrayList);
        adapterIklan.notifyDataSetChanged();



        postList = getView().findViewById(R.id.dashboard_recycler_view);
        postList.setNestedScrollingEnabled(false);
        postList.setLayoutManager(new LinearLayoutManager(context));
        context = getContext();
        adapter = new FrelanceRecycleViewAdapter(freelancerArrayList , ((MainActivity) getActivity()));

        postList.setAdapter(adapter);
        freelancerArrayList = new ArrayList<>();

        getPosts();


    }


    private void getPosts(){
        freelancerArrayList = new ArrayList<>();
        firebaseFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                freelancerArrayList.add(new Freelancer(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("deskripsi").toString(),
                                        document.getData().get("pendidikan").toString(),
                                        document.getData().get("keahlian").toString(),
                                        document.getData().get("photo").toString(),
                                        document.getData().get("no_akun").toString(),
                                        document.getData().get("no_cif").toString()
                                    )
                                );
                            }

                            adapter.addItem(freelancerArrayList);
                            adapter.notifyDataSetChanged();
                            Log.w(TAG, "onComplete: "+ freelancerArrayList.size() );
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public class PostHolder extends RecyclerView.ViewHolder {
        private TextView name, keahlian,desc;
        private ImageView profile;
        private View view;
        private ProgressBar progressBar;
        public PostHolder(View itemView){
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.freelancer_nama);
            desc = itemView.findViewById(R.id.freelancer_deskripsi);
            keahlian = itemView.findViewById(R.id.freelancer_keahlian);
            profile= itemView.findViewById(R.id.freelancer_foto_profile);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
