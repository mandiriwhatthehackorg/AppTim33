package men.ngopi.zakwan.jasanesia.Dashboard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import men.ngopi.zakwan.jasanesia.FrelanceProfileFragment;
import men.ngopi.zakwan.jasanesia.MainActivity;
import men.ngopi.zakwan.jasanesia.Model.Freelancer;
import men.ngopi.zakwan.jasanesia.R;

public class FrelanceRecycleViewAdapter extends RecyclerView.Adapter<FrelanceRecycleViewAdapter.POstHolder> {

    private ArrayList<Freelancer> dataList;
    private MainActivity activity;

    private String TAG = "===== Frelance View Holder";

    public FrelanceRecycleViewAdapter(ArrayList<Freelancer> dataList , MainActivity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    public void addItem(ArrayList<Freelancer> mData) {
        this.dataList = mData;
        notifyDataSetChanged();
    }

    @Override
    public POstHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_dashboard_freelancer_recycle_layout, parent, false);
        return new POstHolder(view);
    }

    @Override
    public void onBindViewHolder(POstHolder holder, int position) {
        Log.w(TAG, "onBindViewHolder: "+ dataList.size() );
        try{
            Picasso.get().load(dataList.get(position).getPhoto()).resize(100,100).centerCrop().into(holder.profile);
        }catch (Exception e){
            Picasso.get().load("http://api.lakonesia.com/asset/image/1.jpg").resize(100,100).centerCrop().into(holder.profile);
        }
        holder.name.setText(dataList.get(position).getName());
        holder.desc.setText(dataList.get(position).getPendidikan());
        holder.keahlian.setText(dataList.get(position).getKeahlian());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FrelanceProfileFragment fragment = new FrelanceProfileFragment();
                fragmentTransaction.replace(R.id.fragment_dashboard_main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class POstHolder extends RecyclerView.ViewHolder{
        private LinearLayout container;
        private TextView txtNamaLayanan, txtNpm, txtNoHp , txtHargaLayanan , txtNamaLakon , txtHargaNormal , txtKategori;
        private ImageView ivLakonProfile;
        private RelativeLayout hargaNormalViewgroup;

        private TextView name, keahlian,desc;
        private ImageView profile;
        private View view;
        private ProgressBar progressBar;
        public POstHolder(View itemView){
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.freelancer_nama);
            desc = itemView.findViewById(R.id.freelancer_deskripsi);
            keahlian = itemView.findViewById(R.id.freelancer_keahlian);
            profile= itemView.findViewById(R.id.freelancer_foto_profile);
        }
    }
}

