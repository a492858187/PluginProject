package org.tq.pluginproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tq.router.annotations.Destination

@Destination(url = "router://page-kotlin", description = "登陆页面")
class KtMainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}