package tabcabs.in.tabcabs.CabTabFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Adapter.post_List_Adapter;
import tabcabs.in.tabcabs.Model.post_ads_report;
import tabcabs.in.tabcabs.R;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Advertisement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Advertisement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Advertisement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View myView;
    post_List_Adapter adapter;





    List<post_ads_report> post_ads_reportTable;


    List<post_ads_report> post_ads_reportTableArray=new ArrayList<>();
    LinearLayout back;

    android.support.v4.app.FragmentManager fragmentManager;

    LinearLayoutManager layoutManager;

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;
    private OnFragmentInteractionListener mListener;

    public Advertisement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Advertisement.
     */
    // TODO: Rename and change types and number of parameters
    public static Advertisement newInstance(String param1, String param2) {
        Advertisement fragment = new Advertisement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_advertisement, container, false);


        myView = inflater.inflate(R.layout.fragment_advertisement, container, false);


        back=(LinearLayout)myView.findViewById(R.id.back);



        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                DashBoardFragment hello = new DashBoardFragment();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


            }
        });





        mShimmerViewContainer =(ShimmerFrameLayout) myView.findViewById(R.id.shimmer_view_container);

        mShimmerViewContainer.startShimmerAnimation();

        apiInterface = APIClient.getClient().create(ApiInterface.class);

        recyclerView = (RecyclerView) myView.findViewById(R.id.cab_report);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);





        displayPage();
        return myView;

    }










    public void displayPage() {






        Call<List<post_ads_report>> call = apiInterface.post_ads_report_view();

        call.enqueue(new Callback<List<post_ads_report>>() {
            @Override
            public void onResponse(Call<List<post_ads_report>> call, Response<List<post_ads_report>> response) {



                post_ads_reportTable = response.body();
                for (final post_ads_report cab_reportvalue :post_ads_reportTable) {



                    try {


                        post_ads_reportTableArray.add(cab_reportvalue);




                    }catch (Exception e)
                    {

                    }

                }










                adapter = new post_List_Adapter( post_ads_reportTableArray);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);


                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);










            }

            @Override
            public void onFailure(Call<List<post_ads_report>> call, Throwable t) {
                call.cancel();
            }
        });




    }














    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
