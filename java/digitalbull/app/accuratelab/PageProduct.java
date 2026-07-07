package digitalbull.app.accuratelab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import digitalbull.app.accuratelab.Adapter.ProductAdapter;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJsonWithoutProgress;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.Notify;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PageProduct extends AppCompatActivity {
    LinearLayout norecord;
    ProductAdapter categoryAdapter;
    ArrayList<ProductModel> categoryModels=new ArrayList<>();
    RecyclerView rvcat;
    CustomerModel customerModel;
    String catid,catnm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        EdgeToEdge.enable(this);
        catid=getIntent().getStringExtra("data");
        catnm=getIntent().getStringExtra("catnm");

        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        norecord=findViewById(R.id.norecord);
        rvcat=findViewById(R.id.rvcat);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText(catnm);

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(PageProduct.this, 2);
        rvcat.setLayoutManager(mLayoutManager2);
        categoryAdapter = new ProductAdapter(PageProduct.this, categoryModels, R.layout.item_product, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetData();
            }

            @Override
            public void onRemove(ProductModel data) {
                GetData();

            }
        },"G","0","0");
        rvcat.setAdapter(categoryAdapter);
        rvcat.setItemViewCacheSize(categoryModels.size());
        GetData();

    }

    private void GetData() {
        categoryModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(PageProduct.this);
        param.add(new NetParam("category_id",catid));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("cart_guest_id",""));
        jc.SendRequest("get_catwiseproduct", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                rvcat.setVisibility(View.VISIBLE);
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setId(obj.getString("id"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setProduct_market_price(obj.getString("product_market_price"));
                    product.setPhoto_path(obj.getString("photo_path"));
                    product.setProduct_full_name(obj.getString("product_full_name"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_description(obj.getString("product_description"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setProduct_parent_category_id(obj.getString("product_parent_category_id"));
                    product.setProduct_unit(obj.getString("product_unit"));
                    product.setProduct_unit_value(obj.getString("product_unit_value"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_GST_percentage(obj.getString("product_GST_percentage"));
                    product.setProduct_GST_rate(obj.getString("product_GST_rate"));

                    product.setProduct_CGST_percentage(obj.getString("product_CGST_percentage"));
                    product.setProduct_CGST_rate(obj.getString("product_CGST_rate"));
                    product.setProduct_SGST_percentage(obj.getString("product_SGST_percentage"));
                    product.setProduct_SGST_rate(obj.getString("product_SGST_rate"));

                    product.setProduct_GST_type(obj.getString("product_GST_type"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    product.setProduct_stock(obj.getString("product_stock"));
                    product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                    product.setProduct_sell_price(obj.getString("product_sell_price"));
                    categoryModels.add(product);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

}
