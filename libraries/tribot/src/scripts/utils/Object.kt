package scripts.utils

import org.tribot.api2007.Objects
import org.tribot.api2007.types.RSObject

fun nearestObj(dist: Int, vararg objName: String) = Objects.findNearest(dist, *objName).firstOrNull()

fun nearestObj(dist: Int, filter: (RSObject) -> Boolean) = Objects.findNearest(dist, filter).firstOrNull()
