package ru.rinet.questik.ui.chat.single_chat

import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_single_chat.*
import kotlinx.android.synthetic.main.toolbar_info.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.models.UserModel
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.utils.*

class SingleChatFragment(private val contact: CommonModel) :
    BaseChangeFragment(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolbar: AppValueEventListener
    private lateinit var mReceivingUser: UserModel
    private lateinit var mToolbarInfo: View
    private lateinit var mRefUser: DatabaseReference

    private lateinit var mRefMessages: DatabaseReference
    private lateinit var mAdapter: SingleChatAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMessagesListener: AppChildEventListener
    private var mCountMessages = 15
    private var mIsScrooling = false
    private var mSmoothScrollToPosition = true

    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mLayoutManager: LinearLayoutManager
//    private var mListListeners = mutableListOf<AppChildEventListener>()


    override fun onResume() {
        super.onResume()
        initViewFields()
        println("------------------------------------onResume--------------------------------------------")
        initRecyclerView()
        initInfoToolBar()

    }

    private fun initRecyclerView() {
        println("------------------------------------initRecyclerView--------------------------------------------")
        mSwipeRefreshLayout = chat_swipe_layout
        mLayoutManager = LinearLayoutManager(this.context)
        mRecyclerView = chat_recycler_view
        mAdapter = SingleChatAdapter()

        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.isNestedScrollingEnabled = false

        mRecyclerView.layoutManager = mLayoutManager

        mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID).child(contact.id)

        chat_input_message.setOnClickListener { mRecyclerView.smoothScrollToPosition(mAdapter.itemCount) }

        mMessagesListener = AppChildEventListener {
            println("------------------------------------mMessagesListener--------------------------------------------")
            val message = it.getCommonModel()
            if (mSmoothScrollToPosition) {
                mAdapter.addItemToBottom(message) {
                    mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
                }

            } else {
                mAdapter.addItemToTop(message) {
                    mSwipeRefreshLayout.isRefreshing = false
                }
            }
        }
        mRefMessages.limitToLast(mCountMessages).addChildEventListener(mMessagesListener)
//        mListListeners.add(mMessagesListener)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    mIsScrooling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mIsScrooling && dy < 0 && mLayoutManager.findFirstVisibleItemPosition() <= 3) {
                    updateData()
                }
            }
        })

        mSwipeRefreshLayout.setOnRefreshListener {
            updateData()
        }
    }

    private fun updateData() {
        mSmoothScrollToPosition = false
        mIsScrooling = false
        mCountMessages += 10
        mRefMessages.removeEventListener(mMessagesListener)
        mRefMessages.limitToLast(mCountMessages).addChildEventListener(mMessagesListener)
//                mListListeners.add(mMessagesListener)
    }

    private fun initViewFields() {
        println("------------------------------------initViewFields--------------------------------------------")
        btn_send_message.setOnClickListener {
            mSmoothScrollToPosition = true
            val message = chat_input_message.text.toString()
            if (message.isEmpty()) {
                showToast("Сообщение не может быть пустым!")
            } else {
                sendMessage(message, contact.id, TYPE_TEXT) {
                    println("------------------------------------sendMessage--------------------------------------------")
                    chat_input_message.setText("")
                }
                // mAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun initInfoToolBar() {
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initToolbar()
        }
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
    }

    private fun initToolbar() {
        mToolbarInfo = APP_ACTIVITY.mToolbar.toolbar_info
        mToolbarInfo.visibility = View.VISIBLE

        if (mReceivingUser.fullname.isEmpty()) {
            mToolbarInfo.single_chat_fragment_user_fullname.text = contact.fullname
        } else {
            mToolbarInfo.single_chat_fragment_user_fullname.text = mReceivingUser.fullname
        }
        mToolbarInfo.single_chat_fragment_user_status.text = mReceivingUser.status
        mToolbarInfo.single_chat_fragment_user_photo.downloadAndSetImage(mReceivingUser.photoUrl)
    }

    override fun onPause() {
        super.onPause()
        println("------------------------------------onPause--------------------------------------------")
        mToolbarInfo.visibility = View.GONE
        mRefUser.removeEventListener(mListenerInfoToolbar)
        mRefMessages.removeEventListener(mMessagesListener)
/*        mListListeners.forEach {
            mRefMessages.removeEventListener(it)
        }*/

        println()
    }

}