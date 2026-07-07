package digitalbull.app.accuratelab;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import digitalbull.app.accuratelab.Adapter.OrderAdapter;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.Order;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Utils.SessionManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PageOrder extends AppCompatActivity {
        RecyclerView recycler;
        LinearLayout norecord;
        CustomerModel customerModel;
        ArrayList<Order> orders=new ArrayList<>();
        OrderAdapter orderAdapter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.my_order);
            customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());

            ImageView btback=findViewById(R.id.btback);
            btback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            TextView title = findViewById(R.id.title);
            title.setText("My Order");

            norecord=findViewById(R.id.norecord);
            recycler=findViewById(R.id.recycler);

            GridLayoutManager mLayoutManager = new GridLayoutManager(PageOrder.this, 1);
            recycler.setLayoutManager(mLayoutManager);
            orderAdapter = new OrderAdapter(PageOrder.this, orders, R.layout.item_order);
            recycler.setAdapter(orderAdapter);
            recycler.setItemViewCacheSize(orders.size());
            GetData();

        }

        private void GetData() {
            orders.clear();
            ArrayList<NetParam> param;
            param = new ArrayList<NetParam>();
            CallJson jc = new CallJson(PageOrder.this);
            param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
            jc.SendRequest("get_allorder", param, new JsonCallbacks() {
                @Override
                public void onPostSuceess(String json, String method) throws JSONException {
                    JSONArray array = new JSONArray(json);
                    for (int s = 0; s < array.length(); s++) {
                        JSONObject obj = array.getJSONObject(s);
                        Order product = new Order();
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
                        product.setTotal_amount_of_product(obj.getString("total_amount_of_product"));
                        product.setProduct_photo(obj.getString("product_photo"));
                        product.setProduct_qty(obj.getString("product_qty"));
                        product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                        orders.add(product);
                    }
                    orderAdapter.notifyDataSetChanged();
                    BindDataToView();
                }

                @Override
                public void onPostError(String msg) {
                    BindDataToView();
                }
            }, "", "Loading..");
        }

        private void BindDataToView() {
            if(orders.size()>0)
                norecord.setVisibility(View.GONE);
            else
                norecord.setVisibility(View.VISIBLE);
        }


    }