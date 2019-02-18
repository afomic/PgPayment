package afomic.com.pgpayment.network.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendEmailResponse {
    @Expose
    @SerializedName("code")
    public int code;
}
