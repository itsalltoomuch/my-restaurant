package cuong.app.myrestaurant.ui.fragments.bookings;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.ui.DataCommunication;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookingFragment extends Fragment implements View.OnClickListener{

    DataCommunication mCallback;
    private Button nextStepButton;
    private EditText restaurantName;
    private EditText time;
    private String resName;
    private String resTime;
    public AddBookingFragment() {
        // Required empty public constructor
    }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addBookingView = inflater.inflate(R.layout.fragment_add_booking, container, false);
        nextStepButton = addBookingView.findViewById(R.id.button_save);
        restaurantName = addBookingView.findViewById(R.id.booking_name_form);
        nextStepButton.setOnClickListener(this);
        return addBookingView;
    }
    @Override
    public void onClick(View view) {

        resName = restaurantName.getText().toString().trim();
        resTime = time.getText().toString().trim();

        if (resName.equals("") || resTime.equals("")) {
            showAlert();
        } else {
            mCallback.setResName(resName);
            mCallback.setTheTime(resTime);
        }
    }
    public void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Please fill in all fields");

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
