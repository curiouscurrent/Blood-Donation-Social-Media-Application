package com.anusha.coffee.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anusha.coffee.BloodBank;
import com.anusha.coffee.R;

import java.util.List;

public class BloodBankAdapter extends ArrayAdapter<BloodBank> {

    private Context context;
    private List<BloodBank> bloodBanks;

    public BloodBankAdapter(Context context, List<BloodBank> bloodBanks) {
        super(context, R.layout.blood_bank_list_item, bloodBanks);
        this.context = context;
        this.bloodBanks = bloodBanks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.blood_bank_list_item, null);

            holder = new ViewHolder();
            holder.nameTextView = view.findViewById(R.id.blood_bank_name);
            holder.locationTextView = view.findViewById(R.id.blood_bank_location);
            holder.bloodTypesTextView = view.findViewById(R.id.blood_bank_available_blood_types);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        BloodBank bloodBank = bloodBanks.get(position);
        holder.nameTextView.setText(bloodBank.getName());
        holder.locationTextView.setText(bloodBank.getLocation());
        holder.bloodTypesTextView.setText("Available Blood Types: " + bloodBank.getAvailableBloodTypes());

        return view;
    }

    static class ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        TextView bloodTypesTextView;
    }
}

