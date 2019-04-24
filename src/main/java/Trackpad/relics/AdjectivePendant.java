package Trackpad.relics;

import Trackpad.cards.*;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.CodexAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EnvenomPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AdjectivePendant extends CustomRelic implements CustomSavable<String>
{
    public static final String ID = trackpad.makeID("AdjectivePendant");
    public static final String IMG = trackpad.makePath(trackpad.ADJECTIVE_PENDANT);
    public static final String OUTLINE = trackpad.makePath(trackpad.ADJECTIVE_PENDANT_OUTLINE);

    public String chosenAdjective = null;
    private boolean pickCard = false;

    public AdjectivePendant()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.FLAT);

    }

    @Override
    public void atBattleStart() {
        if(this.chosenAdjective != null)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));

            switch(chosenAdjective)
            {
                case "trackpad:StrongOption":
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                    new StrengthPower(AbstractDungeon.player, 2), 2));
                    break;
                case "trackpad:SwiftOption":
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                    new DexterityPower(AbstractDungeon.player, 2), 2));
                    break;
                case "trackpad:HealthyOption":
                    AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
                    break;
                case "trackpad:EnergeticOption":
                    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
                    break;
                case "trackpad:WealthyOption":
                    AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                        public void update() {
                            CardCrawlGame.sound.play("GOLD_GAIN");
                            AbstractDungeon.player.gainGold(8);
                            this.isDone = true;
                        }
                    });
                    break;
                case "trackpad:CreativeOption":
                    AbstractDungeon.actionManager.addToBottom(new CodexAction());
                    break;
                default:
                    trackpad.logger.warn("Somehow reached default statement with Adjective Pendant. Please Report to Left Click.");
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Somehow reached default statement with Pendant. Please Report to Left Click.", 1.0F, 2.0F));
            }
        }
    }


    @Override
    public String getUpdatedDescription()
    {
        if (this.chosenAdjective == null) {
            return DESCRIPTIONS[0];
        } else {
            String ID = chosenAdjective;
            CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
            return cardStrings.DESCRIPTION;
        }
    }

    @Override
    public String onSave()
    {
        if (chosenAdjective == null) {
            return null;
        }
        else {
            return chosenAdjective;
        }
    }

    @Override
    public void onLoad(String chosenAdjective)
    {
        if (chosenAdjective == null) {
            return;
        }
        if(!chooseAdjective(chosenAdjective))
        {
            trackpad.logger.warn("Something went wrong trying to load the saved adjective for AdjectivePendant");
        }
    }

    @Override
    public void onEquip()
    {
        flash();

        this.pickCard = true;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        group.addToTop(new PendantCreative());
        group.addToTop(new PendantEnergetic());
        group.addToTop(new PendantHealthy());
        group.addToTop(new PendantStrong());
        group.addToTop(new PendantSwift());
        group.addToTop(new PendantWealthy());

        AbstractDungeon.gridSelectScreen.open(group, 1, "Select an Adjective to add onto the Pendant.", false);
    }

    @Override
    public void update()
    {
        super.update();

        if (this.pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.pickCard = false;
            AbstractCard selected = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            chooseAdjective(selected.cardID);
        }
    }

    private boolean chooseAdjective(String chosenAdjective)
    {
        this.chosenAdjective = chosenAdjective;
        if (chosenAdjective == null) {
            if (CardCrawlGame.dungeon == null) {
                trackpad.logger.warn("Dungeon is null");
            }
            return false;
        }
        description = getUpdatedDescription();
        tips.clear();
        String ID = chosenAdjective;
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        String NAME = cardStrings.NAME;
        String DESCRIPTION = cardStrings.DESCRIPTION;


        tips.add(new PowerTip( NAME + " " + name, DESCRIPTION));
        initializeTips();

        return true;
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new AdjectivePendant();
    }
}