package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;

import org.firstinspires.ftc.teamcode.oldcode.MechanumDrive;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp

public class MechanumTest extends OpMode
{
    private Follower follower;

    MechanumDriveTest drive = new MechanumDriveTest();
    @Override
    public void init()
    {
        follower = Constants.create(hardwareMap);
    }
    @Override
    public void loop()
    {
        follower.manual(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x);
        follower.update();
    }
}
