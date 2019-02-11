package afomic.com.pgpayment.ui.paymentHistory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.model.PaymentHistory;
import afomic.com.pgpayment.viewHelper.paymentHistory.PaymentHistoryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentHistoryFragment extends Fragment implements PaymentHistoryView {
    @BindView(R.id.rv_payment_history)
    RecyclerView paymentHistoryRecyclerView;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;

    public static String TAG = "PaymentHistoryFragment";


    private PaymentHistoryPresenter mPaymentOverviewPresenter;
    private PaymentHistoryAdapter mPaymentHistoryAdapter;
    private List<PaymentHistory> mPaymentHistories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPaymentOverviewPresenter = new PaymentHistoryPresenter(this);
        mPaymentOverviewPresenter.loadData(this);

    }

    @Override
    public void showPaymentHistory(List<PaymentHistory> paymentHistories) {
        mPaymentHistories.clear();
        mPaymentHistories.addAll(paymentHistories);
        mPaymentHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyListView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyListView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        paymentHistoryRecyclerView.setLayoutManager(linearLayoutManager);
        paymentHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mPaymentHistories = new ArrayList<>();
        mPaymentHistoryAdapter = new PaymentHistoryAdapter(getContext(), mPaymentHistories);
        paymentHistoryRecyclerView.setAdapter(mPaymentHistoryAdapter);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
