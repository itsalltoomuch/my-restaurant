package cuong.app.myrestaurant.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Restaurant.class, Booking.class, Meal.class, Dish.class}, version = 1, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
    public abstract BookingDao bookingDao();
    public abstract MealDao mealDao();
    public abstract DishDao dishDao();
    private static MyAppDatabase INSTANCE;

    public static MyAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, "my_app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
