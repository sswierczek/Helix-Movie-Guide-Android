package com.androidmess.helix.common.presentation

/**
 * MVP contract for all Views and Presenters.
 */
interface Mvp {

    interface View

    interface Presenter<in VIEW : View> {

        /**
         * View connects to a Presenter. This is done once per View instance.
         */
        fun connect(view: VIEW)

        /**
         * View is visible
         */
        fun visible()

        /**
         * View is invisible
         */
        fun invisible()

        /**
         * View instance died and disconnected.
         */
        fun disconnect()
    }
}