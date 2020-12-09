package scripts.constants

import org.tribot.api2007.Skills

enum class AxeType(val lvl: Int) {

    BRONZE(1), IRON(1), STEEL(6), BLACK(11), MITHRIL(21),
    ADAMANT(31), RUNE(41), DRAGON(61), NONE(100);

    fun canUse(): Boolean {
        val wcLvl = Skills.SKILLS.WOODCUTTING.currentLevel
        return lvl <= wcLvl
    }
}