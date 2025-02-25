package com.example.monitor.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.monitor.data.Website

class MonitorViewModel : ViewModel() {
    private val _websites = MutableStateFlow<List<Website>>(emptyList())
    val websites = _websites.asStateFlow()

    fun addWebsite(url: String, interval: Int) {
        _websites.value = _websites.value + Website(url, interval)
    }

    fun removeWebsite(website: Website) {
        _websites.value = _websites.value - website
    }
}
