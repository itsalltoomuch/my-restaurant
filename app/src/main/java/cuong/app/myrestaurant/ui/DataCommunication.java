package cuong.app.myrestaurant.ui;

import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;

public interface DataCommunication {

     String getResName();
     String getMealName();
     String getAddress();
     String getBookName();
     String getTheTime();
     String getHowGood();
     String getCommentary();
     String getLatitude();
     String getLongitude();
     String getRating();
     String getMealComment();
     String getTheDate();
     String getPrice();
     String getPlace();
     RestaurantListAdapter getRestaurantListAdapter();
     BookingListAdapter getBookingListAdapter();
     MealListAdapter getMealListAdapter();

     void setResName(String name);
     void setBookName (String name);
     void setHowGood(String howGood);
     void setMealName (String meal);
     void setPlace(String place);
     void setPrice (String price);
     void setCommentary(String commentary);
     void setRating(String rating);
     void setMealComment(String comment);
     void setLatitude(String latitude);
     void setLongitude(String longitude);
     void setRestaurantListAdapter(RestaurantListAdapter restaurantListAdapter);
     void setTheTime(String time);
     void setTheDate(String date);
     void setTheAddress (String address);

     void setBookingListAdapter(BookingListAdapter adapter);
     void setMealListAdapter(MealListAdapter mealListAdapter);
}
