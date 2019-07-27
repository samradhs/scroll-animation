package `in`.co.healofy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CommentAdapter(private val items: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val commentView = inflater.inflate(R.layout.item_tv, parent, false)
        return CommentVH(commentView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val commentStr         = items[position]
        val commentVH     = holder as CommentVH
        commentVH.commentTv.text      = commentStr
    }

    override fun getItemCount(): Int {

        return items.size
    }
}

class CommentVH(view: View): RecyclerView.ViewHolder(view) {

    val commentTv: TextView = view.findViewById(R.id.tv_text)
}