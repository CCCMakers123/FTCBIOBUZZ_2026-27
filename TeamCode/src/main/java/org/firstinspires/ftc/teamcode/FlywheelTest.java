package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


@TeleOp
public class FlywheelTest extends OpMode
{

   public DcMotorEx FlywheelMotor;
   public DcMotor HexMotor;
   public double HighVelocity = 1700;
   public double LowVelocity = 1600;
   double curTargetVelocity = HighVelocity;
   double F = 0;
   double P = 0;
   double D = 0;
   double[] stepSizes = {1000,100, 10.0, 1.0, 0.1, 0.001, 0.0001};
   int stepIndex = 1;
    @Override
    public void init()
    {
        HexMotor = hardwareMap.get(DcMotor.class, "curry");
        HexMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HexMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        FlywheelMotor = hardwareMap.get(DcMotorEx.class, "kevind");
        FlywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FlywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, D, F);
        FlywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("init complete");

    }

    @Override
    public void loop()
    {
        HexMotor.setPower(0.4);
        // get all gamepad commands
        // set target velocity
        // update telemetry
        //drive.drive(gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);
        if(gamepad1.yWasPressed())
        {
            if(curTargetVelocity == HighVelocity)
            {
                curTargetVelocity = LowVelocity;

            }
            else
            {
                curTargetVelocity = HighVelocity;
            }
        }
        if (gamepad1.bWasPressed())
        {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

       if (gamepad1.dpadLeftWasPressed())
       {
           F -= stepSizes[stepIndex];
       }
       if (gamepad1.dpadRightWasPressed())
       {
           F += stepSizes[stepIndex];
       }
       if (gamepad1.dpadDownWasPressed())
       {
           P -= stepSizes[stepIndex];
       }
       if (gamepad1.dpadUpWasPressed())
       {
           P += stepSizes[stepIndex];
       }
       if (gamepad1.rightBumperWasPressed())
       {
           D += stepSizes[stepIndex];
       }
       if(gamepad1.leftBumperWasPressed())
       {
           D -= stepSizes[stepIndex];
       }

       // set new PIDF coefficents
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0, D, F);
       FlywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

       // set velocity
        FlywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = FlywheelMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("Target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addLine("------------------------------------");
        telemetry.addData("Tuning P", "%.4f (D-Pad U/D", P);
        telemetry.addData("Tuning F", "%.4f (D-Pad L/R)", F);
        telemetry.addData("Tuning D", "%.4f (Right/Left Bumper)", D);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);

    }
}
