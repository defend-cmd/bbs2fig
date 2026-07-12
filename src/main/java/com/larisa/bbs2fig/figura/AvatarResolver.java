package com.larisa.bbs2fig.figura;

import com.larisa.bbs2fig.form.FiguraForm;
import net.minecraft.client.MinecraftClient;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;

public class AvatarResolver
{
    public static Avatar resolve(FiguraForm form)
    {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null)
        {
            return null;
        }

        return AvatarManager.getAvatar(mc.player);
    }
}
