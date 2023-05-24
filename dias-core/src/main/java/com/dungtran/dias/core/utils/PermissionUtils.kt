package com.dungtran.dias.core.utils

import android.Manifest
import androidx.fragment.app.Fragment


interface PermissionUtils {
    fun hasPermission(
        fragment: Fragment?,
        permission: String
    ): Boolean

    fun shouldShowRequestPermissionRationale(
        fragment: Fragment?,
        permission: String
    ): Boolean

    fun requestPermissions(
        fragment: Fragment?,
        permission: String
    )
}

object AndroidPermission {
    private var STORAGE_PERMISSION_UNDER_STORAGE_SCOPE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    private var WRITE_SETTING_PERMISSION = arrayOf(
        Manifest.permission.WRITE_SETTINGS
    )

    private var STORAGE_PERMISSION_STORAGE_SCOPE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    private var CAMERA_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    //get permission mapping with API Level
    fun getStoragePermissions(): Array<String> {
        return if (AndroidVersionUtils.isAndroidR()) {
            STORAGE_PERMISSION_STORAGE_SCOPE
        } else {
            STORAGE_PERMISSION_UNDER_STORAGE_SCOPE
        }
    }

    fun getWritingPermission(): Array<String> {
        return WRITE_SETTING_PERMISSION
    }

    fun getCameraPermission(): Array<String> {
        return CAMERA_PERMISSIONS
    }
}
