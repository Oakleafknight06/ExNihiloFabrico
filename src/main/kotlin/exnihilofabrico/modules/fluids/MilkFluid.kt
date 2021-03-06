package exnihilofabrico.modules.fluids

import exnihilofabrico.modules.ModFluids
import exnihilofabrico.modules.base.AbstractFluid
import exnihilofabrico.modules.base.BaseFluidBlock
import exnihilofabrico.modules.base.FluidSettings
import net.fabricmc.fabric.api.tag.TagRegistry
import net.minecraft.fluid.Fluid
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import java.util.function.Supplier

class MilkFluid(isStill: Boolean): AbstractFluid(isStill, fluidSettings, Supplier { block }, Supplier { bucket }, Supplier { flowing }, Supplier { still }) {
    override fun matchesType(fluid: Fluid) = fluid == still || fluid == flowing
    companion object {
        val fluidSettings = FluidSettings("milk")
        val still = MilkFluid(true)
        val flowing = MilkFluid(false)
        val bucket = Items.MILK_BUCKET
        val block = BaseFluidBlock(still, ModFluids.blockSettings)
        val tag = TagRegistry.fluid(Identifier("c:milk"))
    }
}