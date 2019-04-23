package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class VoidSphere extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("VoidSphere");
    public static final String IMG = trackpad.makePath(trackpad.VOID_SPHERE);
    public static final String OUTLINE = trackpad.makePath(trackpad.VOID_SPHERE_OUTLINE);


    public VoidSphere() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        for(AbstractCard c : AbstractDungeon.player.drawPile.group)
        {
            if(c.isEthereal == false) {
                c.isEthereal = true;
                c.rawDescription += " NL Ethereal.";
                c.initializeDescription();
            }
        }
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new VoidSphere();
    }
}
