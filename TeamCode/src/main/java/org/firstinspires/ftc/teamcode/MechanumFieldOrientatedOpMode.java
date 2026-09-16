package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MechanumDrive;
@TeleOp
public class MechanumFieldOrientatedOpMode extends OpMode {
    MechanumDrive drive = new MechanumDrive();
    double SP = 1500;
    @Override
    public void init() {drive.init(hardwareMap);}
    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);
        double actualVelocity = drive.kevind.getVelocity();
        telemetry.addData("Current Velocity", "%.2f", actualVelocity);
        if (gamepad2.dpad_down) {SP=1400;}
        if (gamepad2.dpad_left) {SP=1500;}
        if (gamepad2.dpad_up) {SP=1600;}
        if (gamepad2.dpad_right) {SP=1700;}
        if (gamepad2.left_bumper) {drive.setHexPower(1.0);} else {drive.setHexPower(0);}
        if (gamepad2.right_bumper) {drive.kevind.setVelocity(SP);} else {drive.setShooterPower(0);}
    }}