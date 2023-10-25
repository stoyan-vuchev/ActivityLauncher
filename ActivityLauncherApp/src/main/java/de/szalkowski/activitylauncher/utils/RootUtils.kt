package de.szalkowski.activitylauncher.utils

import java.io.File
import java.util.Objects
import java.util.Vector

object RootUtils {
    
    fun hasSU(): Boolean {

        val paths = Vector<File>()
        val dirs = Objects.requireNonNull(System.getenv("PATH")).split(":".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()

        for (dir in dirs) {
            paths.add(File(dir, "su"))
        }

        for (path in paths) {
            if (path.exists() && path.canExecute() && path.isFile) {
                return true
            }
        }

        return false

    }

}