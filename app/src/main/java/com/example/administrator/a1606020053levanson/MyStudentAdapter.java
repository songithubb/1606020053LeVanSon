package com.example.administrator.a1606020053levanson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStudentAdapter  extends RecyclerView.Adapter<MyStudentAdapter.ViewHolder>  {
    List<SanPham> models;
    int mResource;
    Context mContext;
    public MyStudentAdapter(Context context,int resource, List<SanPham> objects){
        this.mContext = context;
        this.mResource = resource;
        this.models = objects;
    }
    @NonNull
    @Override
    public MyStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStudentAdapter.ViewHolder viewHolder, final int i) {
        final SanPham model = models.get(i);
        viewHolder.edtId.setText(model.getId()+"");
        viewHolder.edtProductName.setText(model.getProductName()+"");
        viewHolder.edtPrice.setText(model.getPrice()+"");
        viewHolder.edtdescription.setText(model.getSdescription());
        viewHolder.edtproducter.setText(model.getProducer());

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> mMap = new HashMap<>();
                mMap.put("user_id","9");
                mMap.put("id",model.getId()+"");
                new LoginAsyncTask(mContext, new ILoginView() {
                    @Override
                    public void onLoginSuccess(String m) {
                        models.remove(i);
                        Toast.makeText(mContext,m,Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onLoginFail(String m) {
                        Toast.makeText(mContext,m,Toast.LENGTH_SHORT).show();
                    }
                },mMap).execute("http://www.vidophp.tk/api/account/dataaction?context=delete");
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText edtId;
        private EditText edtProductName;
        private  EditText edtPrice;
        private EditText edtproducter;
        private EditText edtdescription;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.edtId = itemView.findViewById(R.id.edt_id);
            this.edtProductName = itemView.findViewById(R.id.edt_ProductName);
            this.edtPrice = itemView.findViewById(R.id.edt_price);
            this.edtproducter = itemView.findViewById(R.id.edt_producer);
            this.edtdescription = itemView.findViewById(R.id.edt_sdescription);
            this.btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

}
