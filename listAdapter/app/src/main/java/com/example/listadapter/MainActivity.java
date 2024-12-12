package com.example.listadapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnChangeListener,View.OnClickListener{

    ProductAdapter adapterProduct;

    private TextView tv_countChecks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater;
        layoutInflater = LayoutInflater.from(this);

        View view_header, view_footer;
        view_header = layoutInflater.inflate(R.layout.list_header, null);
        view_footer = layoutInflater.inflate(R.layout.list_footer, null);

        ListView mainList = (ListView) findViewById(R.id.MainList) ;
        mainList.addHeaderView(view_header);
        mainList.addFooterView(view_footer);

        adapterProduct = new ProductAdapter(this,getData(),this);
        mainList.setAdapter(adapterProduct);

        tv_countChecks = view_footer.findViewById(R.id.countSelectItem);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private  ArrayList<Product> getData(){
        ArrayList<Product> products = new ArrayList<Product>() ;
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
       /* products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "sir", 2.0f));
        products.add( new Product(java.util.UUID.randomUUID().toString(), "dar", 3.2f));
        products.add(new Product(java.util.UUID.randomUUID().toString(), "gir", 0.2f));
*/
        return products;
    }
    private ArrayList<Product> checked_products = new ArrayList<Product>();

    public void onClick(View view) {
        checked_products = adapterProduct.getCheckedProduct();
        Intent intent = new Intent(this, Backet.class);
        intent.putParcelableArrayListExtra("MyList", (ArrayList<? extends Parcelable>) checked_products);
        startActivity(intent);
    }

    @Override
    public void onDataChanged() {
        int size = adapterProduct.getCheckedProduct().size();
        tv_countChecks.setText("Count of products = " + size + "");
    }
}