package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Random;

public class HandOfMidas extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("HandOfMidas");
    public static final String IMG = trackpad.makePath(trackpad.HAND_OF_MIDAS);
    public static final String OUTLINE = trackpad.makePath(trackpad.HAND_OF_MIDAS_OUTLINE);


    public HandOfMidas() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(target instanceof AbstractMonster)
        {
            AbstractMonster monster = (AbstractMonster)target;
            if(monster.type.equals(AbstractMonster.EnemyType.NORMAL))
            {
                if(Math.random() <= 0.1)
                {
                    AbstractDungeon.player.gainGold(monster.currentHealth);
                    monster.die(true);
                }
            }
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
        return new HandOfMidas();
    }
}
