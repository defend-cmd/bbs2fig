package com.larisa.bbs2fig.form;

import com.larisa.bbs2fig.figura.AvatarResolver;
import mchorse.bbs_mod.forms.FormUtilsClient;
import mchorse.bbs_mod.forms.renderers.FormRenderer;
import mchorse.bbs_mod.forms.renderers.FormRenderingContext;
import mchorse.bbs_mod.ui.framework.UIContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.model.rendering.EntityRenderMode;
import org.joml.Vector3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiguraFormRenderer extends FormRenderer<FiguraForm>
{
    private static final Logger LOGGER = LoggerFactory.getLogger("bbs2fig");

    public FiguraFormRenderer(FiguraForm form)
    {
        super(form);
    }

    @Override
    protected void renderInUI(UIContext context, int x1, int y1, int x2, int y2)
    {
    }

    @Override
    protected void render3D(FormRenderingContext context)
    {
        Avatar avatar = AvatarResolver.resolve(this.form);

        if (avatar == null || !avatar.loaded || avatar.renderer == null)
        {
            return;
        }

        Entity host = MinecraftClient.getInstance().player;

        if (host == null)
        {
            return;
        }

        MatrixStack matrices = context.stack;
        VertexConsumerProvider provider = FormUtilsClient.getProvider();
        Vector3d cam = context.camera != null ? context.camera.position : new Vector3d();

        try
        {
            avatar.worldRender(host, cam.x, cam.y, cam.z, matrices, provider, context.light, context.getTransition(), EntityRenderMode.OTHER);
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to render Figura avatar in BBS form", e);
        }
    }
}
