package com.yxl.schedule.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yxl.schedule.databinding.ScheduleItemBinding
import com.yxl.schedule.models.ScheduleData

class ScheduleAdapter() : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: ScheduleData.Data.Schedule) = with(binding){
            tvSubName.text = schedule.subject.abbreviated
            tvSubType.text = schedule.type.abbreviated
            tvProfName.text = schedule.teacher.fullName
            tvStartTime.text = schedule.time.start
            tvEndTime.text = schedule.time.end
            tvSubRoom.text = schedule.auditory.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ScheduleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ScheduleData.Data.Schedule>(){
        override fun areItemsTheSame(oldItem: ScheduleData.Data.Schedule, newItem: ScheduleData.Data.Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ScheduleData.Data.Schedule, newItem: ScheduleData.Data.Schedule): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}