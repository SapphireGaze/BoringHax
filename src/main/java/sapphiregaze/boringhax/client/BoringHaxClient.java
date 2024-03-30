package sapphiregaze.boringhax.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import sapphiregaze.boringhax.hacks.BoatFlyHack;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class BoringHaxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BoatFlyHack boatFly = new BoatFlyHack();
        ClientTickEvents.START_CLIENT_TICK.register(boatFly::tick);
    }
}
