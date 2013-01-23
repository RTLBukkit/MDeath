package ca.q0r.mdeath.types;

import ca.q0r.mdeath.configs.LocaleUtil;
import com.miraclem4n.mchat.util.MessageUtil;

public enum LocaleType {
    MESSAGE_DEATH_IN_FIRE("message.death.inFire"),
    MESSAGE_DEATH_ON_FIRE("message.death.onFire"),
    MESSAGE_DEATH_LAVA("message.death.lava"),
    MESSAGE_DEATH_IN_WALL("message.death.inWall"),
    MESSAGE_DEATH_DROWN("message.death.drown"),
    MESSAGE_DEATH_STARVE("message.death.starve"),
    MESSAGE_DEATH_CACTUS("message.death.cactus"),
    MESSAGE_DEATH_FALL("message.death.fall"),
    MESSAGE_DEATH_OUT_OF_WORLD("message.death.outOfWorld"),
    MESSAGE_DEATH_GENERIC("message.death.generic"),
    MESSAGE_DEATH_EXPLOSION("message.death.explosion"),
    MESSAGE_DEATH_MAGIC("message.death.magic"),
    MESSAGE_DEATH_ENTITY("message.death.entity"),
    MESSAGE_DEATH_ARROW("message.death.arrow"),
    MESSAGE_DEATH_FIREBALL("message.death.fireball"),
    MESSAGE_DEATH_THROWN("message.death.thrown");

    private final String option;

    LocaleType(String option) {
        this.option = option;
    }

    public String getVal() {
        if (LocaleUtil.getConfig().isSet(option))
            return MessageUtil.addColour(LocaleUtil.getConfig().getString(option));

        return "Locale Option '" + option + "' not found!";
    }

    public String getRaw() {
        if (LocaleUtil.getConfig().isSet(option))
            return LocaleUtil.getConfig().getString(option);

        return "Locale Option '" + option + "' not found!";
    }
}
