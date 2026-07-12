package com.larisa.bbs2fig.form;

import mchorse.bbs_mod.forms.renderers.FormRenderer;
import mchorse.bbs_mod.forms.renderers.FormRenderingContext;
import mchorse.bbs_mod.ui.framework.UIContext;

public class FiguraFormRenderer extends FormRenderer<FiguraForm>
{
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
    }
}
