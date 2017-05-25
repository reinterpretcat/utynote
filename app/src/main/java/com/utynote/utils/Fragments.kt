package com.utynote.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.utynote.R

class Fragments(private val fragmentManager: FragmentManager) {

    fun <T : Fragment> find(tag: String, type: Class<T>): T {
        return type.cast(checkNotNull(fragmentManager.findFragmentByTag(tag)))
    }

    fun <T : Fragment> addToContent(tag: String, factory: () -> T): Fragments {
        add(tag, R.id.main_content, factory)
        return this
    }

    fun <T : Fragment> addToPanel(tag: String, factory: () -> T): Fragments {
        add(tag, R.id.panel_content, factory)
        return this
    }

    fun <T : Fragment> replaceInPanel(tag: String, factory: () -> T): Fragments {
        fragmentManager
                .beginTransaction()
                .replace(R.id.panel_content, get(tag, factory), tag)
                .commit()

        return this
    }

    fun isAttached(tag: String): Boolean {
        val fragment = fragmentManager.findFragmentByTag(tag)
        return fragment != null && !fragment.isDetached
    }

    operator fun get(tag: String, factory: () -> Fragment): Fragment {
        val fragment = fragmentManager.findFragmentByTag(tag)
        return fragment ?: factory()
    }

    private fun <T : Fragment> add(tag: String, @IdRes containerId: Int, factory: () -> T): Fragments {
        fragmentManager
                .beginTransaction()
                .add(containerId, get(tag, factory), tag)
                .commit()

        return this
    }
}
