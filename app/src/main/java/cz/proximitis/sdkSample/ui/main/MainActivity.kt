package cz.proximitis.sdkSample.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import cz.proximitis.sdk.ui.detail.DetailFragment
import cz.proximitis.sdk.ui.main.MainFragment
import cz.proximitis.sdkSample.R
import cz.proximitis.sdkSample.ui.base.BaseActivity

class MainActivity : BaseActivity() {

	lateinit var frag: MainFragment

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (
				ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
				&&
				ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
		) {
			ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
		}

		showMainProximitisView()
	}

	override fun onResume() {
		super.onResume()

		intent?.extras?.getString("campaign_id")?.let {
			frag.goTo(DetailFragment.getInstance(campaignId = it))
		}
	}

	override val activityLayout: Int = R.layout.activity_main

	override fun onBackPressed() {
		if (!frag.onBackPressed()) {
			super.onBackPressed()
		}
	}

	private fun showMainProximitisView(campaignId: String? = null) {
		frag = MainFragment.getInstance()

		val fragment = if (!campaignId.isNullOrEmpty()) {
			DetailFragment.getInstance(campaignId)
		} else {
			frag
		}

		val fragmentManager = supportFragmentManager
		val fragmentTransaction = fragmentManager.beginTransaction()

		fragmentManager.popBackStack(fragment.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
		fragmentTransaction.replace(R.id.main_fragment, fragment)
		fragmentTransaction.commit()
	}
}