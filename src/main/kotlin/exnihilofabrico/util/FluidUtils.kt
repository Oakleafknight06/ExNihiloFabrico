package exnihilofabrico.util

import exnihilofabrico.impl.BucketFluidAccessor
import exnihilofabrico.modules.fluid.FluidInstance
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

fun getID(fluidInstance: FluidInstance) = fluidInstance.fluid
fun Fluid.getID() = Registry.FLUID.getId(this)
fun FluidBlock.getDefaultFluidState() = this.getFluidState(this.defaultState)
fun FluidBlock.getFluid() = this.getDefaultFluidState().fluid

/**
 * Return the fluid represented by an item, or null if it does not represent a fluid
 */
fun Item.maybeGetFluid(): Fluid? {
    return when(this) {
        is BucketFluidAccessor -> this.getFluid()
        else -> null
    }
}