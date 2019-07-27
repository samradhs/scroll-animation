package `in`.co.healofy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PostAdapter(private val items: List<String>,
                  private val postListener: PostListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val postView = inflater.inflate(R.layout.item_post, parent, false)
        return PostVH(postView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val postStr         = items[position]
        val postVH         = holder as PostVH
        postVH.postTv.text         = postStr
        postVH.itemView.setOnClickListener {
            postListener.onPostClick(postStr)
        }
    }

    override fun getItemCount(): Int {

        return items.size
    }
}

class PostVH(view: View): RecyclerView.ViewHolder(view) {

    val postTv: TextView = view.findViewById(R.id.post_text)
}

interface PostListener {

    fun onPostClick(string: String)
}