package cuong.app.myrestaurant.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Booking;
import cuong.app.myrestaurant.data.Restaurant;
import cuong.app.myrestaurant.ui.HowGoodWasTheRestaurantDescriptor;
import cuong.app.myrestaurant.ui.MainActivity;
import cuong.app.myrestaurant.ui.fragments.bookings.SelectedBookingFragment;
import cuong.app.myrestaurant.ui.fragments.restaurants.SelectedRestaurantViewFragment;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.BookingViewHolder> {

    class BookingViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvBookingId;
        private final TextView tvDate;
        private final TextView tvTime;
        private ImageButton selected_item;

        private BookingViewHolder(View itemView) {
            super(itemView);
            tvBookingId = itemView.findViewById(R.id.tv_booking_id);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    private final LayoutInflater mInflater;
    private List<Booking> mBookings;
    private Context mContext;
    private int adapterPosition;

    public BookingListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_booking_item, parent, false);
        final BookingViewHolder rHolder = new BookingViewHolder(itemView);

        rHolder.selected_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterPosition = rHolder.getAdapterPosition();
                Bundle adapterPositionBundle = new Bundle();
                adapterPositionBundle.putInt("positionClicked", adapterPosition);

                SelectedBookingFragment selectedBookingFragment = new SelectedBookingFragment();
                MainActivity myActivity = (MainActivity)mContext;
                FragmentTransaction fragmentTransaction = myActivity.getSupportFragmentManager().beginTransaction();
                selectedBookingFragment.setArguments(adapterPositionBundle);
                fragmentTransaction.replace(R.id.fragment_container_home, selectedBookingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rHolder;
    }


    @Override
    public void onBindViewHolder(BookingViewHolder holder, final int position) {
        if (mBookings != null) {
            Booking current = mBookings.get(position);
            holder.tvBookingId.setText(current.getId());
            holder.tvDate.setText(current.getDate());
            holder.tvTime.setText(current.getTime());

        } else {
            holder.tvBookingId.setText("");
        }
    }

    public void setBookings(List<Booking> bookings){
        mBookings = bookings;
        notifyDataSetChanged();
    }

    public List<Booking> getBookings() {
        return mBookings;
    }

    @Override
    public int getItemCount() {
        if (mBookings != null)
            return mBookings.size();
        else return 0;
    }
}
