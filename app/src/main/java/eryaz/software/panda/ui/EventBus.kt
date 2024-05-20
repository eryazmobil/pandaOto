package eryaz.software.panda.ui

import eryaz.software.panda.util.SingleLiveEvent

object EventBus {
    val navigateToSplash = SingleLiveEvent<Boolean>()
}
