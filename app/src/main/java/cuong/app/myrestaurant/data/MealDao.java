package cuong.app.myrestaurant.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MealDao {

    @Insert
    void insert(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT * from meals")
    LiveData<List<Meal>> getAllMeals();
}
