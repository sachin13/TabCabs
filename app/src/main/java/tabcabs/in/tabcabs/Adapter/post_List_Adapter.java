package tabcabs.in.tabcabs.Adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tabcabs.in.tabcabs.Model.post_ads_report;
import tabcabs.in.tabcabs.R;

public class post_List_Adapter extends RecyclerView.Adapter<post_List_Adapter.DataObjectHolder> implements Filterable {



    private static String LOG_TAG = "MyRecyclerViewAdapter",Flat_No;
    private List<post_ads_report> mDataset,filterList;
    public Activity mActivity;
    private static post_List_Adapter.MyClickListener myClickListener;

    String idvalue,Society_number,mom_id,Minutes_of_Meeting,Present_Member,Absent_Member,Minutes_of_Meeting_document,register_date;


    FrameLayout layout;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    public post_List_Adapter() {

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterList = mDataset;
                } else {
                    List<post_ads_report> filteredList = new ArrayList<>();
                    for (post_ads_report row : mDataset) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                       /* if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }*/
                    }

                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (ArrayList<post_ads_report>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView textViewOwner_Name,textViewMobile_No,textViewTotalAmount,no_of_days,textViewBalanceAmount;

        LinearLayout view;


        public DataObjectHolder(View itemView) {
            super(itemView);
            textViewOwner_Name = (TextView) itemView.findViewById(R.id.textViewOwner_Name);
            textViewMobile_No=(TextView) itemView.findViewById(R.id.textViewMobile_No);
            textViewTotalAmount=(TextView) itemView.findViewById(R.id.textViewTotalAmount);


            no_of_days=(TextView) itemView.findViewById(R.id.no_of_days);




            view=(LinearLayout) itemView.findViewById(R.id.view);





            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(post_List_Adapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public post_List_Adapter(List<post_ads_report> myDataset) {
        this.mDataset = myDataset;
        this.filterList = myDataset;
    }

    @Override
    public post_List_Adapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cab, parent, false);
        mActivity = (Activity) parent.getContext();

        post_List_Adapter.DataObjectHolder dataObjectHolder = new post_List_Adapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final post_List_Adapter.DataObjectHolder holder, final int position) {


        try {




            holder.textViewOwner_Name.setText(filterList.get(position).getCustomer_name());
            holder.textViewMobile_No.setText(filterList.get(position).getCustomer_mobile());





            holder.no_of_days.setText(filterList.get(position).getNo_of_days());




            holder.textViewTotalAmount.setText(filterList.get(position).getTotal_Amount());











            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


            AppCompatActivity activity = (AppCompatActivity) v.getContext();
/*

                    View_Cab hello = new View_Cab();

                Bundle args = new Bundle();

                    args.putString("cab_id", filterList.get(position).getCab_id());
                    args.putString("Employee_Name", filterList.get(position).getEmployee_Name());
                    args.putString("Employee_ID", filterList.get(position).getEmployee_ID());
                    args.putString("Owner_Name", filterList.get(position).getOwner_Name());
                    args.putString("Mobile_No", filterList.get(position).getMobile_No());
                    args.putString("License_Number", filterList.get(position).getLicense_Number());
                    args.putString("City", filterList.get(position).getCity());
                    args.putString("Owner_Email", filterList.get(position).getOwner_Email());

                    args.putString("Address", filterList.get(position).getAddress());
                    args.putString("Aadhar_Number", filterList.get(position).getAadhar_Number());
                    args.putString("PAN_number", filterList.get(position).getPAN_number());
                    args.putString("RC_Number", filterList.get(position).getRC_Number());
                    args.putString("TC_Number", filterList.get(position).getTC_Number());
                    args.putString("Insurance_Paid", filterList.get(position).getInsurance_Paid());
                    args.putString("Permit", filterList.get(position).getPermit());
                    args.putString("Road_Tax_Paid", filterList.get(position).getRoad_Tax_Paid());
                    args.putString("Paid_Payment", filterList.get(position).getPaid_Payment());
                    args.putString("Total_Amount", filterList.get(position).getTotal_Amount());
                    args.putString("Balance_Amount", filterList.get(position).getBalance_Amount());




















                    hello.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();
*/


                }
            });


        }catch(Exception e)
        {

        }



















    }




    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }




    public void addItem(post_ads_report dataObj, int index) {
        filterList.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        filterList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return filterList.size() ;
    }

























    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }














}
