package cage_the_spire.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import cage_the_spire.CageTheSpire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.lang.reflect.Field;

public class AbstractCardHooks {

    private static final String NICK_CAGE_FACE_SMALL_PATH = "nick_cage_face_small.png";
    private static final String NICK_CAGE_REGION_NAME = "status/nickCage";
    private static final String NICK_CAGE_CARD_NAME = "Nick Cage";
    private static final String NICK_CAGE_CARD_DESCRIPTION = "Nick Cage";

    private static void updateCardAtlas() {
        try {
            System.out.println("Updating TextureAtlas with Nick Cage images");

            // Create new nick cage texture
            Texture t = new Texture(Gdx.files.internal(NICK_CAGE_FACE_SMALL_PATH));
            TextureRegion tr = new TextureRegion(t);

            // Update artwork
            Field ca = AbstractCard.class.getDeclaredField("cardAtlas");
            ca.setAccessible(true);
            TextureAtlas ta = (TextureAtlas) ca.get(null);
            ta.addRegion(NICK_CAGE_REGION_NAME, tr);


            // Update old artwork
            Field oldCa = AbstractCard.class.getDeclaredField("oldCardAtlas");
            oldCa.setAccessible(true);
            TextureAtlas oldTa = (TextureAtlas) oldCa.get(null);
            oldTa.addRegion(NICK_CAGE_REGION_NAME, tr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Could not add Nick Cage card art to TextureAtlas");
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "initialize")
    public static class PostInitializeHook {
        public static void Postfix() {
            updateCardAtlas();
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    int.class,
                    String.class,
                    AbstractCard.CardType.class,
                    AbstractCard.CardColor.class,
                    AbstractCard.CardRarity.class,
                    AbstractCard.CardTarget.class,
                    DamageInfo.DamageType.class
            }
    )
    public static class AbstractCardConstructorHooks {
        public static void Prefix(AbstractCard _instance, String id, String name, String jokeUrl, @ByRef String[] imgUrl, int cost,
                                   String rawDescription, CardType type, CardColor color, CardRarity rarity,
                                   CardTarget target, DamageInfo.DamageType dType) {
            if (CageTheSpire.isCageCosmeticActive() || CageTheSpire.isCageFullActive()) {
                imgUrl[0] = NICK_CAGE_REGION_NAME;
            }
        }

        public static void Postfix(AbstractCard _instance, String id, String name, String jokeUrl, String imgUrl, int cost,
                                   String rawDescription, CardType type, CardColor color, CardRarity rarity,
                                   CardTarget target, DamageInfo.DamageType dType) {
            if (CageTheSpire.isCageFullActive()) {
                _instance.name = NICK_CAGE_CARD_NAME;
                _instance.rawDescription = NICK_CAGE_CARD_DESCRIPTION;
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "initializeDescription")
    public static class PreInitializeDescriptionHook {
        public static void Prefix(AbstractCard _instance) {
            if (CageTheSpire.isCageFullActive()) {
                _instance.rawDescription = NICK_CAGE_CARD_DESCRIPTION;
            }
        }
    }

}