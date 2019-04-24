package Trackpad.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method=SpirePatch.CLASS
)
public class BottledTsunamiField
{
    public static SpireField<Boolean> inBottledTsunami = new SpireField<>(() -> false);

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy
    {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance)
        {
            inBottledTsunami.set(__result, inBottledTsunami.get(__instance));
            return __result;
        }
    }
}
