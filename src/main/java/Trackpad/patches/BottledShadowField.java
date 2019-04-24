package Trackpad.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method=SpirePatch.CLASS
)
public class BottledShadowField
{
    public static SpireField<Boolean> inBottledShadow = new SpireField<>(() -> false);

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy
    {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance)
        {
            inBottledShadow.set(__result, inBottledShadow.get(__instance));
            return __result;
        }
    }
}
