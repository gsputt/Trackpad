package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.cards.CardGroup.DRAW_PILE_X;
import static com.megacrit.cardcrawl.cards.CardGroup.DRAW_PILE_Y;

public class BottledAnger extends CustomRelic{

    // ID, images, text.
    public static final String ID = trackpad.makeID("BottledAnger");
    public static final String IMG = trackpad.makePath(trackpad.BOTTLED_ANGER);
    public static final String OUTLINE = trackpad.makePath(trackpad.BOTTLED_ANGER_OUTLINE);

    public static ArrayList<AbstractCard> angerList = new ArrayList<>();


    public BottledAnger() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        return numberOfCards + 1;
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                for(AbstractCard c: angerList)
                {
                        c.unhover();
                        c.lighten(true);
                        c.setAngle(0.0F);
                        c.drawScale = 0.12F;
                        c.targetDrawScale = 0.75F;
                        c.current_x = DRAW_PILE_X;
                        c.current_y = DRAW_PILE_Y;
                        AbstractDungeon.player.hand.addToTop(c);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                }
                angerList.clear();
                this.isDone = true;
            }
        });
    }


    @Override
    public void atBattleStartPreDraw()
    {
        //angerList.clear();
        for(AbstractCard c: new ArrayList<>(AbstractDungeon.player.drawPile.group))
        {
            if(c.cardID.equals(Anger.ID) || c.cardID.equals("jedi:controlledanger") || c.cardID.equals("jedi:fear") || c.cardID.equals("jedi:hate"))
            {
                angerList.add(c);
                AbstractDungeon.player.drawPile.removeCard(c);
            }
        }
    }


    @Override
    public void onVictory()
    {
        angerList.clear();
    }
    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new BottledAnger();
    }
}
