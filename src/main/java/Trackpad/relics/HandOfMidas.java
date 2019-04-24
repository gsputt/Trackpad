package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import java.util.Random;

import static com.megacrit.cardcrawl.neow.NeowEvent.rng;

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
            if(monster.type == AbstractMonster.EnemyType.NORMAL)
            {
                //trackpad.logger.info("monster type is NORMAL");
                if(AbstractDungeon.cardRandomRng.random(99) < 10)
                {
                    this.flash();
                    AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(monster, this));
                    //CardCrawlGame.sound.play("GOLD_GAIN");
                    for(int i = 0; i < monster.currentHealth; i++) {
                        AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player, monster.hb.cX, monster.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false));
                    }
                    AbstractDungeon.player.gainGold(monster.currentHealth);
                    monster.currentHealth = 0;
                    monster.damage(new DamageInfo((AbstractCreature)null, 0, DamageInfo.DamageType.THORNS));
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
