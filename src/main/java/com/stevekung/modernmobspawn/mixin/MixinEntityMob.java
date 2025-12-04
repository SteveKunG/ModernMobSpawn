package com.stevekung.modernmobspawn.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

@Mixin(EntityMob.class)
public class MixinEntityMob extends EntityCreature
{
    MixinEntityMob()
    {
        super(null);
    }

    @ModifyReturnValue(method = "isValidLightLevel", at = @At(value = "RETURN", ordinal = 1))
    private boolean modernMobSpawn$replaceSpawnCondition(boolean original, @Local BlockPos blockPos)
    {
        int blockLight = this.world.getLightFor(EnumSkyBlock.BLOCK, blockPos);

        // Prevent mob spawning if light level is more than 0
        if (blockLight > 0)
        {
            return false;
        }
        return original;
    }
}