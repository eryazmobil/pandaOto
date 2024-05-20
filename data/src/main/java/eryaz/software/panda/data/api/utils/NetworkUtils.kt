package eryaz.software.panda.data.api.utils

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import eryaz.software.panda.data.BuildConfig

object NetworkUtils {

    private var startOfLocalHostIp = "10.10.20."

    private fun getWifiIpAddress(context: Context): String {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo? = wifiManager.connectionInfo

        val ipAddress = wifiInfo?.ipAddress ?: 0

        return (ipAddress and 0xFF).toString() + "." +
                (ipAddress shr 8 and 0xFF) + "." +
                (ipAddress shr 16 and 0xFF) + "." +
                (ipAddress shr 24 and 0xFF)

    }

    fun getIpAddressTypeOutOrIn(context: Context): String {

        val ipAddress = getWifiIpAddress(context)
        println(ipAddress)
        return if (ipAddress.contains(startOfLocalHostIp)) {
            BuildConfig.BASE_URL
        } else {
            BuildConfig.BASE_OUT_URL
        }
    }

}