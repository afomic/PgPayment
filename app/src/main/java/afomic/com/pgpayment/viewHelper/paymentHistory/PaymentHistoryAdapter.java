package afomic.com.pgpayment.viewHelper.paymentHistory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHistoryViewHolder> {

    @NonNull
    @Override
    public PaymentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryViewHolder paymentHistoryViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PaymentHistoryViewHolder extends RecyclerView.ViewHolder{
        public PaymentHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
