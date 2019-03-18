package cuong.app.myrestaurant.ui.fragments.meals;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Meal;
import cuong.app.myrestaurant.data.MyAppDatabase;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;

public class SelectedMealFragment extends Fragment  {

    DataCommunication mCallback;
    private TextView mealName, mealPrice, mealPlace;
    private ImageButton deleteButton;
    private int position;
    MealListAdapter mealListAdapter;
    List<Meal> listOfMeals;
    Meal selectedMeal;

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
        View selectedBookView = inflater.inflate(R.layout.fragment_selected_meal, null);
        mealName = selectedBookView.findViewById(R.id.meal_name);
        mealPrice = selectedBookView.findViewById(R.id.meal_price);
        mealPlace = selectedBookView.findViewById(R.id.restaurant_address);
        deleteButton = selectedBookView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteFromDBAsync().execute();
                changeFragmentToHome();
                Toast.makeText(getContext(),"Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        Bundle selectedResPositionBundle = getArguments();
        position = selectedResPositionBundle.getInt("positionClicked");
        mealListAdapter = mCallback.getMealListAdapter();
        listOfMeals = mealListAdapter.getmMeal();

        selectedMeal = listOfMeals.get(position);
        mealName.setText(selectedMeal.getMealName());
        mealPlace.setText(selectedMeal.getMealPlace());
        mealPrice.setText(selectedMeal.getPrice());



        return selectedBookView;

    }



    public void changeFragmentToHome() {
        MealsFragment mealsFragment = new MealsFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_home, mealsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private class DeleteFromDBAsync extends AsyncTask<Object, Void, List<Meal>> {

        @Override
        protected List<Meal> doInBackground(Object... params) {
            MyAppDatabase.getDatabase(getContext()).mealDao().deleteMeal(selectedMeal);
            return null;
        }
    }
}
