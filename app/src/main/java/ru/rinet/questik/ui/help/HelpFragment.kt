package ru.rinet.questik.ui.help

import kotlinx.android.synthetic.main.help_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment

class HelpFragment : BaseFragment(R.layout.help_fragment) {
lateinit var a:String
    lateinit var b:String
     var c:Int = 0
     var d:Int = 0
    override fun onResume() {
        c=3
        d=3
        a=c.toString()
        b=d.toString()

        println("$c + $d = ${c+d}   и $a + $b = ${a+b}")
        txt_string1.text = "$c + $d = ${c+d}   и   $a + $b = ${a+b}"
        super.onResume()
        
    }



}