package com.larisa.bbs2fig.form;

import com.larisa.bbs2fig.figura.AvatarResolver;
import mchorse.bbs_mod.forms.FormUtilsClient;
import mchorse.bbs_mod.forms.entities.IEntity;
import mchorse.bbs_mod.forms.entities.MCEntity;
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
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.model.rendering.PartFilterScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiguraFormRenderer extends FormRenderer<FiguraForm>
{
    private static final Logger LOGGER = LoggerFactory.getLogger("bbs2fig");

    private int diagnostics = 0;
    private long lastTick = 0L;

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

        if (diag)
        {
            this.diagnostics++;
            LOGGER.info("render3D: avatar={} loaded={} renderer={} player={}", avatar != null, avatar != null && avatar.loaded, avatar != null && avatar.renderer != null, mc.player != null);
        }

        if (avatar == null || !avatar.loaded || avatar.renderer == null || mc.player == null)
        {
            return;
        }

        LivingEntityRenderer<?, ?> livingRenderer = this.getLivingRenderer(mc, mc.player);

        if (livingRenderer == null)
        {
            return;
        }

        Entity host = this.resolveHost(context, mc);
        float delta = context.getTransition();

        this.tickAvatar(avatar);

        MatrixStack matrices = context.stack;
        VertexConsumerProvider provider = FormUtilsClient.getProvider();

        matrices.push();
        matrices.scale(-1F, -1F, 1F);
        matrices.translate(0.0F, -1.501F, 0.0F);

        try
        {
            FiguraMat4 mat = FiguraMat4.of().set(matrices.peek().getPositionMatrix());

            avatar.renderEvent(delta, mat);
            avatar.render(host, 0F, delta, 1F, matrices, provider, context.light, context.overlay, livingRenderer, PartFilterScheme.MODEL, false, false);
            avatar.postRenderEvent(delta, mat);

            if (diag)
            {
                LOGGER.info("rendered avatar '{}'", avatar.name);
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

    private void tickAvatar(Avatar avatar)
    {
        long now = System.currentTimeMillis();

        if (now - this.lastTick >= 50L)
        {
            this.lastTick = now;

            try
            {
                avatar.tick();
            }
            catch (Exception e)
            {
                LOGGER.error("Failed to tick Figura avatar", e);
            }
        }
    }

    private Entity resolveHost(FormRenderingContext context, MinecraftClient mc)
    {
        IEntity entity = context.entity;

        if (entity instanceof MCEntity mcEntity && mcEntity.getMcEntity() != null)
        {
            return mcEntity.getMcEntity();
        }

        return mc.player;
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
