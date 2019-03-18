package cuong.app.myrestaurant.ui.fragments.bookings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.MyAppDatabase;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;

public class SelectedBookingFragment extends Fragment  {

    DataCommunication mCallback;
    private TextView restaurantName, date, time, address;
    private ImageButton deleteButton;
    private Button btnCall;
    private int position;
    BookingListAdapter resListAdapter;
    List<Booking> listOfBookings;
    Booking selectedBooking;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (DataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View selectedBookView = inflater.inflate(R.layout.fragment_selected_booking, null);
        restaurantName = selectedBookView.findViewById(R.id.book_restaurant);
        date = selectedBookView.findViewById(R.id.booking_date);
        time = selectedBookView.findViewById(R.id.booking_time);
        address = selectedBookView.findViewById(R.id.restaurant_address);
        deleteButton = selectedBookView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteFromDBAsync().execute();
                changeFragmentToHome();
                Toast.makeText(getContext(),"Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        Bundle selectedResPositionBundle = getArguments();
        position = selectedResPositionBundle.getInt("positionClicked");
        resListAdapter = mCallback.getBookingListAdapter();
        listOfBookings = resListAdapter.getmBookings();

        selectedBooking = listOfBookings.get(position);
        restaurantName.setText(selectedBooking.getRestaurantId());
        date.setText(selectedBooking.getDate());
        time.setText(selectedBooking.getTime());
        address.setText(selectedBooking.getAddress());

        btnCall = selectedBookView.findViewById(R.id.call_restaurant);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:000"));
                startActivity(call);
            }
        });


        return selectedBookView;

    }



    public void changeFragmentToHome() {
        BookingsFragment bookingsFragment = new BookingsFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_home, bookingsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private class DeleteFromDBAsync extends AsyncTask<Object, Void, List<Booking>> {

        @Override
        protected List<Booking> doInBackground(Object... params) {
            MyAppDatabase.getDatabase(getContext()).bookingDao().deleteBooking(selectedBooking);
            return null;
        }
    }
}
