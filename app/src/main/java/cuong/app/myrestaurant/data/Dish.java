package cuong.app.myrestaurant.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "dishes")
public class Dish {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="dish_id")
    private int id;

    @NonNull
    @ColumnInfo (name="dish_name")
    private String dishName;

    @ColumnInfo (name="price")
    private long price;

    @ColumnInfo (name="review")
    private String review;

    @ColumnInfo (name="picture")
    private String picture;

    @ColumnInfo (name="rating")
    private int rating;

    @ColumnInfo (name="meal_id")
    private String mealId;

    public Dish(int id, @NonNull String dishName, long price, String review, String picture, int rating, String mealId) {
        this.id = id;
        this.dishName = dishName;
        this.price = price;
        this.review = review;
        this.picture = picture;
        this.rating = rating;
        this.mealId = mealId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDishName() {
        return dishName;
    }

    public void setDishName(@NonNull String dishName) {
        this.dishName = dishName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
