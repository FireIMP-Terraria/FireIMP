package net.fireimp.server.datatypes.enums;

/**
 * Details all buffs and debuffs in game
 */

@SuppressWarnings(value = "unused")
public enum Buff {


    /*
     * Potion effects
     */
    OBSIDIAN_SKIN(1, BuffCategory.POTION),
    REGENERATION(2, BuffCategory.POTION),
    SWIFTNESS(3, BuffCategory.POTION),
    GILLS(4, BuffCategory.POTION),
    HEART_LAMP(89, BuffCategory.POTION),
    IRONSKIN(5, BuffCategory.POTION),
    MAGIC_POWER(7, BuffCategory.POTION),
    MANA_REGENERATION(6, BuffCategory.POTION),
    MANA_SICKNESS(94, BuffCategory.POTION),
    POTION_SICKNESS(21, BuffCategory.POTION),
    FEATHERFALL(8, BuffCategory.POTION),
    SPELUNKER(9, BuffCategory.POTION),
    INVISIBILITY(10, BuffCategory.POTION),
    SHINE(11, BuffCategory.POTION),
    NIGHHT_OWL(12, BuffCategory.POTION),
    BATTLE(13, BuffCategory.POTION),
    THORNS(14, BuffCategory.POTION),
    WATER_WALKING(15, BuffCategory.POTION),
    ARCHERY(16, BuffCategory.POTION),
    HUNTER(17, BuffCategory.POTION),
    GRAVITATION(18, BuffCategory.POTION),
    TIPSY(25, BuffCategory.POTION),
    WELL_FED(26, BuffCategory.POTION),
    MINING(104, BuffCategory.POTION),
    HEARTREACH(105, BuffCategory.POTION),
    CALM(106, BuffCategory.POTION),
    BUILDER(107, BuffCategory.POTION),
    TITAN(108, BuffCategory.POTION),
    FLIPPER(109, BuffCategory.POTION),
    SUMMONING(110, BuffCategory.POTION),
    DANGERSENSE(111, BuffCategory.POTION),
    AMMO_RSERVATION(112, BuffCategory.POTION),
    LIFEFORCE(113, BuffCategory.POTION),
    ENDURANCE(114, BuffCategory.POTION),
    RAGE(115, BuffCategory.POTION),
    INFERNO(116, BuffCategory.POTION),
    WRATH(117, BuffCategory.POTION),
    STINKY(120, BuffCategory.POTION),
    FISHING(121, BuffCategory.POTION),
    SONAR(122, BuffCategory.POTION),
    CRATE(123, BuffCategory.POTION),
    WARMTH(124, BuffCategory.POTION),

    /*
     * Accessory/Item buffs
     */
    AMMO_BOX(93, BuffCategory.ACCESSORY),
    BEWITCHED(150, BuffCategory.ACCESSORY),
    WEREWORLF(28, BuffCategory.ACCESSORY),
    CLAIRVOYANCE(29, BuffCategory.ACCESSORY),
    ICE_BARRIER(62, BuffCategory.ACCESSORY),
    LOVESTRUCK(119, BuffCategory.ACCESSORY),
    MERFOLK(34, BuffCategory.ACCESSORY),
    MONSTER_BANNER(147, BuffCategory.ACCESSORY),
    PALADINS_SHIELD(43, BuffCategory.ACCESSORY),
    PANIC(63, BuffCategory.ACCESSORY),
    PEACE_CANDLE(157, BuffCategory.ACCESSORY),
    SHARPENED(159, BuffCategory.ACCESSORY),
    SOUL_DRAIN(151, BuffCategory.ACCESSORY),
    STARR_IN_BOTTLE(158, BuffCategory.ACCESSORY),
    WATER_CANDLE(86, BuffCategory.ACCESSORY),

    /*
     * Pets/Minions/Mounts
     */
    BABY_DINOSAUR(61, BuffCategory.SUMMONABLE),
    BABY_EATER(45, BuffCategory.SUMMONABLE), //Not as bad as it sounds.
    BABY_FACE_MONSTER(154, BuffCategory.SUMMONABLE),
    BABY_GRINCH(92, BuffCategory.SUMMONABLE),
    BABY_HORNET(51, BuffCategory.SUMMONABLE),
    BABY_PENGUIN(41, BuffCategory.SUMMONABLE),
    BABY_SKELETON_HEAD(50, BuffCategory.SUMMONABLE),
    BABY_SLIME(64, BuffCategory.SUMMONABLE),
    BABY_SNOWMAN(66, BuffCategory.SUMMONABLE),
    BABY_TRUFFLE(55, BuffCategory.SUMMONABLE),
    BEE_MOUNT(132, BuffCategory.SUMMONABLE),
    BLACK_CAT(84, BuffCategory.SUMMONABLE),
    BUNNY_MOUNT(128, BuffCategory.SUMMONABLE),
    CURSED_SAPLING(85, BuffCategory.SUMMONABLE),
    CUTE_FISHRON_MOUNT(168, BuffCategory.SUMMONABLE),
    DEADLY_SPHERE(161, BuffCategory.SUMMONABLE),
    DRILL_MOUNT(142, BuffCategory.SUMMONABLE),
    EYEBALL_SPRING(62, BuffCategory.SUMMONABLE),
    HORNET_MINION(125, BuffCategory.SUMMONABLE),
    IMP_MINION(126, BuffCategory.SUMMONABLE),
    MINECART_LEFT(118, BuffCategory.SUMMONABLE),
    MINECART_LEFT_MECH(167, BuffCategory.SUMMONABLE),
    MINECART_LEFT_WOOD(184, BuffCategory.SUMMONABLE),
    MINECART_RIGHT(138, BuffCategory.SUMMONABLE),
    MINECART_RIGHT_MECH(166, BuffCategory.SUMMONABLE),
    MINECART_RIGHT_WOOD(185, BuffCategory.SUMMONABLE),
    PET_BUNNY(40, BuffCategory.SUMMONABLE),
    PET_TURTLE(42, BuffCategory.SUMMONABLE),
    PET_SPIDER(81, BuffCategory.SUMMONABLE),
    PET_LIZARD(53, BuffCategory.SUMMONABLE),
    PET_PARROT(54, BuffCategory.SUMMONABLE),
    PET_SAPLING(56, BuffCategory.SUMMONABLE),
    PIGRON_MOUNT(129, BuffCategory.SUMMONABLE),
    PIRATE_MINION(135, BuffCategory.SUMMONABLE),
    PUPPY(91, BuffCategory.SUMMONABLE),
    PYGMIES(49, BuffCategory.SUMMONABLE),
    RAVENS(83, BuffCategory.SUMMONABLE),
    RUDOLPH(90, BuffCategory.SUMMONABLE),
    SCUTLIX_MOUNT(143, BuffCategory.SUMMONABLE),
    SHARKNADO_MINION(139, BuffCategory.SUMMONABLE),
    SLIME_MOUNT(130, BuffCategory.SUMMONABLE),
    SPIDER_MINION(133, BuffCategory.SUMMONABLE),
    SQUASHLING(82, BuffCategory.SUMMONABLE),
    STARDUST_DRAGON_MINION(188, BuffCategory.SUMMONABLE),
    STARDUST_GUARDIAN_MINION(187, BuffCategory.SUMMONABLE),
    SUSPICIOUS_TENTACLE(190, BuffCategory.SUMMONABLE),
    TIKI_SPIRIT(52, BuffCategory.SUMMONABLE),
    TURTLE_MOUNT(131, BuffCategory.SUMMONABLE),
    TWIN_EYES_MINION(134, BuffCategory.SUMMONABLE),
    UFO_MOUNT(141, BuffCategory.SUMMONABLE),
    UFO_MINION(140, BuffCategory.SUMMONABLE),
    UNICORN_MOUNT(162, BuffCategory.SUMMONABLE),

    /*
     * Light sources
     */
    CRIMSON_HEART(155, BuffCategory.LIGHT_SUMMONABLE),
    FAIRY_BLUE(27, BuffCategory.LIGHT_SUMMONABLE),
    FAIRY_GREEN(102, BuffCategory.LIGHT_SUMMONABLE),
    FAIRY_RED(101, BuffCategory.LIGHT_SUMMONABLE),
    MAGIC_LANTERN(152, BuffCategory.LIGHT_SUMMONABLE),
    SHADOW_ORB(19, BuffCategory.LIGHT_SUMMONABLE),
    WISP(57, BuffCategory.LIGHT_SUMMONABLE),

    /*
     * Positive buffs not invoked by potions, accessories, or items
     */
    BEETLE_ENDURANCE_1(95, BuffCategory.POSITIVE_ENVIRONMENTAL),
    BEETLE_ENDURANCE_2(96, BuffCategory.POSITIVE_ENVIRONMENTAL),
    BEETLE_ENDURANCE_3(97, BuffCategory.POSITIVE_ENVIRONMENTAL),
    BEETLE_MIGHT_1(98, BuffCategory.POSITIVE_ENVIRONMENTAL),
    BEETLE_MIGHT_2(99, BuffCategory.POSITIVE_ENVIRONMENTAL),
    BEETLE_MIGHT_3(100, BuffCategory.POSITIVE_ENVIRONMENTAL),
    CAMPFIRE(87, BuffCategory.POSITIVE_ENVIRONMENTAL),
    CHAOS_STATE(88, BuffCategory.POSITIVE_ENVIRONMENTAL),
    DRYADS_WARD(165, BuffCategory.POSITIVE_ENVIRONMENTAL),
    HONEY(48, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_LIFE_1(173, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_LIFE_2(174, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_LIFE_3(175, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_MANA_1(176, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_MANA_2(177, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_MANA_3(178, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_DMG_1(179, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_DMG_2(180, BuffCategory.POSITIVE_ENVIRONMENTAL),
    NEBULA_UP_DMG_3(181, BuffCategory.POSITIVE_ENVIRONMENTAL),
    RAPID_HEALING(58, BuffCategory.POSITIVE_ENVIRONMENTAL),
    SHADOW_DODGE(59, BuffCategory.POSITIVE_ENVIRONMENTAL),
    SOLAR_SHEILD_1(170, BuffCategory.POSITIVE_ENVIRONMENTAL),
    SOLAR_SHEILD_2(171, BuffCategory.POSITIVE_ENVIRONMENTAL),
    SOLAR_SHEILD_3(172, BuffCategory.POSITIVE_ENVIRONMENTAL),
    STARDUST_MINION(182, BuffCategory.POSITIVE_ENVIRONMENTAL),
    STARDUST_MINION_BLEED(181, BuffCategory.POSITIVE_ENVIRONMENTAL),
    SUNFLOWER(146, BuffCategory.POSITIVE_ENVIRONMENTAL),
    LEAF_CRYSTAL(60, BuffCategory.POSITIVE_ENVIRONMENTAL),

    /*
     * Negative buffs not invoked by potions
     */
    BLEEDING(30, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    BLACKOUT(80, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    BONE_JAVELIN(169, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    BROKEN_ARMOR(36, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    BURNING(67, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    CHILLED(46, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    CONFUSED(31, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    CURSED(23, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    CURSED_INFERNO(39, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    DARKNESS(22, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    DAYBREAK(189, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    DAZED(160, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    DRYADS_WARD_DEBUFF(186, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    ELECTRIFIED(144, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    FROST_BURN(44, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    FROZEN(47, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    HORRIFIED(37, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    ICHOR(69, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    MOON_LEECH(145, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    SHADOW_FLAME(153, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    STONED(156, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    OBSTRUCTED(163, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    ON_FIRE(24, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    POISONED(20, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    RABIES(148, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    SLOW(32, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    SILENCED(35, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    SLIMED(137, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    SUFFOCATION(68, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    THE_TOUNGE(38, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    VENOM(70, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    VORTEX_DEBUFF(164, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    WEAK(33, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    WEBBED(149, BuffCategory.NEGATIVE_ENVIRONMENTAL),
    WET(103, BuffCategory.NEGATIVE_ENVIRONMENTAL),

    /*
     * Weapon Imbued effects
     */
    WEAPON_IMBUE_CURSED_FLAMES(73, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_CONFETTI(78, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_FIRE(74, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_GOLD(75, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_ICHOR(76, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_NANITES(77, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_POISON(79, BuffCategory.WEAPON_IMBUED),
    WEAPON_IMBUE_VENOM(71, BuffCategory.WEAPON_IMBUED);

    private int netId;
    private BuffCategory category;
    Buff(int networkId, BuffCategory category) {
        this.netId = networkId;
        this.category = category;
    }

}
