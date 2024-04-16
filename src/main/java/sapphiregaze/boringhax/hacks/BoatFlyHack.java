package sapphiregaze.boringhax.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class BoatFlyHack {
    int toggleTick = 0;
    double acceleration = 0.1;
    final int MAX_SPEED = 3;
    final double DOWNWARD_VELOCITY = -0.04;

    public void tick(MinecraftClient client) {
        // return if there is no player or if the player does not have a vehicle
        if (client.player == null || !client.player.hasVehicle()) return;
        
        // check player key presses
        boolean jump = client.options.keyJump.isPressed();
        boolean forward = client.options.keyForward.isPressed();
        boolean left = client.options.keyLeft.isPressed();
        boolean right = client.options.keyRight.isPressed();
        boolean back = client.options.keyBack.isPressed();

        // get vehicle and assert that vehicle is not null
        Entity vehicle = client.player.getVehicle();
        assert vehicle != null;

        // get velocity from vehicle and set y-axis velocity in respect with player jump key press
        Vec3d velocity = vehicle.getVelocity();
        Vec3d newVelocity = new Vec3d(velocity.x, -DOWNWARD_VELOCITY, velocity.z);

        if (jump) {
            if (forward) {
                newVelocity = client.player.getRotationVector().multiply(acceleration);
            }
            if (left) {
                newVelocity = client.player.getRotationVector()
                        .multiply(acceleration)
                        .rotateY((float) Math.PI / 2);
                newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
            }
            if (right) {
                newVelocity = client.player.getRotationVector()
                        .multiply(acceleration)
                        .rotateY(-(float) Math.PI / 2);
                newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
            }
            if (back) {
                newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
            }

            newVelocity = new Vec3d(newVelocity.x,
                    ((toggleTick == 0) && (newVelocity.y > DOWNWARD_VELOCITY))
                            ? DOWNWARD_VELOCITY : newVelocity.y,
                    newVelocity.z);

            vehicle.setVelocity(newVelocity);

            if (forward || left || right || back) {
                if (acceleration < MAX_SPEED) {
                    acceleration += 0.1;
                }
            } else if (acceleration > 0.2) {
                acceleration -= 0.2;
            }
        }

        if (toggleTick == 0 || newVelocity.y <= -0.04) {
            toggleTick = 40;
        }
        toggleTick--;
    }
}
