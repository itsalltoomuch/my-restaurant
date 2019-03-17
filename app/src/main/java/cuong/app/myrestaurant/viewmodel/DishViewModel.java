package cuong.app.myrestaurant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.BookingsRepository;
import cuong.app.myrestaurant.data.Dish;
import cuong.app.myrestaurant.data.DishesRepository;

public class DishViewModel extends AndroidViewModel {

    private DishesRepository mRepository;
    private LiveData<List<Dish>> mAllDishes;

    public DishViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DishesRepository(application);
        mAllDishes = mRepository.getAllDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return mAllDishes;
    }
}
