package afomic.com.pgpayment.viewHelper.paymentHistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.model.PaymentHistory;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHistoryViewHolder> {
    private Context mContext;
    private List<PaymentHistory> mPaymentHistories;

    public PaymentHistoryAdapter(Context context, List<PaymentHistory> paymentHistories) {
        mContext = context;
        mPaymentHistories = paymentHistories;
    }

    @NonNull
    @Override

    public PaymentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_payment_history, viewGroup, false);
        return new PaymentHistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryViewHolder paymentHistoryViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if (mPaymentHistories != null) {
            mPaymentHistories.size();
        }
        return 0;
    }

    public class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {
        public PaymentHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
