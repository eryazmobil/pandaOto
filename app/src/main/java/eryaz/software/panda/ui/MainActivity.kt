package eryaz.software.panda.ui

import android.os.Bundle
import eryaz.software.panda.databinding.ActivityMainBinding
import eryaz.software.panda.ui.base.BaseActivity
import eryaz.software.panda.util.KeyboardEventListener
import eryaz.software.panda.util.StatusBarUtil

class MainActivity : BaseActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucent(this)
        setContentView(binding.root)
        keyboardListener()
    }

    private fun keyboardListener() {
        KeyboardEventListener(
            activity = this,
            root = binding.root,
            resizeableView = binding.navHost,
            bottomView = null
        )
    }

    override fun getContentView() = binding.root
}
