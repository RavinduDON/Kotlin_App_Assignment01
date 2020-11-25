package lk.dev.exerciseso.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lk.dev.exerciseso.R
import lk.dev.exerciseso.ThirdFragment
import lk.dev.exerciseso.posts.models.Post

class PostAdaptor(private val dataSet: ArrayList<Post>) : RecyclerView.Adapter<PostAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdaptor.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostAdaptor.ViewHolder, position: Int) {
        val dataModel=dataSet.get(position)
        holder.titleTextView.text=dataModel.title
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemlayoutView: View): RecyclerView.ViewHolder(itemlayoutView){
         var titleTextView:TextView
        init {
            titleTextView=itemlayoutView.findViewById(R.id.title_textview)
        }
    }

}