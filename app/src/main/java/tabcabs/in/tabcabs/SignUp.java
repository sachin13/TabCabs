package tabcabs.in.tabcabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Model.Insert_Operation;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

public class SignUp extends AppCompatActivity {

    EditText edit_username,edit_password,editMobile_No,editEmailId,edit_name,edit_cofirm_password;

    ApiInterface apiInterfaceadmin;
    CheckBox mCbShowPwd;

    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_uo);


        edit_username=(EditText) findViewById(R.id.edit_username);

        edit_password=(EditText) findViewById(R.id.edit_password);



        editMobile_No=(EditText) findViewById(R.id.editMobile_No);

        editEmailId=(EditText) findViewById(R.id.editEmailId);

        edit_name=(EditText) findViewById(R.id.edit_name);

        edit_cofirm_password=(EditText) findViewById(R.id.edit_cofirm_password);


        btn_enter=(Button) findViewById(R.id.btn_enter);




        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);





        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    edit_cofirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    edit_cofirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });












        btn_enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                btn_enter.setText("Submit...");

                btn_enter.setEnabled(false);

                apiInterfaceadmin = APIClient.getClient().create(ApiInterface.class);


                Pattern emailvalide = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+");
                Matcher emailvalide1 = emailvalide.matcher(editEmailId.getText().toString());

                boolean emailvalide2 = emailvalide1.matches();
                if (editEmailId.getText().toString().isEmpty() || !emailvalide2) {
                    editEmailId.setError("Mandatory Field/Enter Correct input");
                    //validate = false;

                }else {
                  //  validate = true;

                }








                if(edit_password.getText().toString().equals(edit_cofirm_password.getText().toString())) {

                    if (!edit_name.getText().toString().isEmpty() && !edit_username.getText().toString().isEmpty() && !edit_password.getText().toString().isEmpty() && !editEmailId.getText().toString().isEmpty() && !editMobile_No.getText().toString().isEmpty()  &&editMobile_No.getText().toString().length()==10) {


                        Call<Insert_Operation> call2 = apiInterfaceadmin.SignUp(edit_name.getText().toString(), editEmailId.getText().toString(), editMobile_No.getText().toString(), edit_username.getText().toString(), edit_password.getText().toString(), "Yes");

                        call2.enqueue(new Callback<Insert_Operation>() {
                            @Override
                            public void onResponse(Call<Insert_Operation> call, Response<Insert_Operation> response) {


                                Insert_Operation add_lawdetail = response.body();


                                List<Insert_Operation.Insertwork> change_rolldata = add_lawdetail.society_insert;

                                if (change_rolldata != null) {
                                    for (Insert_Operation.Insertwork change_rolldataValue : change_rolldata) {

                                        if (change_rolldataValue.Message.equals("Success")) {

                                            new SweetAlertDialog(SignUp.this, SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Great...!")
                                                    .setContentText("Data has been saved successfully!")
                                                    .show();





                                            Intent intent = new Intent(SignUp.this, OTP.class);
                                            intent.putExtra("name",edit_name.getText().toString() );

                                            intent.putExtra("username",edit_username.getText().toString() );


                                            intent.putExtra("Mobile_No",editMobile_No.getText().toString() );



                                            intent.putExtra("EmailId",editEmailId.getText().toString() );







                                            startActivity(intent);












/*

                                            edit_name.getText().clear();

                                            edit_username.getText().clear();
                                            edit_password.getText().clear();
                                            editMobile_No.getText().clear();

                                            editEmailId.getText().clear();

                                            edit_cofirm_password.getText().clear();
*/



                                            btn_enter.setText("Submit");

                                            btn_enter.setEnabled(true);


                                        } else {

                                            new SweetAlertDialog(SignUp.this, SweetAlertDialog.ERROR_TYPE)
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


                    } else {


                        new SweetAlertDialog(SignUp.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something is missing...!")
                                .show();


                        if (edit_name.getText().toString().isEmpty()) {
                            edit_name.setError("Mandatory Field/Enter Correct input");
                        }


                        if (edit_username.getText().toString().isEmpty()) {
                            edit_username.setError("Mandatory Field/Enter Correct input");
                        }

                        if (edit_password.getText().toString().isEmpty()) {
                            edit_password.setError("Mandatory Field/Enter Correct input");
                        }


                        if (editMobile_No.getText().toString().isEmpty()) {
                            editMobile_No.setError("Mandatory Field/Enter Correct input");
                        }


                        if (editEmailId.getText().toString().isEmpty()) {
                            editEmailId.setError("Mandatory Field/Enter Correct input");
                        }


                        if (editMobile_No.getText().toString().length()!=10) {
                            editMobile_No.setError("Enter 10 digit Number");
                        }

                        btn_enter.setText("Submit");

                        btn_enter.setEnabled(true);















                    }
                }else{



                    if (!edit_password.getText().toString().equals(edit_cofirm_password.getText().toString())) {
                        edit_password.setError("Password Not Match");


                        edit_cofirm_password.setError("Password Not Match");
                    }


                    btn_enter.setText("Submit");

                    btn_enter.setEnabled(true);


                }






                btn_enter.setText("Submit");

                btn_enter.setEnabled(true);








            }
        });















    }
}
