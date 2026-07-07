package digitalbull.app.accuratelab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import digitalbull.app.accuratelab.Models.Order;
import digitalbull.app.accuratelab.OrderProductDetails;
import digitalbull.app.accuratelab.R;
import digitalbull.app.accuratelab.Utils.Constants;
import digitalbull.app.accuratelab.Utils.SessionManage;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<Order> arrayList;
    private Context context;
    String Userid="";
    private int layout;

    public OrderAdapter(Context context, ArrayList<Order> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getCustomer_id();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final Order data = arrayList.get(i);

        holder.txorderid.setText("Order Id : "+data.getOrder_id());
        holder.txdate.setText(" Placed on : "+data.getOrder_date());
        holder.txstatus.setText("Status : "+data.getOrder_status());

        String url = Constants.BASEURI2+data.getProduct_photo();
        Glide.with(context).load(url).into(holder.prdimg);

        if(data.getOrder_status().equals("Cancelled")){
            holder.click.setBackground(ContextCompat.getDrawable(context,R.drawable.red_box));
        }

        holder.txcount.setText(data.getProduct_name() + " ("+data.getProduct_qty()+" items)");
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, OrderProductDetails.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txorderid,txdate,txstatus,txcount;
        LinearLayout click;
        ImageView prdimg;


        ViewHolder(View view) {
            super(view);
            txorderid = view.findViewById(R.id.txorderid);
            txdate= view.findViewById(R.id.txdate);
            txcount= view.findViewById(R.id.txcount);
            txstatus= view.findViewById(R.id.txstatus);
            click= view.findViewById(R.id.click);
            prdimg= view.findViewById(R.id.prdimg);
        }
    }

    public void updateList(ArrayList<Order> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}