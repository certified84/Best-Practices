package com.certified.datastoreexample.util

import android.widget.Toast
import androidx.fragment.app.Fragment

object Extensions {
    fun Fragment.showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}