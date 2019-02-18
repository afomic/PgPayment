package afomic.com.pgpayment.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class PaymentHistory implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String transactionId;
    private String section;
    private int amount;
    private boolean status;

    public PaymentHistory() {

    }

    protected PaymentHistory(Parcel in) {
        id = in.readInt();
        transactionId = in.readString();
        section = in.readString();
        amount = in.readInt();
        status = in.readByte() != 0;
    }

    public static final Creator<PaymentHistory> CREATOR = new Creator<PaymentHistory>() {
        @Override
        public PaymentHistory createFromParcel(Parcel in) {
            return new PaymentHistory(in);
        }

        @Override
        public PaymentHistory[] newArray(int size) {
            return new PaymentHistory[size];
        }
    };

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(transactionId);
        parcel.writeString(section);
        parcel.writeInt(amount);
        parcel.writeByte((byte) (status ? 1 : 0));
    }
}
