package tabcabs.in.tabcabs.Adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tabcabs.in.tabcabs.CabTabFragment.Advertisement;
import tabcabs.in.tabcabs.CabTabFragment.Analytics;
import tabcabs.in.tabcabs.CabTabFragment.History;
import tabcabs.in.tabcabs.CabTabFragment.Membership;
import tabcabs.in.tabcabs.CabTabFragment.Post_Adv;
import tabcabs.in.tabcabs.CabTabFragment.Setting;
import tabcabs.in.tabcabs.R;


/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static final int ITEM_COUNT = 6;



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        return new ViewHolder(inflater.inflate(R.layout.row_empty_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        if(position==0)
        {


            holder.grid_item_label1.setText("Post Advertisement");
            holder.grid_item_image1.setImageResource(R.drawable.postads);










        }else if(position==1)
        {
            holder.grid_item_label1.setText("Analytics");
            holder.grid_item_image1.setImageResource(R.drawable.analyticview);
        }else if (position==2)
        {

            holder.grid_item_label1.setText("history");
            holder.grid_item_image1.setImageResource(R.drawable.historyview);




        }else if (position==3)
        {
            holder.grid_item_label1.setText("Membership");

            holder.grid_item_image1.setImageResource(R.drawable.membership);

        }


        else if (position==4)
        {
            holder.grid_item_label1.setText("Advertisement");
            holder.grid_item_image1.setImageResource(R.drawable.adsview);

        }else if (position==5)
        {
            holder.grid_item_label1.setText("Setting");
            holder.grid_item_image1.setImageResource(R.drawable.settingview);
        }












        holder.linear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                if(position==0)
                {



                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Post_Adv hello = new Post_Adv();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();



















                }else if(position==1)
                {





                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Analytics hello = new Analytics();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();



                }else if (position==2)
                {



                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    History hello = new History();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();



                }else if (position==3)
                {




                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Membership hello = new Membership();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();








           }


                else if (position==4)
                {





                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Advertisement hello = new Advertisement();


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, hello).commit();



                }else if (position==5)
                {





                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Setting hello = new Setting ();


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
