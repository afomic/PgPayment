package afomic.com.pgpayment.viewHelper.paymentHistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        PaymentHistory paymentHistory = mPaymentHistories.get(i);
        paymentHistoryViewHolder.bind(paymentHistory);
    }

    @Override
    public int getItemCount() {
        if (mPaymentHistories != null) {
            return mPaymentHistories.size();
        }
        return 0;
    }

    public class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView transactionSectionTextView;
        private TextView transactionIdTextView;
        private TextView transactionAmount;
        private TextView transactionStatusTextView;

        public PaymentHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionIdTextView = itemView.findViewById(R.id.tv_transaction_id);
            transactionSectionTextView = itemView.findViewById(R.id.tv_transaction_section);
            transactionAmount = itemView.findViewById(R.id.tv_transaction_amount);
            transactionStatusTextView = itemView.findViewById(R.id.tv_transaction_status);
        }

        public void bind(PaymentHistory paymentHistory) {
            if (paymentHistory.isStatus()) {
                transactionStatusTextView.setText("Success");
            } else {
                transactionStatusTextView.setText("Failed");
            }
            String amount = mContext.getString(R.string.naira) + paymentHistory.getAmount();
            transactionAmount.setText(amount);
            transactionIdTextView.setText(paymentHistory.getTransactionId());
            String section = paymentHistory.getTransactionId() + "Section";
            transactionSectionTextView.setText(section);

        }
    }
}
