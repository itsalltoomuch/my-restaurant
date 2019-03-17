package cuong.app.myrestaurant.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MealsRepository {

    private MealDao mMealDao;
    private LiveData<List<Meal>> mAllMeals;

    public MealsRepository(Application application) {
        MyAppDatabase db = MyAppDatabase.getDatabase(application);
        mMealDao = db.mealDao();
        mAllMeals = mMealDao.getAllMeals();
    }

    public LiveData<List<Meal>> getmAllMeals() {
        return mAllMeals;
    }


    public void insert (Meal meal) {
        new insertAsyncTask(mMealDao).execute(meal);
    }

    private static class insertAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDao mAsyncTaskDao;

        insertAsyncTask(MealDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
