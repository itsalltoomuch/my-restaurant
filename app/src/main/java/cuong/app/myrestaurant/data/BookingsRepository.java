package cuong.app.myrestaurant.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class BookingsRepository {

    private BookingDao mBookingDao;
    private LiveData<List<Booking>> mAllBookings;

    public BookingsRepository(Application application) {
        MyAppDatabase db = MyAppDatabase.getDatabase(application);
        mBookingDao = db.bookingDao();
        mAllBookings = mBookingDao.getAllBookings();
    }

    public LiveData<List<Booking>> getAllBookings() {
        return mAllBookings;
    }


    public void insert (Booking booking) {
        new insertAsyncTask(mBookingDao).execute(booking);
    }

    private static class insertAsyncTask extends AsyncTask<Booking, Void, Void> {

        private BookingDao mAsyncTaskDao;

        insertAsyncTask(BookingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Booking... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
