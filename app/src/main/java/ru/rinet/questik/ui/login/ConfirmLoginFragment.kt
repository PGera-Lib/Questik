package ru.rinet.questik.ui.login


import kotlinx.android.synthetic.main.fragment_confirm_login.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.*

class ConfirmLoginFragment : BaseFragment(R.layout.fragment_confirm_login) {

    override fun onStart() {
        super.onStart()
        login_enter_confirm_code.addTextChangedListener(AppTextWatcher {
            val string = login_enter_confirm_code.text.toString()
            if (string.length == 6) {
                verifCode()
            }
        })
    }

    fun verifCode() {
        showToast("Code is good, make some noize!")
        confirm_fab_next.setOnClickListener {
            APP_ACTIVITY.initDrawer()
            replaceFragment(ProjectsFragment())
        }

    }
}

