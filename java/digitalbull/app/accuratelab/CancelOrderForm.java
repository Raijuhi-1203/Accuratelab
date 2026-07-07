package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.Order;
import digitalbull.app.accuratelab.Models.OrderProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.SessionManage;

import org.json.JSONException;

import java.util.ArrayList;

public class CancelOrderForm extends AppCompatActivity  {

    Order order;
    CustomerModel customerModel;
    Spinner spnr;
    String spnrid;
    EditText reason;
    Button btnsubmit;
    OrderProductModel orderProductModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_orderform);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        order=(Order)getIntent().getSerializableExtra("order");
        orderProductModel=(OrderProductModel)getIntent().getSerializableExtra("orderproduct");

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Cancel Order");

        reason=findViewById(R.id.reason);
        btnsubmit=findViewById(R.id.btnsubmit);
        spnr=findViewById(R.id.spnr);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cancelorder, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr.setAdapter(adapter);

        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spnrid = spnr.getSelectedItem().toString();;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reason.getText().length()==0){
                    reason.setError("Please write reason..");
                }else {
                    if (orderProductModel == null){
                        CancelOrder();
                    }else if (order == null) {
                        CancelItem();
                    }
                }
            }
        });
    }

    private void CancelItem() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(CancelOrderForm.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("product_id",orderProductModel.getProduct_id()));
        param.add(new NetParam("order_return_reason",spnrid));
        param.add(new NetParam("order_return_comment",reason.getText().toString()));
        param.add(new NetParam("order_id",orderProductModel.getOrder_id()));
        jc.SendRequest("Cancel_order_Test", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Item Cancel Successfully.",CancelOrderForm.this);
                startActivity(new Intent(CancelOrderForm.this,CancelMessage.class));
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void CancelOrder() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(CancelOrderForm.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("order_return_reason",spnrid));
        param.add(new NetParam("order_return_comment",reason.getText().toString()));
        param.add(new NetParam("order_id",order.getOrder_id()));
        jc.SendRequest("Cancel_Allorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
               UserUtil.ShowMsg("Order Cancel Successfully.",CancelOrderForm.this);
               startActivity(new Intent(CancelOrderForm.this,CancelMessage.class));
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

}
