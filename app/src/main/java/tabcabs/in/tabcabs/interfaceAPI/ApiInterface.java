package tabcabs.in.tabcabs.interfaceAPI;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import tabcabs.in.tabcabs.Model.Insert_Operation;
import tabcabs.in.tabcabs.Model.LoginDetail;
import tabcabs.in.tabcabs.Model.post_ads_report;

/**
 * Created by Administrator on 10-08-2017.
 */

public interface ApiInterface {


    /* Module-1

    Financial Tracker and Bill payment history and My profile webservice here

    developer-sachin Khairnar
    9960675737



     */


    //member registration web service
    @GET("customer_check_login.php")
    Call<LoginDetail> doLogin(@Query("Username") String username, @Query("Password") String password);


    @GET("sign_up_customer.php")
    Call<Insert_Operation> SignUp(@Query("cust_name") String cust_name, @Query("cust_email") String cust_email, @Query("cust_mobile_no") String cust_mobile_no, @Query("cust_username") String cust_username, @Query("cust_password") String cust_password, @Query("cust_active") String cust_active);



    @GET("")
    Call<Insert_Operation> payment(@Query("money") String money, @Query("tranasaction") String tranasaction);

    @GET("Post_Ads.php")
    Call<Insert_Operation> post_adv(@Query("customer_id") String customer_id, @Query("customer_name") String customer_name,@Query("customer_mobile") String customer_mobile, @Query("customer_email") String customer_email,@Query("image_path") String image_path,@Query("Start_date") String Start_date,@Query("Expire_date") String Expire_date,@Query("no_of_days") String no_of_days,@Query("Total_Amount") String Total_Amount,@Query("Paid_Amount") String Paid_Amount,@Query("Status") String Status);




    @GET("change_password.php")
        Call<Insert_Operation>  Change_Password(@Query("id") String customer_id, @Query("cust_password") String cust_password);







    @GET("OTP.php")
    Call<Insert_Operation> OTP(@Query("cust_name") String cust_name, @Query("cust_email") String cust_email, @Query("cust_mobile_no") String cust_mobile_no, @Query("OTP") String OTP);





    @GET("Post_Ads_Report.php")
    Call<List<post_ads_report>> post_ads_report_view();

    @Multipart
    @POST("Post_Ads.php")
    Call<Insert_Operation> uploadImage(@Part MultipartBody.Part file);





}
