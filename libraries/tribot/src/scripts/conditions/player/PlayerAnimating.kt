package scripts.conditions.player

import org.tribot.api2007.Player
import scripts.interfaces.Condition

class PlayerAnimating(private vararg val animIDs: Int): Condition({
    animIDs.contains(Player.getAnimation())
}) {
    override fun toString() = "PlayerAnimating<${animIDs.contentToString()}>?"
}