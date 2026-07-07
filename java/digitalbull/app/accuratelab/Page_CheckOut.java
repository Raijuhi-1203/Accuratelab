package digitalbull.app.accuratelab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import digitalbull.app.accuratelab.Models.AddressModel;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.ExtraCallBack;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Page_CheckOut extends AppCompatActivity {
    String carttotal,market_price_total;
    CustomerModel customerModel;
    TextView txmrp,txsmrp,txpamt,btorder;
    RadioGroup radioGroup;
    RadioButton radioButton;
    ArrayList<ProductModel> productModels;
    ArrayList<AddressModel> addressModels;
    double discount=0,shipping=0,coupondis=0,totalpay=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_details);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModels=(ArrayList<ProductModel>)getIntent().getSerializableExtra("productModel");
        addressModels=(ArrayList<AddressModel>)getIntent().getSerializableExtra("address");



        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Final Bill");

        radioGroup=findViewById(R.id.radioGroup);
        txmrp=findViewById(R.id.txmrp);
        txsmrp=findViewById(R.id.txsmrp);
        txpamt=findViewById(R.id.txpamt);
        btorder=findViewById(R.id.btorder);

        txmrp.setText(market_price_total);
        txsmrp.setText(productModels.get(0).getProduct_shipping_charge());

        shipping=Double.parseDouble(txsmrp.getText().toString());
        totalpay = Double.parseDouble(carttotal) + shipping;

        txpamt.setText(String.format("%.02f",totalpay));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                radioButton.getText().toString();
            }
        });

        btorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderNow();
            }
        });



    }

    private void OrderNow() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        String orderitms= new Gson().toJson(productModels);
        String address= new Gson().toJson(addressModels);
        CallJson jc = new CallJson(Page_CheckOut.this);
        param.add(new NetParam("payment_mode",radioButton.getText().toString()));
        float f = Float.parseFloat(carttotal);
        int ctotal = (int) f;
        param.add(new NetParam("total_order_amount",String.valueOf(ctotal)));
        param.add(new NetParam("items",orderitms));
        param.add(new NetParam("address",address));
        param.add(new NetParam("guest_id"," "));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("delivery_schedule_time"," "));
        jc.SendRequest("addorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                startActivity(new Intent(Page_CheckOut.this,PageSuccefull.class));
                UserUtil.ShowMsg("Order Placed !!",Page_CheckOut.this);
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }


}
