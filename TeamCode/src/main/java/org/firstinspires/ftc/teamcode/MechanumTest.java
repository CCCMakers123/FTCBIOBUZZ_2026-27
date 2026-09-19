package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.oldcode.MechanumDrive;
@TeleOp
public class MechanumTest extends OpMode
{
    MechanumDriveTest drive = new MechanumDriveTest();
    @Override
    public void init() {drive.init(hardwareMap);}
    @Override
    public void loop()
    {
        drive.drive(-gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
    }
}
