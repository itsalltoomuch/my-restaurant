package cuong.app.myrestaurant.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "meals")
public class Meal {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="meal_id")
    private int id;
    @NonNull
    @ColumnInfo (name="meal_name")
    private String mealName;
    @NonNull
    @ColumnInfo (name="mealprice")
    private String price;
    @NonNull
    @ColumnInfo (name = "howgood")
    private String howGoodIsTheMeal;
    @NonNull
    @ColumnInfo (name = "commentary")
    private String MealCommentary;
    @NonNull
    @ColumnInfo (name="mealplace")
    private String mealPlace;

    public Meal(@NonNull String mealName,@NonNull String price,@NonNull String howGoodIsTheMeal,@NonNull String MealCommentary,@NonNull String mealPlace) {
        this.mealName = mealName;
        this.price = price;
        this.howGoodIsTheMeal = howGoodIsTheMeal;
        this.MealCommentary = MealCommentary;
        this.mealPlace = mealPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getMealName() {
        return mealName;
    }

    public void setMealName(@NonNull String mealName) {
        this.mealName = mealName;
    }
    @NonNull
    public String getPrice() {
        return price;
    }

    public void setPrice(@NonNull String price) {
        this.price = price;
    }

    @NonNull
    public String getHowGoodIsTheMeal() { return howGoodIsTheMeal; }

    public void setHowGoodIsTheMeal(@NonNull String howGoodIsTheMeal) { this.howGoodIsTheMeal = howGoodIsTheMeal; }

    @NonNull
    public String getMealCommentary() { return MealCommentary; }

    public void setMealCommentary(@NonNull String MealCommentary) { this.MealCommentary = MealCommentary; }

    @NonNull
    public String getMealPlace() { return mealPlace; }

    public void setMealPlace(@NonNull String mealPlace) {
        this.mealPlace = mealPlace;
    }

}
