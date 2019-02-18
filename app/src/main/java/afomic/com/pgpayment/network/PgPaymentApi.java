package afomic.com.pgpayment.network;


import afomic.com.pgpayment.network.param.SendEmailRequest;
import afomic.com.pgpayment.network.param.SendEmailResponse;
import afomic.com.pgpayment.network.param.SendSmsRequest;
import afomic.com.pgpayment.network.param.SendSmsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PgPaymentApi {

    @POST("/api/mail")
    Call<SendEmailResponse> sendMail(@Body SendEmailRequest emailRequest);

    @POST("/api/sms")
    Call<SendSmsResponse> sendSms(@Body SendSmsRequest request);


}
