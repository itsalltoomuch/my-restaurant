package cuong.app.myrestaurant.ui.fragments.bookings;


import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.MyAppDatabase;
import cuong.app.myrestaurant.ui.DataCommunication;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookingFragment extends Fragment implements View.OnClickListener {

    DataCommunication mCallback;
    private Button addBookingButton;
    private EditText BookingName;
    private EditText time;
    private EditText date;
    private EditText address;
    private String resName;
    private String resDate;
    private String resTime;
    private String resAddress;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addBookingView = inflater.inflate(R.layout.fragment_add_booking, container,false);
        addBookingButton = addBookingView.findViewById(R.id.button_save);
        BookingName = addBookingView.findViewById(R.id.booking_name_form);
        time = addBookingView.findViewById(R.id.the_time);
        date = addBookingView.findViewById(R.id.the_date);
        address = addBookingView.findViewById(R.id.booking_address_form);
        addBookingButton.setOnClickListener(this);
        return addBookingView;
    }
    @Override
    public void onClick(View view) {
        resName = BookingName.getText().toString().trim();
        resTime = time.getText().toString().trim();
        resDate = date.getText().toString().trim();
        resAddress = address.getText().toString().trim();

        if (resName.equals("") || resTime.equals("") || resDate.equals("") || resAddress.equals("")) {
            showAlert();
        } else {
            mCallback.setBookName(resName);
            mCallback.setTheTime(resTime);
            mCallback.setTheDate(resDate);
            mCallback.setTheAddress(resAddress);



        }
        new SaveToDatabaseAsync().execute();

        BookingsFragment bookingsFragment = new BookingsFragment();
        FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.fragment_container_home, bookingsFragment);
        fragmentTransaction2.addToBackStack(null);
        fragmentTransaction2.commit();
        Toast.makeText(getContext(),"Added successfully", Toast.LENGTH_SHORT).show();
        addBookingButton.setEnabled(false);
    }




    public void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Please fill in all fields");

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private class SaveToDatabaseAsync extends AsyncTask<Object, Void, List<Booking>> {

        private String resName = mCallback.getBookName();
        private String resDate = mCallback.getTheDate();
        private String resTime = mCallback.getTheTime();
        private String resAddress = mCallback.getAddress();


        @Override
        protected List<Booking> doInBackground(Object... params) {
            Booking booking = new Booking(resName, resDate, resTime, resAddress);
            MyAppDatabase.getDatabase(getContext()).bookingDao().insert(booking);
            return null;
        }
    }



}
