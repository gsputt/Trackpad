package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class BottledAnger extends CustomRelic{

    // ID, images, text.
    public static final String ID = trackpad.makeID("BottledAnger");
    public static final String IMG = trackpad.makePath(trackpad.BOTTLED_ANGER);
    public static final String OUTLINE = trackpad.makePath(trackpad.BOTTLED_ANGER_OUTLINE);


    public BottledAnger() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    @Override
    public void atBattleStartPreDraw() {
        ArrayList<AbstractCard> deck = new ArrayList<>(AbstractDungeon.player.drawPile.group);
        for(AbstractCard c: deck)
        {
            if(c.cardID.equals(Anger.ID))
            {
                AbstractDungeon.player.drawPile.removeCard(c);
                AbstractDungeon.player.drawPile.moveToHand(c, AbstractDungeon.player.drawPile);
            }
        }
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new BottledAnger();
    }
}
