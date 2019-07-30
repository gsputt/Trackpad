package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class NeutrinoGenerator extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("NeutrinoGenerator");
    public static final String IMG = trackpad.makePath(trackpad.NEUTRINO_GENERATOR);
    public static final String OUTLINE = trackpad.makePath(trackpad.NEUTRINO_GENERATOR_OUTLINE);


    public NeutrinoGenerator() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if((c.energyOnUse >= 2 || c.costForTurn >= 2) && !c.freeToPlayOnce)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(AbstractOrb.getRandomOrb(true)));
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new NeutrinoGenerator();
    }
}
