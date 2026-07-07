package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.lang.reflect.Method;

public class MenuOption extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        CardView prescription,logout,contact,about,share,rate,privacy,term;
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));
        EdgeToEdge.enable(this);
        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Menu");


        prescription=findViewById(R.id.prescription);
        logout=findViewById(R.id.logout);
        contact=findViewById(R.id.contact);
        about=findViewById(R.id.about);
        share=findViewById(R.id.share);
        rate=findViewById(R.id.rate);
        privacy=findViewById(R.id.privacy);
        term=findViewById(R.id.term);

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOption.this,Term.class));
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOption.this,Privacy.class));
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOption.this,About.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOption.this,Contact.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOption.this,PageOrder.class));
            }
        });

    }
}
