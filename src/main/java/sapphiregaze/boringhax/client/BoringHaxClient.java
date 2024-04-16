package sapphiregaze.boringhax.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sapphiregaze.boringhax.hacks.BoatFlyHack;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class BoringHaxClient implements ClientModInitializer {
    public static final String MOD_ID = "boring_hax";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        BoatFlyHack boatFly = new BoatFlyHack();
        ClientTickEvents.START_CLIENT_TICK.register(boatFly::tick);
    }
}
