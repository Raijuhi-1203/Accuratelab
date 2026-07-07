package digitalbull.app.accuratelab.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.PageProductDetails;
import digitalbull.app.accuratelab.R;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.CallJsonWithoutProgress;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.Constants;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.ExtraCallBack;
import digitalbull.app.accuratelab.interfaces.Notify;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    Notify notify;
    ExtraCallBack ecb;
    String str,token;
    String num ="N",seller_id,firm_name,GuestID;
    public ProductAdapter(Context context, ArrayList<ProductModel> arrayList, int layout,Notify notify1,String n,String seller_id,String firm_name) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        this.notify=notify1;
        this.num=n;
        this.seller_id=seller_id;
        this.firm_name=firm_name;

        if (SessionManage.getCurrentUser(context) == null )
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            GuestID = prefs.getString("guest_id","empty");
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            GuestID = prefs.getString("guest_id","empty");
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
        final ProductModel data = arrayList.get(i);

        holder.prdnm.setText(data.getProduct_full_name());
        holder.mrp.setText("₹ "+data.getProduct_market_price());

        String url = Constants.BASEURI2+data.getPhoto_path();
        Glide.with(context).load(url).into(holder.prdimg);

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(num, "R")){

                }else if (Objects.equals(num, "G")){
                    Intent intent=new Intent(context, PageProductDetails.class);
                    intent.putExtra("data",data);
                    context.startActivity(intent);
                }
            }
        });


        holder.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String section = "";
                if (data.getProduct_id().startsWith("F")){
                    section="Restaurant";
                } else if (data.getProduct_id().startsWith("P")) {
                    section="Grocery";
                }

                AddCart(section,Userid, data.getProduct_id(),data.getId(),data.getProduct_final_sell_price(),data.getProduct_full_name(),data.getProduct_market_price(),data.getProduct_sell_price(),data.getProduct_discount_percentage(),data.getProduct_discount_price(),data.getProduct_with_gst_Price(),data.getProduct_final_sell_price(),data.getProduct_market_price());
            }
        });


    }

    private void RemoveCart(String product_id, String userid, String product_final_sell_price) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("product_price_id",product_final_sell_price));
        param.add(new NetParam("customer_id",userid));
        param.add(new NetParam("cart_guest_id",GuestID));
        jc.SendRequest("removetocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Remove from Cart !!",context);
                if (ecb != null){
                    ecb.OnCompleted("removed","removed");
                }
                notify.onAdd(null);
//                BindCart();
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }

    private void AddCart(String section,String userid, String product_id, String product_final_sell_price,String price,String product_name,String product_market_price,String product_sell_price,String product_discount_percentage,String product_discount_price,String product_with_gst_Price,String product_final_sell_price2,String total_market_price) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("cart_qty","1"));
        param.add(new NetParam("product_price_id",product_final_sell_price));
        param.add(new NetParam("customer_id",userid));
        param.add(new NetParam("cart_guest_id",GuestID));
        param.add(new NetParam("total_order_amount",price));
        param.add(new NetParam("cart_section",section));
        param.add(new NetParam("product_name",product_name));
        param.add(new NetParam("product_market_price",product_market_price));
        param.add(new NetParam("product_sell_price",product_sell_price));
        param.add(new NetParam("product_discount_percentage",product_discount_percentage));
        param.add(new NetParam("product_discount_price",product_discount_price));
        param.add(new NetParam("product_with_gst_Price",product_with_gst_Price));
        param.add(new NetParam("product_final_sell_price",product_final_sell_price2));
        param.add(new NetParam("total_market_price",total_market_price));
        param.add(new NetParam("token_id",token));

        if (product_id.startsWith("P")){
            param.add(new NetParam("product_sellerid","0"));
            param.add(new NetParam("product_sellername","0"));
        }else if (product_id.startsWith("F")){
            param.add(new NetParam("product_sellerid",seller_id));
            param.add(new NetParam("product_sellername",firm_name));
        }

        jc.SendRequest("addtocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Add to Cart !!",context);
                if (ecb != null){
                    ecb.OnCompleted("removed","removed");
                }
                notify.onAdd(null);
//                BindCart();
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

    private void BindCart() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
        param.add(new NetParam("customer_id",Userid));
        jc.SendRequest("get_cart_qty", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
                if (obj.length() != 1) {
                    Constants.CARTCOUNT = obj.getString("carttotal");
                }
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prdnm,mrp;
        Button btncart;
        ImageView prdimg;
        LinearLayout click_layout;

        ViewHolder(View view) {
            super(view);
            prdnm = (TextView) view.findViewById(R.id.prdnm);
            mrp = (TextView) view.findViewById(R.id.mrp);
            prdimg=(ImageView)view.findViewById(R.id.prdimg);
            click_layout= view.findViewById(R.id.click_layout);
            btncart= view.findViewById(R.id.btncart);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateList(ArrayList<ProductModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

}
