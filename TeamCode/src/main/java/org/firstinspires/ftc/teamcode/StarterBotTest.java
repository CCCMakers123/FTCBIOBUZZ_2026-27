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

    double invert = 1;
    //boolean rotating = false;
    //double timer = 0;
    @Override
    public void loop() {
        if (gamepad1.dpadLeftWasPressed() /* && !rotating */) {
            invert *= -1;
            //rotating = true;
            // time to rotate
            //timer = 1;
        }
        double rx = -gamepad1.right_stick_x;
        //if (rotating) {
        //    rx = 1;
        //    timer -= 0.01;
        //}
        //if (timer < 0) {
        //    rotating = false;
        //}
        drive.drive(-gamepad1.left_stick_y*invert, gamepad1.left_stick_x*invert, rx);
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
            drive.intake.setPower(1.0);
        } else {
            drive.intake.setPower(0.0);
        }
        if (gamepad1.right_bumper) {
            drive.grip.setPower(1.0);
        } else {
            drive.grip.setPower(0.0);
        }
        if (gamepad1.b) {
            drive.intake.setPower(-1.0);
        } else {
            drive.intake.setPower(0.0);
        }

    }
}

