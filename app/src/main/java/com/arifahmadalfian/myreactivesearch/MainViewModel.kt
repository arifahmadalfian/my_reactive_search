package com.arifahmadalfian.myreactivesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arifahmadalfian.myreactivesearch.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel: ViewModel() {
    private val accessToken = "pk.eyJ1IjoiYXJpZmFobWFkYWxmaWFuIiwiYSI6ImNrbDdtNnkwMTAzcW4ydWxiOWVkbmhiMWgifQ.Is9HRuKmKGXgW0l5AiWGlw"
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
            .debounce(300)
            .distinctUntilChanged()
            .filter {
                it.trim().isNotEmpty()
            }
            .mapLatest {
                ApiConfig.provideApiService().getCountry(it, accessToken).features
            }
            .asLiveData()
}