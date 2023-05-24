package com.dungtran.diascoreandroid

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dungtran.android.core.permission.PermissionManager
import com.dungtran.dias.core.utils.ToastUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ToastUtils.showToast(this, "hello")
        checkCameraPermission()

    }
    private fun checkCameraPermission() {
        PermissionManager(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionManager.PermissionListener {
                override fun onPermissionGranted(permission: String) {
                    ToastUtils.showToast(applicationContext, "granted")
                }

                override fun onPermissionDenied(permission: String) {
                    ToastUtils.showToast(applicationContext, "denied")
                }
            })
            .request()
    }
}