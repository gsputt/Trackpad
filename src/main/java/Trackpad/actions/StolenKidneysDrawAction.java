package Trackpad.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class StolenKidneysDrawAction extends AbstractGameAction {

    public StolenKidneysDrawAction(AbstractCreature source) {
        this.source = source;
        this.actionType = ActionType.DRAW;
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
        if(AbstractDungeon.player.drawPile.getTopCard().hasTag(AbstractCard.CardTags.STARTER_STRIKE) || AbstractDungeon.player.drawPile.getTopCard().hasTag(AbstractCard.CardTags.STARTER_DEFEND))
        {
            AbstractDungeon.actionManager.addToTop(new StolenKidneysDrawAction(this.source));
        }
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.source, 1));
        this.isDone = true;
    }
}
