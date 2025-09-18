package com.example.tpandroid.common

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel

public open class ENIViewModel(application: Application) : AndroidViewModel(application) {

    fun getString(@StringRes resId: Int) : String {
        return getApplication<Application>().getString(resId);
    }
}