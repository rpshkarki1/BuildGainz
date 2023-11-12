package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildgainz.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WorkoutsAdapter extends RecyclerView.Adapter< WorkoutsAdapter.WorkoutViewHolder> {

    private final List<WorkoutItem> workoutItemList;
    private final OnItemClickListener onItemClickListener;

    public WorkoutsAdapter ( List <WorkoutItem> workoutItemList, OnItemClickListener onItemClickListener) {
        this.workoutItemList = workoutItemList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.workout_item, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        WorkoutItem workoutItem = workoutItemList.get(position);
        holder.bind(workoutItem);
    }

    @Override
    public int getItemCount() {
        return workoutItemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {
        ImageView exerciseImageView;
        TextView nameTextView;
        TextView repsSetsTextView;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImageView = itemView.findViewById(R.id.exerciseImageView);
            nameTextView = itemView.findViewById(R.id.name);
            repsSetsTextView = itemView.findViewById(R.id.repsXSets);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(WorkoutItem workoutItem) {
            try {
                InputStream ims = itemView.getContext().getAssets().open(workoutItem.getImagePath ());
                Drawable d = Drawable.createFromStream(ims, null);
                exerciseImageView.setImageDrawable(d);
            } catch ( IOException e) {
                e.printStackTrace();
            }            nameTextView.setText(workoutItem.getName());
            repsSetsTextView.setText(workoutItem.getRepsSets());
        }
    }


}
