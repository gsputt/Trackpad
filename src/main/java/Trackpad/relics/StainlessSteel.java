package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StainlessSteel extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("StainlessSteel");
    public static final String IMG = trackpad.makePath(trackpad.STAINLESS_STEEL);
    public static final String OUTLINE = trackpad.makePath(trackpad.STAINLESS_STEEL_OUTLINE);


    public StainlessSteel() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.SOLID);
    }


    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        this.counter++;
        if(this.counter % 3 == 0)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 5));
            this.counter = 0;
        }
        return MathUtils.floor(blockAmount);
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new StainlessSteel();
    }
}
