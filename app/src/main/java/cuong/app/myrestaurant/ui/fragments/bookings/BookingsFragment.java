package cuong.app.myrestaurant.ui.fragments.bookings;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.Restaurant;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;
import cuong.app.myrestaurant.ui.fragments.restaurants.AddRestaurantFragment;
import cuong.app.myrestaurant.viewmodel.BookingViewModel;
import cuong.app.myrestaurant.viewmodel.RestaurantViewModel;

public class BookingsFragment extends Fragment {

    DataCommunication mCallback;
    private BookingViewModel mBookingViewModel;
    private FloatingActionButton btnAddNew;

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

        View content = inflater.inflate(R.layout.fragment_bookings, null);

        RecyclerView recyclerView = content.findViewById(R.id.recyclerview);
        final BookingListAdapter adapter = new BookingListAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mBookingViewModel = ViewModelProviders.of(this).get(BookingViewModel.class);

        mBookingViewModel.getAllBooking().observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(@Nullable final List<Booking> bookings) {
                adapter.setBookings(bookings);
            }
        });
        mCallback.setBookingListAdapter(adapter);

        btnAddNew = content.findViewById(R.id.btn_add);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNewInterface();
            }
        });

        return content;

    }

    private void showAddNewInterface() {
        AddBookingFragment addBookingFragment = new AddBookingFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_home, addBookingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}