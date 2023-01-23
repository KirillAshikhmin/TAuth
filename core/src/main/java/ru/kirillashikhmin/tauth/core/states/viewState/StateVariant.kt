package ru.kirillashikhmin.tauth.core.states.viewState

@JvmInline
value class StateVariant(val state : String) {
    //Predefined states. You can use this or create another state variant
    companion object {
        val Clean = StateVariant("Clean") //Default state that created as initial value
        val Loading = StateVariant("Loading")
        val Normal = StateVariant("Normal")
        val Error = StateVariant("Error")
        val Extra = StateVariant("Extra") // Additional normal state. Like Normal2
        val NoData = StateVariant("NoData")
        val NoInternet = StateVariant("NoInternet")
        val Empty = StateVariant("Empty")
    }
}
