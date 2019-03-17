package cuong.app.myrestaurant.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "bookings")
public class Booking {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="booking_id")
    private int id;
    @NonNull
    @ColumnInfo (name="restaurant_id")
    private String restaurantId;
    @NonNull
    @ColumnInfo (name = "date")
    private String date;
    @NonNull
    @ColumnInfo (name = "time")
    private String time;
    @NonNull
    @ColumnInfo (name = "address")
    private String address;

    public Booking(@NonNull String restaurantId, @NonNull String date, @NonNull String time, @NonNull String address) {
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId (int id) { this.id = id; }
    @NonNull
    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NonNull String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setTheDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTheTime(@NonNull String time) {
        this.time = time;
    }
    @NonNull
    public String getAddress() { return address;}

    public void setTheAddress(@NonNull String address) {this.address = address; }
}
