package scripts

import org.tribot.api.util.abc.ABCUtil
import org.tribot.api2007.Game
import org.tribot.api2007.Options
import scripts.dax_api.walker_engine.WalkingCondition

class DaxRunCondition(private val abc: ABCUtil): WalkingCondition {

    private var run = abc.generateRunActivation()

    override fun action(): WalkingCondition.State {
        if(Game.getRunEnergy() >= run && !Game.isRunOn()) {
            Options.setRunEnabled(true)
            run = abc.generateRunActivation()
        }
        return WalkingCondition.State.CONTINUE_WALKER
    }
}