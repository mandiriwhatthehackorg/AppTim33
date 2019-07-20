package men.ngopi.zakwan.jasanesia.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import men.ngopi.zakwan.jasanesia.R;


public class IklanRecycleViewAdapter extends RecyclerView.Adapter<IklanRecycleViewAdapter.IklanRecycleViewHolder> {

    private ArrayList<IklanModel> dataList;

    public IklanRecycleViewAdapter(ArrayList<IklanModel> dataList) {
        this.dataList = dataList;
    }

    public void addItem(ArrayList<IklanModel> mData) {
        this.dataList = mData;
        notifyDataSetChanged();
    }

    @Override
    public IklanRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_dashboard_iklan_layout, parent, false);
        return new IklanRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IklanRecycleViewHolder holder, int position) {

        Picasso.get().load(dataList.get(position).getFoto_url()).fit().centerCrop().into(holder.ivIklanBanner);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class IklanRecycleViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivIklanBanner;

        public IklanRecycleViewHolder(View itemView) {
            super(itemView);
            ivIklanBanner = (ImageView) itemView.findViewById(R.id.dashboard_iklan_img);
        }
    }
}
