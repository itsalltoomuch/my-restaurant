package cuong.app.myrestaurant.ui.fragments.meals;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Meal;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;
import cuong.app.myrestaurant.viewmodel.MealViewModel;

public class MealsFragment extends Fragment {

    DataCommunication mCallback;
    private MealViewModel mealViewModel;
    private FloatingActionButton btnAddNew;

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

        View browseMealList = inflater.inflate(R.layout.fragment_meal, null);

        RecyclerView recyclerView = browseMealList.findViewById(R.id.recyclerview);
        final MealListAdapter adapter = new MealListAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        mealViewModel.getAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable final List<Meal> meals) {
                adapter.setMeals(meals);
            }
        });
        mCallback.setMealListAdapter(adapter);
        btnAddNew = browseMealList.findViewById(R.id.btn_add);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNewInterface();
            }
        });

        return browseMealList;

    }
    private void showAddNewInterface() {
        AddMealFragment addMealFragment = new AddMealFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_home, addMealFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}