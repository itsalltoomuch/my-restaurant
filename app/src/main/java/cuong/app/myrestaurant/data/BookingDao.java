package cuong.app.myrestaurant.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookingDao {

    @Insert
    void insert(Booking booking);

    @Delete
    void deleteBooking(Booking booking);

    @Query("SELECT * from bookings")
    LiveData<List<Booking>> getAllBookings();
}
