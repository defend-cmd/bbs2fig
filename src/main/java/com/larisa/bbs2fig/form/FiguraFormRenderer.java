package com.larisa.bbs2fig.form;

import com.larisa.bbs2fig.figura.AvatarResolver;
import mchorse.bbs_mod.forms.FormUtilsClient;
import mchorse.bbs_mod.forms.renderers.FormRenderer;
import mchorse.bbs_mod.forms.renderers.FormRenderingContext;
import mchorse.bbs_mod.ui.framework.UIContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.model.rendering.PartFilterScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiguraFormRenderer extends FormRenderer<FiguraForm>
{
    private static final Logger LOGGER = LoggerFactory.getLogger("bbs2fig");

    private int diagnostics = 0;

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
        boolean diag = this.diagnostics < 3;

        Avatar avatar = AvatarResolver.resolve(this.form);
        MinecraftClient mc = MinecraftClient.getInstance();
        Entity host = mc.player;

        if (diag)
        {
            this.diagnostics++;
            LOGGER.info("render3D reached: avatar={} loaded={} renderer={} host={}", avatar != null, avatar != null && avatar.loaded, avatar != null && avatar.renderer != null, host != null);
        }

        if (avatar == null || !avatar.loaded || avatar.renderer == null || host == null)
        {
            return;
        }

        LivingEntityRenderer<?, ?> livingRenderer = this.getLivingRenderer(mc, host);

        if (livingRenderer == null)
        {
            if (diag)
            {
                LOGGER.info("no LivingEntityRenderer for host");
            }

            return;
        }

        MatrixStack matrices = context.stack;
        VertexConsumerProvider provider = FormUtilsClient.getProvider();

        matrices.push();
        matrices.scale(-1F, -1F, 1F);
        matrices.translate(0.0F, -1.501F, 0.0F);

        try
        {
            avatar.render(host, 0F, context.getTransition(), 1F, matrices, provider, context.light, context.overlay, livingRenderer, PartFilterScheme.MODEL, false, false);

            if (diag)
            {
                LOGGER.info("avatar.render called for '{}'", avatar.name);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to render Figura avatar in BBS form", e);
        }
        finally
        {
            matrices.pop();
        }
    }

    private LivingEntityRenderer<?, ?> getLivingRenderer(MinecraftClient mc, Entity host)
    {
        EntityRenderer<?> renderer = mc.getEntityRenderDispatcher().getRenderer(host);

        if (renderer instanceof LivingEntityRenderer<?, ?> living)
        {
            return living;
        }

        return null;
    }
}
