package Trackpad.patches;


import Trackpad.relics.AncestralArmaments;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class AncestralArmamentsPatch {

    private static final CardStrings cardStringsForShiv;

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
                AbstractCard shiv = (AbstractCard)ReflectionHacks.getPrivate(__instance, MakeTempCardInHandAction.class, "c");
                shiv.upgrade();
            }
        }
    }
    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCard.class,
                    boolean.class
            }
    )
    public static class IDontKnowHowToDoThisProperly {
        @SpirePostfixPatch
        public static void ActualPatch(MakeTempCardInHandAction __instance, AbstractCard card, boolean isOtherCardInCenter) {
            if (card instanceof Shiv && AbstractDungeon.player.hasRelic(AncestralArmaments.ID)) {
                AbstractCard shiv = (AbstractCard) ReflectionHacks.getPrivate(__instance, MakeTempCardInHandAction.class, "c");
                shiv.upgrade();
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
    public static class ShivUpgradeShenanigans {
        @SpirePrefixPatch
        public static SpireReturn<AbstractCard> ActualPatch(Shiv __instance)
        {

            if(AbstractDungeon.player != null) {
                if (AbstractDungeon.player.hasRelic(AncestralArmaments.ID)) {
                    try {
                        Method iTouchTheProtectedStuff = AbstractCard.class.getDeclaredMethod("upgradeDamage", int.class);
                        iTouchTheProtectedStuff.setAccessible(true);
                        iTouchTheProtectedStuff.invoke(__instance, 2 + __instance.timesUpgraded);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    __instance.timesUpgraded += 1;
                    __instance.upgraded = true;

                    __instance.name = cardStringsForShiv.NAME + "+" + __instance.timesUpgraded;

                    try {
                        Method whyIsThisMethodProtectedToo = AbstractCard.class.getDeclaredMethod("initializeTitle");
                        whyIsThisMethodProtectedToo.setAccessible(true);
                        whyIsThisMethodProtectedToo.invoke(__instance);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = Shiv.class,
            method = "makeCopy"
    )
    public static class copyTheThingCorrectly
    {
        @SpirePostfixPatch
        public static AbstractCard whySoManyPatches(AbstractCard __result, Shiv __instance)
        {
            __result.timesUpgraded = __instance.timesUpgraded;
            return __result;
        }
    }

    static {
        cardStringsForShiv = CardCrawlGame.languagePack.getCardStrings("Shiv");
    }
}

