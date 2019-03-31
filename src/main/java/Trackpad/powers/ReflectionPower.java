package Trackpad.powers;

import Trackpad.actions.SpawnDissipatingMonsterAction;
import Trackpad.monsters.Reflection;
import Trackpad.trackpad;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;

//Gain 1 dex for the turn for each card played.

public class ReflectionPower extends AbstractPower {

    public static final String POWER_ID = trackpad.makeID("ReflectionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = trackpad.makePath(trackpad.REFLECTION_POWER);
    private boolean isPlayerTurn;
    private ArrayList<Trackpad.actions.SpawnDissipatingMonsterAction> deferredSpawns = new ArrayList();

    public ReflectionPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.img = new Texture(IMG);
        this.canGoNegative = false;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(isPlayerTurn) {
            AbstractDungeon.actionManager.addToBottom(new SpawnDissipatingMonsterAction(new Reflection(damageAmount), false, true));
        }
        else
        {
            deferredSpawns.add(new SpawnDissipatingMonsterAction(new Reflection(damageAmount), false, true));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        isPlayerTurn = false;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(!isPlayer)
        {
            if(deferredSpawns.size() > 0)
            {
                while(deferredSpawns.size() > 0)
                {
                    AbstractDungeon.actionManager.addToBottom(deferredSpawns.get(0));
                    deferredSpawns.remove(0);
                }
            }
            isPlayerTurn = true;
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
