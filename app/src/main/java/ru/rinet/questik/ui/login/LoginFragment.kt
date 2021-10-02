package ru.rinet.questik.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.rinet.questik.R
import ru.rinet.questik.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment) {
   private lateinit var mBinding: LoginFragmentBinding
   private lateinit var mFabNext: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = LoginFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mFabNext = mBinding.loginFabNext
        mFabNext.setOnClickListener{
            sendCode()
        }

    }

    private fun sendCode() {

    }

    override fun onResume() {
        super.onResume()

    }


}