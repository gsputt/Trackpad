package Trackpad.patches;


import Trackpad.relics.AncestralArmaments;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;



public class AncestralArmamentsPatch {

    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCard.class,
                    int.class
            }
    )
    public static class MakeTempCardInHandActionPatch {
        @SpirePostfixPatch
        public static void ActualPatch(MakeTempCardInHandAction __instance, AbstractCard card, int amount) {
            if (card instanceof Shiv && AbstractDungeon.player.hasRelic(AncestralArmaments.ID)) {
                AbstractCard shiv = card.makeStatEquivalentCopy();
                shiv.upgrade();
                ReflectionHacks.setPrivate(__instance, MakeTempCardInHandAction.class, "c", shiv);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUpgrade"
    )
    public static class AbstractCardPatch {
        @SpirePostfixPatch
        public static boolean ActualPatch(boolean __Result, AbstractCard __instance) {
            if (AbstractDungeon.player != null) {
                if (!__Result && __instance instanceof Shiv && AbstractDungeon.player.hasRelic(AncestralArmaments.ID)) {

                    //__instance.upgraded = false;
                    return true;
                }
            }
            return __Result;
        }
    }

    @SpirePatch(
            clz = Shiv.class,
            method = "upgrade"
    )
    public static class ShivPatch {
        @SpirePrefixPatch
        public static SpireReturn ActualPatch(Shiv __instance)
        {
            if(AbstractDungeon.player != null) {
                if (AbstractDungeon.player.hasRelic(AncestralArmaments.ID)) {
                    __instance.baseDamage += 2 + __instance.timesUpgraded;
                    __instance.upgradedDamage = true;
                    __instance.upgraded = true;
                    __instance.timesUpgraded++;
                    __instance.name = Shiv.NAME + "+" + __instance.timesUpgraded;
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }
}

