package com.cleo.todoapp.data

data class ActivityModel (
    var image:Int = 0,
    var activityName:String = "",
    var activityTime:String = "",
    var activityDescription:String = "",
    var activityStatus: ActivityStatus = ActivityStatus.Completed
        )