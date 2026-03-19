package com.tru.core.connectivity.connectivity_manager

import android.net.Network

interface NetworkAwareComponent {

    fun onNetworkAvailable(network: Network)

    fun onNetworkLost(network: Network)
}
