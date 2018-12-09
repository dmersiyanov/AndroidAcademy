package com.dmity.androidacademy.features.newsList.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Categories(val value: String) : Parcelable {

    EMPTY_CATEGORY( ""),
    HOME("Home"),
    OPINION("Opinion"),
    WORLD ("World"),
    NATIONAL("National"),
    POLITICS("Politics"),
    UPSHOT("Upshot"),
    NY_REGION("NY Region"),
    BUSINESS("Business"),
    TECHNOLOGY("Technology"),
    SCIENCE("Science"),
    HEALTH("Health"),
    SPORTS("Sports"),
    ARTS("Arts"),
    BOOKS("Books"),
    MOVIES("Movies"),
    THEATER("Theater"),
    SUNDAY_REVIEW("Sunday Review"),
    FASHION("Fashion"),
    FOOD("Food"),
    TRAVEL("Travel"),
    MAGAZINE("Magazine"),
    REAL_ESTATE("Real Estate"),
    AUTO("Automobiles"),
    OBITUARIES("Obituaries"),
    INSIDER("Insider")

}