package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "UltraPlanetary Slow Velocity Test", group = "Test")
public class UltraPlanetaryHDHexTest extends LinearOpMode {

    private DcMotorEx hdMotor;

    @Override
    public void runOpMode() {
        // Get the motor from hardwareMap – match the config name
        hardwareMap.logDevices();
        hdMotor = hardwareMap.get(DcMotorEx.class, "motor1");

        // Set motor direction if needed
        hdMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Use encoder feedback for precise control
        hdMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Choose zero power behavior
        hdMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // Example: run at slow velocity while opMode is active
        // Define a slow target velocity in ticks per second
        // We need to convert a desired RPM into ticks/sec:
        // ticksPerMotorRev = 28  (for bare motor) * (gearbox ratio, if any)
        // desiredRPM = e.g. 100 RPM  → rotations per second = 100/60 = ~1.667
        // so ticksPerSecond = 1.667 * ticksPerMotorRev

        double desiredRPM = 100.0;  // slow speed, change as desired
        double rotationsPerSecond = desiredRPM / 60.0;
        double ticksPerMotorRev = 28.0;  // bare motor
        double gearboxRatio = 1.0;       // if using gearbox, multiply this
        // if you have, say, a 20:1 reduction, set gearboxRatio = 20.0

        double ticksPerSecond = rotationsPerSecond * ticksPerMotorRev * gearboxRatio;

        // Main loop
        while (opModeIsActive()) {
            // Set velocity
            hdMotor.setVelocity(ticksPerSecond);

            // Telemetry so you can see what's happening
            telemetry.addData("Target RPM", "%.1f", desiredRPM);
            telemetry.addData("Target ticks/sec", "%.1f", ticksPerSecond);
            telemetry.addData("Actual Position (ticks)", hdMotor.getCurrentPosition());
            telemetry.addData("Actual Velocity (ticks/sec)", hdMotor.getVelocity());
            telemetry.update();

            sleep(50); // small delay so telemetry is readable
        }

        // At end, stop motor
        hdMotor.setVelocity(0);
    }
}
