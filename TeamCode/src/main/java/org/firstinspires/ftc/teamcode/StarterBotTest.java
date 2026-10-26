package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class StarterBotTest extends OpMode {
    double SP = 1400;
    StarterBotDrive drive = new StarterBotDrive();

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x);
        double actualVelocity = drive.launcher.getVelocity();
        telemetry.addData("Current Velocity", "%.2f", actualVelocity);
        if (gamepad1.dpadDownWasPressed()) {
            SP -= 100;
        }
        if (gamepad1.dpadUpWasPressed()) {
            SP += 100;
        }
        if (gamepad1.left_bumper) {
            drive.launcher.setVelocity(SP);
        } else {
            drive.launcher.setVelocity(0.0);
        }
        if (gamepad1.x) {
            drive.intake.setPower(-1.0);
        } else {
            drive.intake.setPower(0.0);
        }
        if (gamepad1.right_bumper) {
            drive.grip.setPower(1.0);
        } else {
            drive.grip.setPower(0.0);
        }
        if (gamepad1.b) {
            drive.intake.setPower(1.0);
        } else {
            drive.intake.setPower(0.0);
        }

    }
}

