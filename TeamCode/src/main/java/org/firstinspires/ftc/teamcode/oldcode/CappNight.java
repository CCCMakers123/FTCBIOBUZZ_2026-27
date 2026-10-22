package org.firstinspires.ftc.teamcode.oldcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class CappNight extends OpMode {
    double SP = 1500;
    CappNightMechanumDrive drive = new CappNightMechanumDrive();
    @Override
    public void init() {drive.init(hardwareMap);}
    @Override
    public void loop() {
        drive.drive(-gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
        double actualVelocity = drive.kevind.getVelocity();
        telemetry.addData("Current Velocity", "%.2f", actualVelocity);
        if (gamepad1.dpadDownWasPressed()) {SP -= 100;}
        if (gamepad1.dpadUpWasPressed()) {SP += 100;}
        if (gamepad1.left_bumper) {drive.setHexPower(0.8);} else {drive.setHexPower(0);}
        if (gamepad1.right_bumper) {drive.kevind.setVelocity(SP);} else {drive.setShooterPower(0);}
    }}