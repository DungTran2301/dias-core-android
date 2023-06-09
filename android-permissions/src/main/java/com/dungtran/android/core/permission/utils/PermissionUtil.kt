package com.dungtran.android.core.common.permission.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


object PermissionUtil {

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}