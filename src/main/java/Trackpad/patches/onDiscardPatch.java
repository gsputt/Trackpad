package Trackpad.patches;


import Trackpad.relicInterfaces.triggerOnAnyDiscardRelicInterface;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToDiscardPile"
)

public class onDiscardPatch {

    @SpirePostfixPatch
    public static void onDiscardTriggerPatch( CardGroup __instance, AbstractCard c)
    {
        Nested.triggerOnAnyDiscardRelic(c);
    }

    public static class Nested
    {
        public static void triggerOnAnyDiscardRelic(AbstractCard c)
        {
            for(AbstractRelic r : AbstractDungeon.player.relics)
            {
                if(r instanceof triggerOnAnyDiscardRelicInterface)
                {
                    ((triggerOnAnyDiscardRelicInterface)r).onDiscard(c);
                }
            }
        }
    }

}
