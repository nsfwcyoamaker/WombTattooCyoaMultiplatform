package com.nsfwcyoamaker.wombtattoocyoa.state

data class PointsGlobalState(
    val totalPointsPool: PointsPool? = null,
    val designedMarksPointsPools: Map<String, PointsPool> = emptyMap()
)