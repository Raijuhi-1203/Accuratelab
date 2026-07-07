package digitalbull.app.accuratelab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.Constants;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.ExtraCallBack;
import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.ArrayList;

public class PageProductDetails extends AppCompatActivity {
    ProductModel productModel;
    ImageView img;
    TextView txnm,mrp,fulldesp,bt_buy,btncart;
    CustomerModel customerModel;
    ExtraCallBack ecb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        EdgeToEdge.enable(this);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        productModel=(ProductModel) getIntent().getSerializableExtra("data");

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText(productModel.getProduct_full_name());

        BindIds();

        String url = Constants.BASEURI2+productModel.getPhoto_path();
        Glide.with(PageProductDetails.this).load(url).into(img);

        bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCart();
                Intent intent=new Intent(PageProductDetails.this, CartPage.class);
                startActivity(intent);
            }
        });

    }

    private void BindIds() {
        btncart=findViewById(R.id.btncart);bt_buy=findViewById(R.id.bt_buy);
        img=findViewById(R.id.img);txnm=findViewById(R.id.txnm);
        mrp=findViewById(R.id.mrp);fulldesp=findViewById(R.id.fulldesp);

        txnm.setText(productModel.getProduct_full_name());
        mrp.setText("₹ "+productModel.getProduct_final_sell_price());
        fulldesp.setText(Html.fromHtml(productModel.getProduct_description()));

    }

    private void AddCart() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageProductDetails.this);

        param.add(new NetParam("product_id",productModel.getProduct_id()));
        param.add(new NetParam("cart_qty","0"));
        param.add(new NetParam("product_price_id",productModel.getId()));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("cart_guest_id"," "));
        param.add(new NetParam("total_order_amount",productModel.getProduct_final_sell_price()));
        param.add(new NetParam("cart_section","Grocery"));
        param.add(new NetParam("product_name",productModel.getProduct_full_name()));
        param.add(new NetParam("product_market_price",productModel.getProduct_market_price()));
        param.add(new NetParam("product_sell_price",productModel.getProduct_sell_price()));
        param.add(new NetParam("product_discount_percentage",productModel.getProduct_discount_percentage()));
        param.add(new NetParam("product_discount_price",productModel.getProduct_discount_price()));
        param.add(new NetParam("product_with_gst_Price",productModel.getProduct_with_gst_Price()));
        param.add(new NetParam("product_final_sell_price",productModel.getProduct_final_sell_price()));
        param.add(new NetParam("total_market_price",productModel.getProduct_final_sell_price()));
        param.add(new NetParam("token_id",""));
        param.add(new NetParam("product_sellerid","0"));
        param.add(new NetParam("product_sellername","0"));

        jc.SendRequest("addtocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Add to Cart !!",PageProductDetails.this);
                if (ecb != null){
                    ecb.OnCompleted("removed","removed");
                }

            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

}
