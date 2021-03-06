package exnihilofabrico.modules.farming

import exnihilofabrico.modules.base.BaseItem
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult

open class PlantableItem(val plants: List<BlockState>, settings: Settings): BaseItem(settings) {

    constructor(plant: Block, settings: Settings): this(listOf(plant.defaultState), settings)

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if(context.world.isClient)
            return ActionResult.SUCCESS
        val plantPos = context.blockPos.offset(context.side)
        for (plant in plants.shuffled()) {
            if(placementCheck(context) && plant.canPlaceAt(context.world, plantPos)){
                context.world.setBlockState(plantPos, plant)
                if(context.player?.isCreative == false)
                    context.stack.decrement(1)
                return ActionResult.SUCCESS
            }
        }
        return super.useOnBlock(context)
    }

    open fun placementCheck(context: ItemUsageContext): Boolean {
        return true
    }
}