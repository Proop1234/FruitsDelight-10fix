package dev.xkmc.fruitsdelight.init.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import dev.xkmc.fruitsdelight.content.recipe.JellyCraftShapelessBuilder;
import dev.xkmc.fruitsdelight.init.FruitsDelight;
import dev.xkmc.fruitsdelight.init.food.FDCrates;
import dev.xkmc.fruitsdelight.init.food.FDFood;
import dev.xkmc.fruitsdelight.init.food.FruitType;
import dev.xkmc.fruitsdelight.init.plants.FDBushes;
import dev.xkmc.fruitsdelight.init.plants.FDPineapple;
import dev.xkmc.fruitsdelight.init.plants.FDTrees;
import dev.xkmc.fruitsdelight.init.plants.PlantDataEntry;
import dev.xkmc.fruitsdelight.init.registrate.FDBlocks;
import dev.xkmc.fruitsdelight.init.registrate.FDItems;
import dev.xkmc.l2library.serial.ingredients.PotionIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.BiFunction;

public class RecipeGen {

	public static void genRecipes(RegistrateRecipeProvider pvd) {
		PlantDataEntry.gen(pvd, PlantDataEntry::genRecipe);
		FDCrates.genRecipes(pvd);
		{

			for (var e : FruitType.values()) {
				jelly(pvd, e);

				storageBlock(pvd, FDItems.JELLO[e.ordinal()].get(),
						FDBlocks.JELLO[e.ordinal()].get(), Items.BOWL);

				storageBlock(pvd, FDItems.JELLY[e.ordinal()].get(),
						FDBlocks.JELLY[e.ordinal()].get(), Items.GLASS_BOTTLE);
			}

			{
				juice(pvd, FDFood.HAMIMELON_JUICE, 4, false, false);
				juice(pvd, FDFood.ORANGE_JUICE, 2, true, false);
				juice(pvd, FDFood.PEAR_JUICE, 2, true, false);
				juice(pvd, FDFood.LEMON_JUICE, 2, true, false);
				juice(pvd, FDFood.HAWBERRY_TEA, 4, true, true);
				juice(pvd, FDFood.MANGO_TEA, 2, true, true);
				juice(pvd, FDFood.PEACH_TEA, 2, true, true);
				juice(pvd, FDFood.MANGOSTEEN_TEA, 2, true, true);

			}

			{
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FDBushes.LEMON.getFruit()),
								Ingredient.of(ForgeTags.TOOLS_KNIVES), FDFood.LEMON_SLICE.item.get(), 4, 1)
						.addResult(FDBushes.LEMON.getSeed())
						.build(pvd, new ResourceLocation(FruitsDelight.MODID, "lemon_cutting"));

				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FDTrees.ORANGE.getFruit()),
								Ingredient.of(ForgeTags.TOOLS_KNIVES), FDFood.ORANGE_SLICE.item.get(), 4, 1)
						.build(pvd, new ResourceLocation(FruitsDelight.MODID, "orange_cutting"));
			}

			{
				smoking(pvd, FDFood.BAKED_PEAR);
				smoking(pvd, FDFood.DRIED_PERSIMMON);
			}

			{

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.HAWBERRY_ROLL.item, 1)::unlockedBy,
						FDFood.HAWBERRY_SHEET.item.get())
						.requires(FDFood.HAWBERRY_SHEET.item.get())
						.requires(TagGen.JELLY)
						.save(pvd);

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.MANGO_SALAD.item, 1)::unlockedBy,
						FDFood.MANGO_SALAD.getFruit())
						.requires(Items.BOWL)
						.requires(FDTrees.MANGO.getFruit())
						.requires(TagGen.JELLY)
						.save(pvd);

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.JELLY_BREAD.item, 1)::unlockedBy,
						Items.BREAD)
						.requires(Items.BREAD)
						.requires(TagGen.JELLY)
						.save(pvd);
			}

			{

				unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FDFood.HAWBERRY_SHEET.item)::unlockedBy,
						FDFood.HAWBERRY_SHEET.getFruit())
						.requires(FDTrees.HAWBERRY.getFruit(), 3)
						.requires(Items.SUGAR)
						.save(pvd);


				unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FDFood.HAWBERRY_STICK.item)::unlockedBy,
						FDFood.HAWBERRY_STICK.getFruit())
						.requires(FDTrees.HAWBERRY.getFruit(), 3)
						.requires(Items.STICK)
						.requires(Items.SUGAR, 2)
						.save(pvd);

				unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FDFood.HAMIMELON_SHAVED_ICE.item)::unlockedBy,
						FDFood.HAMIMELON_SHAVED_ICE.getFruit())
						.requires(Items.GLASS_BOTTLE)
						.requires(FDFood.HAMIMELON_SHAVED_ICE.getFruit(), 2)
						.requires(ForgeTags.MILK_BOTTLE)
						.requires(Items.SUGAR)
						.requires(Items.ICE)
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FDFood.PERSIMMON_COOKIE.item, 8)::unlockedBy,
						FDFood.PERSIMMON_COOKIE.getFruit())
						.pattern("ABA")
						.define('A', Items.WHEAT)
						.define('B', FDFood.DRIED_PERSIMMON.item.get())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FDFood.CRANBERRY_COOKIE.item, 8)::unlockedBy,
						FDFood.CRANBERRY_COOKIE.getFruit())
						.pattern("ABA")
						.define('A', Items.WHEAT)
						.define('B', FDFood.CRANBERRY_COOKIE.getFruit())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FDFood.LEMON_COOKIE.item, 8)::unlockedBy,
						FDFood.LEMON_COOKIE.getFruit())
						.pattern(" C ").pattern("ABA")
						.define('C', ForgeTags.MILK_BOTTLE)
						.define('A', Items.WHEAT)
						.define('B', FDFood.LEMON_COOKIE.getFruit())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FDFood.HAMIMELON_POPSICLE.item.get(), 1)::unlockedBy,
						FDFood.HAMIMELON_POPSICLE.getFruit())
						.pattern(" MM").pattern("IMM").pattern("SI ")
						.define('I', Items.ICE)
						.define('M', FDFood.HAMIMELON_POPSICLE.getFruit())
						.define('S', Items.STICK)
						.save(pvd);

			}

			{

				CookingPotRecipeBuilder.cookingPotRecipe(FDBlocks.PINEAPPLE_RICE.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(FDPineapple.PINEAPPLE.getWholeFruit())
						.addIngredient(Ingredient.of(ForgeTags.GRAIN_RICE), 3)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.addIngredient(Tags.Items.EGGS)
						.build(pvd);


				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LYCHEE_CHERRY_TEA.item, 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.LYCHEE_CHERRY_TEA.getFruit(), 2)
						.addIngredient(Items.CHERRY_LEAVES)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.MANGO_MILKSHAKE.item, 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.MANGO_MILKSHAKE.getFruit())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BLUEBERRY_CUSTARD.item, 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.BLUEBERRY_CUSTARD.getFruit(), 2)
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BELLINI_COCKTAIL.item, 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FruitType.PEACH.fruit.get(), 2)
						.addIngredient(Items.SUGAR)
						.addIngredient(Items.ICE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PINEAPPLE_PIE.item, 2, 200, 0.1f)
						.addIngredient(FDFood.PINEAPPLE_PIE.getFruit(), 2)
						.addIngredient(ModItems.PIE_CRUST.get())
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LEMON_TART.item, 2, 200, 0.1f)
						.addIngredient(FDFood.LEMON_TART.getFruit())
						.addIngredient(ModItems.PIE_CRUST.get())
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.build(pvd);


				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BLUEBERRY_MUFFIN.item, 2, 200, 0.1f)
						.addIngredient(FDFood.BLUEBERRY_MUFFIN.getFruit(), 2)
						.addIngredient(ModItems.WHEAT_DOUGH.get())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.CRANBERRY_MUFFIN.item, 2, 200, 0.1f)
						.addIngredient(FDFood.CRANBERRY_MUFFIN.getFruit(), 2)
						.addIngredient(ModItems.WHEAT_DOUGH.get())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.ORANGE_CHICKEN.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_CHICKEN)
						.addIngredient(FDFood.ORANGE_SLICE.item.get(), 4)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.ORANGE_MARINATED_PORK.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(FDFood.ORANGE_SLICE.item.get(), 4)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PEAR_WITH_ROCK_SUGAR.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(Items.SUGAR, 4)
						.addIngredient(FDTrees.PEAR.getFruit(), 2)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.MANGOSTEEN_CAKE.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(Items.WHEAT, 2)
						.addIngredient(Items.SUGAR, 2)
						.addIngredient(FDTrees.MANGOSTEEN.getFruit(), 2)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LYCHEE_CHICKEN.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_CHICKEN)
						.addIngredient(FDTrees.LYCHEE.getFruit(), 4)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PINEAPPLE_MARINATED_PORK.item, 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(FDPineapple.PINEAPPLE.getSlice(), 4)
						.addIngredient(Items.CARROT)
						.build(pvd);
			}

		}

	}

	private static void storageBlock(RegistrateRecipeProvider pvd, Item item, Block block, Item cont) {
		unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
				block)::unlockedBy, item)
				.requires(item, 8)
				.save(pvd);
		unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
				item, 8)::unlockedBy, block.asItem())
				.requires(block).requires(cont, 8)
				.save(pvd);
	}

	private static void jelly(RegistrateRecipeProvider pvd, FruitType jelly) {
		CookingPotRecipeBuilder.cookingPotRecipe(jelly.getJelly(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
				.addIngredient(jelly.fruit.get(), jelly.jellyCost)
				.addIngredient(Items.SUGAR)
				.addIngredient(FDFood.LEMON_SLICE.item.get())
				.build(pvd);
	}

	private static void juice(RegistrateRecipeProvider pvd, FDFood juice, int count, boolean tea, boolean hot) {
		if (hot) {
			var e = CookingPotRecipeBuilder.cookingPotRecipe(juice.item, 1, 200, 0.1f, Items.GLASS_BOTTLE);
			if (tea) {
				e.addIngredient(Items.SUGAR);
				e.addIngredient(ItemTags.LEAVES);
			}
			e.addIngredient(juice.getFruit(), count);
			e.build(pvd);
		} else {
			var e = unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, juice.item)::unlockedBy, juice.getFruit());
			if (tea) {
				e.requires(new PotionIngredient(Potions.WATER));
				e.requires(Items.SUGAR);
			} else {
				e.requires(Items.GLASS_BOTTLE);
			}
			e.requires(juice.getFruit(), count);
			e.save(pvd);
		}
	}

	private static void smoking(RegistrateRecipeProvider pvd, FDFood food) {
		pvd.smoking(DataIngredient.items(food.getFruit()), RecipeCategory.FOOD, food.item, 0.1f);
		pvd.campfire(DataIngredient.items(food.getFruit()), RecipeCategory.FOOD, food.item, 0.1f);
	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
	}


}
