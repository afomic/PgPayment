package afomic.com.pgpayment.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import afomic.com.pgpayment.BuildConfig;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    private static ApiService sApiService;
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    public PgPaymentApi mPgPaymentApi;
    private static final String BASE_URL = "https://afomic.herokuapp.com/";


    public static ApiService getInstance(Context context) {
        if (sApiService == null) {
            sApiService = new ApiService(context);
        }
        return sApiService;
    }

    private ApiService(Context context) {
        Cache cache = provideCache(context);
        HttpLoggingInterceptor httpLoggingInterceptor = provideLoggingInterceptor();
        OkHttpClient okHttpClient = provideOkHttpClient(cache, httpLoggingInterceptor);
        mPgPaymentApi = buildObserverApi(okHttpClient);
    }

    private PgPaymentApi buildObserverApi(OkHttpClient okHttpClient) {
        return provideKliQrAPI(okHttpClient);
    }

    private PgPaymentApi provideKliQrAPI(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(PgPaymentApi.class);
    }


    private OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.cache(cache);
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }


    private Cache provideCache(Context context) {
        Cache cache = null;
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(context.getCacheDir(), "http");
            cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    private HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;

    }
}
