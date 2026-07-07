package digitalbull.app.accuratelab;

import android.os.Bundle;
import android.view.View;
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

import digitalbull.app.accuratelab.Adapter.CartAdapter;
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

public class Category extends AppCompatActivity {

    RecyclerView recycler;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> categoryModels=new ArrayList<>();
    CustomerModel customerModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        EdgeToEdge.enable(this);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Categories");
        recycler = findViewById(R.id.recycler);


        GridLayoutManager mLayoutManager2 = new GridLayoutManager(Category.this, 2);
        recycler.setLayoutManager(mLayoutManager2);
        categoryAdapter = new CategoryAdapter(Category.this, categoryModels,R.layout.item_categories);
        recycler.setAdapter(categoryAdapter);
        GetData();

    }

    private void GetData() {
        categoryModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(Category.this);
        jc.SendRequest("get_category", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
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

}
