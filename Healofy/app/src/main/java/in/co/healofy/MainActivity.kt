package `in`.co.healofy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_rv.*

class MainActivity : AppCompatActivity(), PostListener, View.OnTouchListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var dY: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rv)
        setAdapter()
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
//                        dX = view!!.x - event.rawX
                dY = view!!.y - event.rawY
            }

            MotionEvent.ACTION_MOVE -> {

                Log.e(TAG, "moving: $view")
//                        val newX = event.rawX + dX
                val newY = event.rawY + dY

//                        if ((newX <= 0 || newX >= screenWidth - view!!.width) || (newY <= 0 || newY >= screenHeight - view.height)) {
//                            return true
//                        }

                view!!.animate()
//                            .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start()
            }
            else -> return true
        }

        return true
    }

    override fun onPostClick(string: String) {

        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        showCommentsDialog()

//        var dX: Float = 0.toFloat()
    }

    private fun showCommentsDialog() {

//        alertDialog.findViewById<View>(R.id.d_tv)!!.setOnTouchListener(this)
//        alertDialog.listView.setOnTouchListener()
//        alertDialog.setOnTo

        dialog_cont.visibility = View.VISIBLE
        dialog_cont.setOnClickListener {
            Log.e(TAG, "preventing click on recycler view")
        }

        d_list.setOnTouchListener(this)

        val dummyComments = listOf("Comment1", "Comment2", "Comment3", "Comment4", "Comment5", "Comment6",
            "Comment7", "Comment8", "Comment9", "Comment10", "Comment11", "Comment12", "Comment13", "Comment14",
            "Comment15")

        val inflater = LayoutInflater.from(this)
        for (dummyComment in dummyComments) {

            val itemView = inflater.inflate(R.layout.item_comment_tv, d_list, false)
            itemView.findViewById<TextView>(R.id.d_tv).text = dummyComment
            d_list.addView(itemView)
        }
    }

    private fun setAdapter() {

        val dummyList = listOf("Post1", "Post2", "Post3", "Post4", "Post5", "Post6", "Post7", "Post8",
            "Post9", "Post10", "Post11", "Post12", "Post13", "Post14", "Post15")

        val recyclerView            = rv_root
        recyclerView.layoutManager  = LinearLayoutManager(this)
        recyclerView.adapter        = PostAdapter(dummyList, this)
    }
}
