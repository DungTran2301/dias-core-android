package com.dungtran.android.core.permission

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dungtran.android.core.common.permission.utils.PermissionUtil

internal class PermissionManagerActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        if (PermissionManager.instance == null) {
            finish()
            return
        }

        val isAllPermissionsGranted =
            PermissionManager.instance!!.permissions.all { PermissionUtil.isPermissionGranted(this, it) }
        if (isAllPermissionsGranted) {
            PermissionManager.instance?.permissions?.forEach {
                PermissionManager.instance?.onPermissionStatus(true, it)
            }
            finish()
            return
        }

        ActivityCompat.requestPermissions(
            this,
            PermissionManager.instance!!.permissions.filter { !PermissionUtil.isPermissionGranted(this, it) }
                .toTypedArray(),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            PermissionManager.instance?.permissions?.forEach {
                PermissionManager.instance?.onPermissionStatus(PermissionUtil.isPermissionGranted(this, it), it)
            }
            finish()
            PermissionManager.instance = null
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PermissionManager.instance = null
    }
}