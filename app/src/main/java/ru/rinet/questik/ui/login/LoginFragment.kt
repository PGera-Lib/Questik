package ru.rinet.questik.ui.login


import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.login_fragment.*
import ru.rinet.questik.MainActivity
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.AUTH
import ru.rinet.questik.utils.replaceFragment
import ru.rinet.questik.utils.showToast
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful){
                        showToast("Успех епт!")
                        replaceFragment(ProjectsFragment())
                    } else {
                        showToast(it.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
               replaceFragment(ConfirmLoginFragment(mPhoneNumber, id))
            }
        }
        login_fab_next.setOnClickListener { sendCode() }

    }

    private fun sendCode() {
        val string = login_enter_phone_number.text.toString()
        if (string.length == 10) {
            authUser()
           // replaceFragment(ConfirmLoginFragment(mPhoneNumber, id))
        } else {
            showToast("Номер телефона не может быть пуст, либо введен с ошибками")
        }
    }

    private fun authUser() {
        mPhoneNumber = "+7"+login_enter_phone_number.text.toString()

        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions
                .newBuilder(FirebaseAuth.getInstance())
                .setActivity(APP_ACTIVITY)
                .setPhoneNumber(mPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(mCallback)
                .build())
    }

}