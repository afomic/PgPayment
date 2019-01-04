package afomic.com.pgpayment.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {
    private String section;
    private String matricNumber;
    private String email;
    private String type;
    private String Department;
    private int amount;
    private String faculty;

    public Payment() {

    }

    protected Payment(Parcel in) {
        section = in.readString();
        matricNumber = in.readString();
        email = in.readString();
        type = in.readString();
        Department = in.readString();
        amount = in.readInt();
        faculty = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(section);
        parcel.writeString(matricNumber);
        parcel.writeString(email);
        parcel.writeString(type);
        parcel.writeString(Department);
        parcel.writeInt(amount);
        parcel.writeString(faculty);
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
