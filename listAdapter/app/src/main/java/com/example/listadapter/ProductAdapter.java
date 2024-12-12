package com.example.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{
    private Context context;
    LayoutInflater inflater;
    private ArrayList<Product> products;
    private ArrayList<Product> checked_product = new ArrayList<Product>();
    private OnChangeListener onChangeListener;
    public ProductAdapter(@NonNull Context context,  @NonNull ArrayList<Product> products, OnChangeListener onChangeListener) {
        this.context = context;
        this.products = products;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.onChangeListener = onChangeListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = this.inflater.inflate(R.layout.item, parent, false);
        }

        Product product = this.products.get(position);
        TextView name = (TextView) view.findViewById(R.id.itemName);
        name.setText(product.getName());

        TextView article = (TextView) view.findViewById(R.id.itemId);
        article.setText(product.getArticle());

        TextView cost = (TextView) view.findViewById(R.id.itemCost);
        String costStr = String.format("%.2f",product.getCost());
        cost.setText(costStr);

        CheckBox check = (CheckBox) view.findViewById(R.id.itemCheck);
        check.setChecked(product.getCheck());
        check.setTag(position);
        check.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public int getCount() {
        return products.size();
    }
    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.isShown()) {
            int i = (int) compoundButton.getTag();
            products.get(i).setCheck(isChecked);
            notifyDataSetChanged();
            if(isChecked){
                checked_product.add(products.get(i));
            }else {
                checked_product.remove(products.get(i));
            }
            onChangeListener.onDataChanged();
        }
    }
    public ArrayList<Product> getCheckedProduct() {
        return checked_product;
    }
}

