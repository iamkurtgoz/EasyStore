package com.iamkurtgoz.easystore

sealed class EasyState {
    data class onDataSaved(var key: String, var value: Any): EasyState()
}