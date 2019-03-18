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

import org.w3c.dom.Text;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Meal;

import cuong.app.myrestaurant.ui.HowGoodWasTheMealDescriptor;
import cuong.app.myrestaurant.ui.MainActivity;

import cuong.app.myrestaurant.ui.fragments.meals.SelectedMealFragment;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {

    class MealViewHolder extends RecyclerView.ViewHolder {
        private final TextView mealId;
        private final TextView mealPrice;
        private final TextView mealPlace;
        private final TextView howGoodMealView;
        private final TextView commentaryMealView;
        private ImageButton selected_item;
        private View container;

        private MealViewHolder(View itemView) {
            super(itemView);
            mealId = itemView.findViewById(R.id.dish_name);
            mealPrice = itemView.findViewById(R.id.price);
            mealPlace = itemView.findViewById(R.id.address);
            howGoodMealView = itemView.findViewById(R.id.mealevaluation);
            commentaryMealView = itemView.findViewById(R.id.mealcomment);

            selected_item = itemView.findViewById(R.id.select_restaurant);
            container = itemView.findViewById(R.id.card_view);
        }
    }

    private final LayoutInflater mInflater;
    private List<Meal> mMeal;
    private Context mContext;
    private int adapterPosition;

    public MealListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_meal_item, parent, false);
        final MealViewHolder rHolder = new MealViewHolder(itemView);

        rHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterPosition = rHolder.getAdapterPosition();
                Bundle adapterPositionBundle = new Bundle();
                adapterPositionBundle.putInt("positionClicked", adapterPosition);

                SelectedMealFragment selectedMealFragmente = new SelectedMealFragment();
                MainActivity myActivity = (MainActivity)mContext;
                FragmentTransaction fragmentTransaction = myActivity.getSupportFragmentManager().beginTransaction();
                selectedMealFragmente.setArguments(adapterPositionBundle);
                fragmentTransaction.replace(R.id.fragment_container_home, selectedMealFragmente);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rHolder;
    }


    @Override
    public void onBindViewHolder(MealViewHolder holder, final int position) {
        if (mMeal != null) {
            Meal current = mMeal.get(position);
            holder.mealId.setText(current.getMealName());
            holder.mealPrice.setText(current.getPrice());
            holder.mealPlace.setText(current.getMealPlace());
            holder.howGoodMealView.setText(current.getHowGoodIsTheMeal());
            holder.commentaryMealView.setText(current.getMealCommentary());

            switch (current.getHowGoodIsTheMeal()) {
                case HowGoodWasTheMealDescriptor.GOOD:
                    holder.howGoodMealView.setTextColor(Color.parseColor("#00b015"));
                    break;
                case HowGoodWasTheMealDescriptor.OK:
                    holder.howGoodMealView.setTextColor(Color.parseColor("#ff9e00"));
                    break;
                case HowGoodWasTheMealDescriptor.BAD:
                    holder.howGoodMealView.setTextColor(Color.parseColor("#ee1010"));
                    break;
            }
        } else {
            holder.mealId.setText("");
        }
    }

    public void setMeals(List<Meal> meals){
        mMeal = meals;
        notifyDataSetChanged();
    }

    public List<Meal> getmMeal() {
        return mMeal;
    }

    @Override
    public int getItemCount() {
        if (mMeal != null)
            return mMeal.size();
        else return 0;
    }
}
