package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class FirstSteps extends OpMode {
    private DcMotorEx gerald;
    private DcMotor Jimothy;

    @Override
    public void init() {
        gerald = hardwareMap.get(DcMotorEx.class, "gerald");
        gerald.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gerald.setDirection(DcMotorSimple.Direction.REVERSE);
        Jimothy = hardwareMap.get(DcMotor.class, "christian");
        Jimothy.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {gerald.setVelocity(1600);}
        else {gerald.setVelocity(0);}
        if (gamepad1.b) {
            Jimothy.setPower(0.5);
        }
        else {Jimothy.setPower(0);}
        ;

    }
}

