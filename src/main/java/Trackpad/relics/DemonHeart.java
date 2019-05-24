package Trackpad.relics;

import Trackpad.actions.demonHeartGainEnergyAction;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class DemonHeart extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("DemonHeart");
    public static final String IMG = trackpad.makePath(trackpad.DEMON_HEART);
    public static final String OUTLINE = trackpad.makePath(trackpad.DEMON_HEART_OUTLINE);


    public DemonHeart() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw()
    {
        AbstractDungeon.actionManager.addToBottom(new demonHeartGainEnergyAction());
    }



    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new DemonHeart();
    }
}
