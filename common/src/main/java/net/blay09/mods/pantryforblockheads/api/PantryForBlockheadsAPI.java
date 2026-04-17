package net.blay09.mods.pantryforblockheads.api;

import java.lang.reflect.InvocationTargetException;

public class PantryForBlockheadsAPI {

    public static final String MOD_ID = "pantryforblockheads";

    private static final InternalMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalMethods) Class.forName("net.blay09.mods.pantryforblockheads.InternalMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
