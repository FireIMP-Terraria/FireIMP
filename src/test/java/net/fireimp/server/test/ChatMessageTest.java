package net.fireimp.server.test;

import net.fireimp.server.datatypes.chat.MessageSection;
import net.fireimp.server.datatypes.enums.Achievement;
import net.fireimp.server.datatypes.enums.ItemPrefix;
import org.junit.Assert;
import org.junit.Test;

public class ChatMessageTest {
    @Test
    public void testAchievementEncode() {
        MessageSection testSection = new MessageSection().setAchievement(Achievement.GET_A_LIFE);
        Assert.assertEquals(testSection.getMessage(), "[a:GET_A_LIFE]");
    }

    @Test
    public void testItemEncode() {
        MessageSection testSection = new MessageSection().setItem(0, ItemPrefix.SHODDY, 5000);
        Assert.assertEquals(testSection.getMessage(), "[i/p" + ItemPrefix.SHODDY.ordinal() + "/s5000:0]");
    }
}
