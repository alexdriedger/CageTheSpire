package cage_the_spire.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import cage_the_spire.patches.com.megacrit.cardcrawl.cards.AbstractCard.AbstractCardHooks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import java.lang.reflect.Field;

public class SingleCardViewPopupHooks {

    private static final String NICK_CAGE_FACE_LARGE_PATH = "nick_cage_face_large.png";

    public static void setPortraitImg(SingleCardViewPopup instance) {
        try {
            Field portraitImg = SingleCardViewPopup.class.getDeclaredField("portraitImg");
            portraitImg.setAccessible(true);
            portraitImg.set(instance, ImageMaster.loadImage(NICK_CAGE_FACE_LARGE_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Could not change big card card artwork");
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.screens.SingleCardViewPopup",
            method = "open",
            paramtypes = {
                    "com.megacrit.cardcrawl.cards.AbstractCard"
            }
    )
    public static class PostOpenFirstHook {
        public static void Postfix(SingleCardViewPopup _instance, AbstractCard card) {
            System.out.println("Post open(AbstractCard card)");
            setPortraitImg(_instance);
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.screens.SingleCardViewPopup",
            method = "open",
            paramtypes = {
                    "com.megacrit.cardcrawl.cards.AbstractCard",
                    "com.megacrit.cardcrawl.cards.CardGroup"
            }
    )
    public static class PostOpenSecondHook {
        public static void Postfix(SingleCardViewPopup _instance, AbstractCard card, CardGroup group) {
            System.out.println("Post open(AbstractCard card, CardGroup group)");
            setPortraitImg(_instance);
        }
    }
}
