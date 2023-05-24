package com.dungtran.dias.base.fragment

import androidx.databinding.ViewDataBinding
import com.dungtran.android.core.dias.base.fragment.BaseFragment

abstract class BaseViewModelFragment<V : ViewDataBinding> : BaseFragment<V>(){

    override fun performBeforeInitView() {
        initViewModel()
    }

    abstract fun initViewModel()

}