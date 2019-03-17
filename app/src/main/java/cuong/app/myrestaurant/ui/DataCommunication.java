package cuong.app.myrestaurant.ui;

import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;

public interface DataCommunication {

     String getResName();
     String getAddress();
     String getBookName();
     String getTheTime();
     String getHowGood();
     String getCommentary();
     String getLatitude();
     String getLongitude();
     String getTheDate();
     RestaurantListAdapter getRestaurantListAdapter();
     BookingListAdapter getBookingListAdapter();
     MealListAdapter getMealListAdapter();

     void setResName(String name);
     void setBookName (String name);
     void setHowGood(String howGood);
     void setCommentary(String commentary);
     void setLatitude(String latitude);
     void setLongitude(String longitude);
     void setRestaurantListAdapter(RestaurantListAdapter restaurantListAdapter);
     void setTheTime(String time);
     void setTheDate(String date);
     void setTheAddress (String address);

     void setBookingListAdapter(BookingListAdapter adapter);
}
