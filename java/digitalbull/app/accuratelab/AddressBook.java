package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import digitalbull.app.accuratelab.Adapter.AddressAdapter;
import digitalbull.app.accuratelab.Models.AddressModel;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.Notify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddressBook extends AppCompatActivity  {
    Button btn_add;
    RecyclerView recycler;
    LinearLayout norecord;
    ArrayList<ProductModel> productModel;
    TextView btproceed;
    String carttotal,market_price_total,order_section;
    CustomerModel customerModel;
    AddressAdapter addressAdapter;
    ArrayList<AddressModel> addressModels=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_book);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModel=(ArrayList<ProductModel>)getIntent().getSerializableExtra("productModel");

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Add Address");

        norecord=findViewById(R.id.norecord);
        btn_add=findViewById(R.id.btn_add);
        btproceed=findViewById(R.id.btproceed);
        recycler=findViewById(R.id.recycler);

        btproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addressModels.size() == 0)
                {
                    UserUtil.ShowMsg("Please add address than appoinment booked.",AddressBook.this);
                }
                else
                {
                    Intent intent=new Intent(AddressBook.this,Page_CheckOut.class);
                    intent.putExtra("total",carttotal);
                    intent.putExtra("market_price_total",market_price_total);
                    intent.putExtra("productModel",productModel);
                    intent.putExtra("address",addressModels);
                    startActivity(intent);
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddressBook.this,AddAddress.class);
                intent.putExtra("total",carttotal);
                intent.putExtra("market_price_total",market_price_total);
                intent.putExtra("order_section",order_section);
                intent.putExtra("productModel",productModel);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(AddressBook.this, 1);
        recycler.setLayoutManager(mLayoutManager);
        addressAdapter = new AddressAdapter(AddressBook.this, addressModels, R.layout.item_address, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetData();
            }

            @Override
            public void onRemove(ProductModel data) {
                GetData();
            }
        },carttotal,market_price_total,productModel);
        recycler.setAdapter(addressAdapter);
        recycler.setItemViewCacheSize(addressModels.size());
        GetData();

    }

    private void GetData() {
        addressModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddressBook.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        jc.SendRequest("get_address", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    AddressModel product = new AddressModel();
                    product.setAddress_city_name(obj.getString("address_city_name"));
                    product.setAddress_default(obj.getString("address_default"));
                    product.setAddress_customer_email(obj.getString("address_customer_email"));
                    product.setAddress_city_name(obj.getString("address_city_name"));
                    product.setAddress_customer_mobileno(obj.getString("address_customer_mobileno"));
                    product.setAddress_customer_name(obj.getString("address_customer_name"));
                    product.setAddress_line_1(obj.getString("address_line_1"));
                    product.setAddress_line_2(obj.getString("address_line_2"));
                    product.setAddress_state_name(obj.getString("address_state_name"));
                    product.setCustomer_id(obj.getString("customer_id"));
                    product.setAddress_pincode(obj.getString("address_pincode"));
                    product.setId(obj.getString("id"));
                    addressModels.add(product);
                }
                addressAdapter.notifyDataSetChanged();
                BindDataToView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataToView();
            }
        }, "", "Loading..");
    }

    private void BindDataToView() {
        if(addressModels.size()>0)
            norecord.setVisibility(View.GONE);
        else
            norecord.setVisibility(View.VISIBLE);
    }

}