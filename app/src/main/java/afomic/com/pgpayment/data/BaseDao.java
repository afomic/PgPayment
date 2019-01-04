package afomic.com.pgpayment.data;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BaseDao<T> {
    @Insert
    long insert(T data);

    @Insert
    void insert(List<T> data);

    @Delete
    void delete(T data);

    @Update
    void update(T data);
}
