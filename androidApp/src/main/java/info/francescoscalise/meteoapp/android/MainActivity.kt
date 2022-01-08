package info.francescoscalise.meteoapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import info.francescoscalise.meteoapp.AirVisualSDK
import info.francescoscalise.meteoapp.entity.Country
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    private val sdk = AirVisualSDK()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScope.launch {
            kotlin.runCatching {
                sdk.getAllCountries()
            }.onSuccess {
                print(it.count())
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
