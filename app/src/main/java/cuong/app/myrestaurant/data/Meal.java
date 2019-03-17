package cuong.app.myrestaurant.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "meals")
public class Meal {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="meal_id")
    private int id;

    @NonNull
    @ColumnInfo (name="meal_name")
    private String mealName;

    @ColumnInfo (name="booking_id")
    private int bookingId;

    @ColumnInfo (name="restaurant_id")
    private int restaurantId;

    public Meal(int id, @NonNull String mealName, int bookingId, int restaurantId) {
        this.id = id;
        this.mealName = mealName;
        this.bookingId = bookingId;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getMealName() {
        return mealName;
    }

    public void setMealName(@NonNull String mealName) {
        this.mealName = mealName;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
