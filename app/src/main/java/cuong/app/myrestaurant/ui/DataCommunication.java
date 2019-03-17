package cuong.app.myrestaurant.ui;

import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;

public interface DataCommunication {

     String getResName();
     String getResTime();
     String getHowGood();
     String getCommentary();
     String getLatitude();
     String getLongitude();
     RestaurantListAdapter getRestaurantListAdapter();
     BookingListAdapter getBookingListAdapter();
     MealListAdapter getMealListAdapter();

     void setResName(String name);
     void setHowGood(String howGood);
     void setCommentary(String commentary);
     void setLatitude(String latitude);
     void setLongitude(String longitude);
     void setRestaurantListAdapter(RestaurantListAdapter restaurantListAdapter);
     void setTheTime(String time);

     void setBookingListAdapter(BookingListAdapter adapter);
}
