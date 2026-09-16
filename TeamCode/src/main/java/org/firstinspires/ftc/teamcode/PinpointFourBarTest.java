package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Pinpoint 4-Bar Test", group = "Marauder")
public class PinpointFourBarTest extends OpMode {
    GoBildaPinpointDriver pinpoint;
    MechanumDrive drive = new MechanumDrive();
    boolean driveOk = true;

    static final double TEST_DISTANCE_IN = 24;
    static final double Y_POD_OFFSET_MM = 0;
    static final GoBildaPinpointDriver.EncoderDirection Y_DIRECTION = GoBildaPinpointDriver.EncoderDirection.FORWARD;

    @Override
    public void init() {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(0, Y_POD_OFFSET_MM, DistanceUnit.MM);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, Y_DIRECTION);
        pinpoint.resetPosAndIMU();

        try {
            drive.init(hardwareMap);
        } catch (Exception e) {
            driveOk = false;
        }
    }

    @Override
    public void loop() {
        pinpoint.update();

        if (driveOk) {
            drive.drive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        }
        if (gamepad1.aWasPressed()) {
            pinpoint.resetPosAndIMU();
        }

        double yIn = pinpoint.getPosY(DistanceUnit.INCH);
        double errorPct = 100 * (Math.abs(yIn) - TEST_DISTANCE_IN) / TEST_DISTANCE_IN;

        telemetry.addData("Pinpoint", pinpoint.getDeviceStatus());
        telemetry.addData("Update rate", "%.0f Hz", pinpoint.getFrequency());
        telemetry.addData("Drive", driveOk ? "gamepad1" : "off, push by hand");
        telemetry.addLine("A = zero, hold still");
        telemetry.addData("Y ticks", pinpoint.getEncoderY());
        telemetry.addData("Y inches", "%.2f", yIn);
        telemetry.addData("Y speed", "%.1f in/s", pinpoint.getVelY(DistanceUnit.INCH));
        telemetry.addData("Off by", "%.1f%% of " + (int) TEST_DISTANCE_IN + " in", errorPct);
        telemetry.addData("Heading", "%.1f deg", pinpoint.getHeading(AngleUnit.DEGREES));
        telemetry.addData("X ticks", pinpoint.getEncoderX());
    }

    @Override
    public void stop() {
        if (driveOk) {drive.drive(0, 0, 0);}
    }
}
