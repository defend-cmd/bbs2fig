package com.larisa.bbs2fig.mixin;

import com.larisa.bbs2fig.form.FiguraForm;
import mchorse.bbs_mod.forms.sections.ExtraFormSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExtraFormSection.class)
public class ExtraFormSectionMixin
{
    @Inject(method = "initiate", at = @At("TAIL"))
    private void bbs2fig$addFiguraForm(CallbackInfo ci)
    {
        ExtraFormSection self = (ExtraFormSection) (Object) this;

        self.getExtraCategory().addForm(new FiguraForm());
    }
}
