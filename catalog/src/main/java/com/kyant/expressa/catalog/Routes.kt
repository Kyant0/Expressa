package com.kyant.expressa.catalog

data object Category

sealed interface Styles {
    data object ColorSchemes : Styles
    data object Elevation : Styles
    data object MotionSchemes : Styles
    data object Shapes : Styles
    data object Typography : Styles
}

sealed interface Components {
    data object Buttons : Components
    data object IconButtons : Components
}
