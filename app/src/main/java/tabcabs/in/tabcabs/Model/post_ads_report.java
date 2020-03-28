package tabcabs.in.tabcabs.Model;

import com.google.gson.annotations.SerializedName;

public class post_ads_report {



    @SerializedName("id")
    public String id;

    @SerializedName("customer_id")
    public String customer_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getExpire_date() {
        return Expire_date;
    }

    public void setExpire_date(String expire_date) {
        Expire_date = expire_date;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(String no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(String total_Amount) {
        Total_Amount = total_Amount;
    }

    public String getPaid_Amount() {
        return Paid_Amount;
    }

    public void setPaid_Amount(String paid_Amount) {
        Paid_Amount = paid_Amount;
    }

    public String getBalance_due() {
        return Balance_due;
    }

    public void setBalance_due(String balance_due) {
        Balance_due = balance_due;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRegistration_Date() {
        return Registration_Date;
    }

    public void setRegistration_Date(String registration_Date) {
        Registration_Date = registration_Date;
    }

    @SerializedName("customer_name")
    public String customer_name;

    @SerializedName("customer_mobile")
    public String customer_mobile;


    @SerializedName("customer_email")
    public String customer_email;

    @SerializedName("image_path")
    public String image_path;

    @SerializedName("Start_date")
    public String Start_date;


    @SerializedName("Expire_date")
    public String Expire_date;


    @SerializedName("no_of_days")
    public String no_of_days;




    @SerializedName("Total_Amount")
    public String Total_Amount;


    @SerializedName("Paid_Amount")
    public String Paid_Amount;


    @SerializedName("Balance_due")
    public String Balance_due;




    @SerializedName("Status")
    public String Status;


    @SerializedName("Registration_Date")
    public String Registration_Date;
}
