package tabcabs.in.tabcabs.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 20-11-2017.
 */

public class Insert_Operation {

    @SerializedName("insert")
    public List<Insertwork> society_insert = new ArrayList();
    public class Insertwork {


        @SerializedName("Message")
        public String Message;



    }

}
