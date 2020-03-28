package tabcabs.in.tabcabs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Model.LoginDetail;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

public class Login extends AppCompatActivity {


    LinearLayout createaacount;
    CheckBox mCbShowPwd;
    Button btn_enter;

    private static final int PERMISSION_REQUEST_CODE = 1;

    EditText edit_username,edit_password;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);



        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }

        createaacount=(LinearLayout)findViewById(R.id.createaacount);
        edit_username=(EditText) findViewById(R.id.edit_username);

        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        edit_password=(EditText) findViewById(R.id.edit_password);
        btn_enter=(Button)findViewById(R.id.btn_enter);




        btn_enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {




                login();






            }
        });


        createaacount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent i1 = new Intent(Login.this, SignUp.class);
                startActivity(i1);
                finish();


            }
        });


        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }










    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Login.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Login.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Login.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }


















    private void login() {


        btn_enter.setText("Submit..");

        btn_enter.setEnabled(false);
        if(!edit_username.getText().toString().isEmpty() && !edit_password.getText().toString().isEmpty()) {

            apiInterface = APIClient.getClient().create(ApiInterface.class);
            Call<LoginDetail> call2 = apiInterface.doLogin(edit_username.getText().toString(), edit_password.getText().toString());
            call2.enqueue(new Callback<LoginDetail>() {
                @Override
                public void onResponse(Call<LoginDetail> call, Response<LoginDetail> response) {

                    btn_enter.setText("Submit..");

                    btn_enter.setEnabled(false);

                    LoginDetail logindetail = response.body();


                    List<LoginDetail.login> userdata = logindetail.login_info;

                    if (userdata != null) {
                        for (LoginDetail.login userdatavalue : userdata) {


                            //get login data and stored in shared preference


                            if (userdatavalue.Message == null) {








                                    btn_enter.setText("Submit");

                                    btn_enter.setEnabled(true);


                                    Splash.editor.putString("id", userdatavalue.id);
                                    Splash.editor.putString("cust_name", userdatavalue.cust_name);
                                    Splash.editor.putString("cust_email", userdatavalue.cust_email);
                                    Splash.editor.putString("cust_mobile_no", userdatavalue.cust_mobile_no);
                                    Splash.editor.putString("cust_username", userdatavalue.cust_username);
                                    Splash.editor.putString("cust_password", userdatavalue.cust_password);
                                    Splash.editor.putString("cust_reg_date", userdatavalue.cust_reg_date);
                                    Splash.editor.putString("cust_active", userdatavalue.cust_active);

                                    Splash.editor.apply();


                                    Intent i1 = new Intent(Login.this, MainActivity.class);
                                    startActivity(i1);
                                    finish();





                            }else{

                                btn_enter.setText("Submit");

                                btn_enter.setEnabled(true);
                                new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("Username and  Password is Wrong..!")
                                        .show();
                            }

                        }


                    }

                    btn_enter.setText("Submit");

                    btn_enter.setEnabled(true);


                }

                @Override
                public void onFailure(Call<LoginDetail> call, Throwable t) {

                    btn_enter.setText("Submit");

                    btn_enter.setEnabled(true);


                }


            });
        }else{
            if (edit_password.getText().toString().isEmpty()) {
                edit_password.setError("Mandatory Field/Enter Correct input");
            }


            if (edit_username.getText().toString().isEmpty()) {
                edit_username.setError("Mandatory Field/Enter Correct input");
            }
        }

        btn_enter.setText("Submit");

        btn_enter.setEnabled(true);

    }

}
