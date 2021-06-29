import android.content.Context
import com.MyApplication
import com.dev.BuildConfig
import com.facebook.flipper.android.utils.FlipperUtils
import okhttp3.Interceptor
import com.facebook.soloader.SoLoader
import  com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
object FlipperManager {

    fun initNetworkDebug(appContext: Context) {
        // Flipper
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(appContext)) {
            SoLoader.init(appContext, false);
            val client = AndroidFlipperClient.getInstance(appContext)
            client.addPlugin(getFlipperNetworkClient())
            client.addPlugin(InspectorFlipperPlugin(appContext, DescriptorMapping.withDefaults()))
            client.start()
        }
    }

    // Flipper Integration
    private var networkFlipperPlugin: NetworkFlipperPlugin? = null

    private fun getFlipperNetworkClient(): NetworkFlipperPlugin {
        if (networkFlipperPlugin == null) {
            networkFlipperPlugin = NetworkFlipperPlugin()
        }
        return networkFlipperPlugin!!
    }

    @JvmStatic
    fun getFlipperInterceptor(): Interceptor? {
        //return null
        return com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor(getFlipperNetworkClient())
    }

}