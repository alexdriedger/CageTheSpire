package cage_the_spire.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.*;
import com.megacrit.cardcrawl.cards.DamageInfo;

import java.lang.reflect.Field;

// Code Quality
// TODO : MOVE PATCH TO INITIALIZE METHOD (POSTFIX) IN ABSTRACT CARD SO THAT IT IS CALLED ONLY ONCE
// TODO : FIX ANGER NOT RENDERING WITH NICK CAGE ART

// Features
// TODO : MAKE CLOSE UP ART ALSO NICK CAGE

public class AbstractCardHooks {

    private static final String NICK_CAGE_FACE_SMALL_PATH = "nick_cage_face_small.png";
    private static final String NICK_CAGE_REGION_NAME = "status/nickCage";
    private static final String NICK_CAGE_CARD_NAME = "Nick Cage";
    private static final String NICK_CAGE_CARD_DESCRIPTION = "Nick Cage";

    @SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "createCardImage")
    public static class PostCreateCardImageHook {
        private static boolean atlasChanged = false;

        public static void Prefix(Object __obj_instance) {
            System.out.println("Post createCardImage");

            AbstractCard ac = (AbstractCard) __obj_instance;
            try {
                if (!atlasChanged) {
                    System.out.println("Updating TextureAtlas with Nick Cage images");
                    atlasChanged = true;

                    // Create new nick cage texture
                    Texture t = new Texture(Gdx.files.internal(NICK_CAGE_FACE_SMALL_PATH));
                    TextureRegion tr = new TextureRegion(t);

                    // Update artwork
                    Field ca = AbstractCard.class.getDeclaredField("cardAtlas");
                    ca.setAccessible(true);
                    TextureAtlas ta = (TextureAtlas) ca.get(ac);
                    ta.addRegion(NICK_CAGE_REGION_NAME, tr);


                    // Update old artwork
                    Field oldCa = AbstractCard.class.getDeclaredField("oldCardAtlas");
                    oldCa.setAccessible(true);
                    TextureAtlas oldTa = (TextureAtlas) oldCa.get(ac);
                    oldTa.addRegion(NICK_CAGE_REGION_NAME, tr);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Could not add Nick Cage card art to TextureAtlas");
            }
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.cards.AbstractCard",
            method = SpirePatch.CONSTRUCTOR,
            paramtypes = {
                    "java.lang.String",
                    "java.lang.String",
                    "java.lang.String",
                    "java.lang.String",
                    "int",
                    "java.lang.String",
                    "com.megacrit.cardcrawl.cards.AbstractCard$CardType",
                    "com.megacrit.cardcrawl.cards.AbstractCard$CardColor",
                    "com.megacrit.cardcrawl.cards.AbstractCard$CardRarity",
                    "com.megacrit.cardcrawl.cards.AbstractCard$CardTarget",
                    "com.megacrit.cardcrawl.cards.DamageInfo$DamageType"
            }
    )
    public static class AbstractCardConstructorHooks {
        public static void Prefix(Object __obj_instance, String id, String name, String jokeUrl, @ByRef String[] imgUrl, int cost,
                                   String rawDescription, CardType type, CardColor color, CardRarity rarity,
                                   CardTarget target, DamageInfo.DamageType dType) {
            System.out.println("Pre AbstractCard Constructor");
            imgUrl[0] = NICK_CAGE_REGION_NAME;
        }

        public static void Postfix(Object __obj_instance, String id, String name, String jokeUrl, String imgUrl, int cost,
                                   String rawDescription, CardType type, CardColor color, CardRarity rarity,
                                   CardTarget target, DamageInfo.DamageType dType) {
            System.out.println("Post AbstractCard Constructor");

            AbstractCard ac = (AbstractCard) __obj_instance;
            ac.name = NICK_CAGE_CARD_NAME;
            ac.rawDescription = NICK_CAGE_CARD_DESCRIPTION;
        }
    }

    @SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "initializeDescription")
    public static class PreInitializeDescriptionHook {
        public static void Prefix(Object __obj_instance) {
            AbstractCard ac = (AbstractCard) __obj_instance;
            ac.rawDescription = NICK_CAGE_CARD_DESCRIPTION;
        }
    }

}

//@SpirePatch(cls="com.megacrit.cardcrawl.cards.AbstractCard", method="initialize")
//public class AbstractCardHooks {
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
