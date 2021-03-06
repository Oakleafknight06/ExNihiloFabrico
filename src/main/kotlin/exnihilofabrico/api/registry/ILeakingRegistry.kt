package exnihilofabrico.api.registry

import alexiil.mc.lib.attributes.fluid.volume.FluidVolume
import exnihilofabrico.api.crafting.FluidIngredient
import exnihilofabrico.api.crafting.ItemIngredient
import exnihilofabrico.api.recipes.barrel.LeakingRecipe
import net.minecraft.block.Block
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.tag.Tag

interface ILeakingRegistry: IRegistry<LeakingRecipe> {

    fun register(target: ItemIngredient, fluid: FluidIngredient, loss: Int, result: Block) =
        register(LeakingRecipe(target, fluid, loss, result))

    fun register(target: ItemIngredient, fluid: FluidVolume, result: Block) =
        register(LeakingRecipe(target, FluidIngredient(fluid), fluid.amount, result))

    fun register(target: ItemConvertible, fluid: Fluid, amount: Int, result: Block) =
        register(LeakingRecipe(ItemIngredient(target), FluidIngredient(fluid), amount, result))
    fun register(target: Tag<Item>, fluid: Fluid, amount: Int, result: Block) =
        register(LeakingRecipe(ItemIngredient(target), FluidIngredient(fluid), amount, result))
    fun register(target: Tag<Item>, fluid: Tag<Fluid>, amount: Int, result: Block) =
        register(LeakingRecipe(ItemIngredient(target), FluidIngredient(fluid), amount, result))
    fun register(target: ItemConvertible, fluid: Tag<Fluid>, amount: Int, result: Block) =
        register(LeakingRecipe(ItemIngredient(target), FluidIngredient(fluid), amount, result))

    /**
     * Returns the block to transform the input block into, and the amount to drain.
     */
    fun getResult(block: Block, fluid: FluidVolume): Pair<Block, Int>?

    // All recipes, chunked/broken up for pagination
    fun getREIRecipes(): Collection<LeakingRecipe>
}