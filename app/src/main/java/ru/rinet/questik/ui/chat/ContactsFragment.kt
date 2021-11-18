package ru.rinet.questik.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.contacts_recycler_item.view.*
import kotlinx.android.synthetic.main.fragment_contacts.*
import ru.rinet.questik.R
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.ui.chat.single_chat.SingleChatFragment
import ru.rinet.questik.utils.*

class ContactsFragment : BaseChangeFragment(R.layout.fragment_contacts) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter:FirebaseRecyclerAdapter<CommonModel, ContactsHolder>
    private lateinit var mRefContacts:DatabaseReference
    private lateinit var mRefUsers:DatabaseReference
    private lateinit var mRefUsersListener: AppValueEventListener
    private var mapListeners = hashMapOf<DatabaseReference, AppValueEventListener>()


    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакаты"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = contacts_fragment_recyclerView
        mRefContacts = REF_DATABASE_ROOT.child(NODE_PHONE_CONTACTS).child(CURRENT_UID)


        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(mRefContacts, CommonModel::class.java)
            .build()
        mAdapter = object: FirebaseRecyclerAdapter<CommonModel, ContactsHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.contacts_recycler_item, parent, false)
                return ContactsHolder(view)
            }

            override fun onBindViewHolder(
                holder: ContactsHolder,
                position: Int,
                model: CommonModel
            ) {
                mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)

                mRefUsersListener = AppValueEventListener {
                    val contact = it.getCommonModel()
                    if (contact.fullname.isEmpty()){
                        holder.contact_fullname.text = model.fullname
                    } else {
                        holder.contact_fullname.text = contact.fullname
                    }
                    holder.status.text = contact.status
                    holder.photo.downloadAndSetImage(contact.photoUrl)
                    holder.itemView.setOnClickListener { replaceFragment(SingleChatFragment(contact)) }
                }
                mRefUsers.addValueEventListener(mRefUsersListener)
                mapListeners[mRefUsers] = mRefUsersListener
            }

        }

        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
        mapListeners.forEach {
            it.key.removeEventListener(it.value)
        }
    }

    class ContactsHolder(view: View):RecyclerView.ViewHolder(view){
        val contact_fullname:TextView = view.contacts_fragment_user_fullname
        val status:TextView = view.contacts_fragment_user_status
        val photo:CircleImageView = view.contacts_fragment_user_photo
    }
}


