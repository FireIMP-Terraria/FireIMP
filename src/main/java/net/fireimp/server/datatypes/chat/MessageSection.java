package net.fireimp.server.datatypes.chat;

import net.fireimp.server.datatypes.enums.Achievement;
import net.fireimp.server.datatypes.enums.ItemPrefix;

import java.awt.*;

public class MessageSection {
    private String message;
    private boolean hasTag = false;
    public MessageSection(String message) {
        this.message = message;
    }

    public MessageSection() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    /**
     * Set the achievement of this MessageSection.
     * @param achievement
     */
    public MessageSection setAchievement(Achievement achievement) {
        setTagged();
        if(!message.isEmpty()) {
            throw new IllegalArgumentException("Can only tag empty MessageSections with achievements!");
        } else {
            message = "[a:" + achievement.name() + "]";
        }
        return this;
    }

    /**
     * Set the color of this MessageSection
     * @param color
     * @return section
     */
    public MessageSection setColor(Color color) {
        setTagged();
        String hex = "#" + Integer.toHexString(color.getRGB()).substring(2);
        message = "[c/" + hex + ":" + message + "]";
        return this;
    }

    /**
     * Tag this MessageSection with an item
     * @param name The item's name
     * @param prefix The prefix/modifier of the item
     * @param stackCount The stack count of this item(0 to not display)
     * @return section
     */
    public MessageSection setItem(String name, ItemPrefix prefix, int stackCount) {
        setTagged();
        if(!message.isEmpty()) {
            throw new IllegalArgumentException("Can only tag empty MessageSections with items!");
        } else {
            message = "[i";
            if(prefix != ItemPrefix.NONE) {
                message += "/p" + prefix.ordinal();
            }
            if(stackCount != 0) {
                message += "/s" + stackCount;
            }
            message += ":" + name;
            message += "]";
        }
        return this;
    }


    /**
     * Tag this MessageSection with an item
     * @param id The item's id
     * @param prefix The prefix/modifier of the item
     * @param stackCount The stack count of this item(0 to not display)
     * @return section
     */
    public MessageSection setItem(int id, ItemPrefix prefix, int stackCount) {
        return setItem("" + id, prefix, stackCount);
    }

    /**
     * Tag this MessageSection with an item
     * @param name The item's id
     * @param prefix The prefix/modifier of the item
     * @return section
     */
    public MessageSection setItem(String name, ItemPrefix prefix) {
        return setItem(name, prefix, 0);
    }

    /**
     * Tag this MessageSection with an item
     * @param id The item's id
     * @param prefix The prefix/modifier of the item
     * @return section
     */
    public MessageSection setItem(int id, ItemPrefix prefix) {
        return setItem(id, prefix, 0);
    }

    /**
     * Tag this MessageSection with an item
     * @param name The item's id
     * @return section
     */
    public MessageSection setItem(String name) {
        return setItem(name, ItemPrefix.NONE, 0);
    }

    /**
     * Tag this MessageSection with an item
     * @param id The item's id
     * @return section
     */
    public MessageSection setItem(int id) {
        return setItem(id, ItemPrefix.NONE, 0);
    }

    private void setTagged() {
        if(hasTag) {
            throw new IllegalArgumentException("Cannot add multiple tags to a single MessageSection!");
        }
        hasTag = true;
    }

    public MessageSection append(MessageSection message) {
        return new MessageSection(this.message + message.message);
    }
}
