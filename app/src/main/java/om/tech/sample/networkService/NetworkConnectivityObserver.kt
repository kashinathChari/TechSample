@file:Suppress("DEPRECATION")

package com.designway.parko.networkService

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver (private val context: Context): LiveData<Boolean>() {
       private val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback:ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when{
            Build.VERSION.SDK_INT>=Build.VERSION_CODES.N->{
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallBack())
            }
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP->{
                lollipopNetworkRequest()
            }
            else ->{
                context.registerReceiver(
                    networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            connectivityManager.unregisterNetworkCallback(connectivityManagerCallBack())
        }
        else
        {
            context.unregisterReceiver(networkReceiver)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest(){
val requestBuilder=NetworkRequest.Builder()
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),connectivityManagerCallBack()
        )
    }

    private fun connectivityManagerCallBack():ConnectivityManager.NetworkCallback{
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            networkCallback=object: ConnectivityManager.NetworkCallback(){
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return networkCallback
        }else{
            throw IllegalAccessError("Error")
        }
    }

    private val networkReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }
    }
    private fun updateConnection(){
         val activeNetwork: NetworkInfo?=connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected==true)
    }
//    override fun observe(): Flow<ConnectivityObserver.Status> {
//        return callbackFlow { val callBack= object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                launch { send(ConnectivityObserver.Status.Available) }
//            }
//
//            override fun onUnavailable() {
//                super.onUnavailable()
//                launch { send(ConnectivityObserver.Status.Unavailable) }
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                launch { send(ConnectivityObserver.Status.Lost) }
//            }
//
//            override fun onLosing(network: Network, maxMsToLive: Int) {
//                super.onLosing(network, maxMsToLive)
//                launch { send(ConnectivityObserver.Status.Losing) }
//            }
//        }
//            val networkRequest = NetworkRequest.Builder()
//                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//                .build()
//            connectivityManager.requestNetwork(networkRequest,callBack)
////            connectivityManager.registerDefaultNetworkCallback(callBack)
//                awaitClose{
//                    connectivityManager.unregisterNetworkCallback(callBack)
//                }
//
//        }.distinctUntilChanged()
//    }
//
//

}