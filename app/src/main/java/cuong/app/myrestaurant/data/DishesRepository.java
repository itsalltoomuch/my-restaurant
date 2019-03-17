package cuong.app.myrestaurant.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DishesRepository {

    private DishDao mDishDao;
    private LiveData<List<Dish>> mAllDishes;

    public DishesRepository(Application application) {
        MyAppDatabase db = MyAppDatabase.getDatabase(application);
        mDishDao = db.dishDao();
        mAllDishes = mDishDao.getAllDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return mAllDishes;
    }


    public void insert (Dish dish) {
        new insertAsyncTask(mDishDao).execute(dish);
    }

    private static class insertAsyncTask extends AsyncTask<Dish, Void, Void> {

        private DishDao mAsyncTaskDao;

        insertAsyncTask(DishDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Dish... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
