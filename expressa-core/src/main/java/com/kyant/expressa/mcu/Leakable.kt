package com.kyant.expressa.mcu

@LeakableObject
interface Leakable {

    fun isFreed(): Boolean

    fun free()
}
