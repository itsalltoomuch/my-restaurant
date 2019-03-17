package cuong.app.myrestaurant.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DishDao {

    @Insert
    void insert(Dish dish);

    @Delete
    void deleteBooking(Dish dish);

    @Query("SELECT * from dishes")
    LiveData<List<Dish>> getAllDishes();
}
