package cz.proximitis.sdkSample

import android.app.Application
import cz.proximitis.sdk.ProximitisSDK
import cz.proximitis.sdkSample.ui.main.MainActivity
import cz.proximitis.sdkSample.utils.constant.Constant

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ProximitisSDK.init(this, Constant.Promimitis.SDK_DEMO_KEY, MainActivity::class.java)
        ProximitisSDK.getInstance().deviceUUID()
    }
}