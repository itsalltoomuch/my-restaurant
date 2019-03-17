package cuong.app.myrestaurant.ui.fragments.bookings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cuong.app.myrestaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookingFragment extends Fragment {


    public AddBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_booking, container, false);
    }

}
