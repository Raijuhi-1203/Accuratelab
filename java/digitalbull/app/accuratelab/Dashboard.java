package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Adapter.CategoryAdapter;
import digitalbull.app.accuratelab.Adapter.ProductAdapter;
import digitalbull.app.accuratelab.Models.CategoryModel;
import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJsonWithoutProgress;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Utils.SessionManage;
import digitalbull.app.accuratelab.interfaces.Notify;

public class Dashboard extends AppCompatActivity {
    TextView tvMarquee;
    LinearLayout home,cat,packagess,profiles,report;
    RecyclerView rv_test,rv_category;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> categoryModels=new ArrayList<>();
    ArrayList<ProductModel> productModels=new ArrayList<>();
    ProductAdapter productAdapter;
    ImageView profile,logout;
    CustomerModel customerModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));

        tvMarquee = findViewById(R.id.tvMarquee);home=findViewById(R.id.home);
        rv_category = findViewById(R.id.rv_category);rv_test = findViewById(R.id.rv_test);
        packagess=findViewById(R.id.packagess);cat=findViewById(R.id.cat);
        profiles=findViewById(R.id.profiles);profile=findViewById(R.id.profile);
        logout=findViewById(R.id.logout);report=findViewById(R.id.report);

        // Important line to start marquee
        tvMarquee.setSelected(true);
        
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Profile.class));
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,CartPage.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Profile.class));
            }
        });
        packagess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Packagess.class));
            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Category.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SessionManage.ClearSession(getApplicationContext());
                        startActivity(new Intent(Dashboard.this,MainActivity.class));
                        finish();
                    }
                });

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Dashboard.this, LinearLayoutManager.HORIZONTAL,false);
        rv_category.setLayoutManager(mLayoutManager);
        categoryAdapter = new CategoryAdapter(Dashboard.this, categoryModels,R.layout.item_category);
        rv_category.setAdapter(categoryAdapter);
        GetData();

        GridLayoutManager mLayoutManager4 = new GridLayoutManager(Dashboard.this, 2);
        rv_test.setLayoutManager(mLayoutManager4);
        productAdapter = new ProductAdapter(Dashboard.this, productModels, R.layout.item_product, new Notify() {
            @Override
            public void onAdd(ProductModel data) {
                GetProduct();
            }
            @Override
            public void onRemove(ProductModel data) {
                GetProduct();
            }
        },"G","0","0");
        rv_test.setAdapter(productAdapter);
        rv_test.setItemViewCacheSize(productModels.size());
        GetProduct();

    }

    private void GetData() {
        categoryModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(Dashboard.this);
        jc.SendRequest("get_category", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                rv_category.setVisibility(View.VISIBLE);
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CategoryModel product = new CategoryModel();
                    product.setCategory_name(obj.getString("category_name"));
                    product.setCategory_id(obj.getString("category_id"));
                    product.setCategory_photo(obj.getString("category_photo"));
                    categoryModels.add(product);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void GetProduct() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(Dashboard.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        jc.SendRequest("get_product_top", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setProduct_id(obj.getString("product_id"));
                    product.setId(obj.getString("id"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
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
                    product.setProduct_GST_type(obj.getString("product_GST_type"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    product.setProduct_stock(obj.getString("product_stock"));
                    product.setProduct_shipping_charge(obj.getString("product_shipping_charge"));
                    product.setProduct_sell_price(obj.getString("product_sell_price"));
                    product.setProduct_CGST_percentage(obj.getString("product_CGST_percentage"));
                    product.setProduct_CGST_rate(obj.getString("product_CGST_rate"));
                    product.setProduct_SGST_percentage(obj.getString("product_SGST_percentage"));
                    product.setProduct_SGST_rate(obj.getString("product_SGST_rate"));
                    productModels.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

}
