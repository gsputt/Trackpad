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
        int i = 1;
        AbstractMonster savedMonster = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
        AbstractMonster targetMonster;
        while(i < AbstractDungeon.getCurrRoom().monsters.monsters.size())
        {
            targetMonster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if((targetMonster.currentHealth < savedMonster.currentHealth) && !(targetMonster.isDeadOrEscaped() || targetMonster.currentHealth <= 0 || targetMonster.halfDead))
            {
                savedMonster = targetMonster;
            }
            i++;
        }
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(savedMonster,
                new DamageInfo(AbstractDungeon.player, 2, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(savedMonster, this));
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
