package org.andriodtown.contact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.andriodtown.contact.model.Contact;

import java.util.List;

/**
 * Created by user on 2017-09-26.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

    private List<Contact> data;

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Contact> data){
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Contact contact = data.get(position);
        holder.setTxt_id(contact.getId());
        holder.setTxt_name(contact.getName());
        holder.setTxt_number(contact.getNumber());

    }
    public class Holder extends RecyclerView.ViewHolder{
        private TextView txt_id;
        private TextView txt_name;
        private TextView txt_number;
        private Button btn_call;

        public Holder(View itemView){
            super(itemView);
            txt_id = (TextView)itemView.findViewById(R.id.txt_id);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name);
            txt_number = (TextView)itemView.findViewById(R.id.txt_number);
            btn_call = (Button)itemView.findViewById(R.id.btn_call);
            btn_call.setOnClickListener(new View.OnClickListener() {
                //권한없을 때 나오는 메시지를 못나오게(missingpermission - 안드로이드 값)
                @SuppressWarnings("MissingPermission")
                @Override
                public void onClick(View v) {
                    String num = "tel:"+txt_number.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(num));
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void setTxt_id(int id){
            txt_id.setId(id);
        }
        public void setTxt_name(String name){
            txt_name.setText(name);
        }
        public void setTxt_number(String number){
            txt_number.setText(number);
        }
    }
}

