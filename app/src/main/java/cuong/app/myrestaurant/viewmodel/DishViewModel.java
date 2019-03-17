package cuong.app.myrestaurant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cuong.app.myrestaurant.data.Dish;
import cuong.app.myrestaurant.data.DishesRepository;

public class DishViewModel extends AndroidViewModel {

    private DishesRepository mDishesRepository;
    private LiveData<List<Dish>> mAllDishes;

    public DishViewModel(@NonNull Application application) {
        super(application);
        mDishesRepository = new DishesRepository(application);
        mAllDishes = mDishesRepository.getAllDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return mAllDishes;
    }
}
