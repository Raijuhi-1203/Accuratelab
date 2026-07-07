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

import digitalbull.app.accuratelab.Adapter.OrderProductAdapter;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.Order;
import digitalbull.app.accuratelab.Models.OrderProductModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Utils.SessionManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderProductDetails extends AppCompatActivity {

    RecyclerView recycler;
    LinearLayout norecord;
    CustomerModel customerModel;
    Order order;
    TextView orderid,suborderid,txstatus,txnm,txaddress,txmob,txmail,txmrp,txsmrp,txpamt;
    ArrayList<OrderProductModel> orders=new ArrayList<>();
    OrderProductAdapter orderAdapter;
    Button btncancelall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_product_details);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        order=(Order)getIntent().getSerializableExtra("data");

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Order Id : "+order.getOrder_id());

        norecord=findViewById(R.id.norecord);
        recycler=findViewById(R.id.recycler);txpamt=findViewById(R.id.txpamt);
        txsmrp=findViewById(R.id.txsmrp);txmrp=findViewById(R.id.txmrp);
        txmail=findViewById(R.id.txmail);txmob=findViewById(R.id.txmob);
        txaddress=findViewById(R.id.txaddress);txnm=findViewById(R.id.txnm);
        txstatus=findViewById(R.id.txstatus);suborderid=findViewById(R.id.suborderid);
        orderid=findViewById(R.id.orderid); btncancelall=findViewById(R.id.btncancelall);

        orderid.setText("Order id : "+order.getOrder_id()); suborderid.setText("Suborder id : "+order.getOrder_id_temp());
        txstatus.setText("Status : "+order.getOrder_status());
        txaddress.setText(order.getBilling_address_line1()+", "+order.getBilling_address_line2()+", "+order.getBilling_city_name()+", "+order.getBilling_state_name()+"-"+order.getBilling_pincode());
        txnm.setText(order.getCustomer_name()); txmail.setText(order.getCustomer_email());
        txmob.setText(order.getCustomer_mobileno()); txsmrp.setText(order.getProduct_shipping_charge());
        txmrp.setText(order.getTotal_order_amount());

        float a,b,c,d,e,f;
        a= Float.parseFloat(order.getTotal());
        b= Float.parseFloat(order.getProduct_shipping_charge());
        e= ((a+b));

        txpamt.setText(String.valueOf(e));

        GridLayoutManager mLayoutManager = new GridLayoutManager(OrderProductDetails.this, 1);
        recycler.setLayoutManager(mLayoutManager);
        orderAdapter = new OrderProductAdapter(OrderProductDetails.this, orders, R.layout.item_orderproduct,"item");
        recycler.setAdapter(orderAdapter);
        recycler.setItemViewCacheSize(orders.size());
        GetData();

        if (order.getOrder_status().equals("Cancelled")|| order.getOrder_status().equals("Delivered")){
            btncancelall.setVisibility(View.GONE);
        }

        btncancelall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderProductDetails.this, CancelOrderForm.class);
                intent.putExtra("order",order);
                startActivity(intent);
            }
        });

    }

    private void GetData() {
        orders.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(OrderProductDetails.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("order_id",order.getOrder_id()));
        jc.SendRequest("get_order_product", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    OrderProductModel product = new OrderProductModel();
                    product.setOrder_id(obj.getString("order_id"));
                    product.setTotal(obj.getString("total"));
                    product.setOrder_delivery_date(obj.getString("order_delivery_date"));
                    product.setOrder_status(obj.getString("order_status"));
                    product.setTotal_order_amount(obj.getString("total_order_amount"));
                    product.setOrder_delivery_time(obj.getString("order_delivery_time"));
                    product.setOrder_id_temp(obj.getString("order_id_temp"));
                    product.setSub_order_id_temp(obj.getString("sub_order_id_temp"));
                    product.setSub_order_id(obj.getString("sub_order_id"));
                    product.setOrder_date(obj.getString("order_date"));
                    product.setOrder_time(obj.getString("order_time"));
                    product.setPayment_mode(obj.getString("payment_mode"));
                    product.setCustomer_name(obj.getString("customer_name"));
                    product.setTotal_amount_of_product(obj.getString("total_amount_of_product"));
                    product.setCustomer_mobileno(obj.getString("customer_mobileno"));
                    product.setCustomer_email(obj.getString("customer_email"));
                    product.setGuest_id(obj.getString("guest_id"));
                    product.setBilling_address_line1(obj.getString("billing_address_line1"));
                    product.setBilling_address_line2(obj.getString("billing_address_line2"));
                    product.setBilling_city_name(obj.getString("billing_city_name"));
                    product.setBilling_state_name(obj.getString("billing_state_name"));
                    product.setBilling_pincode(obj.getString("billing_pincode"));
                    product.setBilling_landmark(obj.getString("billing_landmark"));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_name(obj.getString("product_name"));
//                        product.setProduct_hindi_name(obj.getString("product_hindi_name"));
                    product.setProduct_qty(obj.getString("product_qty"));
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
//                        product.setProduct_sell_price(obj.getString("product_sell_price"));
                        product.setProduct_photo(obj.getString("product_photo"));
                    product.setProduct_qty(obj.getString("product_qty"));
                    product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                    orders.add(product);
                }
                orderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

}
