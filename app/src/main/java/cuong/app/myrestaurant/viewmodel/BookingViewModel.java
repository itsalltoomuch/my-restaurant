package cuong.app.myrestaurant.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.BookingsRepository;

public class BookingViewModel extends AndroidViewModel {

    private BookingsRepository mBookingRepository;
    private LiveData<List<Booking>> mAllBooking;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        mBookingRepository = new BookingsRepository(application);
        mAllBooking = mBookingRepository.getAllBookings();
    }

    public LiveData<List<Booking>> getAllBooking() {
        return mAllBooking;
    }
}
