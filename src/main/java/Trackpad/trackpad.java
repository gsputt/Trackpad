package Trackpad;

import Trackpad.relics.*;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.monsters.city.Snecko;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpireInitializer
public class trackpad implements
        PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber
        {
    //PrePlayerUpdateSubscriber, OnReceivePowerPower

    public static final Logger logger = LogManager.getLogger(trackpad.class.getName());

    //MOD SETTINGS PANEL
    private static final String MODNAME = "Trackpad";
    private static final String AUTHOR = "Left Click, ComingVirus";
    private static final String DESCRIPTION = "A general expansion mod.";
    //MOD SETTINGS PANEL

    // =============== INPUT TEXTURE LOCATION =================

    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // Image folder name - This is where your image folder is.
    // This is good practice in case you ever need to move/rename it without screwing up every single path.
    // In this case, it's resources/TrackpadResources/images (and then, say, /cards/Strike.png).

    private static final String TRACKPAD_MOD_ASSETS_FOLDER = "TrackpadResources/images";

    // Card backgrounds
    /*private static final String ATTACK_TRACKPAD_BLUE = "512/bg_attack_scribe_blue.png";
    private static final String POWER_TRACKPAD_BLUE = "512/bg_power_scribe_blue.png";
    private static final String SKILL_TRACKPAD_BLUE = "512/bg_skill_scribe_blue.png";
    private static final String ENERGY_ORB_TRACKPAD_BLUE = "512/card_scribe_blue_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_TRACKPAD_BLUE_PORTRAIT = "1024/bg_attack_scribe_blue.png";
    private static final String POWER_TRACKPAD_BLUE_PORTRAIT = "1024/bg_power_scribe_blue.png";
    private static final String SKILL_TRACKPAD_BLUE_PORTRAIT = "1024/bg_skill_scribe_blue.png";
    private static final String ENERGY_ORB_TRACKPAD_BLUE_PORTRAIT = "1024/card_scribe_blue_orb.png";
    */

    // Card images
    //public static final String SCRIBE_STARTER_ATTACK = "cards/Strike.png";

    // Power images
    //public static final String COMMON_POWER = "powers/placeholder_power.png";
    public static final String SHATTER_POWER = "powers/ShatterPower.png";
    public static final String DISSIPATING_POWER = "powers/DissipatingPower.png";
    public static final String REFLECTION_POWER = "powers/ReflectionPower.png";
    public static final String FOCUSING_LENS_POWER = "powers/FocusingLensPower.png";
    public static final String LUCKY_POWER = "powers/LuckyPower.png";


    // Relic images
    public static final String PLACEHOLDER_RELIC_2 = "relics/placeholder_relic2.png";
    public static final String PLACEHOLDER_RELIC_OUTLINE_2 = "relics/outline/placeholder_relic2.png";
    public static final String POCKET_GALAXY = "relics/PocketGalaxy.png";
    public static final String POCKET_GALAXY_OUTLINE = "relics/outline/PocketGalaxyOutline.png";
    public static final String WONDROUS_WAND = "relics/WondrousWand.png";
    public static final String WONDROUS_WAND_OUTLINE = "relics/outline/WondrousWandOutline.png";
    public static final String DISASTER_IN_A_BOTTLE = "relics/DisasterInABottle.png";
    public static final String DISASTER_IN_A_BOTTLE_OUTLINE = "relics/outline/DisasterInABottleOutline.png";
    public static final String VOID_SPHERE = "relics/VoidSphere.png";
    public static final String VOID_SPHERE_OUTLINE = "relics/outline/VoidSphereOutline.png";
    public static final String ZOMBIE_HEART = "relics/ZombieHeart.png";
    public static final String ZOMBIE_HEART_OUTLINE = "relics/outline/ZombieHeartOutline.png";
    public static final String ADJECTIVE_PENDANT = "relics/AdjectivePendant.png";
    public static final String ADJECTIVE_PENDANT_OUTLINE = "relics/outline/AdjectivePendantOutline.png";
    public static final String CULTIST_FIGURINE = "relics/CultistFigurine.png";
    public static final String CULTIST_FIGURINE_OUTLINE = "relics/outline/CultistFigurineOutline.png";
    public static final String COMPENDIUM = "relics/Compendium.png";
    public static final String COMPENDIUM_OUTLINE = "relics/outline/CompendiumOutline.png";
    public static final String PET_ROCK = "relics/PetRock.png";
    public static final String PET_ROCK_OUTLINE = "relics/outline/PetRockOutline.png";
    public static final String WIND_UP_AUTO_DEALER = "relics/WindUpAutoDealer.png";
    public static final String WIND_UP_AUTO_DEALER_OUTLINE = "relics/outline/WindUpAutoDealerOutline.png";
    public static final String MOUSETRAP = "relics/Mousetrap.png";
    public static final String MOUSETRAP_OUTLINE = "relics/outline/MousetrapOutline.png";
    public static final String GUARDIAN_SPIRIT = "relics/GuardianSpirit.png";
    public static final String GUARDIAN_SPIRIT_OUTLINE = "relics/outline/GuardianSpiritOutline.png";
    public static final String HAND_OF_MIDAS = "relics/HandOfMidas.png";
    public static final String HAND_OF_MIDAS_OUTLINE = "relics/outline/HandOfMidasOutline.png";
    public static final String SAPPING_STONE = "relics/SappingStone.png";
    public static final String SAPPING_STONE_OUTLINE = "relics/outline/SappingStoneOutline.png";
    public static final String SNECKO_SCEPTER = "relics/SneckoScepter.png";
    public static final String SNECKO_SCEPTER_OUTLINE = "relics/outline/SneckoScepterOutline.png";
    public static final String BYRD_CHARM = "relics/ByrdCharm.png";
    public static final String BYRD_CHARM_OUTLINE = "relics/outline/ByrdCharmOutline.png";
    public static final String TOUGH_GLOVES = "relics/ToughGloves.png";
    public static final String TOUGH_GLOVES_OUTLINE = "relics/outline/ToughGlovesOutline.png";
    public static final String BOTTLED_TSUNAMI = "relics/BottledTsunami.png";
    public static final String BOTTLED_TSUNAMI_OUTLINE = "relics/outline/BottledTsunamiOutline.png";
    public static final String RAPID_FIRE = "relics/RapidFire.png";
    public static final String RAPID_FIRE_OUTLINE = "relics/outline/RapidFireOutline.png";
    public static final String BOTTLED_SHADOW = "relics/BottledShadow.png";
    public static final String BOTTLED_SHADOW_OUTLINE = "relics/outline/BottledShadowOutline.png";
    public static final String WRITERS_BLOCK = "relics/WritersBlock.png";
    public static final String WRITERS_BLOCK_OUTLINE = "relics/outline/WritersBlockOutline.png";
    public static final String NEUTRINO_GENERATOR = "relics/NeutrinoGenerator.png";
    public static final String NEUTRINO_GENERATOR_OUTLINE = "relics/NeutrinoGenerator.png";
    public static final String STAINLESS_STEEL = "relics/StainlessSteel.png";
    public static final String STAINLESS_STEEL_OUTLINE = "relics/outline/StainlessSteelOutline.png";
    public static final String CHAINING_VAPORS = "relics/ChainingVapors.png";
    public static final String CHAINING_VAPORS_OUTLINE = "relics/outline/ChainingVaporsOutline.png";
    public static final String WEIGHTLESS_SHIVS = "relics/WeightlessShivs.png";
    public static final String WEIGHTLESS_SHIVS_OUTLINE = "relics/outline/WeightlessShivsOutline.png";
    public static final String ANCESTRAL_ARMAMENTS = "relics/AncestralArmaments.png";
    public static final String ANCESTRAL_ARMAMENTS_OUTLINE = "relics/outline/AncestralArmamentsOutline.png";
    public static final String ICICLE_PRECEPTS = "relics/IciclePrecepts.png";
    public static final String ICICLE_PRECEPTS_OUTLINE = "relics/outline/IciclePreceptsOutline.png";
    public static final String FOCUSING_LENS = "relics/FocusingLens.png";
    public static final String FOCUSING_LENS_OUTLINE = "relics/outline/FocusingLensOutline.png";
    public static final String BOTTLED_ANGER = "relics/BottledAnger.png";
    public static final String BOTTLED_ANGER_OUTLINE = "relics/outline/BottledAngerOutline.png";
    public static final String BERSERKERS_DRAUGHT = "relics/BerserkersDraught.png";
    public static final String BERSERKERS_DRAUGHT_OUTLINE = "relics/outline/BerserkersDraughtOutline.png";
    public static final String BIPHASE_BIPLANE = "relics/BiPhaseBiplane.png";
    public static final String BIPHASE_BIPLANE_OUTLINE = "relics/outline/BiPhaseBiplaneOutline.png";
    public static final String MIRACLE_GROW = "relics/MiracleGrow.png";
    public static final String MIRACLE_GROW_OUTLINE = "relics/outline/MiracleGrowOutline.png";
    public static final String STOLEN_KIDNEYS = "relics/StolenKidneys.png";
    public static final String STOLEN_KIDNEYS_OUTLINE = "relics/outline/StolenKidneysOutline.png";
    public static final String DEMON_HEART = "relics/DemonHeart.png";
    public static final String DEMON_HEART_OUTLINE = "relics/outline/DemonHeartOutline.png";
    public static final String RIGGED_DICE = "relics/RiggedDice.png";
    public static final String RIGGED_DICE_OUTLINE = "relics/outline/RiggedDiceOutline.png";

    //Custom VFX


    //Mod Badge
    public static final String BADGE_IMAGE = "Badge.png";

    // =============== /INPUT TEXTURE LOCATION/ =================

    // =============== IMAGE PATHS =================

    // This is the command that will link up your core assets folder (line 89) ("TrackpadResources/images")
    // together with the card image (everything above) ("cards/Attack.png") and it puts a "/" between them.
    // When adding a card image, you can, in fact, just do "TrackpadResources/images/cards/Attack.png" in the actual card file.
    // This however, is good practice in case you want to change your "/images" folder at any point in time.

    /**
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
        return TRACKPAD_MOD_ASSETS_FOLDER + "/" + resource;
    }

    // =============== /IMAGE PATHS/ =================

    // =============== SUBSCRIBE, CREATE THE COLOR, INITIALIZE =================

    public trackpad() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);

        logger.info("Done subscribing");

    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("Initializing trackpad Mod. Feed Cookies to continue");
        trackpad The_Scribe = new trackpad();
        logger.info(" trackpad Mod Initialized - Cookies have been fed");

    }

    // ============== /SUBSCRIBE, CREATE THE COLOR, INITIALIZE/ =================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        // Add the Custom Dynamic Variables

        //BaseMod.addDynamicVariable(new StrikeLightning());


        logger.info("Adding cards");
        // Add the cards
        /*
        BaseMod.addCard(new DefaultCommonPower());
        BaseMod.addCard(new DefaultUncommonSkill());
        BaseMod.addCard(new DefaultUncommonAttack());
        BaseMod.addCard(new DefaultUncommonPower());
        BaseMod.addCard(new DefaultRareAttack());
        BaseMod.addCard(new DefaultRareSkill());
        BaseMod.addCard(new DefaultRarePower());*/

        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        /*UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
        UnlockTracker.unlockCard(StarterDefend.ID);
        UnlockTracker.unlockCard(DefaultCommonPower.ID);
        UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
        UnlockTracker.unlockCard(DefaultUncommonAttack.ID);
        UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        UnlockTracker.unlockCard(DefaultRareAttack.ID);
        UnlockTracker.unlockCard(DefaultRareSkill.ID);
        UnlockTracker.unlockCard(DefaultRarePower.ID);*/

        logger.info("Done adding cards!");
    }

    // ================ /ADD CARDS/ ===================

    // ================ ADD POTIONS ===================


    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        // Class Specific Potion. If you want your potion to not be class-specific, just remove the player class at the end (in this case the "TheScribeEnum.THE_SCRIBE")
        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheScribeEnum.THE_SCRIBE);

        logger.info("Done editing potions");
    }

    // ================ /ADD POTIONS/ ===================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        BaseMod.addRelic(new PocketGalaxy(), RelicType.SHARED);
        BaseMod.addRelic(new WondrousWand(), RelicType.SHARED);
        BaseMod.addRelic(new DisasterInABottle(), RelicType.SHARED);
        BaseMod.addRelic(new VoidSphere(), RelicType.SHARED);
        BaseMod.addRelic(new ZombieHeart(), RelicType.SHARED);
        BaseMod.addRelic(new AdjectivePendant(), RelicType.SHARED);
        BaseMod.addRelic(new CultistFigurine(), RelicType.SHARED);
        BaseMod.addRelic(new Compendium(), RelicType.SHARED);
        BaseMod.addRelic(new PetRock(), RelicType.SHARED);
        BaseMod.addRelic(new WindUpAutoDealer(), RelicType.SHARED);
        BaseMod.addRelic(new Mousetrap(), RelicType.SHARED);
        BaseMod.addRelic(new GuardianSpirit(), RelicType.SHARED);
        BaseMod.addRelic(new HandOfMidas(), RelicType.SHARED);
        BaseMod.addRelic(new SappingStone(), RelicType.SHARED);
        BaseMod.addRelic(new SneckoScepter(), RelicType.SHARED);
        BaseMod.addRelic(new ByrdCharm(), RelicType.SHARED);
        BaseMod.addRelic(new ToughGloves(), RelicType.SHARED);
        BaseMod.addRelic(new BottledTsunami(), RelicType.SHARED);
        BaseMod.addRelic(new RapidFire(), RelicType.SHARED);
        BaseMod.addRelic(new BottledShadow(), RelicType.SHARED);
        BaseMod.addRelic(new WritersBlock(), RelicType.SHARED);
        BaseMod.addRelic(new NeutrinoGenerator(), RelicType.BLUE);
        BaseMod.addRelic(new StainlessSteel(), RelicType.RED);
        BaseMod.addRelic(new ChainingVapors(), RelicType.GREEN);
        BaseMod.addRelic(new WeightlessShivs(), RelicType.GREEN);
        BaseMod.addRelic(new AncestralArmaments(), RelicType.GREEN);
        BaseMod.addRelic(new IciclePrecepts(), RelicType.BLUE);
        BaseMod.addRelic(new FocusingLens(), RelicType.BLUE);
        BaseMod.addRelic(new BottledAnger(), RelicType.RED);
        BaseMod.addRelic(new BerserkersDraught(), RelicType.RED);
        BaseMod.addRelic(new BiPhaseBiplane(), RelicType.SHARED);
        BaseMod.addRelic(new MiracleGrow(), RelicType.BLUE);
        BaseMod.addRelic(new StolenKidneys(), RelicType.SHARED);
        BaseMod.addRelic(new DemonHeart(), RelicType.RED);
        BaseMod.addRelic(new RiggedDice(), RelicType.GREEN);

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        //BaseMod.addRelicToCustomPool(new StarterRelic2(), AbstractCardEnum.SCRIBE_BLUE);
        // This adds a relic to the Shared pool. Every character can find this relic.
        //BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);

        logger.info("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================

    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "TrackpadResources/localization/Trackpad-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "TrackpadResources/localization/Trackpad-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                "TrackpadResources/localization/Trackpad-Relic-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                "TrackpadResources/localization/Trackpad-Potion-Strings.json");

        /*BaseMod.loadCustomStringsFile(MonsterStrings.class,
                "TrackpadResources/localization/Trackpad-Monster-Strings.json");*/

        logger.info("Done editting strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        final String[] Envenom = {"Envenom"};
        BaseMod.addKeyword("trackpad", "Envenom", Envenom, "Whenever an attack deals unblocked damage, apply 1 Poison.");

        final String[] Spectral = {"Spectral", "spectral"};
        BaseMod.addKeyword("trackpad", "Spectral", Spectral, "Purged from your hand at the end of your turn.");

        /*
        final String[] Cast = { "Cast", "casts", "cast"};
        BaseMod.addKeyword("scribe", "Cast", Cast, "Use up all your stored Spell Effects and Spell Modifiers to cause effects. Additionally causes Scribed Scrolls to stop being added to your hand until another Spell Effect is played.");
        */
    }

    // ================ /LOAD THE KEYWORDS/ ===================

    // =============== POST-INITIALIZE =================


    @Override
    public void receivePostInitialize() {

        logger.info("Loading badge image and mod options");
        // Load the Mod Badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMAGE));

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

            settingsPanel.addUIElement(new ModLabel("trackpad doesn't have any settings!", 400.0f, 700.0f,
                    settingsPanel, (me) -> {
            }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");
    }

    // =============== / POST-INITIALIZE/ =================

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return "trackpad:" + idText;
    }

}