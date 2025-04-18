package eryaz.software.panda.util

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.Locale

class LocaleWrapper(base: Context) : ContextWrapper(base) {

    companion object {
        fun updateLocale(c: Context, localeToSwitchTo: Locale): ContextWrapper {
            var context = c
            val resources = context.resources
            val configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = localeToSwitchTo
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return LocaleWrapper(context)
        }
    }
}