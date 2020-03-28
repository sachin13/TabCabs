package tabcabs.in.tabcabs.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tabcabs.in.tabcabs.CabTabFragment.ChangePassword;
import tabcabs.in.tabcabs.CabTabFragment.FAQ;
import tabcabs.in.tabcabs.CabTabFragment.Help;
import tabcabs.in.tabcabs.CabTabFragment.Report_Issue;
import tabcabs.in.tabcabs.Login;
import tabcabs.in.tabcabs.R;

public class SettingAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static final int ITEM_COUNT = 6;



    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        return new CardAdapter.ViewHolder(inflater.inflate(R.layout.row_empty_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, final int position) {



        if(position==0)
        {
            holder.grid_item_label1.setText("Change Password");

            holder.grid_item_image1.setImageResource(R.drawable.passwordchange);



        }else if(position==1)
        {
            holder.grid_item_label1.setText("Logout");
            holder.grid_item_image1.setImageResource(R.drawable.logoutview);
        }else if (position==2)
        {
            holder.grid_item_label1.setText("FAQ");
            holder.grid_item_image1.setImageResource(R.drawable.faqview);

        }else if (position==3)
        {
            holder.grid_item_label1.setText("Help");
            holder.grid_item_image1.setImageResource(R.drawable.help_support);
        }


        else if (position==4)
        {
            holder.grid_item_label1.setText("About Us");
            holder.grid_item_image1.setImageResource(R.drawable.about_us_view);

        }else if (position==5)
        {
            holder.grid_item_label1.setText("Report Issue");
            holder.grid_item_image1.setImageResource(R.drawable.report_issue);
        }












        holder.linear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



           if(position==0)
                {



                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    ChangePassword hello = new ChangePassword();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();




                }else if(position==1)
                {




                    AppCompatActivity activity = (AppCompatActivity) v.getContext();




                    Intent i = new Intent(activity,Login.class);
                    activity.startActivity(i);






                }else if (position==2)
                {


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FAQ hello = new FAQ();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();



                }else if (position==3)
                {


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Help hello = new Help();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();


                }


                else if (position==4)
                {


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();




                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tabcabs.in/"));
                    activity.startActivity(browserIntent);








                }else if (position==5)
                {



                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Report_Issue hello = new Report_Issue ();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();


                }





            }
        });













    }

    @Override
    public int getItemCount() { return ITEM_COUNT; }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView grid_item_image1;
        TextView grid_item_label1;
        LinearLayout linear;
        public ViewHolder(View itemView) {
            super(itemView);


            grid_item_image1 = (ImageView) itemView.findViewById(R.id.grid_item_image1);
            grid_item_label1 = (TextView) itemView.findViewById(R.id.grid_item_label1);

            linear=(LinearLayout)itemView.findViewById(R.id.linear);

        }
    }

}
