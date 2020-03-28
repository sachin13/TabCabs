package tabcabs.in.tabcabs.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10-08-2017.
 */

public class LoginDetail {

    @SerializedName("login")
    public List<login> login_info = new ArrayList();
    public class login {




        @SerializedName("id")
        public String id;

        @SerializedName("cust_name")
        public String cust_name;

        @SerializedName("cust_email")
        public String cust_email;

        @SerializedName("cust_mobile_no")
        public String cust_mobile_no;


        @SerializedName("cust_username")
        public String cust_username;

        @SerializedName("cust_password")
        public String cust_password;

        @SerializedName("cust_reg_date")
        public String cust_reg_date;


        @SerializedName("cust_active")
        public String cust_active;


        @SerializedName("Message")
        public String Message;

    }

}
