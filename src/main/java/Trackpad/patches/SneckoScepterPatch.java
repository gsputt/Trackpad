package Trackpad.patches;

import Trackpad.trackpad;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "draw",
        paramtypez = {
                int.class
        }
)
public class SneckoScepterPatch {


    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"c"}
    )
    public static void SneckoScepterInsertPatch(AbstractPlayer __instance, int numCards, AbstractCard c)
    {
        if (__instance.hasRelic(trackpad.makeID("SneckoScepter")) && c.cost >= 3) {
            int newCost = AbstractDungeon.cardRandomRng.random(3);
            if (c.cost != newCost) {
                c.cost = newCost;
                c.costForTurn = c.cost;
                c.isCostModified = true;
            }
            __instance.getRelic(trackpad.makeID("SneckoScepter")).flash();
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerWhenDrawn");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
