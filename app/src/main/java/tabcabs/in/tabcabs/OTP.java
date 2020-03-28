package tabcabs.in.tabcabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Model.Insert_Operation;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

public class OTP extends AppCompatActivity {



    String name,username,Mobile_No,EmailId;

    Button btn_enter;

    EditText edit_otp;

    ApiInterface apiInterfaceadmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);






        setContentView(R.layout.activity_otp);


      name = getIntent().getExtras().getString("name");

       username = getIntent().getExtras().getString("username");

       Mobile_No = getIntent().getExtras().getString("Mobile_No");


      EmailId = getIntent().getExtras().getString("EmailId");


        btn_enter=(Button) findViewById(R.id.btn_enter);


        edit_otp=(EditText)  findViewById(R.id.edit_otp);


        btn_enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                btn_enter.setText("Submit...");

                btn_enter.setEnabled(false);



                apiInterfaceadmin = APIClient.getClient().create(ApiInterface.class);


                if(!edit_otp.getText().toString().isEmpty())
                {





                Call<Insert_Operation> call2 = apiInterfaceadmin.OTP(name, Mobile_No, EmailId, edit_otp.getText().toString());

                call2.enqueue(new Callback<Insert_Operation>() {
                    @Override
                    public void onResponse(Call<Insert_Operation> call, Response<Insert_Operation> response) {


                        Insert_Operation add_lawdetail = response.body();


                        List<Insert_Operation.Insertwork> change_rolldata = add_lawdetail.society_insert;

                        if (change_rolldata != null) {
                            for (Insert_Operation.Insertwork change_rolldataValue : change_rolldata) {

                                if (change_rolldataValue.Message.equals("Success")) {

                                    new SweetAlertDialog(OTP.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Great...!")
                                            .setContentText("Sign Up Successfully!")
                                            .show();



                                    edit_otp.getText().clear();

                                    btn_enter.setText("Submit");

                                    btn_enter.setEnabled(true);


                                } else {

                                    new SweetAlertDialog(OTP.this, SweetAlertDialog.ERROR_TYPE)
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


                        btn_enter.setText("Submit");

                        btn_enter.setEnabled(true);

                    }




                });




                }else{


                    btn_enter.setText("Submit");

                    btn_enter.setEnabled(true);

                    if (edit_otp.getText().toString().isEmpty()) {
                        edit_otp.setError("Mandatory Field/Enter Correct input");
                    }


                }




                btn_enter.setText("Submit");

                btn_enter.setEnabled(true);

            }
        });










    }
}
