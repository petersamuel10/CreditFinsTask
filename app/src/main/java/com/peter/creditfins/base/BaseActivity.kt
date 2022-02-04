package com.peter.creditfins.base

import androidx.appcompat.app.AppCompatActivity
import com.peter.creditfins.util.ProgressDialog

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog = ProgressDialog(this)
}