package scripts

import org.tribot.api.util.abc.ABCUtil
import org.tribot.script.Script
import org.tribot.script.ScriptManifest
import org.tribot.util.Util
import scripts.dax_api.api_lib.DaxWalker
import scripts.locale.WCLocaleLoader
import scripts.singletons.Log


@ScriptManifest(authors = ["Ethan"], category = "BotFarm", name = "WCFarm")
class Main: Script() {

    private val baseConfigs = Util.getWorkingDirectory().path + "/plee"

    override fun run() {
        Log.state = Log.State.DEBUG
        initDax()

        val localeLoader = WCLocaleLoader("${baseConfigs}/wc")
        val locale = localeLoader.load("varWOaks")
        val wc = Woodcut(locale).build()


        while(true) {
            wc.run()
            sleep(250)
        }
    }

    private fun initDax() {
        DaxWalker.setCredentials(DaxConfigs("${baseConfigs}/configs/daxkeys.ini"))
        DaxWalker.setGlobalWalkingCondition(DaxRunCondition(ABCUtil()))
    }
}