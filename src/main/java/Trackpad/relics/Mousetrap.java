package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Mousetrap extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("Mousetrap");
    public static final String IMG = trackpad.makePath(trackpad.MOUSETRAP);
    public static final String OUTLINE = trackpad.makePath(trackpad.MOUSETRAP_OUTLINE);
    private boolean set = false;


    public Mousetrap() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.owner != AbstractDungeon.player && info.owner != null)
        {
            if(this.set){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(info.owner, this));
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(info.owner,
                        new DamageInfo(AbstractDungeon.player, 15, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                this.set = false;
            }
        }
        return damageAmount;
    }

    @Override
    public void atBattleStart() {
        this.set = true;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new Mousetrap();
    }
}
