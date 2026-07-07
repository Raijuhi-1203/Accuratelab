package digitalbull.app.accuratelab.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import digitalbull.app.accuratelab.Models.AddressModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.R;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.UpdateAddress;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.ExtraCallBack;
import digitalbull.app.accuratelab.interfaces.Notify;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private ArrayList<AddressModel> arrayList;
    private Context context;
    String Userid="",token_id;
    private int layout;
    Notify notify;
    ExtraCallBack ecb;
    String total,market_price_total;
    ArrayList<ProductModel> productModels;

    public AddressAdapter(Context context, ArrayList<AddressModel> arrayList, int layout, Notify notify1, String ttl, String market_price_total1, ArrayList<ProductModel> productModels) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.notify=notify1;
        this.total=ttl;
        this.market_price_total=market_price_total1;
        this.productModels=productModels;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getCustomer_id();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        final AddressModel data = arrayList.get(i);

        holder.txnm.setText(data.getAddress_customer_name());
        holder.txmail.setText(data.getAddress_customer_email());
        holder.txmob.setText(data.getAddress_customer_mobileno());
        holder.txaddress.setText(data.getAddress_line_1()+", "+data.getAddress_line_2()+", "+data.getAddress_city_name()+", "+data.getAddress_state_name()+"-"+data.getAddress_pincode());
        holder.txmail.setText(data.getAddress_customer_email());

        if(Objects.equals(data.getAddress_default(), "Yes")){
            holder.btdefault.setVisibility(View.GONE);
        } else if (Objects.equals(data.getAddress_default(), "No")) {
            holder.btdefault.setVisibility(View.VISIBLE);
        }

        if (data.getAddress_default().equals("Yes")){
            holder.linear_layout.setBackgroundResource(R.drawable.cat_boxyellow);
        }else if (Objects.equals(data.getAddress_default(), "No")) {
            holder.linear_layout.setBackgroundResource(R.drawable.cat_box2);
        }

        holder.btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAddress(data.getId(),data.getCustomer_id());
            }
        });

        holder.btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateAddress.class);
                intent.putExtra("data",data);
                intent.putExtra("total",total);
                intent.putExtra("market_price_total",market_price_total);
                intent.putExtra("productModel",productModels);
                context.startActivity(intent);
            }
        });
        holder.btdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MakeDefault(data.getId(),data.getCustomer_id(),"Yes");
               holder.linear_layout.setBackgroundResource(R.drawable.cat_boxyellow);
            }
        });
    }

    private void MakeDefault(String id, String customer_id, String status) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("customer_id",customer_id));
        param.add(new NetParam("id",id));
        param.add(new NetParam("status",status));
        jc.SendRequest("makedefault_address", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Address Make Default !!",context);
                if (ecb != null){
                    ecb.OnCompleted("removed","removed");
                }
                notify.onAdd(null);
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

    private void DeleteAddress(String id, String customer_id) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("customer_id",customer_id));
        param.add(new NetParam("id",id));
        param.add(new NetParam("token_id",token_id));
        jc.SendRequest("delete_address", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Address Deleted !!",context);
                if (ecb != null){
                    ecb.OnCompleted("removed","removed");
                }
                notify.onAdd(null);
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txnm,txaddress,txmob,txmail;
        Button btedit,btdefault;
        ImageView btdel;
        LinearLayout linear_layout;
        ViewHolder(View view) {
            super(view);
            txnm = (TextView) view.findViewById(R.id.txnm);
            txmob= view.findViewById(R.id.txmob);
            txaddress= view.findViewById(R.id.txaddress);
            txmail= view.findViewById(R.id.txmail);
            btedit= view.findViewById(R.id.btedit);
            btdel= view.findViewById(R.id.btdel);
            btdefault= view.findViewById(R.id.btdefault);
            linear_layout= view.findViewById(R.id.linear_layout);
        }
    }

    public void updateList(ArrayList<AddressModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}