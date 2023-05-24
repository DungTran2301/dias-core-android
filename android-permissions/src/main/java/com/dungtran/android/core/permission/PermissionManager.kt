package com.dungtran.android.core.permission


import android.content.Context
import android.content.Intent

class PermissionManager(private val context: Context) {

    companion object {
        internal var instance: PermissionManager? = null

    }

    private var listener: PermissionListener? = null
    internal var permissions = mutableListOf<String>()

    fun withPermission(permission: String) = this.apply {
        permissions.clear()
        permissions.add(permission)
    }

    fun withListener(listener: PermissionListener) = this.apply {
        this.listener = listener
    }

    fun request() {
        context.startActivity(
            Intent(context, PermissionManagerActivity::class.java)
        )
        instance = this
    }

    internal fun onPermissionStatus(isGranted: Boolean, permission: String) {
        if (isGranted) {
            listener?.onPermissionGranted(permission)
        } else {
            listener?.onPermissionDenied(permission)
        }
    }

    interface PermissionListener {
        fun onPermissionGranted(permission: String)
        fun onPermissionDenied(permission: String)
    }
}