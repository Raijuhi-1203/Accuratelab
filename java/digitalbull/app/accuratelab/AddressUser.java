package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Adapter.AddressAdapter;
import digitalbull.app.accuratelab.Models.AddressModel;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.Notify;

public class AddressUser extends AppCompatActivity {
    Button btn_add;
    RecyclerView recycler;
    CustomerModel customerModel;
    AddressAdapter addressAdapter;
    ArrayList<AddressModel> addressModels=new ArrayList<>();
    ArrayList<ProductModel> productModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_book2);
        EdgeToEdge.enable(this);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        btn_add=findViewById(R.id.btn_add);recycler=findViewById(R.id.recycler);
        productModel=(ArrayList<ProductModel>)getIntent().getSerializableExtra("productModel");

        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Address");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddressUser.this,AddAddress.class);
                intent.putExtra("total","0");
                intent.putExtra("market_price_total","0");
                intent.putExtra("order_section","0");
                intent.putExtra("productModel",productModel);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(AddressUser.this, 1);
        recycler.setLayoutManager(mLayoutManager);
        addressAdapter = new AddressAdapter(AddressUser.this, addressModels, R.layout.item_address, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetData();
            }

            @Override
            public void onRemove(ProductModel data) {
                GetData();
            }
        },"0","0",productModel);
        recycler.setAdapter(addressAdapter);
        recycler.setItemViewCacheSize(addressModels.size());
        GetData();

    }

    private void GetData() {
        addressModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddressUser.this);
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

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }


}
