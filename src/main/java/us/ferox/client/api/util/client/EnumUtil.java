package us.ferox.client.api.util.client;

import us.ferox.client.api.setting.Setting;

public class EnumUtil {
    public static String getNextEnumValue(Setting<Enum> op, boolean reverse) {
        final Enum currentValue = op.getValue();

        int i = 0;

        for (; i < op.getValue().getClass().getEnumConstants().length; i++) {
            final Enum e = op.getValue().getClass().getEnumConstants()[i];

            if (e.name().equalsIgnoreCase(currentValue.name())) break;
        }

        return op.getValue().getClass().getEnumConstants()[(reverse ? (i != 0 ? i - 1 : op.getValue().getClass().getEnumConstants().length - 1) : i + 1) % op.getValue().getClass().getEnumConstants().length].toString();
    }

    public static void setEnumValue(Setting<Enum> op, String value) {
        for (Enum e : op.getValue().getClass().getEnumConstants()) {
            if (e.name().equalsIgnoreCase(value)) {
                op.setValue(e);
                break;
            }
        }
    }
}