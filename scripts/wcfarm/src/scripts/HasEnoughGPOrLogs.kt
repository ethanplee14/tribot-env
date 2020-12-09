package scripts

import scripts.interfaces.Condition
import scripts.utils.countAll
import scripts.wastedbro.api.rsitem_services.GrandExchange

class HasEnoughGPOrLogs(requiredGP: Int): Condition({
    val logsId = 1511
    GrandExchange.tryGetPrice(logsId)
    val logsCount = countAll(logsId, true) 
    true
})