package cuong.app.myrestaurant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cuong.app.myrestaurant.data.Meal;
import cuong.app.myrestaurant.data.MealsRepository;

public class MealViewModel extends AndroidViewModel {

    private MealsRepository mMealsRepository;
    private LiveData<List<Meal>> mAllMeal;

    public MealViewModel(@NonNull Application application) {
        super(application);
        mMealsRepository = new MealsRepository(application);
        mAllMeal = mMealsRepository.getmAllMeals();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return mAllMeal;
    }
}
