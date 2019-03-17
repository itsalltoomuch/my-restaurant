package cuong.app.myrestaurant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.BookingsRepository;
import cuong.app.myrestaurant.data.Restaurant;

public class BookingViewModel extends AndroidViewModel {

    private BookingsRepository mRepository;
    private LiveData<List<Booking>> mAllBooking;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BookingsRepository(application);
        mAllBooking = mRepository.getAllBookings();
    }

    public LiveData<List<Booking>> getAllBooking() {
        return mAllBooking;
    }
}
