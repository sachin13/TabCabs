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

import java.util.ArrayList;

import tabcabs.in.tabcabs.Adapter.MobileOS;
import tabcabs.in.tabcabs.Adapter.Phone;
import tabcabs.in.tabcabs.Adapter.RecyclerAdapter;
import tabcabs.in.tabcabs.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQ.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQ extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View myView;


    private RecyclerView recyclerView;
    private ArrayList<MobileOS> mobileOSes;
    private RecyclerAdapter adapter;
    LinearLayout back;

    android.support.v4.app.FragmentManager fragmentManager;

    private OnFragmentInteractionListener mListener;

    public FAQ() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQ.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQ newInstance(String param1, String param2) {
        FAQ fragment = new FAQ();
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



        myView = inflater.inflate(R.layout.fragment_faq, container, false);


        back=(LinearLayout)myView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Setting hello = new Setting();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


            }
        });


        recyclerView = (RecyclerView) myView .findViewById(R.id.recycler_view);
        mobileOSes = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(getActivity(), mobileOSes);
        recyclerView.setAdapter(adapter);



        return myView;
    }




    private void setData() {
        ArrayList<Phone> iphones = new ArrayList<>();
        iphones.add(new Phone("Firstly select a name for your ad. Then select the category, number of days and start date of the ad. Then upload the image and pay."));


        ArrayList<Phone> nexus = new ArrayList<>();
        nexus.add(new Phone("Advertising creates awareness for your content/product and by choosing us you will be able to target your ideal customer in the location where your company/shop is situated."));


        ArrayList<Phone> windowPhones = new ArrayList<>();
        windowPhones.add(new Phone("Getting our membership will get you additional discount of upto 5000 on particular number ads and particular loations."));


        ArrayList<Phone> quetion2 = new ArrayList<>();
        quetion2.add(new Phone(": Always match your customer profile with what the target segment reads or visits online. If you are uncertain, sample a few target customers and find out."));







        ArrayList<Phone> quetion3 = new ArrayList<>();
        quetion3.add(new Phone("As a rule of thumb, only consider using an advertising agency if you plan to spend more        than Rupees 10,000 on digital advertising. Typically, agency fees will amount to around 200 per cent of your advertising budget.\n" +
                "Ask for initial ideas to get a feel for what they can offer you - but don't expect too much without a full brief. But here in TabCabs, we work on each and every segment of advertising and increase the product value by smart advertising but, costing only the fraction of other advertising media.\n"));







        ArrayList<Phone> quetion4= new ArrayList<>();
        quetion4.add(new Phone("The best time to advertise is when your target audience is most likely to buy your product or service. Set out what you want to achieve, which might include:\n" +
                "1) launching a new product or service\n" +
                "2) letting customers know about a change of premises or expansion\n" +
                "3) targeting the particular location\n" +
                "4) •targeting a new market segment\n"+
                "5) •\tpromoting a special offer"));




        ArrayList<Phone> quetion5 = new ArrayList<>();
        quetion5.add(new Phone("You should keep an eye on your competitors' adverts, as this can give you valuable insights into their marketing strategies their marketing locations. But don't try to copy them."));













        mobileOSes.add(new MobileOS("1.How to post an ad?", iphones));
        mobileOSes.add(new MobileOS("2.Why should I post an ad here?", nexus));
        mobileOSes.add(new MobileOS("3.Why should I get the membership?", windowPhones));





        mobileOSes.add(new MobileOS("4.Where should I advertise?", quetion2));
        mobileOSes.add(new MobileOS("5.Should I use an advertising agency?", quetion3));
        mobileOSes.add(new MobileOS("6.How do I plan a campaign?", quetion4));


        mobileOSes.add(new MobileOS("7.Should I try to match my competitors' advertising?", quetion5));














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
