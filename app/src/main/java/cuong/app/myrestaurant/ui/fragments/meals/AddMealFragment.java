package cuong.app.myrestaurant.ui.fragments.meals;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Meal;
import cuong.app.myrestaurant.data.MyAppDatabase;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.HowGoodWasTheMealDescriptor;

public class AddMealFragment extends Fragment implements View.OnClickListener {

    DataCommunication mCallback;
    private Button saveButton;
    private EditText DishName;
    private RadioGroup howGoodForm;
    private RadioButton selectedRadioButton;
    private String howGood;
    private EditText MealCommentary;
    private EditText MealPrice;
    private EditText Place;
    private String MealName;
    private String MealComment;
    private String MealEvaluation;
    private String price;
    private String place;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View addMealView = inflater.inflate(R.layout.fragment_add_meal, null);
        saveButton = addMealView.findViewById(R.id.button_save);
        DishName = addMealView.findViewById(R.id.dish_name_form);
        howGoodForm = addMealView.findViewById(R.id.how_good_radio_group);
        MealCommentary = addMealView.findViewById(R.id.meal_comment);
        MealPrice = addMealView.findViewById(R.id.price);
        Place = addMealView.findViewById(R.id.address);
        selectedRadioButton = howGoodForm.findViewById(howGoodForm.getCheckedRadioButtonId());
        howGoodForm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {

                switch (checked) {
                    case R.id.radioButtonVeryGood:
                        selectedRadioButton = howGoodForm.findViewById(checked);
                        howGood = HowGoodWasTheMealDescriptor.GOOD;
                        break;
                    case R.id.radioButtonOK:
                        selectedRadioButton = howGoodForm.findViewById(checked);
                        howGood = HowGoodWasTheMealDescriptor.OK;
                        break;
                    case R.id.radioButtonBad:
                        selectedRadioButton = howGoodForm.findViewById(checked);
                        howGood = HowGoodWasTheMealDescriptor.BAD;
                        break;
                }
            }
        });
        MealCommentary = addMealView.findViewById(R.id.meal_comment);
        saveButton.setOnClickListener(this);
        return addMealView;
    }

    @Override
    public void onClick(View view) {

        MealName = DishName.getText().toString().trim();
        MealComment = MealCommentary.getText().toString().trim();
        MealEvaluation = howGood;
        price = MealPrice.getText().toString().trim();
        place = Place.getText().toString().trim();


        if(MealName.equals("") || MealComment.equals("") || MealEvaluation.equals("") || price.equals("") || place.equals("")) {
            showAlert();
        } else {
            mCallback.setMealName(MealName);
            mCallback.setRating(MealEvaluation);
            mCallback.setMealComment(MealComment);
            mCallback.setPrice(price);
            mCallback.setPlace(place);

            new SaveToDatabaseAsync().execute();

            MealsFragment mealFragment = new MealsFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_home, mealFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            Toast.makeText(getContext(),"Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Please fill in all fields");

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class SaveToDatabaseAsync extends AsyncTask<Object, Void, List<Meal>> {

        private String MealName = mCallback.getMealName();
        private String MealEvaluation = mCallback.getRating();
        private String MealComment = mCallback.getMealComment();
        private String price = mCallback.getPrice();
        private String place = mCallback.getPlace();


        @Override
        protected List<Meal> doInBackground(Object... params) {
            Meal meal = new Meal(MealName, price, MealEvaluation, MealComment, place);
            MyAppDatabase.getDatabase(getContext()).mealDao().insert(meal);
            return null;
        }
    }
}


