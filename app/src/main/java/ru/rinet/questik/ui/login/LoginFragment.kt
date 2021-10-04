package ru.rinet.questik.ui.login


import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_confirm_login.*
import kotlinx.android.synthetic.main.login_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.AppTextWatcher
import ru.rinet.questik.utils.replaceFragment
import ru.rinet.questik.utils.showToast

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    override fun onStart() {
        super.onStart()
        login_fab_next.setOnClickListener { sendCode() }

    }
    private fun sendCode() {
        val string = login_enter_phone_number.text.toString()
        if (string.length==10) {
            replaceFragment(ConfirmLoginFragment())
        } else {
            showToast("Номер телефона не может быть пуст, либо введен с ошибками")
        }
    }

}