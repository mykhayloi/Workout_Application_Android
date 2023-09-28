package com.example.workout_application_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workout_application_android.databinding.WorkoutItemBinding


class WorkoutAdapter(
    private val onTap: (Workout) -> Unit,
): RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {
    class WorkoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = WorkoutItemBinding.bind(itemView)

        fun bind(workout: Workout) {
            binding.iconList.setImageResource(workout.icon)
            binding.workoutTitle.text = workout.title
            binding.workoutDetail.text = "${workout.detail} min"
        }
    }

    private var data: List<Workout> = listOf()

    fun setData(newData: List<Workout>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.workout_item, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = data[position]
        holder.bind(workout)
        holder.itemView.setOnClickListener {
            onTap(workout)
        }
    }

}