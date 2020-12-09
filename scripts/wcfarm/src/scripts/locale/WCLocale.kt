package scripts.locale

import org.tribot.api2007.types.RSArea
import org.tribot.api2007.types.RSTile
import scripts.dax_api.api_lib.json.JsonObject
import scripts.utils.parseTile

class WCLocale(private val locale: JsonObject) {

    fun destTile(): RSTile {
        val tileStr = locale["destTile"]?.asString()
        return when {
            tileStr != null -> parseTile(tileStr)
            locale["treeArea"].isObject -> parseTile(locale["treeArea"].asObject()["center"].asString())
            else -> treeArea().randomTile
        }
    }

    fun treeArea(): RSArea {
        val treeArea = locale["treeArea"]

        return if(treeArea.isObject) {
            val center = treeArea.asObject()["center"].asString()
            RSArea(parseTile(center), treeArea.asObject()["radius"]?.asInt() ?: 3)
        } else if (treeArea.isArray) {
            val tileArr = treeArea.asArray().map { parseTile(it.asString()) }.toTypedArray()
            if (tileArr.size == 2)
                RSArea(tileArr[0], tileArr[1])
            else
                RSArea(tileArr)
        } else error("Tree area configuration is not properly defined: $locale")
    }

    fun treeNames() = locale["treeNames"].asArray().map { it.asString() }.toTypedArray()

    fun bankTile(): RSTile? {
        val tileJsonVal = locale["bankTile"] ?: return null
        return parseTile(tileJsonVal.asString())
    }

    fun depositBox() = locale["depositBox"]?.asBoolean() ?: false
}