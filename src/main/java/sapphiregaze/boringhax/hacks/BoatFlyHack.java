package sapphiregaze.boringhax.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class BoatFlyHack {
    public void tick(MinecraftClient client) {
        // return if there is no player or if the player does not have a vehicle
        if (client.player == null || !client.player.hasVehicle()) { return; }

        final double UPWARD_VELOCITY = 0.75;

        // get vehicle and assert that vehicle is not null
        Entity vehicle = client.player.getVehicle();
        assert vehicle != null;

        // get velocity from vehicle and set y-axis velocity in respect with player jump key press
        Vec3d velocity =  vehicle.getVelocity();
        double velocityY = client.options.keyJump.isPressed() ? UPWARD_VELOCITY : 0;

        // set vehicle velocity
        vehicle.setVelocity(new Vec3d(velocity.x, velocityY, velocity.z));
    }
}
