package Trackpad.patches;

import Trackpad.powers.FocusingLensPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbPassiveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class FocusingLensTargetingPatch {
    @SpirePatch(
            clz = LightningOrbPassiveAction.class,
            method = "update"
    )
    public static class LightningOrbPassiveTargetingPatch {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"m"}
        )
        public static void Patch(LightningOrbPassiveAction __instance, @ByRef(type = "com.megacrit.cardcrawl.core.AbstractCreature") Object[] m)
        {
            ArrayList<AbstractMonster> monsterList = new ArrayList<>();
            for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            {
                if(mo.hasPower(FocusingLensPower.POWER_ID))
                {
                    monsterList.add(mo);
                }
            }
            m[0] = monsterList.get(cardRandomRng.random(monsterList.size() - 1));
        }
    }

    @SpirePatch(
            clz = LightningOrbEvokeAction.class,
            method = "update"
    )
    public static class LightningOrbEvokeTargetingPatch {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"m"}
        )
        public static void Patch(LightningOrbEvokeAction __instance, @ByRef(type = "com.megacrit.cardcrawl.core.AbstractCreature") Object[] m)
        {ArrayList<AbstractMonster> monsterList = new ArrayList<>();
            for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            {
                if(mo.hasPower(FocusingLensPower.POWER_ID))
                {
                    monsterList.add(mo);
                }
            }
            m[0] = monsterList.get(cardRandomRng.random(monsterList.size() - 1));}

    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getRandomMonster");
            int[] LineNumber = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            LineNumber[0] = LineNumber[0] + 1;
            return LineNumber;
        }
    }
}
