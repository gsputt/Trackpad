package Trackpad.patches;

import Trackpad.relicInterfaces.onAttackToChangeDamageWithTargetRelicHook;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;


public class AbstractMonsterOnAttackToChangeDamageWithTargetHook {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "damage"
    )
    public static class addHookWithTargetPatch
    {
        @SpireInsertPatch (
                locator = Locator.class,
                localvars = {"damageAmount"}
        )
        public static void Patch(AbstractMonster __instance, DamageInfo info, @ByRef int[] damageAmount)
        {
            for(AbstractRelic r: AbstractDungeon.player.relics)
            {
                if(r instanceof onAttackToChangeDamageWithTargetRelicHook)
                {
                    damageAmount[0] = ((onAttackToChangeDamageWithTargetRelicHook) r).changeAttackDamageWithTarget(info, damageAmount[0], __instance);
                    //System.out.println("DamageHook got called");
                }
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(DamageInfo.class, "owner");
            int[] found = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            found[0] += 1;
            return  found;
        }
    }
}
