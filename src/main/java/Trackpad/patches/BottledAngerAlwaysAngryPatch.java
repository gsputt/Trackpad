package Trackpad.patches;

import Trackpad.relics.BottledAnger;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

import java.util.ArrayList;

public class BottledAngerAlwaysAngryPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class BottledAngerisVeryAngry
    {
        @SpireInsertPatch (
                locator = Locator.class,
                localvars = {"numCards", "i", "card"}
        )
        public static void Insert(int numCards, int i, @ByRef AbstractCard[] card) {
            if (i == numCards - 1) {
                if (AbstractDungeon.player.hasRelic(BottledAnger.ID)) {
                    card[0] = new Anger();
                }
            }
        }
    }
    private static class Locator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception
        {
            Matcher matcher = new Matcher.MethodCallMatcher(ArrayList.class, "add");
            return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), matcher);
        }
    }
}
