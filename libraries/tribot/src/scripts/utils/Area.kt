package scripts.utils

import org.tribot.api2007.types.RSArea
import org.tribot.api2007.types.RSTile

fun rsArea(x: Int, y: Int, x2: Int, y2: Int) = RSArea(RSTile(x, y), RSTile(x2, y2))

fun rsArea(x: Int, y: Int, plane: Int, x2: Int, y2: Int, plane2: Int)
        = RSArea(RSTile(x, y, plane), RSTile(x2, y2, plane2))

fun rsArea(x: Int, y: Int, rad: Int) = RSArea(RSTile(x, y), rad)