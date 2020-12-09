package scripts.utils

import org.tribot.api2007.types.RSTile


fun parseTile(tileStr: String): RSTile {
    val points = tileStr.split(",").map { Integer.parseInt(it.trim()) }.toIntArray()
    return if (points.size == 2)
        RSTile(points[0], points[1])
    else
        RSTile(points[0], points[1], points[2])
}

