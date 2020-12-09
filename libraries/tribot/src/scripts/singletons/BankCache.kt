package scripts.singletons

import org.tribot.api2007.Banking
import org.tribot.api2007.types.RSItemDefinition

object BankCache {

    private var cache: MutableMap<Int, Int>? = null

    fun update(): Boolean {
        if (!Banking.isBankScreenOpen() || !Banking.isBankLoaded())
            return false

        cache = mutableMapOf()
        cache?.let { cache ->
            Banking.getAll().forEach { cache[it.id] = it.stack }
        }
        return true
    }

    fun has(id: Int): Boolean {
        update()
        return cache?.containsKey(id) ?: false
    }

    fun has(itemName: String): Boolean {
        update()
        return itemNames().contains(itemName)
    }

    /**
     * @return 0 if not found, -1 if cache not initialized
     */
    fun count(id: Int): Int {
        update()
        return cache?.getOrDefault(id, 0) ?: -1
    }

    fun count(itemName: String): Int {
        update()
        return count(idOf(itemName))
    }

    /**
     * @return -1 if bank not initialized or doesn't have item. Returns id if name
     * is found in bank
     */
    fun idOf(name: String): Int {
        if(!initialized() || !has(name))
            return -1

        val ids = cache().keys.toIntArray()
        val names = ids.map { RSItemDefinition.get(it).name }.toTypedArray()
        return ids[names.indexOf(name)]
    }

    fun cache(): Map<Int, Int> {
        update()
        return cache?.toMap() ?: emptyMap()
    }

    fun itemKeys(): IntArray {
        update()
        return cache?.keys?.toIntArray() ?: intArrayOf()
    }
    fun itemNames(): Array<String> {
        update()
        return cache?.keys?.map { RSItemDefinition.get(it).name }?.toTypedArray() ?: emptyArray()
    }

    fun initialized() = cache != null
}
