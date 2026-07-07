package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Models.CityModel;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Models.StateModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Utils.SessionManage;

public class AddAddress extends AppCompatActivity {
    EditText txnm,txmail,txmob,txadd1,txadd2,txpincode;
    Spinner spnrstate,spnrcity;
    Button btn_save;
    CheckBox chkdefault;
    CustomerModel customerModel;
    String spnrstid,spnrctid,statnm,citynm,order_section;
    String carttotal,market_price_total;
    ArrayList<ProductModel> productModels;
    ArrayAdapter<StateModel> stateModelArrayAdapter;
    ArrayList<StateModel> stateModels=new ArrayList<>();
    ArrayAdapter<CityModel> cityModelArrayAdapter;
    ArrayList<CityModel> cityModels=new ArrayList<>();
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);
        EdgeToEdge.enable(this);

        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModels=(ArrayList<ProductModel>)getIntent().getSerializableExtra("productModel");
        id=getIntent().getStringExtra("id");
        Bindids();


        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Add Address");

        spnrstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spnrstate.getSelectedItemPosition();
                spnrstid=String.valueOf(stateModels.get(pos).getState_id());
                statnm=String.valueOf(stateModels.get(pos).getState_name());
                CityCall(spnrstid);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spnrcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spnrcity.getSelectedItemPosition();
                spnrctid=String.valueOf(cityModels.get(pos).getCity_id());
                citynm=String.valueOf(cityModels.get(pos).getCity_name());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        stateModels = new ArrayList<>();
        stateModelArrayAdapter = new ArrayAdapter<StateModel>(AddAddress.this, android.R.layout.simple_spinner_item, stateModels);
        stateModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrstate.setAdapter(stateModelArrayAdapter);

        cityModels = new ArrayList<>();
        cityModelArrayAdapter = new ArrayAdapter<CityModel>(AddAddress.this, android.R.layout.simple_spinner_item, cityModels);
        cityModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrcity.setAdapter(cityModelArrayAdapter);

        AreaJsonCall();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txnm.getText().length()==0){
                    txnm.setError("Enter name");
                }else if(txmob.getText().length()==0){
                    txmob.setError("Enter mobile");
                }else if(txadd1.getText().length()==0){
                    txadd1.setError("Enter address 1");
                }else if(txadd2.getText().length()==0){
                    txadd2.setError("Enter address 2");
                }else {
                    Add_Address();
                }
            }
        });

    }

    private void CityCall(String spnrstid) {
        cityModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddAddress.this);
        param.add(new NetParam("state_id",spnrstid));
        jc.SendRequest("get_city", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CityModel mod = new CityModel();
                    mod.setCity_id(obj.getString("id"));
                    mod.setCity_name(obj.getString("district_name"));
                    cityModels.add(mod);
                    cityModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "LOGIN", "Please wait while getting..");
    }
    private void AreaJsonCall() {
        stateModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddAddress.this);
        jc.SendRequest("get_state", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    StateModel mod = new StateModel();
                    mod.setState_id(obj.getString("state_id"));
                    mod.setState_name(obj.getString("state_name"));
                    stateModels.add(mod);
                    stateModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "LOGIN", "Please wait while getting..");
    }
    private void Add_Address() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddAddress.this);
        param.add(new NetParam("address_customer_name",txnm.getText().toString()));
        param.add(new NetParam("address_customer_mobileno",txmob.getText().toString()));
        param.add(new NetParam("address_customer_email",txmail.getText().toString()));
        param.add(new NetParam("address_line_1",txadd1.getText().toString()));
        param.add(new NetParam("address_line_2",txadd2.getText().toString()));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("address_pincode",txpincode.getText().toString()));
        param.add(new NetParam("address_city_id",spnrctid));
        param.add(new NetParam("address_city_name",citynm));
        param.add(new NetParam("address_state_id",spnrstid));
        param.add(new NetParam("address_state_name",statnm));
        String mdefault;
        if(chkdefault.isChecked()){
            mdefault="Yes";
        }else {
            mdefault="No";
        }
        param.add(new NetParam("address_default",mdefault));
        jc.SendRequest("addaddress", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {

                if (id.equals("1")){
                    Intent intent=new Intent(AddAddress.this,AddressUser.class);
                    intent.putExtra("total",carttotal);
                    intent.putExtra("market_price_total",market_price_total);
                    intent.putExtra("productModel",productModels);
                    intent.putExtra("order_section",order_section);
                    startActivity(intent);
                }else if(id.equals("2")){
                    Intent intent=new Intent(AddAddress.this,PageAddress.class);
                    intent.putExtra("total",carttotal);
                    intent.putExtra("market_price_total",market_price_total);
                    intent.putExtra("productModel",productModels);
                    intent.putExtra("order_section",order_section);
                    startActivity(intent);
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }
    private void Bindids() {
        txnm=findViewById(R.id.txnm);txmail=findViewById(R.id.txmail);
        txmob=findViewById(R.id.txmob);txadd1=findViewById(R.id.txadd1);
        txadd2=findViewById(R.id.txadd2);
        spnrstate=findViewById(R.id.spnrstate);
        spnrcity=findViewById(R.id.spnrcity);txpincode=findViewById(R.id.txpincode);
        btn_save=findViewById(R.id.btn_save);chkdefault=findViewById(R.id.chkdefault);
    }

}
