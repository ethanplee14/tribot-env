package scripts.locale

import scripts.dax_api.api_lib.json.Json
import java.io.File

class WCLocaleLoader(settingsPath: String) {

    private val localeObj = Json.parse(File("$settingsPath/locales.json").bufferedReader()).asObject()

    fun locales(): Array<String> = localeObj.names().toTypedArray()

    fun loadAll(): List<WCLocale> {
        return locales().map { load(it) }
    }

    fun load(localeName: String): WCLocale {
        val locale = if (localeName.isEmpty())
            localeObj[localeObj.names().random()]
        else localeObj[localeName]
        return WCLocale(locale.asObject())
    }
}