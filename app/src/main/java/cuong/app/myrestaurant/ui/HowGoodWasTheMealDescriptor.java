package cuong.app.myrestaurant.ui;

public class HowGoodWasTheMealDescriptor {
    public static final String GOOD = "awesome!";
    public static final String OK = "ok, but not the best";
    public static final String BAD = "won't eat that meal anymore";

    public final String howGoodWasTheMeal;

    public HowGoodWasTheMealDescriptor(String howGoodWasTheMeal) {
        this.howGoodWasTheMeal = howGoodWasTheMeal;
    }
}
