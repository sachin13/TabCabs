package tabcabs.in.tabcabs.CabTabFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tabcabs.in.tabcabs.APIClient.APIClient;
import tabcabs.in.tabcabs.Model.Insert_Operation;
import tabcabs.in.tabcabs.R;
import tabcabs.in.tabcabs.Splash;
import tabcabs.in.tabcabs.interfaceAPI.ApiInterface;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Post_Adv.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Post_Adv#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Post_Adv extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int year, month, day;

    String ENDPOINT =  "http://tabcabs.in/webservice/Post_Ads.php";

    Uri selectedImage,actualpath;
    int flagValue1=0;

    Spinner days,category;

    Uri actualpath1;
    String Path1_tenant,Path1,daysno;

    private static int RESULT_LOAD_File1 = 2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Calendar calendar;

    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;


    View myView;
    TextView startdate;
    EditText total_Amount;
    LinearLayout back;

    Button submit;

    String id,cust_name,cust_mobile_no,cust_email;
    TextView imagevideo;
    Button batn_document,subscribe;
    EditText name_your_ads,total_no_of_view;


    ApiInterface apiInterface;


    android.support.v4.app.FragmentManager fragmentManager;
    private OnFragmentInteractionListener mListener;

    public Post_Adv() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Post_Adv.
     */
    // TODO: Rename and change types and number of parameters
    public static Post_Adv newInstance(String param1, String param2) {
        Post_Adv fragment = new Post_Adv();
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
       // return inflater.inflate(R.layout.fragment_post__adv, container, false);


        myView = inflater.inflate(R.layout.fragment_post__adv, container, false);
        requestStoragePermission();

            back=(LinearLayout)myView.findViewById(R.id.back);
        startdate=(TextView)myView.findViewById(R.id.StartDate);


        days=(Spinner) myView.findViewById(R.id.day);


        category=(Spinner) myView.findViewById(R.id.category);

        total_Amount=(EditText) myView.findViewById(R.id.total_Amount);

        submit=(Button) myView.findViewById(R.id.submit);



        imagevideo=(TextView)  myView.findViewById(R.id.imagevideo);


        id= Splash.initialization.getString("id", null);

        cust_name= Splash.initialization.getString("cust_name", null);

        cust_mobile_no= Splash.initialization.getString("cust_mobile_no", null);


cust_email= Splash.initialization.getString("cust_email", null);


        batn_document=(Button)myView.findViewById(R.id.useraccountphoto_enter);



        subscribe=(Button)myView.findViewById(R.id.subscribe);

        name_your_ads=(EditText) myView.findViewById(R.id.name_your_ads);

        total_no_of_view=(EditText) myView.findViewById(R.id.total_no_of_view);







        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);


            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(category);


            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {


        }









        subscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Membership hello = new Membership();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


            }
        });

        batn_document.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);         //file select in android
                intent.setType("*/*");
                imagevideo.setText(" ");
                startActivityForResult(intent, 2);
            }
        });
/*

        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...!").setCancelText("Cancel")
                .setConfirmText("Subscribe")
                .setContentText("Your Haven't Subscribe To Our  Membership Account..!").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {



                sDialog.cancel();
                Membership hello = new Membership();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();











            }
        }) .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.cancel();
            }
        })
                .show();





                back.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {


                        DashBoardFragment hello = new DashBoardFragment();


                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


                    }
                });


*/



        submit.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {






                if(!name_your_ads.getText().toString().isEmpty()  && !total_Amount.getText().toString().isEmpty()&& !total_no_of_view.getText().toString().isEmpty()  && !startdate.getText().toString().equals("Start Date") &&    flagValue1==1 && !daysno.equals("Select Days"))
                {


                uploadFile();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try{
                    //Setting the date to the given date
                    c.setTime(sdf.parse(startdate.getText().toString()));
                }catch(ParseException e){
                    e.printStackTrace();
                }

                //Number of Days to add
                c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(daysno.substring(0,2).replaceAll(" ","")));
                //Date after adding the days to the given date
                String enddate = sdf.format(c.getTime());









                apiInterface = APIClient.getClient().create(ApiInterface.class);
                Path1="image_path/"+Path1;

                Call<Insert_Operation> call2 = apiInterface.post_adv(id,cust_name,cust_mobile_no,cust_email,Path1,startdate.getText().toString(),enddate,daysno,total_Amount.getText().toString(),total_no_of_view.getText().toString().substring(0,5).replaceAll(" ",""),"No");

                call2.enqueue(new Callback<Insert_Operation>() {
                    @Override
                    public void onResponse(Call<Insert_Operation> call, Response<Insert_Operation> response) {


                        Insert_Operation add_lawdetail = response.body();


                        List<Insert_Operation.Insertwork> change_rolldata = add_lawdetail.society_insert;

                        if (change_rolldata != null) {
                            for (Insert_Operation.Insertwork change_rolldataValue : change_rolldata) {

                                if (change_rolldataValue.Message.equals("Success")) {


                                    days.setBackgroundColor(Color.WHITE);

                                    startdate.setTextColor(Color.BLACK);


                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Great...!")
                                            .setContentText("Data has been saved successfully!")
                                            .show();






                                } else {





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

            }else{



                    if (name_your_ads.getText().toString().isEmpty() ) {
                        name_your_ads.setError("Mandatory Field/Enter Correct input");


                    }


                    if (total_Amount.getText().toString().isEmpty() ) {
                        total_Amount.setError("Mandatory Field/Enter Correct input");


                    }


                    if (total_no_of_view.getText().toString().isEmpty() ) {
                        total_no_of_view.setError("Mandatory Field/Enter Correct input");


                    }

                    if(flagValue1==0)
                    {
                        batn_document.setTextColor(Color.RED);
                    }




                    if(startdate.getText().toString().equals("Start Date")){

                        startdate.setTextColor(Color.RED);

                    }

                    if(daysno.equals("Select Days"))
                    {
                        days.setBackgroundColor(Color.RED);
                    }


                }

            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        startdate.setOnClickListener(new View.OnClickListener()

            {

                @Override
                public void onClick (View v){
                // TODO Auto-generated method stub


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;

                DatePickerDialog endDate = new DatePickerDialog(getActivity(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                endDate.getDatePicker();
                endDate.show();


                updateLabel();

            }

        });



        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        SpinnerDays();


        SpinnerCategory();

        return myView;

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void uploadFile()
    {


            try {

                if(flagValue1==1) {




                    String path = getPath(actualpath);






                    final ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage(getString(R.string.string_title_upload_progressbar_));
                    progressDialog.show();

                    //Create Upload Server Client
                    apiInterface = APIClient.getClient().create(ApiInterface.class);
                    //File creating from selected URL
                    File file = new File(path);

                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    // MultipartBody.Part is used to send also the actual file name
                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("image_path", file.getName(), requestFile);

                    Call<Insert_Operation> resultCall = apiInterface.uploadImage(body);

                    // finally, execute the request
                    resultCall.enqueue(new Callback<Insert_Operation>() {
                        @Override
                        public void onResponse(Call<Insert_Operation> call, Response<Insert_Operation> response) {

                            progressDialog.dismiss();

                            // Response Success or Fail

                        }

                        @Override
                        public void onFailure(Call<Insert_Operation> call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
























                 /*   String uploadId = UUID.randomUUID().toString();

                     String path = getPath(actualpath);




                            //FilePath.getPath(getActivity(), actualpath);
                    //   User_Document=FilePath.getPath(getActivity(), actualpath);



                    new MultipartUploadRequest(getActivity(), uploadId, ENDPOINT)
                            .addFileToUpload(path, "image_path") //Adding file

                            .setNotificationConfig(new UploadNotificationConfig())
                            .setMaxRetries(2)
                            .startUpload();*/

                }

            } catch (Exception exc) {
                Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            }


    }



    private void SpinnerDays() {
        // Initializing a String Array



        String[] nature = new String[]{
                "Select Days",
                "7 Days",
                "14 Days",
                "28 Days"

        };

        final List<String> DesignationList = new ArrayList<>(Arrays.asList(nature));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, DesignationList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint

                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray

                    tv.setTextColor(Color.BLACK);
                } else {

                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        days.setAdapter(spinnerArrayAdapter);
        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               daysno=(String) parent.getItemAtPosition(position);




               if(daysno.equals("7 Days"))


               {

                   total_Amount.getText().clear();
                   total_no_of_view.getText().clear();


                   total_Amount.setText("2999");

                   total_no_of_view.setText("10000 view/per day");

               }else if(daysno.equals("14 Days"))
                {

                    total_Amount.getText().clear();
                    total_no_of_view.getText().clear();



                    total_Amount.setText("4499");
                    total_no_of_view.setText("10000 view/per day");

                }else if(daysno.equals("28 Days"))
                {


                    total_Amount.getText().clear();
                    total_no_of_view.getText().clear();




                    total_Amount.setText("6999");
                    total_no_of_view.setText("15000 view/per day");

                }








                // Wing_Name = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });}











    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }




    private void SpinnerCategory() {
        // Initializing a String Array



        String[] nature = new String[]{
                "Select Category",
                "Automobile",
                "Banks",
                "Car rentals",
                "Courier",
                "Education",
                "Fitness",
                "Hotel",
                "Home Décor",
                "Hospital",
                "Insurance",
                "Interior Designer",
                "Jewellery Shop",
                "Jobs",
                "Logistics",
                "Medicals",
                "Real Estate",
                "Restaurants",
                "Sports",
                "Travel"








        };

        final List<String> DesignationList = new ArrayList<>(Arrays.asList(nature));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, DesignationList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint

                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray

                    tv.setTextColor(Color.BLACK);
                } else {

                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        category.setAdapter(spinnerArrayAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                daysno=(String) parent.getItemAtPosition(position);












                // Wing_Name = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });}















    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == RESULT_LOAD_File1 && resultCode == RESULT_OK && null != data)
        {

            flagValue1=1;

            //    Path = data.getData().getPath();
            actualpath1=data.getData();


            actualpath=data.getData();
            Path1_tenant=FilePath.getPath(getActivity(), actualpath1);
            Path1 = Path1_tenant.substring(Path1_tenant.lastIndexOf("/") + 1);

            imagevideo.setText(Path1);


        }




    }

    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
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
    private void updateLabel( ) {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startdate.setText(sdf.format(calendar.getTime()));
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
