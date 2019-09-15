package Trackpad.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import Trackpad.trackpad;

public class Coffee extends CustomRelic {
    // ID, images, text.
    public static final String ID = trackpad.makeID("Coffee");
    public static final String IMG = trackpad.makePath(trackpad.COFFEE);
    public static final String OUTLINE = trackpad.makePath(trackpad.COFFEE_OUTLINE);


    public Coffee() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public void onEquip()
    {
        this.counter = 3;
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip()
    {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void atTurnStart()
    {
        counter--;
        if(this.counter == 0 )
        {
            this.counter = 3;
            this.flash();
            AbstractCard cardVoid = new VoidCard();
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(cardVoid, 1, false, true, false));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new Coffee();
    }
}
