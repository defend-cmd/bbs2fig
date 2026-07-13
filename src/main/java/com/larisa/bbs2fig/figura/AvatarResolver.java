package com.larisa.bbs2fig.figura;

import com.larisa.bbs2fig.form.FiguraForm;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;

public class AvatarResolver
{
    public static Avatar resolve(FiguraForm form, Entity host)
    {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null)
        {
            return null;
        }

        if (host != null && !(host instanceof PlayerEntity))
        {
            return resolveForActor(mc, host);
        }

        return AvatarManager.getAvatar(mc.player);
    }

    private static Avatar resolveForActor(MinecraftClient mc, Entity host)
    {
        Avatar bound = AvatarManager.getAvatar(host);

        if (bound != null)
        {
            return bound;
        }

        NbtCompound nbt = sourceNbt(mc);

        if (nbt != null)
        {
            return AvatarManager.loadEntityAvatar(host, nbt);
        }

        return null;
    }

    private static NbtCompound sourceNbt(MinecraftClient mc)
    {
        Avatar equipped = AvatarManager.getAvatarForPlayer(mc.player.getUuid());

        return equipped != null ? equipped.nbt : null;
    }
}
