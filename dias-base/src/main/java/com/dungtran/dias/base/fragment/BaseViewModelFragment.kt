package com.dungtran.dias.base.fragment

import androidx.databinding.ViewDataBinding

abstract class BaseViewModelFragment<V : ViewDataBinding> : BaseFragment<V>(){

    override fun performBeforeInitView() {
        initViewModel()
    }

    abstract fun initViewModel()

}