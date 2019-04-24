package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ChainingVapors extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("ChainingVapors");
    public static final String IMG = trackpad.makePath(trackpad.CHAINING_VAPORS);
    public static final String OUTLINE = trackpad.makePath(trackpad.CHAINING_VAPORS_OUTLINE);

    private static boolean isActive = false;

    public ChainingVapors() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.CLINK);
    }


    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if(isActive)
        {
            this.counter++;
            if(this.counter > 4)
            {
                AbstractMonster randomMonster = AbstractDungeon.getCurrRoom().monsters.monsters.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.getCurrRoom().monsters.monsters.size()-1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(randomMonster, AbstractDungeon.player, new PoisonPower(randomMonster, AbstractDungeon.player, 1), 1));
            }
        }
    }

    @Override
    public void atTurnStart()
    {
        this.counter = 0;
        isActive = false;
    }

    @Override
    public void atTurnStartPostDraw() {
        this.counter = 0;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                ChainingVapors.isActive = true;
                this.isDone = true;
            }
        });
    }

    @Override
    public void onPlayerEndTurn() {
        isActive = false;
    }

    @Override
    public void onEquip()
    {
        isActive = false;
    }

    @Override
    public void onVictory()
    {
        isActive = false;
        this.counter = -1;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new ChainingVapors();
    }
}
