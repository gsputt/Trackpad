package Trackpad.actions;

import basemod.BaseMod;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Defend_Blue;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class StolenKidneysDrawAction extends AbstractGameAction {

    public StolenKidneysDrawAction(AbstractCreature source) {
        this.source = source;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
            this.isDone = true;
            return;
        }
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractDungeon.actionManager.addToTop(new StolenKidneysDrawAction(this.source));
            AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
            this.isDone = true;
            return;
        }
        if(AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE)
        {
            this.isDone = true;
            return;
        }
        if(AbstractDungeon.player.drawPile.getTopCard().hasTag(BaseModCardTags.BASIC_STRIKE) || AbstractDungeon.player.drawPile.getTopCard().hasTag(BaseModCardTags.BASIC_DEFEND)
                || AbstractDungeon.player.drawPile.getTopCard() instanceof Strike_Red || AbstractDungeon.player.drawPile.getTopCard() instanceof Strike_Green || AbstractDungeon.player.drawPile.getTopCard() instanceof Strike_Blue
                || AbstractDungeon.player.drawPile.getTopCard() instanceof Defend_Red || AbstractDungeon.player.drawPile.getTopCard() instanceof Defend_Green || AbstractDungeon.player.drawPile.getTopCard() instanceof Defend_Blue)
        {
            AbstractDungeon.actionManager.addToTop(new StolenKidneysDrawAction(this.source));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.source, 1));
        }
        else
        {
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.source, 1));
        }
        this.isDone = true;
    }
}
