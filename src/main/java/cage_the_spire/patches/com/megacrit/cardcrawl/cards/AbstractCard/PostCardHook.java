package cage_the_spire.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.lang.reflect.Field;

// Code Quality
// TODO : MOVE PATCH TO INITIALIZE METHOD (POSTFIX) IN ABSTRACT CARD SO THAT IT IS CALLED ONLY ONCE
// TODO : FIX ANGER NOT RENDERING WITH NICK CAGE ART
// TODO : MOVE NAME AND DESCRIPTION CODE TO CONSTRUCTOR POSTFIX PATCH

// Portability
// TODO : WRITE A SCRIPT THAT COMBINES BOTH CARD.ATLAS (FROM NEW AND OLD) AND THEN CHANGES ALL XY: #, # TO BE XY: 0, 0
// TODO : MOVE NEW CARTATLAS AND CARD ASSETS INTO MOD JAR

// Features
// TODO : MAKE CLOSE UP ART ALSO NICK CAGE
// TODO : WHEN CARDS UPGRADE, KEEP DESCRIPTION AS "NICK CAGE"
@SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "createCardImage")
public class PostCardHook {
    private static boolean atlasChanged = false;

    public static void Prefix(Object __obj_instance) {
        System.out.println("Post create card image");

        AbstractCard ac = (AbstractCard) __obj_instance;
        ac.name = "Nick Cage";
        ac.rawDescription = "Nick Cage";
        try {
            if (!atlasChanged) {
                System.out.println("Atlas has not been changed from original");
                atlasChanged = true;

                // New artwork
                Field ca = AbstractCard.class.getDeclaredField("cardAtlas");
                ca.setAccessible(true);

                System.out.println("Creating new TextureAtlas");
                TextureAtlas ta = new TextureAtlas("mods/cards.atlas");
                ca.set(ac, ta);

                // Old Artwork
                Field oldCa = AbstractCard.class.getDeclaredField("oldCardAtlas");
                oldCa.setAccessible(true);
                oldCa.set(ca, ta);

                System.out.println("Set new TextureAtlas");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Could not change card atlas with reflection post");
        }
    }
}

//@SpirePatch(cls="com.megacrit.cardcrawl.cards.AbstractCard", method="initialize")
//public class PostCardHook {
//    public static void Prefix(Object __obj_instance) {
//        System.out.println("\n\n\n\nInitializing card atlas\n\n\n\n");
//        AbstractCard ac = (AbstractCard) __obj_instance;
//        try {
//            Field ca = AbstractCard.class.getDeclaredField("cardAtlas");
//            ca.setAccessible(true);
//            TextureAtlas ta = new TextureAtlas("mods/cards.atlas");
//            ca.set(ac, ta);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            System.out.println("Could not change card atlas with reflection post");
//        }
//    }
//}

//@SpirePatch(
//        cls="com.megacrit.cardcrawl.cards.AbstractCard",
//        method="ctor",
//        paramtypes={
//                "java.lang.String",
//                "java.lang.String",
//                "java.lang.String",
//                "java.lang.String",
//                "int",
//                "java.lang.String",
//                "com.megacrit.cardcrawl.cards.AbstractCard$CardType",
//                "com.megacrit.cardcrawl.cards.AbstractCard$CardColor",
//                "com.megacrit.cardcrawl.cards.AbstractCard$CardRarity",
//                "com.megacrit.cardcrawl.cards.AbstractCard$CardTarget",
//                "com.megacrit.cardcrawl.cards.DamageInfo$DamageType"
//        }
//)
//public class PostCardHook {
//    public static void Postfix(Object __obj_instance, String id, String name, String jokeUrl, String imgUrl, int cost,
//                              String rawDescription, CardType type, CardColor color, CardRarity rarity,
//                              CardTarget target, DamageType dType) {
//        System.out.println("Post create card constructor");
//        AbstractCard ac = (AbstractCard) __obj_instance;
//        ac.assetURL = "mods/nick_cage_face.jpg";
//        try {
//            Field portraitImg = AbstractCard.class.getDeclaredField("portraitImg");
//            portraitImg.setAccessible(true);
//            portraitImg.set(ac, ImageMaster.loadImage("mods/nick_cage_face.jpg"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            System.out.println("Could not set card image with reflection post");
//        }
//    }
//}
