package ru.rinet.questik.ui.chat.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_item.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.utils.CURRENT_UID
import ru.rinet.questik.utils.asTime

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCash = mutableListOf<CommonModel>()
    private lateinit var mDiffResult: DiffUtil.DiffResult


    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blocUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blocReceiviedMessage: ConstraintLayout = view.block_received_message
        val chatReceiviedMessage: TextView = view.chat_received_message
        val chatReceiviedTime: TextView = view.chat_received_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)


    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (mListMessagesCash[position].from == CURRENT_UID) {
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceiviedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCash[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCash[position].timestamp.toString().asTime()
        } else {
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceiviedMessage.visibility = View.VISIBLE
            holder.chatReceiviedMessage.text = mListMessagesCash[position].text
            holder.chatReceiviedTime.text =
                mListMessagesCash[position].timestamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = mListMessagesCash.size

    fun addItemToBottom(item: CommonModel, isSuccess: () -> Unit) {
        if (!mListMessagesCash.contains(item)) {
            mListMessagesCash.add(item)
            notifyItemInserted(mListMessagesCash.size)
        }
        isSuccess()
    }
    fun addItemToTop(item: CommonModel, isSuccess: () -> Unit) {
        if (!mListMessagesCash.contains(item)) {
            mListMessagesCash.add(item)
            mListMessagesCash.sortBy { it.timestamp.toString() }
            notifyItemInserted(0)
        }
        isSuccess()
    }


}


