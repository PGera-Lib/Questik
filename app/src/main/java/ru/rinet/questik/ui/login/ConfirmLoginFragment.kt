package ru.rinet.questik.ui.login


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_confirm_login.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.*

class ConfirmLoginFragment(val mPhoneNumber: String, val id: String) : BaseFragment(R.layout.fragment_confirm_login) {


    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = mPhoneNumber
        login_enter_confirm_code.addTextChangedListener(AppTextWatcher {
            val string = login_enter_confirm_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }

   private fun enterCode() {
       val code =  login_enter_confirm_code.text.toString()
       val credential = PhoneAuthProvider.getCredential(id, code)
       AUTH.signInWithCredential(credential).addOnCompleteListener {
           if (it.isSuccessful){
                val uid = AUTH.currentUser?.uid.toString()
                val dataMap = mutableMapOf<String, Any>()
               dataMap[CHILD_ID] = uid
               dataMap[CHILD_PHONE] = mPhoneNumber
               dataMap[CHILD_USERNAME] = uid

               REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap).addOnCompleteListener {it2 ->
                   if(it2.isSuccessful) {
                       showToast("Успех епт!")
                       restartActivity()
                   } else {
                       showToast(it2.exception?.message.toString())
                   }
               }



           } else {
               showToast(it.exception?.message.toString())
           }
       }
/*        showToast("Code is good, make some noize!")
        confirm_fab_next.setOnClickListener {
            APP_ACTIVITY.initDrawer()
            replaceFragment(ProjectsFragment())
        }*/

    }
}

