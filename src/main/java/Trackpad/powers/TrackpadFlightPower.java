package Trackpad.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TrackpadFlightPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = "TrackpadFlightPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private int storedAmount;

    public TrackpadFlightPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "TrackpadFlightPower";
        this.owner = owner;
        this.amount = amount;
        this.storedAmount = amount;
        this.updateDescription();
        this.loadRegion("flight");
        this.priority = 50;
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new TrackpadFlightPower(this.owner, this.amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.amount = this.storedAmount;
        this.updateDescription();
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }

    private float calculateDamageTakenAmount(float damage, DamageType type) {
        return type != DamageType.HP_LOSS && type != DamageType.THORNS ? damage / 2.0F : damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        Boolean willLive = this.calculateDamageTakenAmount((float)damageAmount, info.type) < (float)this.owner.currentHealth;
        if (info.owner != null && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS && damageAmount > 0 && willLive) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
        }

        return damageAmount;
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flight");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
