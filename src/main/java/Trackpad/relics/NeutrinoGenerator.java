package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
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
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(c.costForTurn == 0)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(AbstractOrb.getRandomOrb(true)));
        }
        else if(c.costForTurn == 1)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
        }
        else if(c.costForTurn == 2)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Frost()));
        }
        else if(c.costForTurn == 3)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Dark()));
        }
        else if(c.costForTurn == -1)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Plasma()));
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
