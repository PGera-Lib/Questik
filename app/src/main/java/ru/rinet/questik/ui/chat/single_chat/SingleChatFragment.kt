package ru.rinet.questik.ui.chat.single_chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var mMessagesListener: AppValueEventListener
    private var mListMessages = emptyList<CommonModel>()


    override fun onResume() {
        super.onResume()
        initViewFields()
        println("------------------------------------onResume--------------------------------------------")
        initRecyclerView()
        initInfoToolBar()

    }

    private fun initRecyclerView() {
        println("------------------------------------initRecyclerView--------------------------------------------")
        mRecyclerView = chat_recycler_view
        mAdapter = SingleChatAdapter()
        mRecyclerView.adapter = mAdapter
        mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID).child(contact.id)
        mMessagesListener = AppValueEventListener { dataSnapshot ->
            println("------------------------------------mMessagesListener--------------------------------------------")
            mListMessages = dataSnapshot.children.map {
                it.getCommonModel()
            }
            mAdapter.setList(mListMessages)
            mAdapter.notifyDataSetChanged()
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefMessages.addValueEventListener(mMessagesListener)

    }

    private fun initViewFields() {
        println("------------------------------------initViewFields--------------------------------------------")
        btn_send_message.setOnClickListener {
            val message = chat_input_message.text.toString()
            if (message.isEmpty()) {
                showToast("Сообщение не может быть пустым!")
            } else {
                sendMessage(message, contact.id, TYPE_TEXT) {
                }
                mAdapter.notifyDataSetChanged()
                chat_input_message.setText("")
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
    }

}