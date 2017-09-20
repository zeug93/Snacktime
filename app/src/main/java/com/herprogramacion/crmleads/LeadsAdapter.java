package com.herprogramacion.crmleads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Adaptador de leads
 */
public class LeadsAdapter extends ArrayAdapter<Lead> {
    public LeadsAdapter(Context context, List<Lead> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        // ¿Ya se infló este view?
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            convertView = inflater.inflate(
                    R.layout.list_item_lead,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.company = (TextView) convertView.findViewById(R.id.tv_company);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lead actual.
        Lead lead = getItem(position);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("LEE.jpg");

        // Setup.
        holder.name.setText(lead.getName());
        holder.title.setText(lead.getTitle());
        holder.company.setText(lead.getCompany());
        Glide.with(getContext()).using(new FirebaseImageLoader()).load(storageRef).into(holder.avatar);

        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView title;
        TextView company;
    }
}
