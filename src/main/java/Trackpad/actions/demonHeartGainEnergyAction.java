package Trackpad.actions;

import Trackpad.relics.DemonHeart;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
public class demonHeartGainEnergyAction extends AbstractGameAction {
    public demonHeartGainEnergyAction() {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int attackAmount = 0;
        for(AbstractCard c : AbstractDungeon.player.hand.group)
        {
            if(c.type == AbstractCard.CardType.ATTACK)
            {
                attackAmount += 1;
            }
        }
        if(attackAmount >= 3) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            if(AbstractDungeon.player != null) {
                if (AbstractDungeon.player.hasRelic(DemonHeart.ID)) {
                    AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, AbstractDungeon.player.getRelic(DemonHeart.ID)));
                    AbstractDungeon.player.getRelic(DemonHeart.ID);
                }
            }
        }
        this.isDone = true;
    }
}
