package dev.xkmc.fruitsdelight.init.food;

import dev.xkmc.fruitsdelight.init.registrate.FDEffects;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Supplier;

public enum FruitType {
	APPLE(() -> Items.APPLE, List.of(new EffectEntry(() -> MobEffects.ABSORPTION, lv -> lv * 300))),
	BLUEBERRY(FDBushes.BLUEBERRY::getFruit, List.of(
			new EffectEntry(() -> MobEffects.NIGHT_VISION, lv -> lv * 300),
			new EffectEntry(FDEffects.BRIGHTENING::get, lv -> lv * 300)
	)),
	GLOWBERRY(() -> Items.GLOW_BERRIES, List.of(new EffectEntry(() -> MobEffects.GLOWING, lv -> lv * 1200))),
	HAMIMELON(FDMelons.HAMIMELON::getSlice, List.of(new EffectEntry(() -> MobEffects.HEALTH_BOOST, lv -> lv * 300))),
	HAWBERRY(FDTrees.HAWBERRY::getFruit, List.of(new EffectEntry(FDEffects.APPETIZING::get, lv -> lv * 300))),
	LYCHEE(FDTrees.LYCHEE::getFruit, List.of(new EffectEntry(() -> MobEffects.DAMAGE_BOOST, lv -> lv * 100, lv -> 2))),
	MANGO(FDTrees.MANGO::getFruit, List.of(
			new EffectEntry(() -> MobEffects.DAMAGE_BOOST, lv -> lv * 300, lv -> 2),
			new EffectEntry(FDEffects.RAGE_AURA::get, lv -> lv * 300)
	)),
	ORANGE(FDTrees.ORANGE::getFruit, List.of(
			new EffectEntry(() -> MobEffects.REGENERATION, lv -> lv * 300),
			new EffectEntry(FDEffects.RECOVERING::get, lv -> lv * 300)
	)),
	PEACH(FDTrees.PEACH::getFruit, List.of(new EffectEntry(FDEffects.HEAL_AURA::get, lv -> lv * 100))),
	PEAR(FDTrees.PEAR::getFruit, List.of(new EffectEntry(FDEffects.LOZENGE::get, lv -> lv * 300))),
	PERSIMMON(FDTrees.PERSIMMON::getFruit, List.of(new EffectEntry(FDEffects.ASTRINGENT::get, lv -> lv * 1200))),
	PINEAPPLE(FDPineapple.PINEAPPLE::getSlice, List.of(
			new EffectEntry(() -> MobEffects.MOVEMENT_SPEED, lv -> lv * 300),
			new EffectEntry(FDEffects.SWEETENING::get, lv -> lv * 300)
	)),
	LEMON(FDBushes.LEMON::getFruit, List.of(
			new EffectEntry(() -> MobEffects.DIG_SPEED, lv -> lv * 300),
			new EffectEntry(FDEffects.REFRESHING::get, lv -> lv * 300)
	)),
	SWEETBERRY(() -> Items.SWEET_BERRIES, List.of());

	public final Supplier<Item> fruit;
	public final List<EffectEntry> eff;

	FruitType(Supplier<Item> fruit, List<EffectEntry> eff) {
		this.fruit = fruit;
		this.eff = eff;
	}

}
