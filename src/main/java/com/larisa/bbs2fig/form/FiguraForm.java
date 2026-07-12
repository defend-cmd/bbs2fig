package com.larisa.bbs2fig.form;

import mchorse.bbs_mod.forms.forms.Form;
import mchorse.bbs_mod.settings.values.core.ValueString;
import mchorse.bbs_mod.settings.values.numeric.ValueBoolean;

public class FiguraForm extends Form
{
    public final ValueString avatar = new ValueString("avatar", "");
    public final ValueBoolean useEquipped = new ValueBoolean("useEquipped", false);

    public FiguraForm()
    {
        super();

        this.add(this.avatar);
        this.add(this.useEquipped);
    }

    @Override
    public String getDefaultDisplayName()
    {
        if (this.useEquipped.get())
        {
            return "equipped";
        }

        String name = this.avatar.get();

        return name == null || name.isEmpty() ? "none" : name;
    }
}
