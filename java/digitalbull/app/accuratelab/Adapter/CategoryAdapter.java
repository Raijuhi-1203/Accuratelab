package digitalbull.app.accuratelab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import digitalbull.app.accuratelab.Models.CategoryModel;
import digitalbull.app.accuratelab.PageProduct;
import digitalbull.app.accuratelab.R;
import digitalbull.app.accuratelab.Utils.Constants;
import digitalbull.app.accuratelab.Utils.SessionManage;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;

        if (SessionManage.getCurrentUser(context) == null )
        {
           // UserUtil.ShowMsg("Empty",DashBoard.this);
        }
        else {
            this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getCustomer_id();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final CategoryModel data = arrayList.get(i);

        holder.catnm.setText(data.getCategory_name());

        Glide.with(context).load(Constants.BASEURI2+data.getCategory_photo()).into(holder.catimg);

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PageProduct.class);
                intent.putExtra("data",data.getCategory_id());
                intent.putExtra("catnm",data.getCategory_name());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView catnm;
        ImageView catimg;
        LinearLayout click_layout;

        ViewHolder(View view) {
            super(view);
            catnm = (TextView) view.findViewById(R.id.catnm);
            catimg=(ImageView)view.findViewById(R.id.catimg);
            click_layout= view.findViewById(R.id.click_layout);
        }
    }

    public void updateList(ArrayList<CategoryModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}