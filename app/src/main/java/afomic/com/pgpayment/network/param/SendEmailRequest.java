package afomic.com.pgpayment.network.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendEmailRequest {
    @Expose
    @SerializedName("from")
    public String from;

    @Expose
    @SerializedName("to")
    public String to;

    @Expose
    @SerializedName("subject")
    public String subject;


    @Expose
    @SerializedName("html")
    public String html;
}
