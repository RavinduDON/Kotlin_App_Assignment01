package lk.dev.exerciseso.Adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lk.dev.exerciseso.R
import lk.dev.exerciseso.ThirdFragment
import lk.dev.exerciseso.posts.models.Post

class PostAdaptor(private val context: ThirdFragment, private val dataSet: ArrayList<Post>) : RecyclerView.Adapter<PostAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdaptor.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_view_item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(val itemView: View) :RecyclerView.ViewHolder(itemView) {
        lateinit var titleTextView: TextView
        init {
            titleTextView=itemView.findViewById(R.id.title_textview)
        }
    }

    override fun onBindViewHolder(holder: PostAdaptor.ViewHolder, position: Int) {
        val dataMOdel=dataSet.get(position)
        holder.titleTextView.text=dataMOdel.title
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}