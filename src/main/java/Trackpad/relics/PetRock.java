package Trackpad.relics;

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

public class PetRock extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("PetRock");
    public static final String IMG = trackpad.makePath(trackpad.PET_ROCK);
    public static final String OUTLINE = trackpad.makePath(trackpad.PET_ROCK_OUTLINE);


    public PetRock() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public void atTurnStart()
    {
        AbstractMonster weakestMonster = null;
        Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m = (AbstractMonster)var4.next();
            if (!m.isDeadOrEscaped()) {
                if (weakestMonster == null) {
                    weakestMonster = m;
                } else if (m.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = m;
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(weakestMonster,
                new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(weakestMonster, this));
    }



    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new PetRock();
    }
}
