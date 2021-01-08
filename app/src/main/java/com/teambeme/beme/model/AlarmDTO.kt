package com.teambeme.beme.model

data class AlarmDTO(
    var destinationUid : String,
    var userId : String,
    var uid : String,
    var kind : Int,
    var message : String,
    var timestamp: Long
)