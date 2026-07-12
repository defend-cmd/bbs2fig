package com.larisa.bbs2fig;

import com.larisa.bbs2fig.form.FiguraForm;
import com.larisa.bbs2fig.form.FiguraFormRenderer;
import mchorse.bbs_mod.events.BBSAddonMod;
import mchorse.bbs_mod.events.Subscribe;
import mchorse.bbs_mod.events.register.RegisterFormsEvent;
import mchorse.bbs_mod.events.register.RegisterFormsRenderersEvent;
import mchorse.bbs_mod.resources.Link;

public class Bbs2FigAddon implements BBSAddonMod
{
    @Subscribe
    public void onRegisterForms(RegisterFormsEvent event)
    {
        event.getForms().register(Link.create("bbs2fig:figura"), FiguraForm.class);
    }

    @Subscribe
    public void onRegisterRenderers(RegisterFormsRenderersEvent event)
    {
        event.registerRenderer(FiguraForm.class, FiguraFormRenderer::new);
    }
}
