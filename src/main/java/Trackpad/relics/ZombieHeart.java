package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ZombieHeart extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("ZombieHeart");
    public static final String IMG = trackpad.makePath(trackpad.ZOMBIE_HEART);
    public static final String OUTLINE = trackpad.makePath(trackpad.ZOMBIE_HEART_OUTLINE);


    public ZombieHeart() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth == 0 && !m.hasPower(MinionPower.POWER_ID)) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
            AbstractDungeon.player.increaseMaxHp(1, true);
            AbstractDungeon.actionManager
                    .addToBottom(new DamageAction(AbstractDungeon.player,
                            new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.NONE, true));
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
        return new ZombieHeart();
    }
}
