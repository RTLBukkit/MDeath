package com.miraclem4n.msocial.types;

import com.miraclem4n.mchat.util.MessageUtil;
import com.miraclem4n.msocial.configs.ConfigUtil;

import java.util.ArrayList;

public enum ConfigType {
    OPTION_SPOUT("option.spoutPM"),

    ALIASES_SAY("aliases.mchatsay"),
    ALIASES_PM("aliases.pmchat"),
    ALIASES_PM_REPLY("aliases.pmchatreply"),
    ALIASES_PM_INVITE("aliases.pmchatinvite"),
    ALIASES_PM_ACCEPT("aliases.pmchataccept"),
    ALIASES_PM_DENY("aliases.pmchatdeny"),
    ALIASES_PM_LEAVE("aliases.pmchatleave"),
    ALIASES_SHOUT("aliases.mchatshout"),
    ALIASES_MUTE("aliases.mchatmute");

    private final String option;
    private final Object object;

    ConfigType(String option) {
        this.option = option;
        this.object = getObject();
    }

    private Object getObject() {
        Object value = ConfigUtil.getConfig().get(option);

        if (value instanceof String) {
            String val = (String) value;

            value = MessageUtil.addColour(val);
        }

        return value;
    }

    public Boolean getBoolean() {
        return object instanceof Boolean ? (Boolean) object : false;
    }

    public String getString() {
        return object != null ? object.toString() : null;
    }

    public Integer getInteger() {
        return object instanceof Number ? (Integer) object : 0;
    }

    public Double getDouble() {
        return object instanceof Number ? (Double) object : 0;
    }

    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();

        if (object instanceof ArrayList) {
            ArrayList aList = (ArrayList) object;

            for (Object obj : aList)
                list.add((String) obj);

        }

        return list;
    }
}
