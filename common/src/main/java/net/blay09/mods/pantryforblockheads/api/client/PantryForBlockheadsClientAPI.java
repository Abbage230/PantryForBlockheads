package net.blay09.mods.pantryforblockheads.api.client;

import java.lang.reflect.InvocationTargetException;

public class PantryForBlockheadsClientAPI {

    private static final InternalClientMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalClientMethods) Class.forName("net.blay09.mods.pantryforblockheads.client.InternalClientMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
