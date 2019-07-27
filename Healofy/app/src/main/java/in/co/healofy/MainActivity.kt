package `in`.co.healofy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_rv.*


class MainActivity : AppCompatActivity(), PostListener, View.OnTouchListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var rawStartY: Float = 0.toFloat()
    private var startY: Float   = 0.toFloat()
    private var dY: Float       = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rv)
        setAdapter()
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {

                startY = view!!.y
                rawStartY = event.rawY
                dY = view.y - event.rawY
                return true
            }

            MotionEvent.ACTION_MOVE -> {

                if (event.rawY < rawStartY) {
                    Log.e(TAG, "moving down")

                    if (!d_list.canScrollVertically(1)) {
                        val newY = event.rawY + dY

                        view!!.animate()
                            .y(newY)
                            .setDuration(0)
                            .start()
                        return true
                    } else {
                        dY = view!!.y - event.rawY
                    }

                } else {
                    // moving down
                    Log.e(TAG, "moving up")

                    if (!d_list.canScrollVertically(-1)) {
                        val newY = event.rawY + dY

                        view!!.animate()
                            .y(newY)
                            .setDuration(0)
                            .start()
                        return true
                    } else {
                        dY = view!!.y - event.rawY
                    }
                }
                return false
            }

            MotionEvent.ACTION_UP -> {
                val endY = view!!.y
                var diff = endY - startY
                if (diff < 0F) diff *= -1

                if (diff >= (getScreenHeight() / 2).toFloat()) {
                    hideDialog()
                } else {
                    moveDialogToTop()
                }

                return false
            }

            else -> return true
        }
    }

    override fun onBackPressed() {

        if (dialog_cont.visibility == View.VISIBLE) {
            hideDialog()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPostClick(string: String) {

        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        showCommentsDialog()
    }

    private fun showCommentsDialog() {

        dialog_cont.visibility = View.VISIBLE
        dialog_cont.setOnClickListener {
            Log.e(TAG, "preventing click on recycler view")
        }

        d_list.setOnTouchListener(this)

        val dummyComments = listOf("Comment1", "Comment2", "Comment3", "Comment4", "Comment5", "Comment6",
            "Comment7", "Comment8", "Comment9", "Comment10", "Comment11", "Comment12", "Comment13", "Comment14",
            "Comment15")

        val recyclerView= d_list
        recyclerView.layoutManager   = LinearLayoutManager(this)
        recyclerView.adapter         = CommentAdapter(dummyComments)
    }

    private fun setAdapter() {

        val dummyList = listOf("Post1", "Post2", "Post3", "Post4", "Post5", "Post6", "Post7", "Post8",
            "Post9", "Post10", "Post11", "Post12", "Post13", "Post14", "Post15")

        val recyclerView            = rv_root
        recyclerView.layoutManager  = LinearLayoutManager(this)
        recyclerView.adapter        = PostAdapter(dummyList, this)
    }

    private fun getScreenHeight(): Int {

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    private fun hideDialog() {

        d_list.animate()
            .y(0F)
            .setDuration(100)
            .start()
        dialog_cont.visibility = View.GONE
    }

    private fun moveDialogToTop() {

        Log.e(TAG, "moving dialog to top")
        d_list.animate()
            .y(0F)
            .setDuration(100)
            .start()
    }
}
