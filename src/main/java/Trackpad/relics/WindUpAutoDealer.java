package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WindUpAutoDealer extends CustomRelic implements ClickableRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("WindUpAutoDealer");
    public static final String IMG = trackpad.makePath(trackpad.WIND_UP_AUTO_DEALER);
    public static final String OUTLINE = trackpad.makePath(trackpad.WIND_UP_AUTO_DEALER_OUTLINE);


    public WindUpAutoDealer() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public void onEquip()
    {
        this.counter = 0;
    }

    @Override
    public void atTurnStart()
    {
        if(this.counter > 0)
        {
            this.counter--;
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    @Override
    public void onRightClick()
    {
        this.flash();
        this.counter += 1;
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new WindUpAutoDealer();
    }
}
