package thirdwayv.calculator.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import thirdwayv.calculator.databinding.HistoryHolderItemBinding
import thirdwayv.calculator.databinding.OperandsLayoutBinding
import thirdwayv.calculator.pojo.Operation


class HistoryAdapter(val operations:MutableList<Operation>, val iMain: IMain): RecyclerView.Adapter<HistoryAdapter.MyViewHolder>(){
    class MyViewHolder(val historyHolder: HistoryHolderItemBinding): RecyclerView.ViewHolder(historyHolder.root) {

    }

    private  val TAG = "HistoryAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val historyHolder: HistoryHolderItemBinding =HistoryHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return MyViewHolder(historyHolder)
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.historyHolder.itemOperator.text = operations.get(position).operation.toString()
        holder.historyHolder.lastNumber.text = operations.get(position).lastNum.toString()
        holder.itemView.setOnClickListener{
            iMain.removeTask(operations.get(position))
            Log.d(TAG, "onBindViewHolder: " + position)
            Log.d(TAG, "onBindViewHolder: " + (operations.size - position))
            removeAt(position)
        }
    }

    fun removeAt(position: Int) {
        operations.removeAt(position)
        notifyItemRemoved(position)
    }

}