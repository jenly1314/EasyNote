package com.king.easynote.presentation.navigation

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * 导航路由
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
sealed class NavRoute(val route: String) {

    /**
     * 导航参数命名
     */
    open val arguments: List<NamedNavArgument> = emptyList()

    /**
     * 导航图路由
     * @return 导航图路由；示例：route?key={key}
     */
    fun navGraphRoute(): String {
        if (arguments.isNullOrEmpty()) {
            return route
        }
        val builder = StringBuilder()
        for (arg in arguments!!) {
            builder.append(appendKeyValue(arg.name, "{${arg.name}}", false)).append("&")
        }
        if (builder.isNotEmpty()) {
            builder.deleteCharAt(builder.lastIndex)
        }
        return "$route?$builder"

    }

    /**
     * 导航路由
     * @return 导航图路由；示例：route?key=value
     */
    protected fun navigateRoute(params: Map<String, Any?>? = null): String {
        if (params.isNullOrEmpty()) {
            return route
        }
        val builder = StringBuilder()
        for (param in params) {
            param.value?.let {
                builder.append(appendKeyValue(param.key, it.toString())).append("&")
            }
        }
        if (builder.isNotEmpty()) {
            builder.deleteCharAt(builder.lastIndex)
        }
        return "$route?$builder"
    }

    /**
     * 拼接键值对
     * @param key 键
     * @param value 值
     * @param encode 是否进行 Uri 编码；默认为：true
     * @return 拼接后的键值对；示例：key=value
     */
    private fun appendKeyValue(key: String, value: String, encode: Boolean = true): String {
        return if (encode) "$key=${Uri.encode(value)}" else "$key=$value"
    }

    //--------------------------------------------

    object NoteRoute : NavRoute("NoteScreen") {

        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(NavArgumentKey.NoteId.name) {
                type = NavType.IntType
                defaultValue = NONE_ID
            })

        /**
         * 导航路由；带参数
         */
        fun navigateRoute(noteId: Int?): String {
            return navigateRoute(
                mapOf(NavArgumentKey.NoteId.name to noteId)
            )
        }
    }

    object NoteMain : NavRoute("MoteMain")

    object NoteListRoute : NavRoute("NoteListScreen")

    object DeleteNoteDialogRoute : NavRoute("DeleteNoteDialogRoute") {

        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(NavArgumentKey.NoteId.name) {
                type = NavType.IntType
                defaultValue = NONE_ID
            })

        /**
         * 导航路由；带参数
         */
        fun navigateRoute(noteId: Int?): String {
            return navigateRoute(mapOf(NavArgumentKey.NoteId.name to noteId))
        }
    }

    companion object {
        /**
         * 默认ID
         */
        const val NONE_ID = -1

    }
}