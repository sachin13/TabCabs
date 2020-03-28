package tabcabs.in.tabcabs.CabTabFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Model.Insert_Operation;
import tabcabs.in.tabcabs.R;
import tabcabs.in.tabcabs.Splash;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangePassword.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangePassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePassword extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CheckBox mCbShowPwd;
    View myView;

    ApiInterface apiInterfaceadmin;
    String cust_username,id,cust_password;
    LinearLayout back;
    android.support.v4.app.FragmentManager fragmentManager;

    Button submit;
    EditText editoldpassword,editnewpassword,editconfirmpassword;

    private OnFragmentInteractionListener mListener;

    public ChangePassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePassword.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePassword newInstance(String param1, String param2) {
        ChangePassword fragment = new ChangePassword();
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

        myView = inflater.inflate(R.layout.fragment_change_password, container, false);




        cust_username= Splash.initialization.getString("cust_username", null);



        cust_password= Splash.initialization.getString("cust_password", null);


        id= Splash.initialization.getString("id", null);

        submit=(Button)myView.findViewById(R.id.submit);


        editoldpassword=(EditText) myView.findViewById(R.id.editoldpassword);


        editnewpassword=(EditText) myView.findViewById(R.id.editnewpassword);

        back=(LinearLayout)myView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Setting hello = new Setting();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


            }
        });


        editconfirmpassword=(EditText) myView.findViewById(R.id.editconfirmpassword);










        mCbShowPwd = (CheckBox)myView. findViewById(R.id.cbShowPwd);





        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    editoldpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    editnewpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    editconfirmpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    editoldpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    editnewpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());



                    editconfirmpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });








        apiInterfaceadmin = APIClient.getClient().create(ApiInterface.class);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                if(!editoldpassword.getText().toString().isEmpty() && !editoldpassword.getText().toString().isEmpty() && !editconfirmpassword.getText().toString().isEmpty()) {


                    if (editnewpassword.getText().toString().equals(editconfirmpassword.getText().toString()) && editoldpassword.getText().toString().equals(cust_password)) {


                        Call<Insert_Operation> call2 = apiInterfaceadmin.Change_Password(id, editconfirmpassword.getText().toString());

                        call2.enqueue(new Callback<Insert_Operation>() {
                            @Override
                            public void onResponse(Call<Insert_Operation> call, Response<Insert_Operation> response) {


                                Insert_Operation add_lawdetail = response.body();


                                List<Insert_Operation.Insertwork> change_rolldata = add_lawdetail.society_insert;

                                if (change_rolldata != null) {
                                    for (Insert_Operation.Insertwork change_rolldataValue : change_rolldata) {

                                        if (change_rolldataValue.Message.equals("Success")) {

                                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Great...!")
                                                    .setContentText("Data has been saved successfully!")
                                                    .show();


                                        } else {

                                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("Oops...")
                                                    .setContentText("Something went wrong!")
                                                    .show();


                                        }


                                    }
                                }


                            }

                            @Override
                            public void onFailure(Call<Insert_Operation> call, Throwable t) {
                               /* Toast.makeText(getActivity(), "Network Error ", Toast.LENGTH_SHORT).show();
                                call.cancel();*/
                            }


                        });
                    } else {


                        if (!editnewpassword.getText().toString().equals(editconfirmpassword.getText().toString())) {

                            editnewpassword.setError("Wrong Password");

                            editconfirmpassword.setError("Wrong Password");


                        }


                        if (!editoldpassword.getText().toString().equals(cust_password)) {

                            editoldpassword.setError("Wrong Password");


                        }


                    }
                }else{



                    if (editoldpassword.getText().toString().isEmpty()) {
                        editoldpassword.setError("Mandatory Field/Enter Correct input");
                    }


                    if (editnewpassword.getText().toString().isEmpty()) {
                        editnewpassword.setError("Mandatory Field/Enter Correct input");
                    }

                    if (editconfirmpassword.getText().toString().isEmpty()) {
                        editconfirmpassword.setError("Mandatory Field/Enter Correct input");
                    }







                }

            }
        });






        return myView;
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
