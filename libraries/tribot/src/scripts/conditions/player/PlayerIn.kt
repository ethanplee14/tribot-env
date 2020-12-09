package scripts.conditions.player

import org.tribot.api2007.Player
import org.tribot.api2007.types.RSArea
import scripts.interfaces.Condition

class PlayerIn(private val area: RSArea): Condition({area.contains(Player.getPosition())}) {
    override fun toString() = "PlayerIn<$area>?"
}