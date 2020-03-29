package com.demo.trendingrepozomato

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.trendingrepozomato.utils.MyApplication
import com.demo.trendingrepozomato.utils.ViewModelFactory
import javax.inject.Inject

/**
 * This class will  provide the common dependency or common method to all the Activities in
 * the application if needed
 */
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).component.inject(this)
    }

}
