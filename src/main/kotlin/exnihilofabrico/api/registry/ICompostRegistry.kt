package exnihilofabrico.api.registry

import exnihilofabrico.api.crafting.ItemIngredient
import exnihilofabrico.api.recipes.barrel.CompostRecipe
import exnihilofabrico.compatibility.rei.barrel.REICompostRecipe
import exnihilofabrico.util.Color
import exnihilofabrico.util.asStack
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.tag.Tag

interface ICompostRegistry: IRegistry<CompostRecipe> {
    fun register(ingredient: ItemIngredient, result: ItemStack, amount: Double, color: Color) =
        register(CompostRecipe(ingredient, result, amount, color))
    fun register(ingredient: ItemConvertible, result: ItemStack, amount: Double, color: Color) =
        register(ItemIngredient(ingredient), result, amount, color)
    fun register(ingredient: ItemConvertible, result: ItemConvertible, amount: Double, color: Color) =
        register(ItemIngredient(ingredient), result.asStack(), amount, color)
    fun register(ingredient: Tag<Item>, result: ItemConvertible, amount: Double, color: Color) =
        register(ItemIngredient(ingredient), result.asStack(), amount, color)

    fun getRecipe(stack: ItemStack): CompostRecipe?
    fun hasRecipe(stack: ItemStack) = getRecipe(stack) != null

    // All recipes, chunked/broken up for pagination
    fun getREIRecipes(): Collection<REICompostRecipe>
}