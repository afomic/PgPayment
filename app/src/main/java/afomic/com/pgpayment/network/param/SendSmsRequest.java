package afomic.com.pgpayment.network.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendSmsRequest {
    @Expose
    @SerializedName("recipient")
    public String recipient;

    @Expose
    @SerializedName("sender")
    public String sender;

    @Expose
    @SerializedName("message")
    public String message;


}
